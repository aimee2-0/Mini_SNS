package account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

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

    public int Regist(){
        if(name==""||pass==""||confirm_pass==""){
            return 0;
        }else if(!pass.equals(confirm_pass)){
            return 1;
        }
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection cn=
                DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl",
                    "info","pro");
            cn.setAutoCommit(false);

            String sql="SELECT user_name,password FROM Users";
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                String registname=rs.getString(1);

                if(registname.equals(name)){
                    cn.close();
                    return 2;
                }
            }
            rs.close();
            
            PreparedStatement ps=cn.prepareStatement("INSERT INTO Users (user_name,password) VALUES (?,?)");
            ps.setString(1,name);
            ps.setString(2,pass);
            ps.executeUpdate();

            cn.commit();
            cn.close();
            return 3;

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 4;
    }
}