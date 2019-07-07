package dao;

import bean.Supplier;

import java.util.List;

public interface SupplierDao {
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
