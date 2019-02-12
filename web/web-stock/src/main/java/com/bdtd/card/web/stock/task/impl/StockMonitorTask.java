package com.bdtd.card.web.stock.task.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdtd.card.common.util.ThreadPool;
import com.bdtd.card.data.stock.dao.HolidayMapper;
import com.bdtd.card.data.stock.service.IMonitorService;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.web.stock.task.ITask;

@Service
public class StockMonitorTask implements ITask {
	
	@Autowired
	private IMonitorService monitorService;
	@Autowired
	private HolidayMapper holidayMapper;
	private long delay = 60;

	@Override
	public void run() {
		if (CommonsUtil.checkTime(this.holidayMapper)) {
			monitorService.doMonitor();
			this.start();
		}
	}

	@Override
	public void start() {
		ThreadPool.execute(this, delay , TimeUnit.SECONDS);
	}

}
