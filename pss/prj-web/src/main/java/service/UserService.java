package service;

import java.util.*;

import  bean.User;

public interface UserService {
    Long insert(User bean);
    Long delete(Long id);
    Long update(User bean);
    Long count();
    User load(Long id);
    List<User> list();
    User loadByName(String name);
    Long countByName(String name);
    List<User> pager(Long pageNum, Long pageSize);
    List<User> pagerByName(String name, Long pageNum, Long pageSize);

    Long insertRegister(User bean);
}
