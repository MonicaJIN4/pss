package util;

import com.liuvei.common.AppProps;
import com.liuvei.common.DbFun;

import java.sql.*;


public class DbUtil {
    public static final String DRIVER;
    public static final String URL;
    public static final String USERNAME;
    public static final String PASSWORD;

    /**
     * 【静态代码块】：读取配置文件中的四大金刚，加载数据库jar包。
     */
    static {

        AppProps props = AppProps.getInstance();

        try {

            DRIVER = props.getDriver();
            URL = props.getUrl();
            USERNAME = props.getUsername();
            PASSWORD = props.getPassword();



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {
            Class.forName(DbUtil.DRIVER);
            System.out.println("Driver【" + DbUtil.DRIVER + "】加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver【" + DbUtil.DRIVER + "】加载失败");
        }
    }

    /**
     * 【获得连接对象】
     *
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(DbUtil.URL, DbUtil.USERNAME, DbUtil.PASSWORD);
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            System.out.println("获取连接失败");
        }
        return conn;
    }

    /**
     * 关闭连接对象
     *
     * @param conn
     *            欲关闭的连接对象
     */
    public static void close(Connection conn) {
        DbFun.close(conn);
    }

    /**
     * 关闭Statement
     *
     * @param stmt
     */
    public static void close(Statement stmt) {
        DbFun.close(stmt);
    }

    /**
     * 关闭ResultSet
     *
     * @param rs
     */
    public static void close(ResultSet rs) {
        DbFun.close(rs);
    }

    /**
     * 关闭三大对象
     *
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {

        DbFun.close(conn, stmt, rs);
    }

}
