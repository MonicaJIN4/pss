package dao;

import bean.Staff;

import java.util.List;

public interface StaffDao {
    List<Staff> list();
    Long insert(Staff bean);
    Long delete(Long id);
    Long update(Staff bean);

    Staff loadByStaffId(Long id);
    Staff loadByEname(String Ename);

    Long count();
    Long countByName(String Ename);

    List<Staff> pager(Long pageNum, Long pageSize);
    List<Staff> pagerByName(String name, Long pageNum, Long pageSize);
}
