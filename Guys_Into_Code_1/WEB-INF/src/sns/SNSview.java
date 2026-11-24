package sns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import db.DBAccess;

public class SNSview {

    // SNSView (post + latest 3 comments) -> List<Posts>
    public List<Posts> showSNS() throws ClassNotFoundException,SQLException{
        List<Posts> postList = new ArrayList<>();

        Connection conn = null;
        DBAccess db=new DBAccess();
        if(db.getConnection() == null || db.getConnection().isClosed()){
            conn = db.accessOracle();
        }else{
            conn = db.getConnection();
        }

        String pSql =
                "SELECT p.post_id, p.user_id AS post_user_id, u.user_name AS post_user, p.p_body, p.p_created_at " +
                "FROM sns_posts p " +
                "JOIN users u ON p.user_id = u.user_id " +
                "ORDER BY p.post_id DESC FETCH FIRST 30 ROWS ONLY";

        String cSql =
                "SELECT c.comment_id, c.user_id AS comment_user_id, u.user_name AS comment_user, c.c_body, c.c_created_at " +
                "FROM sns_comments c " +
                "JOIN users u ON c.user_id = u.user_id " +
                "WHERE c.post_id = ? " +
                "ORDER BY c.c_created_at DESC";

        PreparedStatement psP = conn.prepareStatement(pSql);
        ResultSet rsP = psP.executeQuery();
        while (rsP.next()) {
            Posts post = new Posts();
            int pid = rsP.getInt("post_id");
            post.setPid(pid);
            post.setUid(rsP.getInt("post_user_id"));
            post.setUserName(rsP.getString("post_user"));
            post.setPbody(rsP.getString("p_body"));
            post.setPcreatedAt(rsP.getString("p_created_at"));

            PreparedStatement psC = conn.prepareStatement(cSql);
            psC.setInt(1, pid);
            ResultSet rsC = psC.executeQuery();
            while (rsC.next()) {
                Comments comment = new Comments();
                comment.setCommentId(rsC.getInt("comment_id"));
                comment.setUserId(rsC.getInt("comment_user_id"));
                comment.setUserName(rsC.getString("comment_user"));
                comment.setCbody(rsC.getString("c_body"));
                comment.setCcreatedAt(rsC.getString("c_created_at"));
                post.commentsList.add(comment);
            }
            postList.add(post);
        }
        return postList;
    }

    public Posts getPostById(int postId) throws ClassNotFoundException,SQLException{

            Connection conn = null;
            DBAccess db=new DBAccess();
            if(db.getConnection() == null || db.getConnection().isClosed()){
                conn = db.accessOracle();
            }else{
                conn = db.getConnection();
            }
            String pSql =
                    "SELECT p.post_id, p.user_id AS post_user_id, u.user_name AS post_user, p.p_body, p.p_created_at " +
                    "FROM sns_posts p " +
                    "JOIN users u ON p.user_id = u.user_id " +
                    "WHERE p.post_id = ?";

            Posts post = null;
            PreparedStatement psP = conn.prepareStatement(pSql);
            psP.setInt(1, postId);
            ResultSet rsP = psP.executeQuery();
            if (!rsP.next()) return null;
            post = new Posts();
            post.setPid(rsP.getInt("post_id"));
            post.setUid(rsP.getInt("post_user_id"));
            post.setUserName(rsP.getString("post_user"));
            post.setPbody(rsP.getString("p_body"));
            post.setPcreatedAt(rsP.getString("p_created_at"));

            String cSql =
                    "SELECT c.comment_id, c.user_id AS comment_user_id, u.user_name AS comment_user, c.c_body, c.c_created_at " +
                    "FROM sns_comments c " +
                    "JOIN users u ON c.user_id = u.user_id " +
                    "WHERE c.post_id = ? " +
                    "ORDER BY c.c_created_at DESC";

            PreparedStatement psC = conn.prepareStatement(cSql);
            psC.setInt(1, postId);
            ResultSet rsC = psC.executeQuery();
            while (rsC.next()) {
                Comments comment = new Comments();
                comment.setCommentId(rsC.getInt("comment_id"));
                comment.setUserId(rsC.getInt("comment_user_id"));
                comment.setUserName(rsC.getString("comment_user"));
                comment.setCbody(rsC.getString("c_body"));
                comment.setCcreatedAt(rsC.getString("c_created_at"));
                post.commentsList.add(comment);
            }
            return post;
    }
}
