package sns;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DBAccess;



public class PRegister {
    private int user_id;
    private String body;
    private int post_id;
    private InputStream imageStream;

    public void setUserId(int user_id){
        this.user_id=user_id;
    }
    public void setBody(String body){
        this.body=body;
    }
    public void setPid(int post_id) {
        this.post_id = post_id;
    }
    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

    public int getUserId(){
        return user_id;
    }
    public String getBody(){
        return body;
    }
    public int getPid() {
        return post_id;
    }
    public InputStream getImageStream() {
        return imageStream;
    }


    public void registPost()throws ClassNotFoundException,SQLException,IOException{
        Connection conn = null;
        PreparedStatement ps = null;

            DBAccess db=new DBAccess();
            if(db.getConnection() == null || db.getConnection().isClosed()){
                conn = db.accessOracle();
            }else{
                conn = db.getConnection();
            }

            conn.setAutoCommit(false);

            if (imageStream != null) {
                ps = conn.prepareStatement(
                        "INSERT INTO sns_posts (user_id, p_body, media) VALUES (?, ?, ?)"
                );
                ps.setInt(1, user_id);
                ps.setString(2, body);
                ps.setBinaryStream(3, imageStream);
            } else {
                ps = conn.prepareStatement(
                        "INSERT INTO sns_posts (user_id, p_body) VALUES (?, ?)"
                );
                ps.setInt(1, user_id);
                ps.setString(2, body);
            }

            ps.executeUpdate();
            conn.commit();

        
    }
}