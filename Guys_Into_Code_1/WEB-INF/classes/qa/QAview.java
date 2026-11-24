package qa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;



public class QAview {

    public List<Questions> ShowQA(){
        List<Questions> QAList = new ArrayList<>();

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","info","pro")) {
                String qSql =
                    "SELECT q.question_id, u.user_name AS question_user, s.subject_name, q.title, q.q_body, q.q_created_at " +
                    "FROM qa_questions q " +
                    "JOIN users u ON q.user_id = u.user_id " +
                    "JOIN subjects s ON q.subject_id = s.subject_id " +
                    "ORDER BY q.question_id DESC FETCH FIRST 10 ROWS ONLY";

                String aSql =
                    "SELECT u.user_name AS answer_user, a.a_body, a.a_created_at " +
                    "FROM qa_answers a " +
                    "JOIN users u ON a.user_id = u.user_id " +
                    "WHERE a.question_id = ? " +
                    "ORDER BY a.a_created_at DESC FETCH FIRST 3 ROWS ONLY";

                try (PreparedStatement psQ = conn.prepareStatement(qSql);
                     ResultSet rsQ = psQ.executeQuery()) {
                    while (rsQ.next()) {
                        Questions questions = new Questions();
                        int qid = rsQ.getInt("question_id");
                        questions.setQid(qid);
                        questions.setSubjectName(rsQ.getString("subject_name"));
                        questions.setTitle(rsQ.getString("title"));
                        questions.setQbody(rsQ.getString("q_body"));
                        questions.setUserName(rsQ.getString("question_user"));
                        questions.setQcreatedAt(rsQ.getString("q_created_at"));
                     //   questions.setUserId(rsQ.getInt("user_id"));

                        try (PreparedStatement psA = conn.prepareStatement(aSql)) {
                            psA.setInt(1, qid);
                            try (ResultSet rsA = psA.executeQuery()) {
                                while (rsA.next()) {
                                    Answers answers = new Answers();
                              //      answers.setUserId(rsA.getInt("user_id"));
                                    answers.setUserName(rsA.getString("answer_user"));
                                    answers.setAbody(rsA.getString("a_body"));
                                    answers.setAcreatedAt(rsA.getString("a_created_at"));
                                    questions.answersList.add(answers);
                                }
                            }
                        }

                        QAList.add(questions);
                    }
                }
            }


        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("DB接続に失敗しました");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("JDBCドライバのロードに失敗しました。");
        }
        return QAList;
    }

    public Questions getQuestionById(int questionId) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","info","pro")) {
                String qSql =
                    "SELECT q.question_id, u.user_name AS question_user, s.subject_name, q.title, q.q_body, q.q_created_at " +
                    "FROM qa_questions q " +
                    "JOIN users u ON q.user_id = u.user_id " +
                    "JOIN subjects s ON q.subject_id = s.subject_id " +
                    "WHERE q.question_id = ?";

                Questions questions = null;
                try (PreparedStatement psQ = conn.prepareStatement(qSql)) {
                    psQ.setInt(1, questionId);
                    try (ResultSet rsQ = psQ.executeQuery()) {
                        if (!rsQ.next()) {
                            return null;
                        }
                        questions = new Questions();
                        questions.setQid(rsQ.getInt("question_id"));
                        questions.setSubjectName(rsQ.getString("subject_name"));
                        questions.setTitle(rsQ.getString("title"));
                        questions.setQbody(rsQ.getString("q_body"));
                        questions.setUserName(rsQ.getString("question_user"));
                        questions.setQcreatedAt(rsQ.getString("q_created_at"));
                    }
                }

                String aSql =
                    "SELECT u.user_name AS answer_user, a.a_body, a.a_created_at " +
                    "FROM qa_answers a " +
                    "JOIN users u ON a.user_id = u.user_id " +
                    "WHERE a.question_id = ? " +
                    "ORDER BY a.a_created_at DESC";

                try (PreparedStatement psA = conn.prepareStatement(aSql)) {
                    psA.setInt(1, questionId);
                    try (ResultSet rsA = psA.executeQuery()) {
                        while (rsA.next()) {
                            Answers answers = new Answers();
                            answers.setUserName(rsA.getString("answer_user"));
                            answers.setAbody(rsA.getString("a_body"));
                            answers.setAcreatedAt(rsA.getString("a_created_at"));
                            questions.answersList.add(answers);
                        }
                    }
                }
                return questions;
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("DB接続に失敗しました");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("JDBCドライバのロードに失敗しました。");
        }
        return null;
    }
}