package usersprof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import sns.Posts;

public class HyojiSNS {

    public List<Posts> SelectPostsByUser(int userid) {

        List<Posts> postList = new ArrayList<>();

        String sql ="SELECT POST_ID, POST_USER, P_BODY, P_CREATED_AT FROM SNSView WHERE USER_ID = ? " + "ORDER BY POST_ID DESC, P_CREATED_AT DESC FETCH FIRST 10 ROWS ONLY";

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
                        Posts post = new Posts();
                        post.setPid(rs.getInt("POST_ID"));
                        post.setUserName(rs.getString("POST_USER"));
                        post.setPbody(rs.getString("P_BODY"));
                        post.setPcreatedAt(rs.getString("P_CREATED_AT"));
                        postList.add(post);
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
        return postList;
        }

}