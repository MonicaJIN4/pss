package service;

import java.util.*;

import  bean.Supplier;

public interface SupplierService {
    Long insert(Supplier bean);
    Long delete(Long id);
    Long update(Supplier bean);
    Long count();
    Supplier load(Long id);
    List<Supplier> list();
    Supplier loadByName(String name);
    Long countByName(String name);
    List<Supplier> pager(Long pageNum, Long pageSize);
    List<Supplier> pagerByName(String name, Long pageNum, Long pageSize);
}
