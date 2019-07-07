package dao;

import bean.CusContact;

import java.util.List;

public interface CusContactDao {
    Long insert(CusContact bean);
    Long delete(Long id);
    Long update(CusContact bean);
    List<CusContact> list();

    CusContact load(Long id);
    CusContact loadByName(String name);

    Long count();
    List<CusContact> pager(Long pagerNum, Long pageSize);

    Long countByName(String name);

    List<CusContact> pagerByName(String name, Long pageNum, Long pageSize);

}
