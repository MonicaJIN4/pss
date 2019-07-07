package dao.impl;

import bean.CusOper;
import com.liuvei.common.DbFun;
import dao.CusOperDao;
import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CusOperDaoImpl implements CusOperDao {
    @Override
    public Long insert(CusOper bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into cusoper");
        sbSQL.append(" (");
        sbSQL.append(" operId,cusId,cusTel,");
        sbSQL.append(" cusAddr,staffId,faxNum,");
        sbSQL.append(" comName");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?,?, ?,?,?, ?)");

        // 2)添加参数
        paramsList.add(bean.getOperId());
        paramsList.add(bean.getCusId());
        paramsList.add(bean.getCusTel());

        paramsList.add(bean.getCusAddr());
        paramsList.add(bean.getStaffId());
        paramsList.add(bean.getFaxNum());

        paramsList.add(bean.getComName());

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
        sbSQL.append(" delete from cusoper");
        sbSQL.append(" where operId=?");

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
    public Long update(CusOper bean) {
        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update cusoper ");
        sbSQL.append(" set cusId=?, cusTel=?,cusAddr=?,");
        sbSQL.append(" staffId=?, faxNum=?,comName=?");
        sbSQL.append(" where operId=?");

        // 2) 添加参数
        paramsList.add(bean.getCusId());
        paramsList.add(bean.getCusTel());
        paramsList.add(bean.getCusAddr())
        ;
        paramsList.add(bean.getStaffId());
        paramsList.add(bean.getFaxNum());
        paramsList.add(bean.getComName());

        paramsList.add(bean.getOperId());

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
    public List<CusOper> list() {
        List<CusOper> list = new ArrayList<>();

        StringBuffer sbSQL= new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append("select * from cusoper");

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
    public CusOper load(Long id) {
        CusOper bean=null;

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList =new ArrayList<Object>();

        sbSQL.append("select * from cusoper C");
        sbSQL.append(" left join cuscontact U on C.cusId=U.cusId left join staff S on C.staffId=S.staffId");
        sbSQL.append(" where operId=?");

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
    public CusOper loadByName(String name) {
        CusOper bean=null;

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList =new ArrayList<Object>();

        sbSQL.append("select * from cusoper");
        sbSQL.append(" where comName=?");
        sbSQL.append(" order by operId asc");

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

        sbSQL.append(" select count(1) from cusoper");

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
    public List<CusOper> pager(Long pagerNum, Long pageSize) {
        List<CusOper>list =new ArrayList<CusOper>();

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" select * from cusoper C");
        sbSQL.append(" left join cuscontact U on C.cusId=U.cusId left join staff S on C.staffId=S.staffId");
        sbSQL.append(" order by operId asc");

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
        sbSQL.append(" select count(1) from cusoper");
        sbSQL.append(" where comName like ?");

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
    public List<CusOper> pagerByName(String name, Long pageNum, Long pageSize) {
        List<CusOper> list = new ArrayList<CusOper>();

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select * from cusoper C");
        sbSQL.append(" left join cuscontact U on C.cusId=U.cusId left join staff S on C.staffId=S.staffId");
        sbSQL.append(" where comName like ?");
        sbSQL.append(" order by operId asc");

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

    private CusOper toBean(ResultSet rs) {
        CusOper bean = new CusOper();

        try {
            bean.setOperId(rs.getInt("operId"));
            bean.setCusId(rs.getInt("cusId"));
            bean.setCusTel(rs.getString("cusTel"));
            bean.setcName(rs.getString("cName"));
            bean.setCusName(rs.getString("cusName"));

            bean.setCusAddr(rs.getString("cusAddr"));
            bean.setStaffId(rs.getInt("staffId"));
            bean.setFaxNum(rs.getString("faxNum"));
            bean.setComName(rs.getString("comName"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;
    }
}
