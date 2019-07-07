package service.impl;

import bean.SaleOut;
import bean.SaleReturn;
import bean.Stock;
import dao.SaleOutDao;
import dao.SaleReturnDao;
import dao.StockDao;
import dao.impl.SaleOutDaoImpl;
import dao.impl.SaleReturnDaoImpl;
import dao.impl.StockDaoImpl;
import service.SaleReturnService;

import java.util.List;

public class SaleReturnServiceImpl implements SaleReturnService {

    private SaleReturnDao saleReturnDao = new SaleReturnDaoImpl();
    private SaleOutDao saleOutDao = new SaleOutDaoImpl();

    private StockDao stockDao = new StockDaoImpl();

    @Override
    public List<SaleReturn> list() {
        return saleReturnDao.list();
    }

    @Override
    public Long insert(SaleReturn bean) {

        Long result = 0L;
        result = saleReturnDao.insert(bean);
        if (result>0){
            Stock stockBean = stockDao.loadByCargoId((long)bean.getCargoId());
            stockBean.setNumber(stockBean.getNumber()+bean.getNumber());
            result = stockDao.update(stockBean);
            if(result > 0){
                //退货状态转换成退货详情
                SaleOut saleOut = saleOutDao.load((long)bean.getSaleId());
                saleOut.setStatus(1);
                System.out.println("----------------");
                System.out.println(saleOut.getTotal());
                System.out.println("-----------------");
                result = saleOutDao.update(saleOut);
            }
        }
        return result;
    }

    @Override
    public Long update(SaleReturn bean)
    {
        Long result = 0L;
        Stock stockBean = stockDao.loadByCargoId((long)bean.getCargoId());
        int currNum = stockBean.getNumber();
        int newNum = bean.getNumber();
        SaleReturn oldBean = saleReturnDao.load((long) bean.getReSaleId());
        int oldNum = oldBean.getNumber();
        currNum = currNum - oldNum + newNum;
        if(currNum >= 0){
            result = saleReturnDao.update(bean);
            if (result>0){
                stockBean.setNumber(currNum);
                result = stockDao.update(stockBean);
            }
        }else result = -1L;
        return result;
    }

    @Override
    public Long delete(Long id) {
        Long result = 0L;
        Long result1 = 0L;
        SaleReturn oldBean = saleReturnDao.load(id);
        Stock stockBean = stockDao.loadByCargoId((long)oldBean.getCargoId());
        SaleOut saleOutBean = saleOutDao.load((long)oldBean.getSaleId());
        int currNum = stockBean.getNumber();
        int oldNum = oldBean.getNumber();
        currNum = currNum + oldNum;
        if(currNum >= 0){
            result = saleReturnDao.delete(id);
            if (result>0){
                stockBean.setNumber(currNum);
                result = stockDao.update(stockBean);
                saleOutBean.setStatus(0);
                result1 = saleOutDao.update(saleOutBean);
            }
        }else result = -1L;
        return result;
    }

    @Override
    public SaleReturn load(Long id) {
        return saleReturnDao.load(id);
    }

    @Override
    public SaleReturn loadByName(String name) {
        return saleReturnDao.loadByName(name);
    }

    @Override
    public Long count() {
        return saleReturnDao.count();
    }

    @Override
    public List<SaleReturn> pager(Long pageNum, Long pageSize) {
        return saleReturnDao.pager(pageNum,pageSize);
    }

    @Override
    public Long countByName(String name) {
        return saleReturnDao.countByName(name);
    }

    @Override
    public List<SaleReturn> pagerByName(String name, Long pageNum, Long pageSize) {
        return saleReturnDao.pagerByName(name,pageNum,pageSize);
    }

    @Override
    public List<SaleReturn> pagerBySaleId(String name, Long pageNum, Long pageSize) {
        return saleReturnDao.pagerBySaleId(name,pageNum,pageSize);
    }


    @Override
    public Long countById(String name) {
        return saleReturnDao.countById(name);
    }
}
