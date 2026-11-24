package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBAccess{
    private Connection connection = null;

    public void setConnection(Connection connection){
        this.connection=connection;
    }

    public Connection getConnection(){
        return connection;
    }

    public Connection accessOracle() throws ClassNotFoundException,SQLException{
        Class.forName("oracle.jdbc.driver.OracleDriver");
            //データベースへ接続し、SQLを実行
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","info","pro");
        return connection;
    }

    public void closeConnection(){
        try{
            connection.close();
        
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}