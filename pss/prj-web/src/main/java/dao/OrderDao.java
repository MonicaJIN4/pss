package dao;

import bean.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao {
//    Long findByTime(String orderTime);
    Long insert(Order bean);
    Long delete(Long id);
    Long update(Order bean);
    List<Order> list();

    Order load(Long id);
    Order loadByName(String name);


    Long countByTime(Date startDate, Date endDate);
    List<Order> pagerByTime(Date startDate, Date endDate, Long pageNum, Long pageSize);

    Long count();
    List<Order> pager(Long pagerNum, Long pageSize);

    Long countByName(String name);

    List<Order> pagerByName(String name, Long pageNum, Long pageSize);
}
