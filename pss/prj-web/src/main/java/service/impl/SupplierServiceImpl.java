package service.impl;


import java.util.*;

import  bean.Supplier;
import  dao.SupplierDao;
import  service.SupplierService;
import  dao.impl.*;

public class SupplierServiceImpl implements SupplierService {

    private SupplierDao SupplierDao = new SupplierDaoImpl();
    @Override
    public Long insert(Supplier bean) {
        // TODO Auto-generated method stub
        return SupplierDao.insert(bean);
    }

    @Override
    public Long delete(Long id) {
        return SupplierDao.delete(id);
    }

    @Override
    public Long update(Supplier bean) {
        return SupplierDao.update(bean);
    }

    @Override
    public Long count() {
        return SupplierDao.count();
    }

    @Override
    public Supplier load(Long id) {
        return SupplierDao.load(id);
    }

    @Override
    public List<Supplier> list() {
        return SupplierDao.list();
    }

    @Override
    public Supplier loadByName(String name) {
        return SupplierDao.loadByName(name);
    }

    @Override
    public Long countByName(String name) {
        return SupplierDao.countByName(name);
    }

    @Override
    public List<Supplier> pager(Long pageNum, Long pageSize) {
        return SupplierDao.pager(pageNum, pageSize);
    }

    @Override
    public List<Supplier> pagerByName(String name, Long pageNum, Long pageSize) {
        return SupplierDao.pagerByName(name, pageNum, pageSize);
    }

}
