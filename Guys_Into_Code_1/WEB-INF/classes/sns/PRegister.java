package sns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class PRegister {
    private int user_id;
    private String body;
    private int post_id;

    public void setUserId(int user_id){
        this.user_id=user_id;
    }
    public void setBody(String body){
        this.body=body;
    }
    public void setPid(int post_id) {
        this.post_id = post_id;
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


    public void RegistPost(){
        Connection cn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", "info", "pro");

            cn.setAutoCommit(false);

            ps = cn.prepareStatement(
                    "INSERT INTO sns_posts (user_id, p_body) VALUES (?, ?)"
            );
            ps.setInt(1, user_id);
            ps.setString(2, body);

            ps.executeUpdate();
            cn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB接続に失敗しました");
            try {
                if (cn != null) cn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBCドライバのロードに失敗しました。");
        } finally {
            try {
                if (ps != null) ps.close();
                if (cn != null) cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}