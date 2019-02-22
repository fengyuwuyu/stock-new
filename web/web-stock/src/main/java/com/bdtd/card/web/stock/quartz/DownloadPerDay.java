package com.bdtd.card.web.stock.quartz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bdtd.card.data.stock.dao.HolidayMapper;
import com.bdtd.card.data.stock.util.CommonsUtil;
import com.bdtd.card.web.stock.service.InitStockServiceI;

/**
 * 每天下载股票的基本数据（当前股价、成交量、昨收、今开等）
 * @author ll
 *
 */
@Component
@Configurable
@EnableScheduling
public class DownloadPerDay {
	
	private InitStockServiceI initStockServiceI;
	private HolidayMapper holidayMapper;
	private Logger log = Logger.getLogger(DownloadPerDay.class);
	
	@Autowired
	public void setHolidayMapper(HolidayMapper holidayMapper) {
		this.holidayMapper = holidayMapper;
	}

	@Autowired
	public void setInitStockServiceI(InitStockServiceI initStockServiceI) {
		this.initStockServiceI = initStockServiceI;
	}
	
	@Scheduled(cron = "0 0 18 * * ?")
	public void execute(){
		log.info("开始下载股票每天综合信息");
//		if(CommonsUtil.checkTime(holidayMapper)){
			try {
				initStockServiceI.initStockEveryDay();
			} catch (Exception e) {
				log.info(CommonsUtil.join(e.getStackTrace(), ","));
			}
//		}
		log.info("下载股票每天综合信息结束");
	}
}
