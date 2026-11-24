package prof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class UpdateProfile{
    private int user_id;
    private String fullname;
    private String skills;
    private String school;

    public void setUser_id(int user_id){
        this.user_id = user_id;
    }

    public int getUser_id(){
        return user_id;
    }

    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    public String getFullname(){
        return fullname;
    }

    public void setSkills(String skills){
        this.skills = skills;
    }

    public String getSkills(){
        return skills;
    }

    public void setSchool(String school){
        this.school = school;
    }

    public String getSchool(){
        return school;
    }


    
    
    public  void userUpdate() {
       
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

            ps = conn.prepareStatement("UPDATE users SET full_name=?, skill=?, school=? WHERE user_id=?");
            ps.setString(1, fullname);
            ps.setString(2, skills);
            ps.setString(3, school);
            ps.setInt(4, user_id);
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

