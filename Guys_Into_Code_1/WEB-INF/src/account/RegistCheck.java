package account;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import db.DBAccess;

public class RegistCheck{
    private String name;
    private String pass;
    private String confirm_pass;

    public void setNewName(String name){
        this.name=name;
    }
    public void setNewPass(String pass){
        this.pass=pass;
    }
    public void setConfirmPass(String confirm_pass){
        this.confirm_pass=confirm_pass;
    }

    public String getNewName(){
        return name;
    }
    public String getNewPass(){
        return pass;
    }
    public String getConfirmPass(){
        return confirm_pass;
    }

    public int registAccount()throws ClassNotFoundException,SQLException,IOException{
        if(name.equals("")||pass.equals("")||confirm_pass.equals("")){
            return 0;
        }else if(!pass.equals(confirm_pass)){
            return 1;
        }
        
            Connection conn = null;
            DBAccess db=new DBAccess();
            if(db.getConnection() == null || db.getConnection().isClosed()){
                conn = db.accessOracle();
            }else{
                conn = db.getConnection();
            }
            conn.setAutoCommit(false);

            PreparedStatement ps1=conn.prepareStatement("SELECT user_name,password FROM Users");
            ResultSet rs=ps1.executeQuery();

            while(rs.next()){
                String registname=rs.getString(1);

                if(registname.equals(name)){
                    rs.close();
                    ps1.close();
                    conn.close();
                    return 2;
                }
            }
            rs.close();
            
            PreparedStatement ps2=conn.prepareStatement("INSERT INTO Users (user_name,password) VALUES (?,?)");
            ps2.setString(1,name);
            ps2.setString(2,pass);
            ps2.executeUpdate();

            conn.commit();
            ps1.close();
            ps2.close();
            conn.close();
            return 3;

    }
}