package com.bdtd.card.web.stock.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdtd.card.base.model.MonitorStatus;
import com.bdtd.card.base.model.MonitorType;
import com.bdtd.card.common.consts.Consts;
import com.bdtd.card.common.util.MapUtil;
import com.bdtd.card.common.web.annotation.EnumEntity;
import com.bdtd.card.common.web.annotation.EnumEntityList;
import com.bdtd.card.common.web.base.BaseController;
import com.bdtd.card.common.web.base.Tip;
import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.model.Monitor;
import com.bdtd.card.data.stock.service.ICurrentIncreaseService;
import com.bdtd.card.data.stock.service.IMonitorService;
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

	/**
	 */
	@RequestMapping("")
	public String index() {
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
	@EnumEntityList(entityList = { @EnumEntity(enumName = "MonitorStatus", fieldName = "status"),
			@EnumEntity(enumName = "MonitorType", fieldName = "monitorType") })
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String condition, Integer offset, Integer limit) {
		QueryWrapper<Monitor> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc(Consts.DEFAULT_SORT_FIELD);
		IPage<Map<String, Object>> page = monitorService.pageMaps(new Page<>(offset, limit), wrapper);
		return MapUtil.createSuccessMap("rows", page.getRecords(), "total", page.getTotal());
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
