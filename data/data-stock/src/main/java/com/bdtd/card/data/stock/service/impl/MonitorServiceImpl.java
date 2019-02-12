package com.bdtd.card.data.stock.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdtd.card.base.consts.Consts;
import com.bdtd.card.common.util.HttpUtils;
import com.bdtd.card.data.stock.dao.MonitorMapper;
import com.bdtd.card.data.stock.model.Monitor;
import com.bdtd.card.data.stock.service.IMonitorService;

/**
 * <p>
 * 股票监控 服务实现类
 * </p>
 *
 * @author lilei
 * @since 2019-01-29
 */
@Service
public class MonitorServiceImpl extends ServiceImpl<MonitorMapper, Monitor> implements IMonitorService {

	private Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void doMonitor() {
		List<Monitor> list = this.baseMapper.selectList(null);
		if (list.size() > 0) {
			String url = getUrl(Consts.STOCK_CURR_DATA_URL, list);
			log.debug("获取股票详情url = {}", url);
			String result = HttpUtils.sendGet(url, null);
			log.debug(result);
		}
	}

	private String getUrl(String stockCurrDataUrl, List<Monitor> list) {
		StringBuilder sb = new StringBuilder();
		for (Monitor monitor : list) {
			sb.append("sh");
			sb.append(monitor.getSymbol());
			sb.append(",");
		}
		return stockCurrDataUrl + sb.substring(0, sb.length() - 1);
	}

}
