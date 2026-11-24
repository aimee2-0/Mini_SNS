package qa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;




public class ARegister {
    private int user_id;
    private int question_id;
    private String body;


    public void setUserId(int user_id){
        this.user_id=user_id;
    }

    public void setQid(int question_id){
        this.question_id=question_id;
    }

    public void setBody(String body){
        this.body=body;
    }

    public int getUserId(){
        return user_id;
    }

    public int getQid(){
        return question_id;
    }

    public String getBody(){
        return body;
    }


    public void RegistAnswer(){
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","info","pro");
            cn.setAutoCommit(false);
            PreparedStatement ps=cn.prepareStatement("INSERT INTO qa_answers (user_id,question_id,A_BODY) values(?,?,?)");
            ps.setInt(1,user_id);
            ps.setInt(2,question_id);
            ps.setString(3,body);
            ps.executeUpdate();
            cn.commit();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("DB接続に失敗しました");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("JDBCドライバのロードに失敗しました。");
        }finally{
            try{
                cn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}