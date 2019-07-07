package dao;

import bean.User;

import java.util.List;

public interface UserDao {
    Long insert(User bean);
    Long insertRegister(User bean);
    Long delete(Long id);
    Long update(User bean);
    Long count();
    User load(Long id);
    List<User> list();
    User loadByName(String name);
    Long countByName(String name);
    List<User> pager(Long pageNum, Long pageSize);
    List<User> pagerByName(String name, Long pageNum, Long pageSize);
}
