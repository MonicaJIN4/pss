package service.impl;


import dao.StockDao;
import dao.impl.StockDaoImpl;
import bean.Stock;
import service.StockService;

import java.util.List;

public class StockServiceImpl implements StockService {
    private StockDao stockDao = new StockDaoImpl();

    @Override
    public List<Stock> list() {
        return stockDao.list();
    }

    @Override
    public List<Stock> listBySafe() {
        return stockDao.listBySafe();
    }

    @Override
    public Long insert(Stock bean) {
        return stockDao.insert(bean);
    }

    @Override
    public Long delete(Long id) {
        return stockDao.delete(id);
    }

    @Override
    public Long update(Stock bean) {
        return stockDao.update(bean);
    }

    @Override
    public Stock loadByStockId(Long id) {
        return stockDao.loadByStockId(id);
    }

    @Override
    public Stock loadByCargoId(Long id) {
        return stockDao.loadByCargoId(id);
    }

    @Override
    public Long count() {
        return stockDao.count();
    }

    @Override
    public Long countByName(String name) {
        return stockDao.countByName(name);
    }

    @Override
    public List<Stock> pager(Long pageNum, Long pageSize) {

        return stockDao.pager(pageNum,pageSize);
    }

    @Override
    public List<Stock> pagerByName(String name, Long pageNum, Long pageSize) {
        return stockDao.pagerByName(name,pageNum,pageSize);
    }

    @Override
    public Long countBySafe() {
        return stockDao.countBySafe();
    }

    @Override
    public List<Stock> pagerBySafe(Long pageNum, Long pageSize) {
        return stockDao.pagerBySafe(pageNum,pageSize);
    }
}
