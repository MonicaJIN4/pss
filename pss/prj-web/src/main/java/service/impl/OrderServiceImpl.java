package service.impl;

import bean.Order;
import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private dao.OrderDao OrderDao= new OrderDaoImpl();
    @Override
    public Long insert(Order bean) {
        return OrderDao.insert(bean);
    }

    @Override
    public Long delete(Long id) {
        return OrderDao.delete(id);
    }

    @Override
    public Long update(Order bean) {
        return OrderDao.update(bean);
    }

    @Override
    public List<Order> list() {
        return OrderDao.list();
    }

    @Override
    public Order load(Long id) {
        return OrderDao.load(id);
    }

    @Override
    public Order loadByName(String name) {
        return OrderDao.loadByName(name);
    }

    @Override
    public Long count() {
        return OrderDao.count();
    }

    @Override
    public List<Order> pager(Long pagerNum, Long pageSize) {
        return OrderDao.pager(pagerNum,pageSize);
    }

    @Override
    public Long countByTime(Date startDate, Date endDate) {
        return OrderDao.countByTime(startDate, endDate);
    }

    @Override
    public List<Order> pagerByTime(Date startDate, Date endDate, Long pageNum, Long pageSize) {
        return OrderDao.pagerByTime(startDate, endDate, pageNum, pageSize);
    }

    @Override
    public Long countByName(String name) {
        return OrderDao.countByName(name);
    }

    @Override
    public List<Order> pagerByName(String name, Long pageNum, Long pageSize) {
        return OrderDao.pagerByName(name,pageNum,pageSize);
    }
}
