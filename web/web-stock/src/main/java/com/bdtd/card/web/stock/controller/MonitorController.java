package com.bdtd.card.web.stock.controller;

import java.util.Date;
import java.util.Map;
import com.bdtd.card.common.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.bdtd.card.data.stock.model.Monitor;
import com.bdtd.card.data.stock.service.IMonitorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdtd.card.common.consts.Consts;
import com.bdtd.card.common.util.MapUtil;

/**
 * 股票监控控制器
 *
 * @author 
 * @Date 2019-01-29 17:01:10
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

    private String PREFIX = "/stock/monitor/";

    @Autowired
    private IMonitorService monitorService;

    /**
     * 跳转到股票监控首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "monitor.html";
    }

    /**
     * 跳转到添加股票监控
     */
    @RequestMapping("/monitor_add")
    public String monitorAdd() {
        return PREFIX + "monitor_add.html";
    }

    /**
     * 跳转到修改股票监控
     */
    @RequestMapping("/monitor_update/{monitorId}")
    public String monitorUpdate(@PathVariable Integer monitorId, Model model) {
        Monitor monitor = monitorService.getById(monitorId);
        model.addAttribute("item",monitor);
        return PREFIX + "monitor_edit.html";
    }

    /**
     * 获取股票监控列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition, Integer offset, Integer limit) {
    	QueryWrapper<Monitor> wrapper = new QueryWrapper<>();
    	IPage<Map<String, Object>> page = monitorService.pageMaps(new Page<>(offset, limit), wrapper);
		return MapUtil.createSuccessMap("rows", page.getRecords(), "total", page.getTotal());
    }

    /**
     * 新增股票监控
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Monitor monitor) {
        monitorService.save(monitor);
        return SUCCESS_TIP;
    }

    /**
     * 删除股票监控
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer monitorId) {
        monitorService.removeById(monitorId);
        return SUCCESS_TIP;
    }

    /**
     * 修改股票监控
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Monitor monitor) {
        monitorService.updateById(monitor);
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
