package account;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import db.DBAccess;

public class LoginCheck{
    private String name;
    private String pass;
    private int user_id;

    public void setName(String name){
        this.name=name;
    }
    public void setPass(String pass){
        this.pass=pass;
    }
    public void setUserId(int user_id){
        this.user_id=user_id;
    }

    public String getName(){
        return name;
    }

    public String getPass(){
        return pass;
    }

    public int getUserId(){
        return user_id;
    }

    public int check()throws ClassNotFoundException,SQLException,IOException{
        
            Connection conn = null;
            DBAccess db=new DBAccess();
            if(db.getConnection() == null || db.getConnection().isClosed()){
                conn = db.accessOracle();
            }else{
                conn = db.getConnection();
            }
            
            PreparedStatement ps=conn.prepareStatement("SELECT user_id,user_name,password FROM Users");
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                String registname=rs.getString("user_name");
                String registpass=rs.getString("password");

                if(registname.equals(name)){
                    if(registpass.equals(pass)){
                        setUserId(rs.getInt("user_id"));
                        rs.close();
                        ps.close();
                        conn.close();
                        return 0;
                    }else{
                        rs.close();
                        ps.close();
                        conn.close();
                        return 1;
                    }
                }
            }
            rs.close();
            ps.close();
            conn.close();
            return 1;
        
    }
}