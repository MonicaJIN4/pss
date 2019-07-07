package service.impl;


import  dao.*;
import  dao.impl.RoleDaoImpl;
import  service.RoleService;
import  bean.Role;
import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao RoleDao = new RoleDaoImpl(); 
    @Override
    public List<Role> list() {
        return RoleDao.list();
    }
}
