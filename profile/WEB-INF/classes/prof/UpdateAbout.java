package prof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

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
    
    
    public  void userAbout() {
       
        //JDBC関連の変数を宣言
        Connection conn = null;
        PreparedStatement ps = null;

        //JDBCドライバのロード
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //データベースへ接続し、SQLを実行
            conn=
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","info","pro");

            conn.setAutoCommit(false);

            ps = conn.prepareStatement("UPDATE users SET user_info=? WHERE user_id=?");
            ps.setString(1, about);
            ps.setInt(2, userid);
            ps.executeUpdate();
            conn.commit();
                
                }catch (SQLException e){
                    e.printStackTrace();
                    System.out.println("DB接続に失敗しました。");
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("JDBCドライバのロードに失敗しました。");
                }finally{
                    try{
                        conn.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            } 
}

