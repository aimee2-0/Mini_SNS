package sns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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


    public void RegistComment(){
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","info","pro");
            cn.setAutoCommit(false);
            PreparedStatement ps=cn.prepareStatement("INSERT INTO sns_comments (user_id, post_id,c_body) values(?,?,?)");
            ps.setInt(1,user_id);
            ps.setInt(2, post_id);
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