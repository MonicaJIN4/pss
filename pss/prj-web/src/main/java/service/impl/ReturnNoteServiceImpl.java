package service.impl;

import bean.Purchase;
import bean.ReturnNote;
import bean.Stock;
import dao.PurchaseDao;
import dao.ReturnNoteDao;
import dao.StockDao;
import dao.impl.PurchaseDaoImpl;
import dao.impl.ReturnNoteDaoImpl;
import dao.impl.StockDaoImpl;
import service.PurchaseService;
import service.ReturnNoteService;

import java.util.Date;
import java.util.List;

public class ReturnNoteServiceImpl implements ReturnNoteService {

    private ReturnNoteDao returnNoteDao = new ReturnNoteDaoImpl();
    private StockDao stockDao = new StockDaoImpl();
    private PurchaseService purchaseService = new PurchaseServiceImpl();
    private PurchaseDao purchaseDao = new PurchaseDaoImpl();

    @Override
    public List<ReturnNote> list() {
        return returnNoteDao.list();
    }

    @Override
    public Long insert(ReturnNote bean) {
        Long result = 0L;
        Stock stockBean = stockDao.loadByCargoId((long)bean.getCargoId());
        int num = stockBean.getNumber() - bean.getNumber();
        if(num >= 0){
            result = returnNoteDao.insert(bean);
            if(result > 0L){
                stockBean.setNumber(num);
                result =stockDao.update(stockBean);

                if (result > 0L){
                    //退货状态转换成退货详情
                    Purchase purchase = purchaseService.load(Long.valueOf(bean.getPurId()));
                    purchase.setStatus(1);
                    result = purchaseService.update(purchase);

                }

            }
        }else result = -1L;
        //result = -1 说明库存不足以退货
        return result;
    }

    @Override
    public Long update(ReturnNote bean)
    {
        Long result = 0L;
        Stock stockBean = stockDao.loadByCargoId((long)bean.getCargoId());
        int num = stockBean.getNumber() - bean.getNumber();
        if(num >= 0){
            result = returnNoteDao.update(bean);
            if(result > 0L){
                stockBean.setNumber(num);
                result =stockDao.update(stockBean);
            }
        }else result = -1L;
        //result = -1 说明库存不足以退货
        return result;
    }

    @Override
    public Long delete(Long id) {
        Long result = 0L;
        Long result1 = 0L;
        ReturnNote reBean = returnNoteDao.load(id);
        Stock stockBean = stockDao.loadByCargoId((long)reBean.getCargoId());
        Purchase purchaseBean = purchaseDao.load((long)reBean.getPurId());

        int num = stockBean.getNumber() + reBean.getNumber();
        result = returnNoteDao.delete(id);
        if(result > 0L){
            stockBean.setNumber(num);
            result =stockDao.update(stockBean);
            purchaseBean.setStatus(0);
            result1 = purchaseDao.update(purchaseBean);
        }
        return result;
    }

    @Override
    public ReturnNote load(Long id) {
        return returnNoteDao.load(id);
    }

    @Override
    public ReturnNote loadByName(String name) {
        return returnNoteDao.loadByName(name);
    }

    @Override
    public Long count() {
        return returnNoteDao.count();
    }

    @Override
    public List<ReturnNote> pager(Long pageNum, Long pageSize) {
        return returnNoteDao.pager(pageNum,pageSize);
    }

    @Override
    public Long countByName(String name) {
        return returnNoteDao.countByName(name);
    }

    @Override
    public List<ReturnNote> pagerByName(String name, Long pageNum, Long pageSize) {
        return returnNoteDao.pagerByName(name,pageNum,pageSize);
    }

    @Override
    public List<ReturnNote> pagerByPurId(String name, Long pageNum, Long pageSize) {
        return returnNoteDao.pagerByPurId(name,pageNum,pageSize);
    }
}
