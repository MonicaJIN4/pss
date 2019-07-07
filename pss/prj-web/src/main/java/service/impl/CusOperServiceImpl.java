package service.impl;

import bean.CusOper;
import dao.CusOperDao;
import dao.impl.CusOperDaoImpl;
import service.CusOperService;

import java.util.List;

public class CusOperServiceImpl implements CusOperService {

    private CusOperDao cusOperDao= new CusOperDaoImpl();
    @Override
    public Long insert(CusOper bean) {
        return cusOperDao.insert(bean);
    }

    @Override
    public Long delete(Long id) {
        return cusOperDao.delete(id);
    }

    @Override
    public Long update(CusOper bean) {
        return cusOperDao.update(bean);
    }

    @Override
    public List<CusOper> list() {
        return cusOperDao.list();
    }

    @Override
    public CusOper load(Long id) {
        return cusOperDao.load(id);
    }

    @Override
    public CusOper loadByName(String name) {
        return cusOperDao.loadByName(name);
    }

    @Override
    public Long count() {
        return cusOperDao.count();
    }

    @Override
    public List<CusOper> pager(Long pagerNum, Long pageSize) {
        return cusOperDao.pager(pagerNum,pageSize);
    }

    @Override
    public Long countByName(String name) {
        return cusOperDao.countByName(name);
    }

    @Override
    public List<CusOper> pagerByName(String name, Long pageNum, Long pageSize) {
        return cusOperDao.pagerByName(name,pageNum,pageSize);
    }
}
