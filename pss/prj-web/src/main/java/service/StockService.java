package service;

import bean.Stock;

import java.util.List;

public interface StockService {
    List<Stock> list();
    List<Stock> listBySafe();
    Long insert(Stock bean);
    Long delete(Long id);
    Long update(Stock bean);

    Stock loadByStockId(Long id);
    Stock loadByCargoId(Long id);

    Long count();
    Long countByName(String name);

    List<Stock> pager(Long pageNum, Long pageSize);
    List<Stock> pagerByName(String name, Long pageNum, Long pageSize);

    //预警
    Long countBySafe();
    List<Stock> pagerBySafe(Long pageNum, Long pageSize);
}
