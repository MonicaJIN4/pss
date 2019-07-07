package dao.impl;

import bean.SaleOut;
import com.liuvei.common.DbFun;
import dao.SaleOutDao;
import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleOutDaoImpl implements SaleOutDao {


    @Override
    public List<SaleOut> list() {
        List<SaleOut> list = new ArrayList<SaleOut>();

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from SaleOut");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try
        {
            conn = DbUtil.getConn();
            rs = DbFun.query(conn, sql, params);
            while (rs.next()) {
                list.add(toBean(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            DbUtil.close(conn);
        }
        return list;
    }

    @Override
    public Long insert(SaleOut bean) {
        Long result = 0L;

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into SaleOut");
        sbSQL.append(" (");
        sbSQL.append(" saleId ,cargoId ,number ,price");
        sbSQL.append(" ,staffId ,cusId ,outDate ,total");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?,?,? ,?,?,?,?)");

        // 2)添加参数
        paramsList.add(bean.getSaleId());
        paramsList.add(bean.getCargoId());
        paramsList.add(bean.getNumber());
        paramsList.add(bean.getPrice());

        paramsList.add(bean.getStaffId());
        paramsList.add(bean.getCusId());
        paramsList.add(bean.getOutDate());
        paramsList.add(bean.getTotal());

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
                result = DbFun.queryScalarLong(conn, sql);//?
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
    public Long update(SaleOut bean) {
        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update SaleOut ");
        sbSQL.append(" set cargoId=? ,number=? ,price=?");
        sbSQL.append(" ,staffId=? ,cusId=? ,outDate=?,status=?,total=?");
        sbSQL.append(" where saleId=?");

        // 2) 添加参数
        paramsList.add(bean.getCargoId());
        paramsList.add(bean.getNumber());
        paramsList.add(bean.getPrice());

        paramsList.add(bean.getStaffId());
        paramsList.add(bean.getCusId());
        paramsList.add(bean.getOutDate());
        paramsList.add(bean.getStatus());
        paramsList.add(bean.getTotal());

        paramsList.add(bean.getSaleId());

        // 3)转换类型
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
    public Long delete(Long id) {
        Long result = 0L;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" delete from SaleOut");
        sbSQL.append(" where saleId=?");

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
    public SaleOut load(Long id) {

        SaleOut bean = null;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select * from SaleOut S");
        sbSQL.append(" left join staff on S.staffId=staff.staffId ");
        sbSQL.append(" left join cargo C on S.cargoId=C.cargoId");
        sbSQL.append("  left join cuscontact U on S.cusId=U.cusId");
        sbSQL.append(" where S.saleId=?");

        // 2) 添加参数
        paramsList.add(id);

        // 3) 转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();
        Connection conn = null;
        ResultSet rs =null;

        try {
            // 4) 取得连接对象
            conn = DbUtil.getConn();

            // 5) 执行sql
            rs = DbFun.query(conn, sql, params);

            // 6)单行转为对象
            if(rs.next()){
                bean = toBean(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }

        return bean;
    }

    @Override
    public SaleOut loadByName(String name) {
        SaleOut bean = null;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select * from SaleOut");
        sbSQL.append(" where cargoId=?");
        sbSQL.append(" order by saleId asc");

        // 2) 添加参数
        paramsList.add(name);

        // 3) 转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();
        Connection conn = null;
        ResultSet rs =null;

        try {
            // 4) 取得连接对象
            conn = DbUtil.getConn();

            // 5) 执行sql
            rs = DbFun.query(conn, sql, params);

            // 6)单行转为对象
            if(rs.next()){
                bean = toBean(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 9) 关闭连接
            DbUtil.close(conn);
        }

        return bean;
    }

    @Override
    public Long count() {
        Long result = 0L;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select count(1) from SaleOut");


        // 3) 转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try {
            // 4) 取得连接对象
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

    @Override
    public List<SaleOut> pager(Long pageNum, Long pageSize) {

        List<SaleOut> list = new ArrayList<SaleOut>();

        // 0）定义变量
        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select * from SaleOut S");
        sbSQL.append(" left join staff on S.staffId=staff.staffId ");
        sbSQL.append(" left join cargo C on S.cargoId=C.cargoId");
        sbSQL.append("  left join cuscontact U on S.cusId=U.cusId");
        sbSQL.append(" order by S.saleId asc");

        // 分页
        if(pageNum < 1) {
            pageNum = 1L;
        }
        if (pageSize < 1){
            pageSize = 10L;
        }
        Long startIndex = (pageNum -1)*pageSize;
        sbSQL.append(" limit " + startIndex + "," + pageSize);

        // 3)转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try
        {
            // 4)取得连接对象
            conn = DbUtil.getConn();

            // 5）执行sql
            rs = DbFun.query(conn, sql, params);

            // 6）多行转为对象列表
            while (rs.next()) {
                list.add(toBean(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
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

        // 1) 组合SQL
        sbSQL.append(" select count(1) from SaleOut S");
        sbSQL.append(" left join cargo C on S.cargoId=C.cargoId");
        sbSQL.append(" where C.cargoName like ? ");

        // 2）添加参数
        name = "%" + name + "%";
        paramsList.add(name);

        // 3) 转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        try {
            // 4) 取得连接对象
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

    @Override
    public List<SaleOut> pagerByName(String name, Long pageNum, Long pageSize) {
        List<SaleOut> list = new ArrayList<SaleOut>();

        // 0）定义变量
        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select * from SaleOut S");
        sbSQL.append(" left join staff on S.staffId=staff.staffId ");
        sbSQL.append(" left join cargo C on S.cargoId=C.cargoId");
        sbSQL.append("  left join cuscontact U on S.cusId=U.cusId");
        sbSQL.append(" where C.cargoName like ? ");
        sbSQL.append(" order by S.saleId asc");


        // 分页
        if(pageNum < 1) {
            pageNum = 1L;
        }
        if (pageSize < 1){
            pageSize = 10L;
        }
        Long startIndex = (pageNum -1)*pageSize;
        sbSQL.append(" limit " + startIndex + "," + pageSize);

        // 2) 添加参数
        name = "%" + name + "%";
        paramsList.add(name);

        // 3)转换类型
        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try
        {
            // 4)取得连接对象
            conn = DbUtil.getConn();

            // 5）执行sql
            rs = DbFun.query(conn, sql, params);

            // 6）多行转为对象列表
            while (rs.next()) {
                list.add(toBean(rs));
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            // 9) 关闭连接
            DbUtil.close(conn);
        }
        return list;
    }

    private SaleOut toBean(ResultSet rs){
        SaleOut bean = new SaleOut();
        try{
            bean.setSaleId(rs.getInt("saleId"));
            bean.setCargoId(rs.getInt("cargoId"));
            bean.setNumber(rs.getInt("number"));
            bean.setPrice(rs.getBigDecimal("price"));
            bean.setStaffId(rs.getInt("staffId"));
            bean.setCusId(rs.getInt("cusId"));
            bean.setOutDate(rs.getDate("outDate"));
            bean.setUnit(rs.getString("unit"));
            bean.setTotal(rs.getBigDecimal("total"));

            bean.setcName(rs.getString("cName"));
            bean.setCargoName(rs.getString("cargoName"));
            bean.setCusName(rs.getString("cusName"));
            bean.setStatus(rs.getInt("status"));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;

    }


}
