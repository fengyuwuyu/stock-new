package com.bdtd.card.data.stock.dao;

import com.bdtd.card.data.stock.model.Monitor;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 股票监控 Mapper 接口
 * </p>
 *
 * @author lilei
 * @since 2019-01-29
 */
public interface MonitorMapper extends BaseMapper<Monitor> {

	List<Monitor> findAll();
	
	List<Monitor> findAll1();

}
