package com.biology.repository;


import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


import java.sql.Connection;
import java.sql.SQLException;

public class AppRepository {
    MysqlDataSource dataSource;
    public AppRepository() {
        dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("test2");
    }

    public void run(String sql, String title) {
        System.out.println("Running " + title);
        Connection conn = null;
        Statement statement = null;
        try {
            conn = dataSource.getConnection();
            statement = (Statement) conn.createStatement();
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
