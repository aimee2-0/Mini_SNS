package prof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import db.DBAccess;

public class UpdateAbout{
    private int userid;
    private String about;

    public void setUserid(int userid){
        this.userid = userid;
    }

    public int getUserid(){
           return userid; 
        }

    public void setAbout(String about){
        this.about = about;
    }

    public String getAbout(){
        return about;
    }
    
    
    public  void userAbout() throws ClassNotFoundException,SQLException{
       
        //JDBC関連の変数を宣言
        Connection conn = null;
        PreparedStatement ps = null;

        //JDBCドライバのロード
        DBAccess db=new DBAccess();
        if(db.getConnection() == null || db.getConnection().isClosed()){
            conn = db.accessOracle();
        }else{
            conn = db.getConnection();
        }

        conn.setAutoCommit(false);

        ps = conn.prepareStatement("UPDATE users SET user_info=? WHERE user_id=?");
        ps.setString(1, about);
        ps.setInt(2, userid);
        ps.executeUpdate();
        conn.commit();    
        conn.close();
        } 
}

