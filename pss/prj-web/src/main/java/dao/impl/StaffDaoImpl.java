package dao.impl;

import dao.StaffDao;
import util.DbUtil;
import bean.Staff;
import com.liuvei.common.DbFun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl implements StaffDao {

    @Override
    public List<Staff> list() {
        List<Staff> list = new ArrayList<Staff>();

        //0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        //1）组合SQL
        sbSQL.append("select * from staff");

        //2）添加参数

        //3）转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try{
            //4)取得连接对象
            conn = DbUtil.getConn();

            //5）执行sql
            rs = DbFun.query(conn,sql,params);

            //6）多行转换为对象列表
            while (rs.next()){
                list.add(toBean(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }finally {
            DbUtil.close(conn);
        }
        return list;
    }

    @Override
    public Long insert(Staff bean) {

        Long result = 0L;

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into staff");
        sbSQL.append(" (");
        sbSQL.append("staffId,cName,eName,phone");
        sbSQL.append(" ,mPhone,email,address");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?,?,?,?,?,?)");

        // 2)添加参数
        paramsList.add(bean.getStaffId());
        paramsList.add(bean.getcName());
        paramsList.add(bean.geteName());
        paramsList.add(bean.getPhone());

        paramsList.add(bean.getmPhone());
        paramsList.add(bean.getEmail());
        paramsList.add(bean.getAddress());

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
        Long result = 0L;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" delete from staff");
        sbSQL.append(" where staffId=?");

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
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }

        return result;
    }

    @Override
    public Long update(Staff bean) {
        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update staff ");
        sbSQL.append(" set cName=?, eName=?, phone=?, mPhone=?");
        sbSQL.append(" , email=?, address=?");
        sbSQL.append(" where staffId=?");

        // 2) 添加参数
        paramsList.add(bean.getcName());
        paramsList.add(bean.geteName());
        paramsList.add(bean.getPhone());

        paramsList.add(bean.getmPhone());
        paramsList.add(bean.getEmail());
        paramsList.add(bean.getAddress());

        paramsList.add(bean.getStaffId());

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
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }
        return result;
    }

    @Override
    public Staff loadByStaffId(Long id) {
        Staff bean = null;

        //0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        //1）组合SQL
        sbSQL.append("select * from staff");
        sbSQL.append(" where staffId = ?");

        //2)添加参数
        paramsList.add(id);

        //3）转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try{
            //4)取得连接对象
            conn = DbUtil.getConn();

            //5)执行sql
            rs = DbFun.query(conn ,sql,params);

            //6)单行转为对象
            if (rs.next()){
                bean = toBean(rs);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn);
        }
        return bean;
    }

    @Override
    public Staff loadByEname(String Ename) {
        Staff bean = null;

        //0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        //1)组合SQL
        sbSQL.append("select * from staff");
        sbSQL.append(" where eName = ?");
        sbSQL.append(" order by staffId asc");

        //2)添加参数
        paramsList.add(Ename);

        //3)转换类型
        String sql = sbSQL.toString();
        Object[] params =  paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try {
            //4）取得连接对象
            conn = DbUtil.getConn();

            //5）执行sql
            rs =  DbFun.query(conn,sql,params);

            //6）单行转为对象
            if (rs.next()){
                bean = toBean(rs);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn);
        }
        return bean;
    }

    @Override
    public Long count() {
        Long result = 0L;

        //0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramslist = new ArrayList<Object>();

        //1)组合sql
        sbSQL.append("select count(1) from staff");

        //2)添加参数

        //3)转换类型
        String sql = sbSQL.toString();
        Object[] params = paramslist.toArray();

        Connection conn = null;

        try{
            //4）取得连接对象
            conn = DbUtil.getConn();

            //5)执行sql
            result = DbFun.queryScalarLong(conn,sql,params);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn);
        }
        return result;
    }

    @Override
    public Long countByName(String Ename) {
        Long result = 0L;

        //0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramslist = new ArrayList<Object>();

        //1)组合sql
        sbSQL.append(" select count(1) from staff");
        sbSQL.append(" where cName like ?");

        //2)添加参数
        Ename = "%" + Ename +"%";
        paramslist.add(Ename);

        //3)转换类型
        String sql = sbSQL.toString();
        Object[] params = paramslist.toArray();

        Connection conn = null;

        try{
            //4）取得连接对象
            conn = DbUtil.getConn();

            //5)执行sql
            result = DbFun.queryScalarLong(conn,sql,params);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn);
        }
        return result;
    }

    @Override
    public List<Staff> pager(Long pageNum, Long pageSize) {
        List<Staff> list = new ArrayList<Staff>();

        //0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramslist = new ArrayList<Object>();

        //1)组合SQL
        sbSQL.append("select * from staff");
        sbSQL.append(" order by staffId asc");

        //分页
        if (pageNum <1 ){
            pageNum = 1L;
        }
        if (pageSize <1 ){
            pageSize = 10L;
        }
        Long startIndex = (pageNum - 1) *pageSize;
        sbSQL.append(" limit "+ startIndex + "," + pageSize);

        //2）添加参数

        //3）转换类型
        String sql = sbSQL.toString();
        Object[] params = paramslist.toArray();

        Connection conn = null;
        ResultSet rs = null;

        try{
            //4）取得连接对象
            conn = DbUtil.getConn();

            //5)执行sql
            rs = DbFun.query(conn,sql,params);

            //6)多行转为对象列表
            while (rs.next()){
                list.add(toBean(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            DbUtil.close(conn);
        }
        return list;
    }

    @Override
    public List<Staff> pagerByName(String name, Long pageNum, Long pageSize) {
        List<Staff> list = new ArrayList<Staff>();

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select * from staff");
        sbSQL.append(" where cName like ?");
        sbSQL.append(" order by staffId asc");

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
                list.add(toBean(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }

        return list;
    }

    private Staff toBean(ResultSet rs){
        Staff  bean = new Staff();
        try{
            bean.setStaffId(rs.getInt("staffId"));
            bean.setcName(rs.getString("cName"));
            bean.seteName(rs.getString("eName"));
            bean.setPhone(rs.getString("phone"));
            bean.setmPhone(rs.getString("mPhone"));
            bean.setEmail(rs.getString("email"));
            bean.setAddress(rs.getString("address"));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;

    }
}
