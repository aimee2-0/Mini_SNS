package usersprof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import qa.Questions;

public class HyojiQUESTION {

    public List<Questions> SelectQuestionsByUser(int userid) {

        List<Questions> questionList = new ArrayList<>();

        String sql ="SELECT QUESTION_ID, QUESTION_USER, SUBJECT_NAME, TITLE, Q_BODY, Q_CREATED_AT FROM QAView WHERE USER_ID = ? " + "ORDER BY QUESTION_ID DESC, Q_CREATED_AT DESC FETCH FIRST 10 ROWS ONLY";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:orcl", "info", "pro"
                );
                ps = conn.prepareStatement(sql);
                ps.setInt(1, userid);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Questions q = new Questions();
                        q.setQid(rs.getInt("QUESTION_ID"));
                        q.setUserName(rs.getString("QUESTION_USER"));
                        q.setSubjectName(rs.getString("SUBJECT_NAME"));
                        q.setTitle(rs.getString("TITLE"));
                        q.setQbody(rs.getString("Q_BODY"));
                        q.setQcreatedAt(rs.getString("Q_CREATED_AT"));
                        questionList.add(q);
                    }
                }
        } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("DB接続に失敗しました");
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("JDBCドライバのロードに失敗しました。");
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return questionList;
        }

}