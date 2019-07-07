package dao.impl;

import com.liuvei.common.DbFun;
import  bean.User;
import  dao.UserDao;
import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public Long insert(User bean) {
        // TODO Auto-generated method stub
        Long result = 0L;

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into User");
        sbSQL.append(" (");
        sbSQL.append(" userId,userName");
        sbSQL.append(" ,userPass,roleId");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?,?,? )");

        // 2)添加参数
        paramsList.add(bean.getUserId());
        paramsList.add(bean.getUserName());


        paramsList.add(bean.getUserPass());
        paramsList.add(bean.getRoleId());
        // 3)转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try {
            // 4)取得连接对象
            conn = DbUtil.getConn();

            // 5)执行SQL
            Long num = DbFun.update(conn, sql, params);

            // 6)结果处理
            if (num > 0) {
                sql = "Select @@identity";
                result = DbFun.queryScalarLong(conn, sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9)关闭连接
            DbUtil.close(conn);
        }
        return result;
    }


    @Override
    public Long insertRegister(User bean) {
        // TODO Auto-generated method stub
        Long result = 0L;

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into User");
        sbSQL.append(" (");
        sbSQL.append(" userName,userPass");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?)");

        // 2)添加参数

        paramsList.add(bean.getUserName());
        paramsList.add(bean.getUserPass());

        // 3)转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try {
            // 4)取得连接对象
            conn = DbUtil.getConn();

            // 5)执行SQL
            Long num = DbFun.update(conn, sql, params);

            // 6)结果处理
            if (num > 0) {
                sql = "Select @@identity";
                result = DbFun.queryScalarLong(conn, sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9)关闭连接
            DbUtil.close(conn);
        }
        return result;
    }

    @Override
    public Long delete(Long id) {
        // TODO Auto-generated method stub

        Long result = 0L;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" delete from User");
        sbSQL.append(" where userId=?");

        // 2) 添加参数
        paramsList.add(id);

        // 3) 转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try {
            // 4) 取得连接对象
            conn = DbUtil.getConn();

            // 5) 执行sql
            result = DbFun.update(conn, sql, params);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }

        return result;
    }

    @Override
    public Long update(User bean) {
        // TODO Auto-generated method stub

        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update User");
        sbSQL.append(" set userName=?, userPass=?, roleId=?");
        sbSQL.append(" where userId=?");

        // 2) 添加参数

        paramsList.add(bean.getUserName());
        paramsList.add(bean.getUserPass());
        paramsList.add(bean.getRoleId());
        paramsList.add(bean.getUserId());
        

        // 3)转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try {
            // 4)取得连接对象
            conn = DbUtil.getConn();

            // 5)执行SQL
            result = DbFun.update(conn, sql, params);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }
        return result;
    }

    @Override
    public Long count() {

        Long result = 0L;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select count(1) From User");

        // 2)添加参数

        // 3）转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;

        try {
            // 4）取得连接对象
            conn = DbUtil.getConn();

            // 5) 执行sql
            result = DbFun.queryScalarLong(conn, sql, params);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }
        return result;
    }

    // @SuppressWarnings("null")
    @Override
    public User load(Long id) {

        User bean = null;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select user.*,role.roleName from user");
        sbSQL.append(" left join role on (user.roleId=role.roleId)");
        sbSQL.append(" where user.userId=?");

        // 2) 添加参数
        paramsList.add(id);

        // 3) 转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try {
            // 4) 取得连接对象
            conn = DbUtil.getConn();

            // 5) 执行sql
            rs = DbFun.query(conn, sql, params);

            // 6) 单行转为对象
            if (rs.next()) {
                bean = toBeanEx(rs);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }
        return bean;
    }

    @Override
    public List<User> list() {
        List<User> list = new ArrayList<User>();

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select * from User");

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

    @Override
    public User loadByName(String name) {

        User bean = null;

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select * from User U");
        sbSQL.append(" left join Role R on R.roleId = U.roleId");
        sbSQL.append(" where U.userName = ?");
        sbSQL.append(" order by U.userId asc");

        // 2)添加参数
        //name = "%" + name + "%";
        paramsList.add(name);

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

            // 6) 单行转为对象
            if (rs.next()) {
                bean = toBeanEx(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9）关闭连接
            DbUtil.close(conn);
        }
        return bean;
    }

    @Override
    public Long countByName(String name) {

        Long result = 0L;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select count(1) from User");
        sbSQL.append(" where userName like ?");

        // 2）添加参数
        name = "%" + name + "%";
        paramsList.add(name);

        // 3）转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;

        try {
            // 4) 取得连接对象
            conn = DbUtil.getConn();

            // 5) 执行sql
            result = DbFun.queryScalarLong(conn, sql, params);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }
        return result;
    }
    
    @Override
    public List<User> pager(Long pageNum, Long pageSize) {
        // TODO Auto-generated method stub

        List<User> list = new ArrayList<User>();

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" select user.*,role.roleName from user");
        sbSQL.append(" left join role on (user.roleId=role.roleId)");
        sbSQL.append(" order by user.userId asc");

        // 分页
        if (pageNum < 1) {
            pageNum = 1L;
        }
        if (pageSize < 1) {
            pageSize = 10L;
        }
        Long startIndex = (pageNum - 1) * pageSize;
        sbSQL.append(" limit " + startIndex + "," + pageSize);

        // 2) 添加参数

        // 3) 转换类型
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
                list.add(toBeanEx(rs));
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

    @Override
    public List<User> pagerByName(String name, Long pageNum, Long pageSize) {
        // TODO Auto-generated method stub

        List<User> list = new ArrayList<User>();

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select user.*,role.roleName from user");
        sbSQL.append(" left join role on (user.roleId=role.roleId)");
        sbSQL.append(" where user.userName like ?");
        sbSQL.append(" order by user.userId asc");

        // 分页
        if (pageNum < 1) {
            pageNum = 1L;
        }
        if (pageSize < 1) {
            pageSize = 10L;
        }
        Long startIndex = (pageNum - 1) * pageSize;
        sbSQL.append(" limit " + startIndex + "," + pageSize);

        // 2) 添加参数
        name = "%" + name + "%";
        paramsList.add(name);

        // 3) 转换类型
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
                list.add(toBeanEx(rs));
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

    private User toBean(ResultSet rs) {
        User bean = new User();

        try {
            bean.setUserId(rs.getInt("userId"));
            bean.setUserName(rs.getString("userName"));
            bean.setUserPass(rs.getString("userPass"));
            bean.setRoleId(rs.getInt("roleId"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new RuntimeException(e);
        }

        return bean;
    }

    private User toBeanEx(ResultSet rs) {
        User bean = new User();

        try {
            bean.setUserId(rs.getInt("userId"));
            bean.setUserName(rs.getString("userName"));
            bean.setUserPass(rs.getString("userPass"));
            bean.setRoleId(rs.getInt("roleId"));
            bean.setRoleName(rs.getString("roleName"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new RuntimeException(e);
        }

        return bean;
    }

}
