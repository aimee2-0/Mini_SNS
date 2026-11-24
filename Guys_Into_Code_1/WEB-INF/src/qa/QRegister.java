package qa;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DBAccess;



public class QRegister {
    private int user_id;
    private int subject_id;
    private String title;
    private String body;
    private String topic;
    private InputStream imageStream;

    public void setUserId(int user_id){
        this.user_id=user_id;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setBody(String body){
        this.body=body;
    }

    public void setTopic(String topic){
        this.topic=topic;
    }

    public void setImageStream(InputStream imageStream) {
        this.imageStream = imageStream;
    }

    public int getUserId(){
        return user_id;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public String getTopic(){
        return topic;
    }

    public void registQuestion() throws ClassNotFoundException,SQLException{
        Connection conn = null;
        ResultSet rs = null;

        DBAccess db=new DBAccess();
        if(db.getConnection() == null || db.getConnection().isClosed()){
            conn = db.accessOracle();
        }else{
            conn = db.getConnection();
        }
        conn.setAutoCommit(false);
        PreparedStatement ps1=conn.prepareStatement("SELECT * FROM subjects WHERE subject_name=?");
        ps1.setString(1,topic);
        rs = ps1.executeQuery();
        rs.next();
        subject_id = rs.getInt("subject_id");
        rs.close();
        ps1.close();
        PreparedStatement ps2;
        if (imageStream != null) {
            ps2 = conn.prepareStatement("INSERT INTO qa_questions (user_id,subject_id,title,Q_BODY,media) values(?,?,?,?,?)");
            ps2.setInt(1,user_id);
            ps2.setInt(2,subject_id);
            ps2.setString(3,title);
            ps2.setString(4,body);
            ps2.setBinaryStream(5, imageStream);
        } else {
            ps2 = conn.prepareStatement("INSERT INTO qa_questions (user_id,subject_id,title,Q_BODY) values(?,?,?,?)");
            ps2.setInt(1,user_id);
            ps2.setInt(2,subject_id);
            ps2.setString(3,title);
            ps2.setString(4,body);
        }
        ps2.executeUpdate();
        conn.commit();
        conn.close();
    }
}