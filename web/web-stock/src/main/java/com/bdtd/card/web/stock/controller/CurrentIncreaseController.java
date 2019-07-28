package com.bdtd.card.web.stock.controller;

import java.io.File;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.base.card.common.model.OriginMask;
import com.base.card.common.util.MapUtil;
import com.base.card.common.web.base.BaseController;
import com.base.card.web.admin.log.LogObjectHolder;
import com.bdtd.card.data.stock.base.StockType;
import com.bdtd.card.data.stock.dao.StockMainMapper;
import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.model.StockMain;
import com.bdtd.card.data.stock.model.query.CurrentIncreaseQuery;
import com.bdtd.card.data.stock.service.ICurrentIncreaseService;
import com.bdtd.card.data.stock.util.DateUtil;
import com.bdtd.card.web.stock.model.MsaSortField;
import com.bdtd.card.web.stock.util.StockUtil;

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
	@Value("${bdtd.file-upload-path}")
	private String tmpDir;
    
    @RequestMapping("/test")
    @ResponseBody
    public Map<String, Object> test() {
    	CurrentIncreaseQuery query = new CurrentIncreaseQuery();
    	query.setAsc(false);
    	query.setSortField("increase");
    	query.setOffset(0);
    	query.setLimit(1);
    	query.setBegin(Date.valueOf(LocalDate.of(2018, Month.OCTOBER, 1)));
    	LocalDate last = LocalDate.now();
    	LocalDate end = currentIncreaseService.findMaxMsaDay();
    	long dayDiff = last.getDayOfYear() - end.getDayOfYear();
    	QueryWrapper<CurrentIncrease> queryWrapper = new QueryWrapper<>();
    	queryWrapper.gt("msa_day", end);
		this.currentIncreaseService.remove(queryWrapper);
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
    	LocalDate last = LocalDate.now();
    	long dayDiff = 7;
    	LocalDate end = last.minusDays(dayDiff);
    	System.out.println(last.getDayOfYear() - end.getDayOfYear());
    	System.out.println(last);
	}

    /**
     */
    @RequestMapping("")
    public String index(Model model) {
    	model.addAttribute("begin", new Date(DateUtil.getDate(2018, 12, 3).getTime()));
    	LocalDate now = LocalDate.now();
    	while (now.getDayOfWeek() == DayOfWeek.SATURDAY || now.getDayOfWeek() == DayOfWeek.SUNDAY) {
    		now = now.plus(-1, ChronoUnit.DAYS);
    	}
    	model.addAttribute("end", new Date(com.base.card.common.util.DateUtil.localDate2Long(now)));
    	model.addAttribute("fieldItemList", MsaSortField.select());
    	model.addAttribute("ascItemList", OriginMask.select());
    	model.addAttribute("stockTypeItemList", StockType.select());
    	
        return PREFIX + "currentIncrease.html";
    }
    
    /**
     */
    @RequestMapping("/sql")
    public String indexBySql(Model model) {
    	model.addAttribute("begin", new Date(DateUtil.getDate(2019, 1, 11).getTime()));
    	LocalDate now = LocalDate.now();
    	while (now.getDayOfWeek() == DayOfWeek.SATURDAY || now.getDayOfWeek() == DayOfWeek.SUNDAY) {
    		now = now.plus(-1, ChronoUnit.DAYS);
    	}
    	model.addAttribute("end", new Date(com.base.card.common.util.DateUtil.localDate2Long(now)));
    	model.addAttribute("fieldItemList", MsaSortField.select());
    	model.addAttribute("ascItemList", OriginMask.select());
    	model.addAttribute("stockTypeItemList", StockType.select());
    	
    	return PREFIX + "currentIncreaseSql.html";
    }

    /**
     */
    @RequestMapping("/currentIncrease_add")
    public String currentIncreaseAdd() {
        return PREFIX + "currentIncrease_add.html";
    }

    /**
     */
    @RequestMapping("/currentIncrease_update/{currentIncreaseId}")
    public String currentIncreaseUpdate(@PathVariable Integer currentIncreaseId, Model model) {
        CurrentIncrease currentIncrease = currentIncreaseService.getById(currentIncreaseId);
        model.addAttribute("item",currentIncrease);
        return PREFIX + "currentIncrease_edit.html";
    }

    /**
     */
//    @EnumEntityList(entityList={@EnumEntity(enumName="StockType", fieldName="stockType")})
    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(CurrentIncreaseQuery query) {
    	if (query.getBegin() == null || query.getEnd() == null) {
    		return MapUtil.createSuccessMap("rows", Collections.emptyList(), "total", 0L);
    	}
    	if (query.getStockType() == StockType.All.getType()) {
    		query.setStockType(null);
    	}
    	IPage<CurrentIncrease> page = this.currentIncreaseService.findByQuery(query);
		return MapUtil.createSuccessMap("rows", page.getRecords(), "total", page.getTotal());
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/createStockFile")
    @ResponseBody
    public Object createStockFile(CurrentIncreaseQuery query) {
    	query.setOffset(0);
    	query.setLimit(600);
    	List<CurrentIncrease> list = (List<CurrentIncrease>) this.list(query).get("rows");
    	list = list.stream().filter((item) -> {return !item.getSymbol().substring(0, 1).equals("3");}).collect(Collectors.toList());
    	StockUtil.exportStock(list.stream().map(CurrentIncrease::getSymbol).collect(Collectors.toList()), tmpDir + File.separator + "自选股.txt");
    	return SUCCESS_TIP;
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/createStockFileSql")
    @ResponseBody
    public Object createStockFileSql(CurrentIncreaseQuery query) {
    	query.setOffset(0);
    	query.setLimit(10000);
    	List<CurrentIncrease> list = (List<CurrentIncrease>) this.listSql(query).get("rows");
    	list = list.stream().filter((item) -> {return !item.getSymbol().substring(0, 1).equals("3");}).collect(Collectors.toList());
    	list = new ArrayList<>(new HashSet<>(list));
    	list = list.subList(0, list.size() > 600 ? 600 : list.size());
    	StockUtil.exportStock(list.stream().map(CurrentIncrease::getSymbol).collect(Collectors.toList()), tmpDir + File.separator + "自选股.txt");
    	return SUCCESS_TIP;
    }
    
    @RequestMapping(value = "/listSql")
    @ResponseBody
    public Map<String, Object> listSql(CurrentIncreaseQuery query) {
    	if (query.getBegin() == null || query.getEnd() == null) {
    		return MapUtil.createSuccessMap("rows", Collections.emptyList(), "total", 0L);
    	}
    	if (query.getStockType() == StockType.All.getType()) {
    		query.setStockType(null);
    	}
    	IPage<CurrentIncrease> page = this.currentIncreaseService.findBySql(query);
		return MapUtil.createSuccessMap("rows", page.getRecords(), "total", page.getTotal());
    }

    /**
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CurrentIncrease currentIncrease) {
        currentIncreaseService.save(currentIncrease);
        return SUCCESS_TIP;
    }

    /**
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer currentIncreaseId) {
        currentIncreaseService.removeById(currentIncreaseId);
        return SUCCESS_TIP;
    }

    /**
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CurrentIncrease currentIncrease) {
        currentIncreaseService.updateById(currentIncrease);
        LogObjectHolder.me().set(currentIncrease);
        return SUCCESS_TIP;
    }

    @RequestMapping(value = "/detail/{currentIncreaseId}")
    @ResponseBody
    public Object detail(@PathVariable("currentIncreaseId") Integer currentIncreaseId) {
        return currentIncreaseService.getById(currentIncreaseId);
    }
}
