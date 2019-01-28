package com.bdtd.card.web.stock.strategy;

import java.sql.Date;
import java.util.List;

import com.bdtd.card.data.stock.model.ResultDetail;
import com.bdtd.card.data.stock.model.StockMain;

public interface IAnalysisStrategy {

	void analysis(List<StockMain> stockMains, int index, List<ResultDetail> result, int maxIndex, Date begin, float limit) throws Exception;
}
