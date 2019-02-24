package com.bdtd.card.web.stock.controller;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdtd.card.data.stock.model.StockQuery;
import com.bdtd.card.web.stock.service.StockMainServiceI;

@RequestMapping("/stock")
@Controller
public class StockController {
	
	private static final String PREFIX = "/stock/";
	
	@Autowired
	private StockMainServiceI stockMainServiceI;

	@RequestMapping("/showChartIndex")
	public String showChart(Model model, Date begin, String symbol, Integer type) {
		model.addAttribute("symbol", symbol);
		model.addAttribute("begin", begin);
		model.addAttribute("type", type);
		return PREFIX + "showChart.html";
	}

	
	@RequestMapping("/showChart")
	@ResponseBody
	public Map<String,Object> showChart(StockQuery query){
		return this.stockMainServiceI.showChart(query);
	}
	
}
