package service;

import bean.Purchase;

import java.util.List;

public interface PurchaseService {

    List<Purchase> list();
    List<Purchase> list_Excel();
    Long insert(Purchase bean);
    Long update(Purchase bean);
    Long delete(Long id);
    Purchase load(Long id);
    Purchase loadByName(int cargoId);
    Long count();
    List<Purchase> pager(Long pageNum, Long pageSize);
    Long countByName(String name);
    List<Purchase> pagerByName(String name, Long pageNum, Long pageSize);
}
