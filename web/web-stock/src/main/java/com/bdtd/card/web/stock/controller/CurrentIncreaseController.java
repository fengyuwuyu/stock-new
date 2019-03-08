package com.bdtd.card.web.stock.controller;

import java.sql.Date;
import java.util.Collections;

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
import com.bdtd.card.data.stock.model.CurrentIncrease;
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

    /**
     * 跳转到最近最大涨幅分析首页
     */
    @RequestMapping("")
    public String index(Model model) {
    	model.addAttribute("begin", DateUtil.getDate(2019, 1, 11));
    	model.addAttribute("end", new Date(System.currentTimeMillis()));
    	model.addAttribute("fieldItemList", MsaSortField.select());
    	model.addAttribute("ascItemList", OriginMask.select());
    	
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
