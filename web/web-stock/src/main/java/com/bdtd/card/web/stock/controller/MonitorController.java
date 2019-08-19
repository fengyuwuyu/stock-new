package com.bdtd.card.web.stock.controller;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bdtd.card.base.consts.StockConsts;
import com.bdtd.card.base.model.MonitorStatus;
import com.bdtd.card.base.model.MonitorType;
import com.bdtd.card.common.util.MapUtil;
import com.bdtd.card.common.web.annotation.EnumEntity;
import com.bdtd.card.common.web.annotation.EnumEntityList;
import com.bdtd.card.common.web.base.BaseController;
import com.bdtd.card.common.web.base.Tip;
import com.bdtd.card.data.stock.dao.MonitorMapper;
import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.model.Monitor;
import com.bdtd.card.data.stock.model.query.CurrentIncreaseQuery;
import com.bdtd.card.data.stock.service.ICurrentIncreaseService;
import com.bdtd.card.data.stock.service.IMonitorService;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.data.stock.util.StockUtils;
import com.bdtd.card.data.stock.util.model.CurrentStockData;
import com.bdtd.card.web.admin.log.LogObjectHolder;

/**
 *
 * @author
 * @Date 2019-01-29 22:29:28
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

	private String PREFIX = "/stock/monitor/";

	@Autowired
	private IMonitorService monitorService;
	@Autowired
	private ICurrentIncreaseService currentIncreaseService;
	@Autowired
	private MonitorMapper monitorMapper;

	/**
	 */
	@RequestMapping("")
	public String index(Model model) {
		LocalDate now = LocalDate.now();
    	while (now.getDayOfWeek() == DayOfWeek.SATURDAY || now.getDayOfWeek() == DayOfWeek.SUNDAY) {
    		now = now.plus(-1, ChronoUnit.DAYS);
    	}
    	model.addAttribute("begin", now);
		return PREFIX + "monitor.html";
	}

	/**
	 */
	@EnumEntityList(entityList = { @EnumEntity(fieldName = "monitorType", enumName = "MonitorType"),
			@EnumEntity(fieldName = "status", enumName = "MonitorStatus") })
	@RequestMapping("/monitor_add")
	public String monitorAdd(Model model) {
		return PREFIX + "monitor_add.html";
	}

	/**
	 */
	@EnumEntityList(entityList = { @EnumEntity(fieldName = "monitorType", enumName = "MonitorType"),
			@EnumEntity(fieldName = "status", enumName = "MonitorStatus") })
	@RequestMapping("/monitor_update/{monitorId}")
	public String monitorUpdate(@PathVariable Integer monitorId, Model model) {
		Monitor monitor = monitorService.getById(monitorId);
		model.addAttribute("item", monitor);
		return PREFIX + "monitor_edit.html";
	}

	/**
	 */
//	@EnumEntityList(entityList = { @EnumEntity(enumName = "MonitorStatus", fieldName = "status"),
//			@EnumEntity(enumName = "MonitorType", fieldName = "monitorType") })
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(CurrentIncreaseQuery query, Integer offset, Integer limit) {
//		wrapper.orderByDesc(Consts.DEFAULT_SORT_FIELD);
//		IPage<Map<String, Object>> page = monitorService.pageMaps(new Page<>(offset, limit), wrapper);
		
		List<Monitor> list = this.monitorMapper.findAll1(query);
		if (list.size() > 0) {
			List<String> symbols = list.stream().map(Monitor::getSymbol).collect(Collectors.toList());
			List<Integer> types = list.stream().map(Monitor::getType).collect(Collectors.toList());
			Map<String, CurrentStockData> map = StockUtils.getCurrentStockData(StockConsts.STOCK_CURR_DATA_URL, symbols, types);
			list.forEach((item) -> {
				CurrentStockData data = map.get(item.getSymbol());
				if (data != null) {
					item.setIncrease(data.getCurrIncrease());
					item.setVolumeCompare(CommonsUtil.formatNumberToFloat((data.getTotalBuyVolume().floatValue() - data.getTotalSellVolume().floatValue()) * 100 / (data.getTotalBuyVolume().floatValue() + data.getTotalSellVolume().floatValue())));
				}
			});
		}
		
		return MapUtil.createSuccessMap("rows", list, "total", list.size());
	}

	/**
     */
    @RequestMapping(value = "/addMonitor")
    @ResponseBody
    public Object addMonitor(Monitor monitor) {
    	LocalDateTime createDate = LocalDateTime.now();
    	monitor.setCreateDate(createDate);
    	monitor.setUpdateDate(createDate);
    	QueryWrapper<Monitor> queryWrapper = new QueryWrapper<>();
    	queryWrapper.eq("symbol", monitor.getSymbol());
		if (this.monitorService.count(queryWrapper ) > 0) {
    		return new Tip(500, "已存在该股票的监控！");
    	}
    	
		QueryWrapper<CurrentIncrease> wrapper = new QueryWrapper<>();
		wrapper.eq("symbol", monitor.getSymbol()).eq("msa_day", monitor.getBeginDate());
		CurrentIncrease currentIncrease =currentIncreaseService.getOne(wrapper );
		if (currentIncrease == null) {
			return new Tip(500, "请先生成当日股票分析数据！");
		}
		monitor.setBeginDate(LocalDate.now());
		monitor.setBuyPrice(new BigDecimal(currentIncrease.getClose() == null ? 0F : currentIncrease.getClose()));
		monitor.setEndDate(monitor.getBeginDate().plus(5, ChronoUnit.DAYS));
		monitor.setMonitorType(MonitorType.BUY.getType());
		monitor.setStatus(MonitorStatus.CREATED.getType());
        monitorService.save(monitor);
        return SUCCESS_TIP;
    }

	/**
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Monitor monitor) {
		LocalDateTime createDate = LocalDateTime.now();
		monitor.setCreateDate(createDate);
		monitor.setUpdateDate(createDate);
		QueryWrapper<Monitor> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("symbol", monitor.getSymbol());
		if (this.monitorService.count(queryWrapper) > 0) {
			return new Tip(500, "已存在该股票的监控！");
		}

		monitorService.save(monitor);
		return SUCCESS_TIP;
	}

	/**
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer monitorId) {
		monitorService.removeById(monitorId);
		return SUCCESS_TIP;
	}

	/**
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Monitor monitor) {
		LocalDateTime createDate = LocalDateTime.now();
		monitor.setUpdateDate(createDate);
		monitorService.updateById(monitor);
		LogObjectHolder.me().set(monitor);
		return SUCCESS_TIP;
	}

	/**
	 * 股票监控详情
	 */
	@RequestMapping(value = "/detail/{monitorId}")
	@ResponseBody
	public Object detail(@PathVariable("monitorId") Integer monitorId) {
		return monitorService.getById(monitorId);
	}
}
