package service.impl;

import dao.StaffDao;
import dao.impl.StaffDaoImpl;
import bean.Staff;
import service.StaffService;

import java.util.List;

public class StaffServiceImpl implements StaffService {
    private StaffDao staffDao = new StaffDaoImpl();

    @Override
    public List<Staff> list() {
        return staffDao.list();
    }

    @Override
    public Long insert(Staff bean) {
        return staffDao.insert(bean);
    }

    @Override
    public Long delete(Long id) {
        return staffDao.delete(id);
    }

    @Override
    public Long update(Staff bean) {
        return staffDao.update(bean);
    }

    @Override
    public Staff loadByStaffId(Long id) {
        return staffDao.loadByStaffId(id);
    }

    @Override
    public Staff loadByEname(String Ename) {
        return staffDao.loadByEname(Ename);
    }

    @Override
    public Long count() {
        return staffDao.count();
    }

    @Override
    public Long countByName(String Ename) {
        return staffDao.countByName(Ename);
    }

    @Override
    public List<Staff> pager(Long pageNum, Long pageSize) {
        return staffDao.pager(pageNum,pageSize);
    }

    @Override
    public List<Staff> pagerByName(String name, Long pageNum, Long pageSize) {
        return staffDao.pagerByName(name,pageNum,pageSize);
    }
}
