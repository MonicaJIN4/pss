package dao.impl;

import com.liuvei.common.DbFun;
import  bean.Role;
import  dao.RoleDao;
import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    @Override
    public List<Role> list() {
        // TODO Auto-generated method stub

        List<Role> list = new ArrayList<Role>();

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select * from Role");

        // 2)添加参数

        // 3）转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try {
            // 4) 取得连接对象
            conn = DbUtil.getConn();

            // 5) 执行sql
            rs = DbFun.query(conn, sql, params);

            // 6) 多行转为对象列表
            while (rs.next()) {
                list.add(toBean(rs));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }
        return list;
    }

    private Role toBean(ResultSet rs) {
        Role bean = new Role();

        try {
            bean.setRoleId(rs.getInt("RoleId"));
            bean.setRoleName(rs.getString("RoleName"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new RuntimeException(e);
        }

        return bean;
    }
}
