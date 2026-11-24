package usersprof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import sns.*;
import qa.*;

public class HyojiANSWER {

    public List<Answers> SelectAnswersByUser(int userid) {

        List<Answers> answerList = new ArrayList<>();

        String sql ="SELECT ANSWER_ID, ANSWER_USER, A_BODY, A_CREATED_AT FROM QAView WHERE USER_ID = ? " + "ORDER BY ANSWER_ID DESC, A_CREATED_AT DESC FETCH FIRST 10 ROWS ONLY";

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
                        Answers a = new Answers();
                        a.setAid(rs.getInt("ANSWER_ID"));
                        a.setUserName(rs.getString("ANSWER_USER"));
                        a.setAbody(rs.getString("P_BODY"));
                        a.setAcreatedAt(rs.getString("A_CREATED_AT"));
                        answerList.add(a);
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
        return answerList;
        }

}