package com.bdtd.card.web.stock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdtd.card.common.util.MapUtil;
import com.bdtd.card.data.stock.dao.StockMainMapper;
import com.bdtd.card.web.stock.quartz.DownloadPerDay;
import com.bdtd.card.web.stock.service.InitStockServiceI;
import com.bdtd.card.web.stock.service.StockMainServiceI;

@Controller
@RequestMapping("/test")
public class TestController {

	private StockMainServiceI stockMainServiceI;
	private InitStockServiceI initStockServiceI;
	private StockMainMapper stockMainMapper;
	private Logger log = Logger.getLogger(TestController.class);

	@Autowired
	public void setStockMainMapper(StockMainMapper stockMainMapper) {
		this.stockMainMapper = stockMainMapper;
	}

	@Autowired
	public void setInitStockServiceI(InitStockServiceI initStockServiceI) {
		this.initStockServiceI = initStockServiceI;
	}

	@Autowired
	public void setStockMainServiceI(StockMainServiceI stockMainServiceI) {
		this.stockMainServiceI = stockMainServiceI;
	}
	
	
	@Autowired
	DownloadPerDay downloadPerDay;

	private int[] range(int begin, int end) {
		int[] result = new int[end + 1 -begin];
		for (int i = 0; i <= end -begin; i++) {
			result[i] = begin + i;
		}
		return result;
	}
	
	@RequestMapping("/test2")
	@ResponseBody
	public Map<String, Object> test2() throws Exception {
		initStockServiceI.initStockEveryDay(null);
		return MapUtil.createSuccessMap();
	}
	
	private static List<Integer> nums = new ArrayList<>();
	static {
		nums.add(1);
		nums.add(3);
		nums.add(5);
		nums.add(7);
		nums.add(8);
		nums.add(10);
		nums.add(12);
		
	}

	private static int chargeDay(int i, int j, int k) {
		if((i % 100 == 0 && i % 400 ==0) || (i % 100 != 0 && i % 4 == 0)){
			if(j == 2 && k == 29){
				return i * 10000 + 300 + 1;
			}
		}else if(j == 2 && k == 28){
			return i * 10000 + 300 + 1;
		}
		if(nums.contains(j)){
			if(k == 31){
				return i * 10000 + (j + 1) * 100 + 1;
			}
		}else if(k == 30){
			return i * 10000 + (j + 1) * 100 + 1;
		}
		return i * 10000 + j * 100 + k + 1;
	}
}
