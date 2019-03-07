package com.bdtd.card.web.stock.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdtd.card.common.util.MapUtil;
import com.bdtd.card.web.stock.model.FutureDay;
import com.bdtd.card.web.stock.model.SearchType;
import com.bdtd.card.web.stock.service.SearcherServiceI;
import com.bdtd.card.web.stock.strategy.BaseAnalysisStrategy;

@Controller
@RequestMapping("/searcher")
public class SearcherController {
	
	private static final String PREFIX = "/stock/";
	
	@RequestMapping("/{queryType}")
	public String index(Model model, @PathVariable Integer queryType) {
		model.addAttribute("begin", new Date(System.currentTimeMillis()));
		model.addAttribute("futureDayItemList", FutureDay.select());
		model.addAttribute("queryType", queryType);
		return PREFIX + "stockMain.html";
	}
	
	@Autowired
	SearcherServiceI searcherServiceI;

	@RequestMapping("/findIncreaseTopn")
	@ResponseBody
	public Map<String, Object> findIncreaseTopn(Date begin, Float limit, Float maxIncrease, Integer searchType, Integer futureDay) {
		if (begin == null) {
			return MapUtil.createSuccessMap("rows", Collections.emptyList(), "total", 0);
		}
		begin = begin == null ? new Date(System.currentTimeMillis()) : begin;
		limit = limit == null ? BaseAnalysisStrategy.INCREASE : limit;
		maxIncrease = maxIncrease == null ? Float.MAX_VALUE : maxIncrease;
		searchType = searchType == null ? SearchType.MAX_INCREASE.getType() : searchType;
		futureDay = futureDay == null ? FutureDay.ONE_WEEK.getType() : futureDay;
		BaseAnalysisStrategy.futureDay = futureDay;
		return searcherServiceI.findIncreaseTopn(begin, limit, maxIncrease, searchType);
	}
	
	@RequestMapping("/searchTypes")
	@ResponseBody
	public List<Map<String, Object>> getSearchTypes() {
		List<Map<String, Object>> result = new ArrayList<>();
		for (SearchType type : SearchType.values()) {
			Map<String, Object> item = new HashMap<>(2);
			item.put("id", type.getType());
			item.put("text", type.getDesc());
			result.add(item);
		}
		return result;
	}
	
	
}
