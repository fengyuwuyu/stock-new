package com.bdtd.card.web.stock.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdtd.card.common.util.MapUtil;
import com.bdtd.card.data.stock.service.IMonitorService;

@RequestMapping("/testStock")
@Controller
public class TestStockController {

	@Autowired
	private IMonitorService monitorService;
	
	@RequestMapping("/monitor")
	@ResponseBody
	public Map<String, Object> monitor() {
		monitorService.doMonitor();
		return MapUtil.createSuccessMap();
	}
}
