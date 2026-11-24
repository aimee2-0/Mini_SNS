package prof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import db.DBAccess;

public class Profile {
    //使用するSQL、ユーザー名、パスワードを定義として定義

    public static final String PROFILE_SQL ="SELECT user_id, full_name, user_info,  skill, school FROM users";

    private String username;
    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }    

    //users表からResultSetを取得してそれをArrayListに詰めなおしてreturn
    public  List<String> getusersList() throws ClassNotFoundException,SQLException{
        //ユーザー情報を格納するListを用意
        List<String> usersList = new ArrayList<>();

        //JDBC関連の変数を宣言
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet  rs = null;

        //JDBCドライバのロード
        DBAccess db=new DBAccess();
        if(db.getConnection() == null || db.getConnection().isClosed()){
            conn = db.accessOracle();
        }else{
            conn = db.getConnection();
        }
        ps = conn.prepareStatement("SELECT user_id, full_name, user_info,  skill, school FROM users WHERE user_name = ?");
        ps.setString(1,username);
        rs = ps.executeQuery();


        //取得したデータをListに格納
        rs.next();
        usersList.add(rs.getString("user_id"));
        usersList.add(rs.getString("full_name"));
        usersList.add(rs.getString("user_info"));
                   
        usersList.add(rs.getString("skill"));
        usersList.add(rs.getString("school"));  
        usersList.add(username);
        rs.close();
        ps.close();
        conn.close();
        return usersList;
    }
}