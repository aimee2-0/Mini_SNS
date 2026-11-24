package qa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class QRegister {
    private int user_id;
    private int subject_id;
    private String title;
    private String body;
    private String topic;

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

    public void RegistQuestion(){
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","info","pro");
            cn.setAutoCommit(false);
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM subjects WHERE subject_name='"+topic+"'");
            rs.next();
            subject_id = rs.getInt("subject_id");
            rs.close();
            st.close();
            PreparedStatement ps=cn.prepareStatement("INSERT INTO qa_questions (user_id,subject_id,title,Q_BODY) values(?,?,?,?)");
            ps.setInt(1,user_id);
            ps.setInt(2,subject_id);
            ps.setString(3,title);
            ps.setString(4,body);
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