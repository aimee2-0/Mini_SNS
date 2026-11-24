package qa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import db.DBAccess;



public class QAview {
    public List<Questions> showQA() throws ClassNotFoundException,SQLException{
        List<Questions> QAList = new ArrayList<>();

            Connection conn = null;
            DBAccess db=new DBAccess();
            if(db.getConnection() == null || db.getConnection().isClosed()){
                conn = db.accessOracle();
            }else{
                conn = db.getConnection();
            }
            String qSql =
                "SELECT q.question_id, q.user_id AS question_user_id, u.user_name AS question_user, s.subject_name, q.title, q.q_body, q.q_created_at " +
                "FROM qa_questions q " +
                "JOIN users u ON q.user_id = u.user_id " +
                "JOIN subjects s ON q.subject_id = s.subject_id " +
                "ORDER BY q.question_id DESC FETCH FIRST 30 ROWS ONLY";

            String aSql =
                "SELECT a.user_id AS answer_user_id, u.user_name AS answer_user, a.a_body, a.a_created_at " +
                "FROM qa_answers a " +
                "JOIN users u ON a.user_id = u.user_id " +
                "WHERE a.question_id = ? " +
                "ORDER BY a.a_created_at DESC";

            PreparedStatement psQ = conn.prepareStatement(qSql);
                ResultSet rsQ = psQ.executeQuery();
                while (rsQ.next()) {
                    Questions questions = new Questions();
                    int qid = rsQ.getInt("question_id");
                    questions.setQid(qid);
                    questions.setUserId(rsQ.getInt("question_user_id"));
                    questions.setSubjectName(rsQ.getString("subject_name"));
                    questions.setTitle(rsQ.getString("title"));
                    questions.setQbody(rsQ.getString("q_body"));
                    questions.setUserName(rsQ.getString("question_user"));
                    questions.setQcreatedAt(rsQ.getString("q_created_at"));

                    PreparedStatement psA = conn.prepareStatement(aSql);
                        psA.setInt(1, qid);
                        ResultSet rsA = psA.executeQuery();
                        while (rsA.next()) {
                            Answers answers = new Answers();
                            answers.setUserId(rsA.getInt("answer_user_id"));
                            answers.setUserName(rsA.getString("answer_user"));
                            answers.setAbody(rsA.getString("a_body"));
                            answers.setAcreatedAt(rsA.getString("a_created_at"));
                            questions.answersList.add(answers);
                        }
                    QAList.add(questions);
            }
        return QAList;
    }

    public Questions getQuestionById(int questionId) throws ClassNotFoundException,SQLException {
        Connection conn = null;
        DBAccess db=new DBAccess();
        if(db.getConnection() == null || db.getConnection().isClosed()){
            conn = db.accessOracle();
        }else{
            conn = db.getConnection();
        }
        String qSql =
            "SELECT q.question_id, q.user_id AS question_user_id, u.user_name AS question_user, s.subject_name, q.title, q.q_body, q.q_created_at " +
            "FROM qa_questions q " +
            "JOIN users u ON q.user_id = u.user_id " +
            "JOIN subjects s ON q.subject_id = s.subject_id " +
            "WHERE q.question_id = ?";

        Questions questions = null;
        PreparedStatement psQ = conn.prepareStatement(qSql);
        psQ.setInt(1, questionId);
        ResultSet rsQ = psQ.executeQuery();
        if (!rsQ.next()) {
            return null;
        }
        questions = new Questions();
        questions.setQid(rsQ.getInt("question_id"));
        questions.setUserId(rsQ.getInt("question_user_id"));
        questions.setSubjectName(rsQ.getString("subject_name"));
        questions.setTitle(rsQ.getString("title"));
        questions.setQbody(rsQ.getString("q_body"));
        questions.setUserName(rsQ.getString("question_user"));
        questions.setQcreatedAt(rsQ.getString("q_created_at"));

        String aSql =
            "SELECT a.user_id AS answer_user_id, u.user_name AS answer_user, a.a_body, a.a_created_at " +
            "FROM qa_answers a " +
            "JOIN users u ON a.user_id = u.user_id " +
            "WHERE a.question_id = ? " +
            "ORDER BY a.a_created_at DESC";

        PreparedStatement psA = conn.prepareStatement(aSql);
        psA.setInt(1, questionId);
        ResultSet rsA = psA.executeQuery();
        while (rsA.next()) {
            Answers answers = new Answers();
            answers.setUserId(rsA.getInt("answer_user_id"));
            answers.setUserName(rsA.getString("answer_user"));
            answers.setAbody(rsA.getString("a_body"));
            answers.setAcreatedAt(rsA.getString("a_created_at"));
            questions.answersList.add(answers);
        }
        return questions;
    }
}