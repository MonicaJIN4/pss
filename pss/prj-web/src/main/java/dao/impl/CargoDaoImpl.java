package dao.impl;

import bean.Cargo;
import com.liuvei.common.DbFun;
import dao.CargoDao;
import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDaoImpl implements CargoDao {
    @Override
    public Long insert(Cargo bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into cargo");
        sbSQL.append(" (");
        sbSQL.append(" cargoId,cargoName,safetyStock,unit");
        sbSQL.append(" ,buyPrice,sellPrice");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?,?,? ,?,?)");

        // 2)添加参数
        paramsList.add(bean.getCargoId());
        paramsList.add(bean.getCargoName());
        paramsList.add(bean.getSafetyStock());
        paramsList.add(bean.getUnit());

        paramsList.add(bean.getBuyPrice());
        paramsList.add(bean.getSellPrice());


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
        sbSQL.append(" delete from Cargo");
        sbSQL.append(" where cargoId=?");

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
    public Long update(Cargo bean) {
        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update Cargo ");
        sbSQL.append(" set cargoName=?, safetyStock=?,unit=?");
        sbSQL.append(" ,buyPrice=?, sellPrice=?");
        sbSQL.append(" where cargoId=?");

        // 2) 添加参数
        paramsList.add(bean.getCargoName());
        paramsList.add(bean.getSafetyStock());
        paramsList.add((bean.getUnit()));


        paramsList.add(bean.getBuyPrice());
        paramsList.add(bean.getSellPrice());

        paramsList.add(bean.getCargoId());

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
    public List<Cargo> list() {
        List<Cargo> list = new ArrayList<>();

        StringBuffer sbSQL= new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append("select * from cargo");

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
    public Cargo load(Long id) {
        Cargo bean=null;

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList =new ArrayList<Object>();

        sbSQL.append("select * from cargo");
        sbSQL.append(" where cargoId=?");

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
    public Cargo loadByName(String name) {
        Cargo bean=null;

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList =new ArrayList<Object>();

        sbSQL.append("select * from cargo");
        sbSQL.append(" where cargoName=?");
        sbSQL.append(" order by cargoId asc");

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

        sbSQL.append(" select count(1) from Cargo");

        // 3）转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;

        try {
            // 4）取得连接对象
            conn = DbUtil.getConn();
            System.out.println(conn);
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
    public List<Cargo> pager(Long pagerNum, Long pageSize) {
        List<Cargo>list =new ArrayList<Cargo>();

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" select * from cargo");
        sbSQL.append(" order by cargoId asc");

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
        sbSQL.append(" select count(1) from cargo");
        sbSQL.append(" where cargoName like ?");

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
    public List<Cargo> pagerByName(String name, Long pageNum, Long pageSize) {
        List<Cargo> list = new ArrayList<Cargo>();

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select * from cargo");
        sbSQL.append(" where cargoName like ?");
        sbSQL.append(" order by cargoId asc");

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

    private Cargo toBean(ResultSet rs) {
        Cargo bean = new Cargo();

        try {
            bean.setCargoId(rs.getInt("cargoId"));
            bean.setCargoName(rs.getString("cargoName"));
            bean.setSafetyStock(rs.getDouble("safetyStock"));

            bean.setBuyPrice(rs.getBigDecimal("buyPrice"));
            bean.setSellPrice(rs.getBigDecimal("sellPrice"));
            bean.setUnit(rs.getString("unit"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            throw new RuntimeException(e);
        }

        return bean;
    }

}
