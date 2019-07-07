package dao.impl;

import bean.Order;
import com.liuvei.common.DbFun;
import com.sun.org.apache.xpath.internal.operations.Or;
import dao.OrderDao;
import util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public Long insert(Order bean) {
        Long result = 0L;

        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" Insert Into `order`");
        sbSQL.append(" (");
        sbSQL.append(" orderId,staffId,sentAdd,cusId,");
        sbSQL.append(" invNum,money,orderDate ");
        sbSQL.append(" )");
        sbSQL.append(" values(?,?,?,?, ?,?,?)");

        // 2)添加参数
        paramsList.add(bean.getOrderId());
        paramsList.add(bean.getStaffId());
        paramsList.add(bean.getSentAdd());
        paramsList.add(bean.getCusId());

        paramsList.add(bean.getInvNum());
        paramsList.add(bean.getMoney());
        paramsList.add(bean.getOrderDate());

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
        sbSQL.append(" delete from `order`");
        sbSQL.append(" where orderId=?");

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
    public Long update(Order bean) {
        Long result = 0L;

        // 0) 定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" update `order` ");
        sbSQL.append(" set  staffId=?,cusId=?,sentAdd=?");
        sbSQL.append(" , invNum=?, money=?,orderDate=?");
        sbSQL.append(" where orderId=?");

        // 2) 添加参数

        paramsList.add(bean.getStaffId());
        paramsList.add(bean.getCusId());
        paramsList.add(bean.getSentAdd());

        paramsList.add(bean.getInvNum());
        paramsList.add(bean.getMoney());
        paramsList.add(bean.getOrderDate());

        paramsList.add(bean.getOrderId());

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
    public List<Order> list() {
        List<Order> list = new ArrayList<>();

        StringBuffer sbSQL= new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        sbSQL.append(" SELECT * FROM `order` ");

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
    public Order load(Long id) {
        Order bean=null;

        StringBuffer sbSQL=new StringBuffer();
        List<Object> paramsList =new ArrayList<Object>();

        sbSQL.append(" select * from `order` O");
        sbSQL.append(" left join staff S on S.staffId=O.staffId");
        sbSQL.append(" left join cuscontact C on C.cusId=O.cusId");
        sbSQL.append(" where orderId=?");

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
    public Order loadByName(String name) {
      return  null;
    }

    @Override
    public Long count() {
        Long result = 0L;
        StringBuffer sbSQL =new StringBuffer();
        List<Object> paramsList = new ArrayList<>();

        sbSQL.append(" select count(1) from `order`");

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
    public List<Order> pager(Long pagerNum, Long pageSize) {
        List<Order>list =new ArrayList<Order>();

        // 0）定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1)组合SQL
        sbSQL.append(" select * from `order` O");
        sbSQL.append(" left join staff S on S.staffId=O.staffId");
        sbSQL.append(" left join cuscontact C on C.cusId=O.cusId");
        sbSQL.append(" order by orderId asc");

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
        sbSQL.append(" select count(1) from `order`");
        sbSQL.append(" where invNum like ?");

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
    public Long countByTime(Date startDate, Date endDate) {
        Long result = 0L;
        StringBuffer sbSQL =new StringBuffer();
        List<Object> paramsList = new ArrayList<>();

        sbSQL.append(" select count(1) from `order`");
        sbSQL.append(" Where OrderDate Between ? and ?");

        paramsList.add(startDate);
        paramsList.add(endDate);

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
    public List<Order> pagerByTime(Date startDate, Date endDate, Long pagerNum, Long pageSize) {
            List<Order>list =new ArrayList<Order>();

            // 0）定义变量
            StringBuffer sbSQL = new StringBuffer();
            List<Object> paramsList = new ArrayList<Object>();

            // 1)组合SQL
            sbSQL.append(" select * from `order` O");
            sbSQL.append(" left join staff S on S.staffId=O.staffId");
            sbSQL.append(" left join cuscontact C on C.cusId=O.cusId");
            sbSQL.append(" Where OrderDate Between ? and ?");
            sbSQL.append(" order by orderId asc");

            paramsList.add(startDate);
            paramsList.add(endDate);

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
    public List<Order> pagerByName(String name, Long pageNum, Long pageSize) {
        List<Order> list = new ArrayList<Order>();

        // 0)定义变量
        StringBuffer sbSQL = new StringBuffer();
        List<Object> paramsList = new ArrayList<Object>();

        // 1) 组合SQL
        sbSQL.append(" select * from `order` O");
        sbSQL.append(" left join staff S on S.staffId=O.staffId");
        sbSQL.append(" left join cuscontact C on C.cusId=O.cusId");
        sbSQL.append(" where O.invNum like ?");
        sbSQL.append(" order by O.orderId asc");

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


    private Order toBean(ResultSet rs) {
        Order bean = new Order();

        try {
            bean.setOrderId(rs.getInt("orderId"));
            bean.setOrderDate(rs.getDate("orderDate"));
            bean.setStaffId(rs.getInt("staffId"));
            bean.setCusId(rs.getInt("cusId"));

            bean.setcName(rs.getString("cName"));
            bean.setCusName(rs.getString("cusName"));

            bean.setSentAdd(rs.getString("sentAdd"));
            bean.setInvNum(rs.getString("invNum"));
            bean.setMoney(rs.getBigDecimal("money"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bean;
    }
}
