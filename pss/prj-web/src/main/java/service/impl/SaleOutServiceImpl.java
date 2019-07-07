package service.impl;

import bean.SaleOut;
import bean.Stock;
import dao.SaleOutDao;
import dao.StockDao;
import dao.impl.SaleOutDaoImpl;
import dao.impl.StockDaoImpl;
import service.SaleOutService;

import java.util.Date;
import java.util.List;

public class SaleOutServiceImpl implements SaleOutService {

    private SaleOutDao saleOutDao = new SaleOutDaoImpl();
    private StockDao stockDao = new StockDaoImpl();

    @Override
    public List<SaleOut> list() {
        return saleOutDao.list();
    }

    @Override
    public Long insert(SaleOut bean) {
        Long result = 0L;
        result = saleOutDao.insert(bean);
        if (result > 0) {
            Stock stockBean = stockDao.loadByCargoId((long) bean.getCargoId());
            //System.out.println(bean.getCargoId());
            if (stockBean == null) {
                System.out.println("连接失败！");
                return 0L;
            } else {
                int stock_number = stockBean.getNumber();
                int sell_number = stock_number - bean.getNumber();
                if (sell_number > 0) {

                    Date curDate = stockBean.getSellDate();
                    if (curDate.compareTo(bean.getOutDate())==-1){
                        stockBean.setSellDate(bean.getOutDate());
                    }
                    stockBean.setNumber(sell_number);
                    result = stockDao.update(stockBean);

                    return result;
                } else {
                    result=-1L;
                   return result;
                }
            }
        }
        return 0L;
    }

    @Override
    public Long update(SaleOut bean)
    {
      Long result = 0L;
        Stock stockBean = stockDao.loadByCargoId((long) bean.getCargoId());
        //当前的数量
        int curNum = stockBean.getNumber();
        //旧的数量（回显的数量）
        int oldNum =saleOutDao.load((long)bean.getSaleId()).getNumber();
        //新的数量
        int newNum = bean.getNumber();
        curNum= curNum+oldNum-newNum ;
        if(curNum >= 0){
            result = saleOutDao.update(bean);
            if(result > 0L){
                stockBean.setNumber(curNum);
                result =stockDao.update(stockBean);
            }
        }
        return result;
//        return saleOutDao.update(bean);
    }

    @Override
    public Long delete(Long id) {


        return saleOutDao.delete(id);
    }

    @Override
    public SaleOut load(Long id) {
        return saleOutDao.load(id);
    }

    @Override
    public SaleOut loadByName(String name) {
        return saleOutDao.loadByName(name);
    }

    @Override
    public Long count() {
        return saleOutDao.count();
    }

    @Override
    public List<SaleOut> pager(Long pageNum, Long pageSize) {
        return saleOutDao.pager(pageNum,pageSize);
    }

    @Override
    public Long countByName(String name) {
        return saleOutDao.countByName(name);
    }

    @Override
    public List<SaleOut> pagerByName(String name, Long pageNum, Long pageSize) {
        return saleOutDao.pagerByName(name,pageNum,pageSize);
    }
}
