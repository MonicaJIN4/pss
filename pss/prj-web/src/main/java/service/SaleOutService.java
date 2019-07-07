package service;

import bean.SaleOut;

import java.util.List;

public interface SaleOutService {

    List<SaleOut> list();
    Long insert(SaleOut bean);
    Long update(SaleOut bean);
    Long delete(Long id);
    SaleOut load(Long id);
    SaleOut loadByName(String name);
    Long count();
    List<SaleOut> pager(Long pageNum, Long pageSize);
    Long countByName(String name);
    List<SaleOut> pagerByName(String name, Long pageNum, Long pageSize);
}
