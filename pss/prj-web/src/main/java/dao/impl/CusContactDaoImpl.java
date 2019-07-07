package dao.impl;

import bean.CusContact;
import com.liuvei.common.DbFun;
import dao.CusContactDao;
import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CusContactDaoImpl implements CusContactDao {
    @Override
    public Long insert(CusContact bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into cuscontact");
        sbSQL.append(" (");
        sbSQL.append(" cusId,cusName,cusTel");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?,?)");

        // 2)添加参数
        paramsList.add(bean.getCusId());
        paramsList.add(bean.getCusName());
        paramsList.add(bean.getCusTel());

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
        sbSQL.append(" delete from cuscontact");
        sbSQL.append(" where cusId=?");

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
    public Long update(CusContact bean) {
        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update cuscontact ");
        sbSQL.append(" set cusName=?, cusTel=?");
        sbSQL.append(" where cusId=?");

        // 2) 添加参数
        paramsList.add(bean.getCusName());
        paramsList.add(bean.getCusTel());
        paramsList.add(bean.getCusId());


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
    public List<CusContact> list() {
        List<CusContact> list = new ArrayList<>();

        StringBuffer sbSQL= new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append("select * from cuscontact");

        String sql=sbSQL.toString();
        Object[] params;
        params = paramsList.toArray();

        Connection conn=null;
        ResultSet rs=null;
        try{
            conn= DbUtil.getConn();

            rs= DbFun.query(conn,sql,params);
            while(rs.next()){
                list.add(toBean(rs));
            }
        }catch (Exception e){
            e.printStackTrace();;
            throw  new RuntimeException(e);

        }finally {
            DbUtil.close(conn);
        }
        return list;
    }

    @Override
    public CusContact load(Long id) {
        CusContact bean=null;

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList =new ArrayList<Object>();

        sbSQL.append("select * from cuscontact");
        sbSQL.append(" where cusId=?");

        paramsList.add(id);

        String sql=sbSQL.toString();
        Object[] params;
        params = paramsList.toArray();

        Connection conn=null;
        ResultSet rs=null;
        try{
            conn= DbUtil.getConn();

            rs= DbFun.query(conn,sql,params);
            while(rs.next()){
                bean=toBean(rs);
            }
        }catch (Exception e){
            e.printStackTrace();;
            throw  new RuntimeException(e);

        }finally {
            DbUtil.close(conn);
        }
        return bean;
    }

    @Override
    public CusContact loadByName(String name) {
        CusContact bean=null;

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList =new ArrayList<Object>();

        sbSQL.append("select * from cuscontact");
        sbSQL.append(" where cusName=?");
        sbSQL.append(" order by cusId asc");

        paramsList.add(name);

        String sql=sbSQL.toString();
        Object[] params=paramsList.toArray();

        Connection conn=null;
        ResultSet rs=null;
        try{
            conn= DbUtil.getConn();

            rs= DbFun.query(conn,sql,params);
            while(rs.next()){
                bean=toBean(rs);
            }
        }catch (Exception e){
            e.printStackTrace();;
            throw  new RuntimeException(e);

        }finally {
            DbUtil.close(conn);
        }
        return bean;
    }

    @Override
    public Long count() {
        Long result = 0L;
        StringBuffer sbSQL =new StringBuffer();
        List<Object> paramsList = new ArrayList<>();

        sbSQL.append(" select count(1) from cuscontact");

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
    public List<CusContact> pager(Long pagerNum, Long pageSize) {
        List<CusContact>list =new ArrayList<CusContact>();

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" select * from cuscontact");
        sbSQL.append(" order by cusId asc");

        if (pagerNum <1){
            pagerNum=1L;
        }
        if (pageSize<1){
            pageSize =10L;

        }
        Long startIndex=(pagerNum-1)*pageSize;
        sbSQL.append(" limit "+startIndex +","+pageSize);

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
    public Long countByName(String name) {
        Long result = 0L;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select count(1) from cuscontact");
        sbSQL.append(" where cusName like ?");

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
    public List<CusContact> pagerByName(String name, Long pageNum, Long pageSize) {
        List<CusContact> list = new ArrayList<CusContact>();

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select * from cuscontact");
        sbSQL.append(" where cusName like ?");
        sbSQL.append(" order by cusId asc");

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
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }

        return list;
    }

    private CusContact toBean(ResultSet rs) {
        CusContact bean = new CusContact();

        try {
            bean.setCusId(rs.getInt("cusId"));
            bean.setCusName(rs.getString("cusName"));
           bean.setCusTel(rs.getString("cusTel"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;
    }
}
