package service.impl;


import java.util.*;

import  bean.User;
import  dao.UserDao;
import  service.UserService;
import  dao.impl.*;

public class UserServiceImpl implements UserService {

    private UserDao UserDao = new UserDaoImpl();
    @Override
    public Long insert(User bean) {
        // TODO Auto-generated method stub
        Long result = 0L;
        User user = new User();
        user =UserDao.loadByName(bean.getUserName());
        if (user != null) return -1L;
        return UserDao.insert(bean);
    }

    @Override
    public Long delete(Long id) {
        return UserDao.delete(id);
    }

    @Override
    public Long update(User bean) {
        return UserDao.update(bean);
    }

    @Override
    public Long count() {
        return UserDao.count();
    }

    @Override
    public User load(Long id) {
        return UserDao.load(id);
    }

    @Override
    public List<User> list() {
        return UserDao.list();
    }

    @Override
    public User loadByName(String name) {
        return UserDao.loadByName(name);
    }

    @Override
    public Long countByName(String name) {
        return UserDao.countByName(name);
    }

    @Override
    public List<User> pager(Long pageNum, Long pageSize) {
        return UserDao.pager(pageNum, pageSize);
    }

    @Override
    public List<User> pagerByName(String name, Long pageNum, Long pageSize) {
        return UserDao.pagerByName(name, pageNum, pageSize);
    }

    @Override
    public Long insertRegister(User bean) {
        Long result = 0L;
        User user = new User();
        user =UserDao.loadByName(bean.getUserName());
        if (user != null) return -1L;
        return UserDao.insertRegister(bean);
    }

}
