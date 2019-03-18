package com.bdtd.card.web.stock.controller;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bdtd.card.common.model.OriginMask;
import com.bdtd.card.common.util.MapUtil;
import com.bdtd.card.common.web.base.BaseController;
import com.bdtd.card.data.stock.base.StockType;
import com.bdtd.card.data.stock.dao.StockMainMapper;
import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.query.CurrentIncreaseQuery;
import com.bdtd.card.data.stock.service.ICurrentIncreaseService;
import com.bdtd.card.data.stock.util.DateUtil;
import com.bdtd.card.web.admin.log.LogObjectHolder;
import com.bdtd.card.web.stock.model.MsaSortField;

/**
 * 最近最大涨幅分析控制器
 *
 * @author 
 * @Date 2019-03-07 23:50:27
 */
@Controller
@RequestMapping("/currentIncrease")
public class CurrentIncreaseController extends BaseController {

	private Logger log = LoggerFactory.getLogger(getClass());
    private String PREFIX = "/msa/currentIncrease/";

    @Autowired
    private ICurrentIncreaseService currentIncreaseService;
	@Autowired
	private StockMainMapper stockMainMapper;
    
    @RequestMapping("/test")
    @ResponseBody
    public Map<String, Object> test() {
    	CurrentIncreaseQuery query = new CurrentIncreaseQuery();
    	query.setAsc(false);
    	query.setSortField("increase");
    	query.setOffset(0);
    	query.setLimit(1);
    	query.setBegin(Date.valueOf(LocalDate.of(2018, Month.DECEMBER, 1)));
    	LocalDate end = LocalDate.of(2019, Month.FEBRUARY, 28);
    	int dayDiff = (int) (LocalDate.of(2019, Month.MARCH, 14).getLong(ChronoField.EPOCH_DAY) - end.getLong(ChronoField.EPOCH_DAY));
		List<StockMain> stockMainList = this.stockMainMapper.findByQuery(query);
    	for (int i = 1; i <= dayDiff; i++) {
    		query.setEnd(Date.valueOf(end.plus(i, ChronoUnit.DAYS)));
    		if (query.getEnd().toLocalDate().getDayOfWeek() == DayOfWeek.SATURDAY || query.getEnd().toLocalDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
    			continue;
    		}

    		this.currentIncreaseService.initAnalysis(query, stockMainList);
    	}
    	return MapUtil.createSuccessMap();
    }
    
    public static void main(String[] args) {
    	long begin = LocalDate.of(2018, Month.MARCH, 1).getLong(ChronoField.EPOCH_DAY);
    	long end = LocalDate.of(2019, Month.FEBRUARY, 28).getLong(ChronoField.EPOCH_DAY);
    	long dayDiff = end - begin;
    	System.out.println(dayDiff);
	}

    /**
     * 跳转到最近最大涨幅分析首页
     */
    @RequestMapping("")
    public String index(Model model) {
    	model.addAttribute("begin", new Date(DateUtil.getDate(2019, 1, 11).getTime()));
    	LocalDate now = LocalDate.now();
    	while (now.getDayOfWeek() == DayOfWeek.SATURDAY || now.getDayOfWeek() == DayOfWeek.SUNDAY) {
    		now = now.plus(-1, ChronoUnit.DAYS);
    	}
    	model.addAttribute("end", new Date(com.bdtd.card.common.util.DateUtil.localDate2Long(now)));
    	model.addAttribute("fieldItemList", MsaSortField.select());
    	model.addAttribute("ascItemList", OriginMask.select());
    	model.addAttribute("stockTypeItemList", StockType.select());
    	
        return PREFIX + "currentIncrease.html";
    }

    /**
     * 跳转到添加最近最大涨幅分析
     */
    @RequestMapping("/currentIncrease_add")
    public String currentIncreaseAdd() {
        return PREFIX + "currentIncrease_add.html";
    }

    /**
     * 跳转到修改最近最大涨幅分析
     */
    @RequestMapping("/currentIncrease_update/{currentIncreaseId}")
    public String currentIncreaseUpdate(@PathVariable Integer currentIncreaseId, Model model) {
        CurrentIncrease currentIncrease = currentIncreaseService.getById(currentIncreaseId);
        model.addAttribute("item",currentIncrease);
        return PREFIX + "currentIncrease_edit.html";
    }

    /**
     * 获取最近最大涨幅分析列表
     */
//    @EnumEntityList(entityList={@EnumEntity(enumName="StockType", fieldName="stockType")})
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(CurrentIncreaseQuery query) {
    	if (query.getBegin() == null || query.getEnd() == null) {
    		return MapUtil.createSuccessMap("rows", Collections.emptyList(), "total", 0L);
    	}
    	IPage<CurrentIncrease> page = this.currentIncreaseService.findByQuery(query);
		return MapUtil.createSuccessMap("rows", page.getRecords(), "total", page.getTotal());
    }

    /**
     * 新增最近最大涨幅分析
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CurrentIncrease currentIncrease) {
        currentIncreaseService.save(currentIncrease);
        return SUCCESS_TIP;
    }

    /**
     * 删除最近最大涨幅分析
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer currentIncreaseId) {
        currentIncreaseService.removeById(currentIncreaseId);
        return SUCCESS_TIP;
    }

    /**
     * 修改最近最大涨幅分析
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CurrentIncrease currentIncrease) {
        currentIncreaseService.updateById(currentIncrease);
        LogObjectHolder.me().set(currentIncrease);
        return SUCCESS_TIP;
    }

    /**
     * 最近最大涨幅分析详情
     */
    @RequestMapping(value = "/detail/{currentIncreaseId}")
    @ResponseBody
    public Object detail(@PathVariable("currentIncreaseId") Integer currentIncreaseId) {
        return currentIncreaseService.getById(currentIncreaseId);
    }
}
