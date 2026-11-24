package prof;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import db.DBAccess;

public class ImageRegister{
    private int id;
    private InputStream in;

    public void setStream(InputStream in){
        this.in=in;
    }

    public InputStream getStream(){
        return in;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getId(){
        return id;
    }

    public void updateImage() throws ClassNotFoundException,SQLException{
        Connection conn = null;
        DBAccess db=new DBAccess();
        if(db.getConnection() == null || db.getConnection().isClosed()){
            conn = db.accessOracle();
        }else{
            conn = db.getConnection();
        }
        PreparedStatement ps = conn.prepareStatement("UPDATE Users SET user_image = ? where user_id=?");
        conn.setAutoCommit(false);
        ps.setBlob(1,in);
        ps.setInt(2,id);
        ps.executeUpdate();
        conn.commit();
        ps.close();
        conn.close();
    }
}