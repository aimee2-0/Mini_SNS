package qa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DBAccess;


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


    public void registAnswer() throws ClassNotFoundException,SQLException{
        Connection conn = null;
        ResultSet rs = null;

        
            DBAccess db=new DBAccess();
            if(db.getConnection() == null || db.getConnection().isClosed()){
                conn = db.accessOracle();
            }else{
                conn = db.getConnection();
            }
            conn.setAutoCommit(false);
            PreparedStatement ps=conn.prepareStatement("INSERT INTO qa_answers (user_id,question_id,A_BODY) values(?,?,?)");
            ps.setInt(1,user_id);
            ps.setInt(2,question_id);
            ps.setString(3,body);
            ps.executeUpdate();
            conn.commit();
        
            conn.close();
    }
}