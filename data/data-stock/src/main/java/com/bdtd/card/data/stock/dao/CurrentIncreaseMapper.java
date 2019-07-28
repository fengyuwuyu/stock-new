package com.bdtd.card.data.stock.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.bdtd.card.data.stock.model.query.CurrentIncreaseQuery;

/**
 * <p>
 * 每日股票汇总 Mapper 接口
 * </p>
 *
 * @author lilei
 * @since 2019-03-16
 */
public interface CurrentIncreaseMapper extends BaseMapper<CurrentIncrease> {

	int insertAll(@Param("list") List<CurrentIncrease> list);

	long countByQuery(CurrentIncreaseQuery query);

	List<CurrentIncrease> findByQuery(CurrentIncreaseQuery query);

	long countBySql(CurrentIncreaseQuery query);

	List<CurrentIncrease> findBySql(CurrentIncreaseQuery query);

	LocalDate findMaxMsaDay();
	
}
