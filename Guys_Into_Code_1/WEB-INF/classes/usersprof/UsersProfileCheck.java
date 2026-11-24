package usersprof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class UsersProfileCheck {
    //使用するSQL、ユーザー名、パスワードを定義として定義
    public static final String PROFILE_SQL ="SELECT user_id, full_name, user_info, skill, school, user_name FROM users WHERE user_name = ? ";

    public static final String user = "info";
    public static final String pass = "pro";

    private String username;

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }    

    public  List<String> UsersProfList() {
        List<String> usersProfList = new ArrayList<>();
        
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBCドライバのロードに失敗しました。");
            return usersProfList;
        }

        try (
            Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl", user, pass);
            PreparedStatement ps = conn.prepareStatement(PROFILE_SQL)
        ) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usersProfList.add(String.valueOf(rs.getInt("user_id")));
                    usersProfList.add(rs.getString("full_name"));
                    usersProfList.add(rs.getString("user_info"));
                    usersProfList.add(rs.getString("skill"));
                    usersProfList.add(rs.getString("school"));
                    usersProfList.add(rs.getString("user_name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB接続に失敗しました。");
        }

        return usersProfList;
    }
}