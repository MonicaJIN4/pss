package dao.impl;

import bean.SaleReturn;
import com.liuvei.common.DbFun;
import dao.SaleReturnDao;
import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleReturnDaoImpl implements SaleReturnDao {


    @Override
    public List<SaleReturn> list() {
        List<SaleReturn> list = new ArrayList<SaleReturn>();

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from SaleReturn");

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
    public Long insert(SaleReturn bean) {
        Long result = 0L;

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into SaleReturn");
        sbSQL.append(" (");
        sbSQL.append(" reSaleId ,saleId ,cargoId");
        sbSQL.append(" ,number ,price ,remark ,returnDate ,total");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?,? ,?,?,?,?,?)");

        // 2)添加参数
        paramsList.add(bean.getReSaleId());
        paramsList.add(bean.getSaleId());
        paramsList.add(bean.getCargoId());
        paramsList.add(bean.getNumber());
        paramsList.add(bean.getPrice());
        paramsList.add(bean.getRemark());
        paramsList.add(bean.getReturnDate());
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
    public Long update(SaleReturn bean) {
        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update SaleReturn ");
        sbSQL.append(" set saleId=? ,cargoId=?");
        sbSQL.append(" ,number=? ,price=? ,remark=? ,returnDate=? ,total=?");
        sbSQL.append(" where reSaleId=?");

        // 2) 添加参数
        paramsList.add(bean.getSaleId());
        paramsList.add(bean.getCargoId());

        paramsList.add(bean.getNumber());
        paramsList.add(bean.getPrice());
        paramsList.add(bean.getRemark());
        paramsList.add(bean.getReturnDate());
        paramsList.add(bean.getTotal());

        paramsList.add(bean.getReSaleId());

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
        sbSQL.append(" delete from SaleReturn");
        sbSQL.append(" where reSaleId=?");

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
    public SaleReturn load(Long id) {

        SaleReturn bean = null;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select S.*,C.cargoName,C.unit from SaleReturn S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where S.reSaleId like ? ");
        sbSQL.append(" order by reSaleId asc");



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
    public SaleReturn loadByName(String name) {
        SaleReturn bean = null;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select S.*,C.cargoName,C.unit from SaleReturn S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where S.cargoId like ? ");
        sbSQL.append(" order by reSaleId asc");


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
        sbSQL.append(" select count(1) from SaleReturn");


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
    public Long countById(String name) {
        Long result = 0L;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select count(1) from SaleReturn");
        sbSQL.append(" where saleId like ? ");

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
    public List<SaleReturn> pager(Long pageNum, Long pageSize) {

        List<SaleReturn> list = new ArrayList<SaleReturn>();

        // 0）定义变量
        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select S.*,C.cargoName,C.unit from SaleReturn S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" order by reSaleId asc");

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
        sbSQL.append(" select count(1) from SaleReturn S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
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
    public List<SaleReturn> pagerByName(String name, Long pageNum, Long pageSize) {
        List<SaleReturn> list = new ArrayList<SaleReturn>();

        // 0）定义变量
        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select S.*,C.cargoName,C.unit from SaleReturn S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where C.cargoName like ? ");
        sbSQL.append(" order by reSaleId asc");

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

    @Override
    public List<SaleReturn> pagerBySaleId(String name, Long pageNum, Long pageSize) {
        List<SaleReturn> list = new ArrayList<SaleReturn>();

        // 0）定义变量
        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select S.*,C.cargoName,C.unit from SaleReturn S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where S.saleId like ? ");
        sbSQL.append(" order by reSaleId asc");

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

    private SaleReturn toBean(ResultSet rs){
        SaleReturn bean = new SaleReturn();
        try{

            bean.setSaleId(rs.getInt("saleId"));
            bean.setReSaleId(rs.getInt("reSaleId"));
            bean.setCargoId(rs.getInt("cargoId"));
            bean.setNumber(rs.getInt("number"));
            bean.setPrice(rs.getBigDecimal("price"));
            bean.setRemark(rs.getString("remark"));
            bean.setReturnDate(rs.getDate("returnDate"));

            //cargo表传来的数据
            bean.setCargoName(rs.getString("cargoName"));
            bean.setUnit(rs.getString("unit"));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;

    }


}
