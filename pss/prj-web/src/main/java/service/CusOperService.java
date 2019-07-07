package service;

import bean.CusOper;

import java.util.List;

public interface CusOperService {
    Long insert(CusOper bean);
    Long delete(Long id);
    Long update(CusOper bean);
    List<CusOper> list();

    CusOper load(Long id);
    CusOper loadByName(String name);

    Long count();
    List<CusOper> pager(Long pagerNum, Long pageSize);

    Long countByName(String name);

    List<CusOper> pagerByName(String name, Long pageNum, Long pageSize);
}
