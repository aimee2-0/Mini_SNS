package sns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DBAccess;


public class CRegister {
    private int user_id;
    private int post_id;
    private String body;


    public void setUserId(int user_id){
        this.user_id = user_id;
    }

    public void setPid(int post_id){
        this.post_id = post_id;
    }

    public void setBody(String body){
        this.body = body;
    }

    public int getUserId(){
        return user_id;
    }

    public int getPid(){
        return post_id;
    }

    public String getBody(){
        return body;
    }


    public void registComment() throws ClassNotFoundException,SQLException{
        Connection conn = null;
        ResultSet rs = null;
        DBAccess db=new DBAccess();
        if(db.getConnection() == null || db.getConnection().isClosed()){
            conn = db.accessOracle();
        }else{
            conn = db.getConnection();
        }
        conn.setAutoCommit(false);
        PreparedStatement ps=conn.prepareStatement("INSERT INTO sns_comments (user_id, post_id,c_body) values(?,?,?)");
        ps.setInt(1,user_id);
        ps.setInt(2, post_id);
        ps.setString(3,body);
        ps.executeUpdate();
        conn.commit();
        conn.close();
    }
}