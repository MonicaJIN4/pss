package service.impl;

import bean.CusContact;
import dao.CusContactDao;
import dao.impl.CusContactDaoImpl;
import service.CusContactService;

import java.util.List;

public class CusContactServiceImpl implements CusContactService {

    private CusContactDao cusContactDao= new CusContactDaoImpl();
    @Override
    public Long insert(CusContact bean) {
        return cusContactDao.insert(bean);
    }

    @Override
    public Long delete(Long id) {
        return cusContactDao.delete(id);
    }

    @Override
    public Long update(CusContact bean) {
        return cusContactDao.update(bean);
    }

    @Override
    public List<CusContact> list() {
        return cusContactDao.list();
    }

    @Override
    public CusContact load(Long id) {
        return cusContactDao.load(id);
    }

    @Override
    public CusContact loadByName(String name) {
        return cusContactDao.loadByName(name);
    }

    @Override
    public Long count() {
        return cusContactDao.count();
    }

    @Override
    public List<CusContact> pager(Long pagerNum, Long pageSize) {
        return cusContactDao.pager(pagerNum,pageSize);
    }

    @Override
    public Long countByName(String name) {
        return cusContactDao.countByName(name);
    }

    @Override
    public List<CusContact> pagerByName(String name, Long pageNum, Long pageSize) {
        return cusContactDao.pagerByName(name,pageNum,pageSize);
    }
}
