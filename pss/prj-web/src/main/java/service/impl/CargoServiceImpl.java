package service.impl;

import bean.Cargo;
import dao.CargoDao;
import dao.impl.CargoDaoImpl;
import service.CargoService;

import java.util.List;

public class CargoServiceImpl implements CargoService {
    private CargoDao cargoDao = new CargoDaoImpl();
    @Override
    public Long insert(Cargo bean) {

        return cargoDao.insert(bean);



    }

    @Override
    public Long delete(Long id) {
        return cargoDao.delete(id);
    }

    @Override
    public Long update(Cargo bean) {
        return cargoDao.update(bean);
    }

    @Override
    public List<Cargo> list() {
        return cargoDao.list();
    }

    @Override
    public Cargo load(Long id) {
        return cargoDao.load(id);
    }

    @Override
    public Cargo loadByName(String name) {
        return cargoDao.loadByName(name);
    }

    @Override
    public Long count() {
        return cargoDao.count();
    }

    @Override
    public List<Cargo> pager(Long pagerNum, Long pageSize) {
        return cargoDao.pager(pagerNum,pageSize);
    }

    @Override
    public Long countByName(String name) {
        return cargoDao.countByName(name);
    }

    @Override
    public List<Cargo> pagerByName(String name, Long pageNum, Long pageSize) {
        return cargoDao.pagerByName(name,pageNum,pageSize);
    }
}
