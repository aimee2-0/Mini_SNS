package account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

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

    public int Check(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection cn=
                DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl",
                    "info","pro");
            String sql="SELECT user_id,user_name,password FROM Users";

            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                String registname=rs.getString("user_name");
                String registpass=rs.getString("password");

                if(registname.equals(name)){
                    if(registpass.equals(pass)){
                        setUserId(rs.getInt("user_id"));
                        cn.close();
                        return 0;
                    }else{
                        cn.close();
                        return 1;
                    }
                }
            }
            cn.close();
            return 2;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 3;
    }
}