package image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.OutputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import db.DBAccess;

public class UserImage {
    private int id;
    private InputStream in;

    public void setId(int id){
        this.id = id;
    }

    public void setStream(InputStream in){
        this.in=in;
    }

    public int getId(){
        return id;
    }

    public InputStream getStream(){
        return in;
    }

    public BufferedImage viewImage()throws ClassNotFoundException,SQLException,IOException{
        
            Connection conn = null;
            DBAccess db=new DBAccess();
            if(db.getConnection() == null || db.getConnection().isClosed()){
                conn = db.accessOracle();
            }else{
                conn = db.getConnection();
            }
            PreparedStatement ps = conn.prepareStatement("SELECT user_image FROM Users where user_id = ?");
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                InputStream in = rs.getBinaryStream("user_image");
                BufferedInputStream bin = new BufferedInputStream(in);
                return ImageIO.read(bin);
            }
            
        return null;
    }

}