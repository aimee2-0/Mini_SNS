package sns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

public class SNSview {

    // SNSView (post + latest 3 comments) -> List<Posts>
    public List<Posts> showSNS() {
        List<Posts> postList = new ArrayList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", "info", "pro"
            )) {

                String pSql =
                        "SELECT p.post_id, p.user_id, u.user_name AS post_user, p.p_body, p.p_created_at " +
                        "FROM sns_posts p " +
                        "JOIN users u ON p.user_id = u.user_id " +
                        "ORDER BY p.post_id DESC FETCH FIRST 10 ROWS ONLY";

                String cSql =
                        "SELECT c.comment_id, u.user_name AS comment_user, c.c_body, c.c_created_at " +
                        "FROM sns_comments c " +
                        "JOIN users u ON c.user_id = u.user_id " +
                        "WHERE c.post_id = ? " +
                        "ORDER BY c.c_created_at DESC FETCH FIRST 3 ROWS ONLY";

                try (PreparedStatement psP = conn.prepareStatement(pSql);
                     ResultSet rsP = psP.executeQuery()) {
                    while (rsP.next()) {
                        Posts post = new Posts();
                        int pid = rsP.getInt("post_id");
                        post.setPid(pid);
                        post.setUid(rsP.getInt("user_id"));
                        post.setUserName(rsP.getString("post_user"));
                        post.setPbody(rsP.getString("p_body"));
                        post.setPcreatedAt(rsP.getString("p_created_at"));

                        try (PreparedStatement psC = conn.prepareStatement(cSql)) {
                            psC.setInt(1, pid);
                            try (ResultSet rsC = psC.executeQuery()) {
                                while (rsC.next()) {
                                    Comments comment = new Comments();
                                    comment.setCommentId(rsC.getInt("comment_id"));
                                //    comment.setUserId(rsC.getInt("user_id"));
                                    comment.setUserName(rsC.getString("comment_user"));
                                    comment.setCbody(rsC.getString("c_body"));
                                    comment.setCcreatedAt(rsC.getString("c_created_at"));
                                    post.commentsList.add(comment);
                                }
                            }
                        }

                        postList.add(post);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB接続に失敗しました");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBCドライバのロードに失敗しました。");
        }

        return postList;
    }

    public Posts getPostById(int postId) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", "info", "pro"
            )) {
                String pSql =
                        "SELECT p.post_id, u.user_name AS post_user, p.p_body, p.p_created_at " +
                        "FROM sns_posts p " +
                        "JOIN users u ON p.user_id = u.user_id " +
                        "WHERE p.post_id = ?";

                Posts post = null;
                try (PreparedStatement psP = conn.prepareStatement(pSql)) {
                    psP.setInt(1, postId);
                    try (ResultSet rsP = psP.executeQuery()) {
                        if (!rsP.next()) return null;
                        post = new Posts();
                        post.setPid(rsP.getInt("post_id"));
                        post.setUserName(rsP.getString("post_user"));
                        post.setPbody(rsP.getString("p_body"));
                        post.setPcreatedAt(rsP.getString("p_created_at"));
                    }
                }

                String cSql =
                        "SELECT c.comment_id, u.user_name AS comment_user, c.c_body, c.c_created_at " +
                        "FROM sns_comments c " +
                        "JOIN users u ON c.user_id = u.user_id " +
                        "WHERE c.post_id = ? " +
                        "ORDER BY c.c_created_at DESC";

                try (PreparedStatement psC = conn.prepareStatement(cSql)) {
                    psC.setInt(1, postId);
                    try (ResultSet rsC = psC.executeQuery()) {
                        while (rsC.next()) {
                            Comments comment = new Comments();
                            comment.setCommentId(rsC.getInt("comment_id"));
                            comment.setUserName(rsC.getString("comment_user"));
                            comment.setCbody(rsC.getString("c_body"));
                            comment.setCcreatedAt(rsC.getString("c_created_at"));
                            post.commentsList.add(comment);
                        }
                    }
                }
                return post;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB接続に失敗しました");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBCドライバのロードに失敗しました。");
        }
        return null;
    }
}
