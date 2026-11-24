package usersprof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class UsersCountCheck {

    private int userId;
    private int snsPosts;
    private int questions;
    private int answers;

     public void setUserId(int userId){
        this.userId = userId;
    }
    public void setSnsPost(int snsPosts) {
        this.snsPosts = snsPosts;
    }
    public void setQuestions(int questions) {
        this.questions = questions;
    }
    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public int getUserId(){
        return userId;
    }  
    public int getSnsPost() {
        return snsPosts;
    }  
    public int getQuestions() {
        return questions;
    }
    public int getAnswers() {
        return answers;
    }

    public void UsersCounts() {

        String sql1 = "SELECT COUNT(*) FROM sns_posts WHERE user_id = ?";
        String sql2 = "SELECT COUNT(*) FROM qa_questions WHERE user_id = ?";
        String sql3 = "SELECT COUNT(*) FROM qa_answers WHERE user_id = ?";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try (
                Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", "info", "pro"
                );
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                PreparedStatement ps3 = conn.prepareStatement(sql3)
            ) {
                ps1.setInt(1, userId);
                try (ResultSet rs1 = ps1.executeQuery()) {
                    if (rs1.next()) setSnsPost(rs1.getInt(1));
                }

                ps2.setInt(1, userId);
                try (ResultSet rs2 = ps2.executeQuery()) {
                    if (rs2.next()) setQuestions(rs2.getInt(1));
                }

                ps3.setInt(1, userId);
                try (ResultSet rs3 = ps3.executeQuery()) {
                    if (rs3.next()) setAnswers(rs3.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB接続に失敗しました。");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBCドライバのロードに失敗しました。");
        }
    }
}
