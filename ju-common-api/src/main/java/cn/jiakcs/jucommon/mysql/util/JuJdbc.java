package cn.jiakcs.jucommon.mysql.util;

import cn.jiakcs.jucommon.julog.JuLogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JuJdbc {
    private Connection connection = null;
    private Statement statement = null;

    /**
     * 初始化数据库连接
     */
    public Boolean Connection(String url, String user, String pwd) {
        return Connection(url, user, pwd, "com.mysql.jdbc.Driver");
    }

    public Boolean Connection(String url, String user, String pwd, String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            JuLogUtil.error("找不到JDBC驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
            return false;
        }

        try {
            connection = java.sql.DriverManager.getConnection(url, user, pwd);
            statement = connection.createStatement();
            return true;

        } catch (Exception e) {
            JuLogUtil.error("数据库连接错误: JDBC:" + url);
            JuLogUtil.error("数据库连接错误: User:" + user);
            JuLogUtil.error("数据库连接错误: Pwd:" + pwd);
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 关闭连接
     */
    public void Disconnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                JuLogUtil.error("关闭MySQL连接失败!");
                e.printStackTrace();
            }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException e) {
                    JuLogUtil.error("卸载连接库资源失败!");
                    e.printStackTrace();
                }
        }
    }

    /**
     * 执行 SQL 查询语句,返回记录表
     */
    public ResultSet GetResultSet(String sql) {
        ResultSet rs = null;
        try {
            JuLogUtil.debug("Select: " + sql);
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            JuLogUtil.error("SQL 命令错误: " + sql);
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 执行SQL语句进行更新\删除等操作
     */
    public boolean Execute(String hql) {
        try {
            JuLogUtil.debug("Execute: " + hql);
            return statement.execute(hql);
        } catch (SQLException e) {
            JuLogUtil.error("SQL 命令错误: " + hql);
            e.printStackTrace();
        }

        return false;
    }
}
