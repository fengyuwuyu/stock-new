package com.bdtd.card.data.stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bdtd.card.data.stock.model.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lilei
 * @since 2019-03-21
 */
public interface StockMapper extends BaseMapper<Stock> {

	int insertAll(@Param("list") List<Stock> list);
	
}
