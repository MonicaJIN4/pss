package dao.impl;

import bean.ReturnNote;
import com.liuvei.common.DbFun;
import dao.ReturnNoteDao;
import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnNoteDaoImpl implements ReturnNoteDao {


    @Override
    public List<ReturnNote> list() {
        List<ReturnNote> list = new ArrayList<ReturnNote>();

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" select * from ReturnNote");

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
    public Long insert(ReturnNote bean) {
        Long result = 0L;

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into ReturnNote");
        sbSQL.append(" (");
        sbSQL.append(" returnId ,purId ,supId ,cargoId ,number ,returnPrice");
        sbSQL.append(" ,remark ,staffId ,returnDate ,total");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?,?,?,?,? ,?,?,?,?)");

        // 2)添加参数
        paramsList.add(bean.getReturnId());
        paramsList.add(bean.getPurId());
        paramsList.add(bean.getSupId());
        paramsList.add(bean.getCargoId());
        paramsList.add(bean.getNumber());
        paramsList.add(bean.getReturnPrice());

        paramsList.add(bean.getRemark());
        paramsList.add(bean.getStaffId());
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
    public Long update(ReturnNote bean) {
        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update ReturnNote ");
        sbSQL.append(" set supId=? ,cargoId=? ,number=?,returnPrice=?");
        sbSQL.append(" ,remark=? ,staffId=? ,returnDate=? ,total=?");
        sbSQL.append(" where returnId=?");

        // 2) 添加参数


        paramsList.add(bean.getSupId());
        paramsList.add(bean.getCargoId());
        paramsList.add(bean.getNumber());
        paramsList.add(bean.getReturnPrice());

        paramsList.add(bean.getRemark());
        paramsList.add(bean.getStaffId());
        paramsList.add(bean.getReturnDate());
        paramsList.add(bean.getTotal());

        paramsList.add(bean.getReturnId());


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
        sbSQL.append(" delete from ReturnNote");
        sbSQL.append(" where returnId=?");

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
    public ReturnNote load(Long id) {

        ReturnNote bean = null;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select R.* ,C.cargoName ,C.unit,S.Name supName ,St.cName  from ReturnNote R");
        sbSQL.append(" left join Cargo C on C.cargoId = R.cargoId");
        sbSQL.append(" left join Supplier S on S.supId = R.supId");
        sbSQL.append(" left join Staff St on St.staffId = R.staffId");
        sbSQL.append(" where R.returnId=?");

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
    public ReturnNote loadByName(String name) {
        ReturnNote bean = null;

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select R.* ,C.cargoName ,C.unit ,S.Name supName ,St.cName  from ReturnNote R");
        sbSQL.append(" left join Cargo C on C.cargoId = R.cargoId");
        sbSQL.append(" left join Supplier S on S.supId = R.supId");
        sbSQL.append(" left join Staff St on St.staffId = R.staffId");
        sbSQL.append(" where C.cargoId=?");
        sbSQL.append(" order by R.returnId asc");

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
        sbSQL.append(" select count(1) from ReturnNote");


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
    public List<ReturnNote> pager(Long pageNum, Long pageSize) {

        List<ReturnNote> list = new ArrayList<ReturnNote>();

        // 0）定义变量
        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select R.* ,C.cargoName ,C.unit ,S.Name supName ,St.cName  from ReturnNote R");
        sbSQL.append(" left join Cargo C on C.cargoId = R.cargoId");
        sbSQL.append(" left join Supplier S on S.supId = R.supId");
        sbSQL.append(" left join Staff St on St.staffId = R.staffId");
        sbSQL.append(" order by R.returnId asc");

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
        sbSQL.append(" select count(1) from ReturnNote");
        sbSQL.append(" where purId like ? ");

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
    public List<ReturnNote> pagerByName(String name, Long pageNum, Long pageSize) {
        List<ReturnNote> list = new ArrayList<ReturnNote>();

        // 0）定义变量
        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select R.* ,C.cargoName ,C.unit ,S.Name supName ,St.cName  from ReturnNote R");
        sbSQL.append(" left join Cargo C on C.cargoId = R.cargoId");
        sbSQL.append(" left join Supplier S on S.supId = R.supId");
        sbSQL.append(" left join Staff St on St.staffId = R.staffId");

        sbSQL.append(" where C.CargoName like ? ");
        sbSQL.append(" order by R.supId asc");


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
    public List<ReturnNote> pagerByPurId(String name, Long pageNum, Long pageSize) {
        List<ReturnNote> list = new ArrayList<ReturnNote>();

        // 0）定义变量
        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1）组合SQL
        sbSQL.append(" select R.* ,C.cargoName ,C.unit ,S.Name supName ,St.cName  from ReturnNote R");
        sbSQL.append(" left join Cargo C on C.cargoId = R.cargoId");
        sbSQL.append(" left join Supplier S on S.supId = R.supId");
        sbSQL.append(" left join Staff St on St.staffId = R.staffId");

        sbSQL.append(" where R.purId like ? ");
        sbSQL.append(" order by R.supId asc");


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

    private ReturnNote toBean(ResultSet rs){
        ReturnNote bean = new ReturnNote();
        try{
            bean.setReturnId(rs.getInt("returnId"));
            bean.setSupId(rs.getInt("supId"));
            bean.setCargoId(rs.getInt("cargoId"));
            bean.setPurId(rs.getInt("purId"));
            bean.setNumber(rs.getInt("number"));
            bean.setRemark(rs.getString("remark"));
            bean.setReturnPrice(rs.getBigDecimal("returnPrice"));
            bean.setStaffId(rs.getInt("staffId"));
            bean.setReturnDate(rs.getDate("returnDate"));
            bean.setTotal(rs.getBigDecimal("total"));


            //外表
            bean.setSupName(rs.getString("supName"));
            bean.setCargoName(rs.getString("cargoName"));
            bean.setcName(rs.getString("cName"));
            bean.setUnit(rs.getString("unit"));

        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;

    }


}
