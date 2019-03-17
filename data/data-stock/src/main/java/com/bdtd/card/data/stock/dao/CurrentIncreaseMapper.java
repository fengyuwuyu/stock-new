package com.bdtd.card.data.stock.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bdtd.card.data.stock.model.CurrentIncrease;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
	
}
