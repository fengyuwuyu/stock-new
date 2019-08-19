package com.bdtd.card.web.stock.util;

import java.util.List;

import com.bdtd.card.common.util.FileUtil;
import com.bdtd.card.common.util.StringUtil;

public class StockUtil {
	
	public static final String NEXT_LINE = "\r\n";

	public static void exportStock(List<String> symbols, String fileName) {
		if (symbols == null || symbols.size() == 0 || StringUtil.isNullEmpty(fileName)) {
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < symbols.size(); i++) {
			sb.append(symbols.get(i));
			if (i != symbols.size() - 1) {
				sb.append(NEXT_LINE);
			}
		}
		FileUtil.writeContent(fileName, sb.toString());
	}
	
}
