package com.biology.repository;


import com.biology.model.entity.Question;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


import java.sql.Connection;
import java.sql.ResultSet;
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

    public Question getById(Integer idQuestion) {
        String sql = "Select * from question where id = %d";
        sql = String.format(sql, idQuestion);

        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            statement = (Statement) conn.createStatement();
            statement.execute(sql);
            rs = statement.getResultSet();
            while(rs.next()) {
                int id = rs.getInt("id");
                long lastActivityDate = rs.getTimestamp("lastActivityDate").getTime();
                long creationDate = rs.getTimestamp("creationDate").getTime();
                int answerCount = rs.getInt("answerCount");
                String title = rs.getString("title");
                String body = rs.getString("body");
                int score = rs.getInt("score");
                int downVoteCount = rs.getInt("downVoteCount");
                int viewCount = rs.getInt("viewCount");
                boolean answered = rs.getInt("answered") == 1;
                int upVoteCount = rs.getInt("upVoteCount");
                Integer userId = rs.getInt("userId");

                Question q = new Question(id, lastActivityDate, creationDate, answerCount,
                        title, body, score, downVoteCount, viewCount, answered, upVoteCount, userId);
                return q;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        return null;
    }

    public static void main(String[] args) {
        AppRepository appRepository = new AppRepository();
        Question q = appRepository.getById(45843);
        System.out.println(q.toString());
    }


}
