package com.bdtd.card.data.stock.service.impl;

import com.bdtd.card.data.stock.model.Stock;
import com.bdtd.card.data.stock.dao.StockMapper;
import com.bdtd.card.data.stock.service.IStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lilei
 * @since 2019-03-21
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements IStockService {

}
