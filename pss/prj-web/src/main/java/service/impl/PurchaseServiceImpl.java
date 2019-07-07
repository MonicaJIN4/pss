package service.impl;

import bean.Purchase;
import bean.Stock;
import dao.PurchaseDao;
import dao.StockDao;
import dao.impl.PurchaseDaoImpl;
import dao.impl.StockDaoImpl;
import service.PurchaseService;

import java.util.Date;
import java.util.List;

public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseDao purchaseDao = new PurchaseDaoImpl();
    private StockDao stockDao = new StockDaoImpl();

    @Override
    public List<Purchase> list() {
        return purchaseDao.list();
    }

    @Override
    public List<Purchase> list_Excel() {
        return purchaseDao.list_Excel();
    }

    @Override
    public Long insert(Purchase bean)
    {   Long result = 0L;
        result = purchaseDao.insert(bean);
        if(result > 0L){
            Stock stockBean = stockDao.loadByCargoId((long)bean.getCargoId());
            if (stockBean == null){
                stockBean = new Stock();
                stockBean.setCargoId(bean.getCargoId());
                stockBean.setNumber(0);
                stockDao.insert(stockBean);
            }
            Date curDate = stockBean.getBuyDate();
            //日期前者小于后者 返回-1 大于返回1
            if (curDate.compareTo(bean.getDate())==-1){
               stockBean.setBuyDate(bean.getDate());
            }
            stockBean.setNumber(stockBean.getNumber()+bean.getNumber());
            result =stockDao.update(stockBean);
        }
        return result;
    }

    @Override
    public Long update(Purchase bean)
    {
        Long result = 0L;
        Stock stockBean = stockDao.loadByCargoId((long)bean.getCargoId());
        int curNum = stockBean.getNumber();
        int oldNum =purchaseDao.load((long)bean.getPurId()).getNumber();
        int newNum = bean.getNumber();
        curNum += newNum - oldNum;
        if(curNum >= 0){
            result = purchaseDao.update(bean);
            if(result > 0L){
                stockBean.setNumber(curNum);
                result =stockDao.update(stockBean);
            }
        }
        return result;
    }

    @Override
    public Long delete(Long id) {
        Long result = 0L;
        Purchase bean =purchaseDao.load(id);
        Stock stockBean = stockDao.loadByCargoId((long)bean.getCargoId());
        int curNum = stockBean.getNumber();
        int newNum = bean.getNumber();
        curNum -= newNum;
        if(curNum >= 0){
            result = purchaseDao.delete(id);
            if(result > 0L){
                stockBean.setNumber(curNum);
                result =stockDao.update(stockBean);
            }
        }
        return result;
    }

    @Override
    public Purchase load(Long id) {
        return purchaseDao.load(id);
    }

    @Override
    public Purchase loadByName(int cargoId) {
        return purchaseDao.loadByName(cargoId);
    }

    @Override
    public Long count() {
        return purchaseDao.count();
    }

    @Override
    public List<Purchase> pager(Long pageNum, Long pageSize) {
        return purchaseDao.pager(pageNum,pageSize);
    }

    @Override
    public Long countByName(String name) {
        return purchaseDao.countByName(name);
    }

    @Override
    public List<Purchase> pagerByName(String name, Long pageNum, Long pageSize) {
        return purchaseDao.pagerByName(name,pageNum,pageSize);
    }
}
