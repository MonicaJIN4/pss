package dao.impl;


import dao.StockDao;
import service.CargoService;
import util.DbUtil;
import bean.Stock;
import com.liuvei.common.DbFun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDaoImpl implements StockDao {

    @Override
    public List<Stock> list() {
        List<Stock> list = new ArrayList<Stock>();

        //0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        //1）组合SQL
        sbSQL.append("select * from stock");

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
    public List<Stock> listBySafe() {
        List<Stock> list = new ArrayList<Stock>();

        //0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        //1）组合SQL
        sbSQL.append(" select S.stockId,C.cargoName,S.number,C.safetyStock,C.unit from stock S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where S.Number < C.SafetyStock");
        sbSQL.append(" order by S.stockId ASC");


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
                list.add(toBean1(rs));
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
    public Long insert(Stock bean) {
        Long result = 0L;

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into stock");
        sbSQL.append(" (");
        sbSQL.append("stockId,number,cargoId,buyDate,sellDate");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?,?,?,?)");

        // 2)添加参数
        paramsList.add(bean.getStockId());
        paramsList.add(bean.getNumber());
        paramsList.add(bean.getCargoId());

        paramsList.add(bean.getBuyDate());
        paramsList.add(bean.getSellDate());

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
        sbSQL.append(" delete from stock");
        sbSQL.append(" where stockId=?");

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
    public Long update(Stock bean) {
        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update stock ");
        sbSQL.append(" set number=?, cargoId=?, buyDate=?, sellDate=?");
        sbSQL.append(" where stockId=?");

        // 2) 添加参数
        paramsList.add(bean.getNumber());
        paramsList.add(bean.getCargoId());
        paramsList.add(bean.getBuyDate());
        paramsList.add(bean.getSellDate());

        paramsList.add(bean.getStockId());


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
    public Stock loadByStockId(Long id) {
        Stock bean = null;

        //0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        //1）组合SQL
        sbSQL.append(" select S.*,C.cargoName,C.safetyStock,C.unit from stock S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where S.stockId = ?");

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
    public Stock loadByCargoId(Long id) {
        Stock bean = null;

        //0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        //1）组合SQL
        sbSQL.append(" select S.*,C.cargoName,C.safetyStock,C.unit from stock S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where C.cargoId = ?");
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
    public Long count() {
        Long result = 0L;

        //0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramslist = new ArrayList<Object>();

        //1)组合sql
        sbSQL.append("select count(1) from stock");

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
    public Long countByName(String name) {
        Long result = 0L;

        //0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramslist = new ArrayList<Object>();

        //1)组合sql
        sbSQL.append("select count(1) from stock S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where C.cargoName like ?");
        sbSQL.append(" order by S.stockId ASC");

        //2)添加参数
        name = "%" + name + "%";
        paramslist.add(name);

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
    public List<Stock> pager(Long pageNum, Long pageSize) {
        List<Stock> list = new ArrayList<Stock>();

        //0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramslist = new ArrayList<Object>();

        //1)组合SQL
        sbSQL.append(" select S.*,C.cargoName,C.safetyStock,C.unit from stock S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" order by S.stockId ASC");

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
    public List<Stock> pagerByName(String name, Long pageNum, Long pageSize) {
        List<Stock> list = new ArrayList<Stock>();

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();


        // 1) 组合SQL
        sbSQL.append(" select S.*,C.cargoName,C.safetyStock,C.unit from stock S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where C.cargoName like ?");
        sbSQL.append(" order by S.stockId ASC");


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

    @Override
    public Long countBySafe() {
        Long result = 0L;

        //0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramslist = new ArrayList<Object>();

        //1)组合sql
        sbSQL.append("select count(1) from stock S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where S.Number < C.SafetyStock");
        sbSQL.append(" order by S.stockId ASC");

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
    public List<Stock> pagerBySafe(Long pageNum, Long pageSize) {
        List<Stock> list = new ArrayList<Stock>();

        //0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramslist = new ArrayList<Object>();

        //1)组合SQL
        sbSQL.append("select S.*,C.cargoName,C.safetyStock,C.unit from stock S");
        sbSQL.append(" left join cargo C on S.cargoId = C.cargoId");
        sbSQL.append(" where S.Number < C.SafetyStock");
        sbSQL.append(" order by S.stockId ASC");

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

    private Stock toBean(ResultSet rs){
        Stock  bean = new Stock();
        try{
            bean.setStockId(rs.getInt("stockId"));
            bean.setNumber(rs.getInt("number"));
            bean.setCargoId(rs.getInt("cargoId"));
            bean.setBuyDate(rs.getDate("buyDate"));
            bean.setSellDate(rs.getDate("sellDate"));

            //cargo表传来的数据
            bean.setCargoName(rs.getString("cargoName"));
            bean.setSafetyStock(rs.getDouble("safetyStock"));
            bean.setUnit(rs.getString("unit"));
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;

    }

    private Stock toBean1(ResultSet rs){
        Stock  bean = new Stock();
        try{
            bean.setStockId(rs.getInt("stockId"));
            bean.setCargoName(rs.getString("cargoName"));
            bean.setNumber(rs.getInt("number"));
            bean.setSafetyStock(rs.getDouble("safetyStock"));
            bean.setUnit(rs.getString("unit"));

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;
    }
}
