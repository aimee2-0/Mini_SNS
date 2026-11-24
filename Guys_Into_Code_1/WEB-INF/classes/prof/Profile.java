package prof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class Profile {
    //使用するSQL、ユーザー名、パスワードを定義として定義

    public static final String PROFILE_SQL ="SELECT user_id, full_name, user_info,  skill, school FROM users";

    public static final String user = "info";
    public static final String pass = "pro";

    private String username;
    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }    

    //users表からResultSetを取得してそれをArrayListに詰めなおしてreturn
    public  List<String> getusersList() {
        //ユーザー情報を格納するListを用意
        List<String> usersList = new ArrayList<>();

        //JDBC関連の変数を宣言
        Connection conn = null;
        Statement stmt = null;
        ResultSet  rs = null;

        //JDBCドライバのロード
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //データベースへ接続し、SQLを実行
            conn=
            DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",user,pass);
            stmt = conn.createStatement();
            rs = stmt.executeQuery( PROFILE_SQL + " WHERE user_name ='" + username + "'");


                //取得したデータをListに格納
                        rs.next();
                        usersList.add(rs.getString("user_id"));
                        usersList.add(rs.getString("full_name"));
                        usersList.add(rs.getString("user_info"));
                   
                        usersList.add(rs.getString("skill"));
                        usersList.add(rs.getString("school"));  
                        usersList.add(username);
                    
                }catch (SQLException e){
                    e.printStackTrace();
                    System.out.println("DB接続に失敗しました。");
                }catch(ClassNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("JDBCドライバのロードに失敗しました。");
                }finally{
                    try{
                        rs.close();
                        stmt.close();
                        conn.close();
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
                return usersList;
            }
}