package util;

import java.sql.*;

/**
 * 工具类: DBUtil 数据库工具类
 */
public class DBUtil {
    //数据库连接信息
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Teaching;encrypt=true;trustServerCertificate=true";
    private static final String USER = "rozen";
    private static final String PASSWORD = "246879";

    /**
     * 连接数据库
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        // 1.加载数据库驱动类
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // 2.创建连接
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 查询方法
     * @param sql
     * @param params
     * @return 查询结果集
     * @throws SQLException
     */
    public static ResultSet executeQuery(String sql, Object... params) throws SQLException {
        try {
            Connection conn = getConnection(); // 获取数据库连接
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]); // 绑定参数
            }
            return pstmt.executeQuery(); // 返回结果集
        } catch (Exception e) {
            throw new SQLException("Database query execution failed", e); // 转换为 SQLException
        }
    }

    /**
     * 更新方法
     * @param sql
     * @param params
     * @return 返回受影响的行数
     * @throws SQLException
     */
    public static int executeUpdate(String sql, Object... params) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeUpdate(); // 返回受影响的行数
        } catch (Exception e) {
            throw new SQLException("Database query execution failed", e); // 转换为 SQLException
        }
    }

}
