package service;

import bean.Cargo;

import java.util.List;

public interface CargoService {
    Long insert(Cargo bean);
    Long delete(Long id);
    Long update(Cargo bean);
    List<Cargo> list();

    Cargo load(Long id);
    Cargo loadByName(String name);

    Long count();
    List<Cargo> pager(Long pagerNum, Long pageSize);

    Long countByName(String name);

    List<Cargo> pagerByName(String name, Long pageNum, Long pageSize);

}
