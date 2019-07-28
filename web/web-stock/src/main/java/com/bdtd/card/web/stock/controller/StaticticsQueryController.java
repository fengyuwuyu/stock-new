package com.bdtd.card.web.stock.controller;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.card.common.util.MapUtil;
import com.bdtd.card.data.stock.dao.ResultDetailMapper;
import com.bdtd.card.data.stock.model.ResultDetail;

@RequestMapping("/statisticsQuery")
@Controller
public class StaticticsQueryController {
	
	@Autowired
	private ResultDetailMapper resultDetailMapper;

	@RequestMapping("/detail")
	@ResponseBody
	public Map<String, Object> detail(Integer type, Date begin, Integer page, Integer rows) {
		type = type == null ? 1 : type;
		List<ResultDetail> list = null;
		page = page != null ? (page - 1) * rows : null;
		Map<String, Object> param = MapUtil.createMap("begin", begin, "page", page, "rows", rows);
		int total = 0;
		if (type == 1) {
			total = resultDetailMapper.countIncrease(param);
			if (total > 0) {
				list = resultDetailMapper.findIncrease(param);
			}
		} else {
			total = resultDetailMapper.countDecrease(param);
			if (total > 0) {
				list = resultDetailMapper.findDecrease(param);
			}
		}
		
		list = list == null ? Collections.emptyList() : list;
		return MapUtil.createSuccessMap("rows", list, "total", total);
	}
	
}
