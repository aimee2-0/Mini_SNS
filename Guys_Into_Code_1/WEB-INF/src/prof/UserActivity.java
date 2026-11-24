package prof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import qa.Questions;
import sns.Posts;
import db.DBAccess;

public class UserActivity {

    public List<Questions> getUserQuestions(int userId) throws ClassNotFoundException,SQLException {
        List<Questions> list = new ArrayList<>();
        String sql =
                "SELECT q.question_id, s.subject_name, q.title, q.q_body, q.q_created_at, u.user_name " +
                "FROM qa_questions q " +
                "JOIN subjects s ON q.subject_id = s.subject_id " +
                "JOIN users u ON q.user_id = u.user_id " +
                "WHERE q.user_id = ? " +
                "ORDER BY q.q_created_at DESC";

        
                Connection conn = null;
                DBAccess db=new DBAccess();
                if(db.getConnection() == null || db.getConnection().isClosed()){
                    conn = db.accessOracle();
                }else{
                    conn = db.getConnection();
                }
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Questions q = new Questions();
                    q.setQid(rs.getInt("question_id"));
                    q.setSubjectName(rs.getString("subject_name"));
                    q.setTitle(rs.getString("title"));
                    q.setQbody(rs.getString("q_body"));
                    q.setQcreatedAt(rs.getString("q_created_at"));
                    q.setUserName(rs.getString("user_name"));
                    list.add(q);
                }
        conn.close();
        return list;
    }

    public List<Posts> getUserPosts(int userId) throws ClassNotFoundException,SQLException {
        List<Posts> list = new ArrayList<>();
        String sql =
                "SELECT p.post_id, u.user_name, p.p_body, p.p_created_at " +
                "FROM sns_posts p " +
                "JOIN users u ON p.user_id = u.user_id " +
                "WHERE p.user_id = ? " +
                "ORDER BY p.p_created_at DESC";

                Connection conn = null;
                DBAccess db=new DBAccess();
                if(db.getConnection() == null || db.getConnection().isClosed()){
                    conn = db.accessOracle();
                }else{
                    conn = db.getConnection();
                }
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Posts p = new Posts();
                    p.setPid(rs.getInt("post_id"));
                    p.setUserName(rs.getString("user_name"));
                    p.setPbody(rs.getString("p_body"));
                    p.setPcreatedAt(rs.getString("p_created_at"));
                    list.add(p);
                
                }
        conn.close();
        return list;
    }

    public List<UserAnswer> getUserAnswers(int userId) throws ClassNotFoundException,SQLException {
        List<UserAnswer> list = new ArrayList<>();
        String sql =
                "SELECT a.question_id, q.title, a.a_body, a.a_created_at " +
                "FROM qa_answers a " +
                "JOIN qa_questions q ON a.question_id = q.question_id " +
                "WHERE a.user_id = ? " +
                "ORDER BY a.a_created_at DESC";

            Connection conn = null;
            DBAccess db=new DBAccess();
            if(db.getConnection() == null || db.getConnection().isClosed()){
                conn = db.accessOracle();
            }else{
                conn = db.getConnection();
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserAnswer ua = new UserAnswer();
                ua.setQuestionId(rs.getInt("question_id"));
                ua.setQuestionTitle(rs.getString("title"));
                ua.setAnswerBody(rs.getString("a_body"));
                ua.setAnswerCreatedAt(rs.getString("a_created_at"));
                list.add(ua);
            }
        return list;
    }
}

