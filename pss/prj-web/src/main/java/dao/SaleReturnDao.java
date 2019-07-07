package dao;

import bean.SaleReturn;

import java.util.List;

public interface SaleReturnDao {

    List<SaleReturn> list();

    Long insert(SaleReturn bean);
    Long update(SaleReturn bean);
    Long delete(Long id);
    SaleReturn load(Long id);
    SaleReturn loadByName(String name);
    Long count();
    Long countById(String name);
    List<SaleReturn> pager(Long pageNum, Long pageSize);
    Long countByName(String name);
    List<SaleReturn> pagerByName(String name, Long pageNum, Long pageSize);
    List<SaleReturn> pagerBySaleId(String name, Long pageNum, Long pageSize);
}
