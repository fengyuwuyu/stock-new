package com.bdtd.card.data.stock.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdtd.card.base.consts.Consts;
import com.bdtd.card.base.model.MonitorType;
import com.bdtd.card.common.util.HttpUtils;
import com.bdtd.card.common.util.StringUtil;
import com.bdtd.card.common.util.mail.SendMail;
import com.bdtd.card.data.stock.dao.MonitorMapper;
import com.bdtd.card.data.stock.model.Monitor;
import com.bdtd.card.data.stock.model.MonitorNotify;
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
	
	public static final String BUY_MESSAGE = "买：";
	public static final String SELL_MESSAGE = "卖：";

	private Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void doMonitor() {
		List<Monitor> list = this.baseMapper.findAll();
		if (list.size() > 0) {
			String url = getUrl(Consts.STOCK_CURR_DATA_URL, list);
			String result = HttpUtils.sendGet(url, null, "GB2312");
			dealResult(result, list);
		}
	}
	
	private void dealResult(String result, List<Monitor> list) {
		if (StringUtil.isNullEmpty(result)) {
			return;
		}
		
		Map<String, Monitor> monitorMap = list.stream().collect(Collectors.toMap(Monitor::getSymbol, item -> item));
		List<String> buyList = new ArrayList<>();
		List<MonitorNotify> sellList = new ArrayList<>();
		
		String[] arr = result.split(";");
		if (arr != null && arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				String str = arr[i];
				str = str.replace("\"", "");
				try {
					String[] contents = str.split(",");
					if (contents != null && contents.length > 0) {
						String symbol = contents[0].split("=")[0];
						symbol = symbol.substring(symbol.length() - 6);
						Monitor monitor = monitorMap.get(symbol);
						if (monitor == null) {
							continue;
						}
						
						float open = Float.valueOf(contents[1]);
						float close = Float.valueOf(contents[2]);
						float current = Float.valueOf(contents[3]);
						float max = Float.valueOf(contents[4]);
						float min = Float.valueOf(contents[5]);
						long volume = Long.valueOf(contents[8]);
						
						float buy1 = Float.valueOf(contents[11]);
						long buyVolume1 = Long.valueOf(contents[10]);
						float buy2 = Float.valueOf(contents[13]);
						long buyVolume2 = Long.valueOf(contents[12]);
						float buy3 = Float.valueOf(contents[15]);
						long buyVolume3 = Long.valueOf(contents[14]);
						float buy4 = Float.valueOf(contents[17]);
						long buyVolume4 = Long.valueOf(contents[16]);
						float buy5 = Float.valueOf(contents[19]);
						long buyVolume5 = Long.valueOf(contents[18]);
						long totalBuyVolume = 0L;
						totalBuyVolume += buyVolume1;
						totalBuyVolume += buyVolume2;
						totalBuyVolume += buyVolume3;
						totalBuyVolume += buyVolume4;
						totalBuyVolume += buyVolume5;

						long sellVolume1 = Long.valueOf(contents[20]);
						float sell1 = Float.valueOf(contents[21]);
						long sellVolume2 = Long.valueOf(contents[22]);
						float sell2 = Float.valueOf(contents[23]);
						long sellVolume3 = Long.valueOf(contents[24]);
						float sell3 = Float.valueOf(contents[25]);
						long sellVolume4 = Long.valueOf(contents[26]);
						float sell4 = Float.valueOf(contents[27]);
						long sellVolume5 = Long.valueOf(contents[28]);
						float sell5 = Float.valueOf(contents[29]);
						long totalSellVolume = 0L;
						totalSellVolume += sellVolume1;
						totalSellVolume += sellVolume2;
						totalSellVolume += sellVolume3;
						totalSellVolume += sellVolume4;
						totalSellVolume += sellVolume5;
						
						MonitorType monitorType = MonitorType.typeOf(monitor.getMonitorType());
						switch (monitorType) {
						case BUY:
							if (current >= monitor.getBuyPrice().floatValue()) {
								buyList.add(symbol);
							}
							break;
						case SELL:
							if (current <= monitor.getSellPriceLow().floatValue() || current >= monitor.getSellPriceHigh().floatValue()) {
								MonitorNotify notify = new MonitorNotify(symbol, current - monitor.getBuyPrice().floatValue());
								sellList.add(notify);
							}
							break;
						default:
							break;
						}
					}
				} catch (Exception e) {
					log.warn(str);
				}
			}
		}
		
		String message = String.format("买入：%s; 卖出：%s", buyList, sellList);
		log.info(message);
		SendMail.sendDefault(message);
	}
	
	

	private String getUrl(String stockCurrDataUrl, List<Monitor> list) {
		StringBuilder sb = new StringBuilder();
		for (Monitor monitor : list) {
			if (monitor.getType() == 0) {
				sb.append("sh");
			} else {
				sb.append("sz");
			}
			sb.append(monitor.getSymbol());
			sb.append(",");
		}
		return stockCurrDataUrl + sb.substring(0, sb.length() - 1);
	}

}
