package dao.impl;

import bean.Purchase;
import com.liuvei.common.DbFun;
import dao.PurchaseDao;
import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDaoImpl implements PurchaseDao {

    @Override
    public List<Purchase> list() {
        List<Purchase> list = new ArrayList<Purchase>();

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from Purchase");

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
    public List<Purchase> list_Excel() {
        List<Purchase> list = new ArrayList<Purchase>();

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select P.purId, S.supId, S.Name supName, C.cargoId, C.cargoName, P.number, P.buyPrice, P.total, P.date from Purchase P");
        sbSQL.append(" left join Cargo C on C.cargoId = P.cargoId");
        sbSQL.append(" left join Supplier S on S.supId = P.supId");

        String sql = sbSQL.toString();
        Object[] params = paramsList.toArray();

        Connection conn = null;
        ResultSet rs = null;
        try
        {
            conn = DbUtil.getConn();
            rs = DbFun.query(conn, sql, params);
            while (rs.next()) {
                list.add(toBeanex1(rs));
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
    public Long insert(Purchase bean) {
        Long result = 0L;

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into Purchase");
        sbSQL.append(" (");
        sbSQL.append(" purId,supId");
        sbSQL.append(" ,date,total,cargoId");
        sbSQL.append(" ,number,buyPrice,amount");
        sbSQL.append(" )");
        sbSQL.append(" values(?,? ,?,?,? ,?,?,?)");

        // 2)添加参数
        paramsList.add(bean.getPurId());
        paramsList.add(bean.getSupId());

        paramsList.add(bean.getDate());
        paramsList.add(bean.getTotal());
        paramsList.add(bean.getCargoId());

        paramsList.add(bean.getNumber());
        paramsList.add(bean.getBuyPrice());
        paramsList.add(bean.getAmount());

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
    public Long update(Purchase bean) {
        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update Purchase ");
        sbSQL.append(" set supId=? ,date=?");
        sbSQL.append(" , total=? ,cargoId=? ,number=?");
        sbSQL.append(" , buyPrice=?, amount=?,status=?");
        sbSQL.append(" where purId=?");

        // 2) 添加参数


        paramsList.add(bean.getSupId());

        paramsList.add(bean.getDate());
        paramsList.add(bean.getTotal());
        paramsList.add(bean.getCargoId());

        paramsList.add(bean.getNumber());
        paramsList.add(bean.getBuyPrice());
        paramsList.add(bean.getAmount());
        paramsList.add(bean.getStatus());
        paramsList.add(bean.getPurId());


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
        sbSQL.append(" delete from Purchase");
        sbSQL.append(" where purId=?");

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
    public Purchase load(Long id) {

        Purchase bean = null;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select P.* ,C.cargoName ,C.unit ,S.Name supName from Purchase  P");
        sbSQL.append(" left join Cargo C on C.cargoId = P.cargoId");
        sbSQL.append(" left join Supplier S on S.supId = P.supId");
        sbSQL.append(" where purId=? ");


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
                bean = toBeanEx(rs);
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
    public Purchase loadByName(int cargoId) {
        Purchase bean = null;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select * from Purchase");
        sbSQL.append(" where cargoId=?");
        sbSQL.append(" order by purId asc");

        // 2) 添加参数
        paramsList.add(cargoId);

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
        sbSQL.append(" select count(1) from Purchase ");


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
    public List<Purchase> pager(Long pageNum, Long pageSize) {

        List<Purchase> list = new ArrayList<Purchase>();

        // 0）定义变量
        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select P.* ,C.cargoName ,C.unit ,S.Name supName from Purchase  P");
        sbSQL.append(" left join Cargo C on C.cargoId = P.cargoId");
        sbSQL.append(" left join Supplier S on S.supId = P.supId");
        sbSQL.append(" order by P.purId asc ");

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
                list.add(toBeanEx(rs));
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
        sbSQL.append(" select count(1) from Purchase P");
        sbSQL.append("  left join Cargo C on C.cargoId = P.cargoId");
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
    public List<Purchase> pagerByName(String name, Long pageNum, Long pageSize) {
        List<Purchase> list = new ArrayList<Purchase>();

        // 0）定义变量
        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select P.* ,C.cargoName ,C.unit ,S.Name supName from Purchase  P");
        sbSQL.append(" left join Cargo C on C.cargoId = P.cargoId");
        sbSQL.append(" left join Supplier S on S.supId = P.supId");
        sbSQL.append(" where C.cargoName like ? ");
        sbSQL.append(" order by P.purId asc ");



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
                list.add(toBeanEx(rs));
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

    private Purchase toBean(ResultSet rs){
        Purchase bean = new Purchase();
        try{
            bean.setPurId(rs.getInt("purId"));
            bean.setSupId(rs.getInt("supId"));
            bean.setDate(rs.getDate("date"));
            bean.setTotal(rs.getBigDecimal("total"));
            bean.setCargoId(rs.getInt("cargoId"));
            bean.setNumber(rs.getInt("number"));
            bean.setBuyPrice(rs.getBigDecimal("buyPrice"));
            bean.setAmount(rs.getBigDecimal("amount"));
            bean.setStatus(rs.getInt("status"));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;

    }

    private Purchase toBeanEx(ResultSet rs){
        Purchase bean = new Purchase();
        try{
            bean.setPurId(rs.getInt("purId"));
            bean.setSupId(rs.getInt("supId"));
            bean.setSupName(rs.getString("supName"));
            bean.setDate(rs.getDate("date"));
            bean.setTotal(rs.getBigDecimal("total"));
            bean.setCargoId(rs.getInt("cargoId"));
            bean.setCargoName(rs.getString("cargoName"));
            bean.setNumber(rs.getInt("number"));
            bean.setUnit(rs.getString("unit"));
            bean.setBuyPrice(rs.getBigDecimal("buyPrice"));
            bean.setAmount(rs.getBigDecimal("amount"));
            bean.setStatus(rs.getInt("status"));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;

    }
    private Purchase toBeanex1(ResultSet rs){
        Purchase bean = new Purchase();
        try{
            bean.setPurId(rs.getInt("purId"));
            bean.setSupId(rs.getInt("supId"));
            bean.setSupName(rs.getString("supName"));
            bean.setCargoId(rs.getInt("cargoId"));
            bean.setCargoName(rs.getString("cargoName"));
            bean.setDate(rs.getDate("date"));
            bean.setTotal(rs.getBigDecimal("total"));
            bean.setNumber(rs.getInt("number"));
            bean.setBuyPrice(rs.getBigDecimal("buyPrice"));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;

    }

}
