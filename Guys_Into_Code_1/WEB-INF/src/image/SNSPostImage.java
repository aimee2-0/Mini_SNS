package image;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import db.DBAccess;

public class SNSPostImage {
    private int postId;

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public BufferedImage viewImage()throws ClassNotFoundException,SQLException,IOException {
        
            Connection conn = null;
            DBAccess db = new DBAccess();
            if (db.getConnection() == null || db.getConnection().isClosed()) {
                conn = db.accessOracle();
            } else {
                conn = db.getConnection();
            }
            PreparedStatement ps = conn.prepareStatement("SELECT media FROM sns_posts WHERE post_id = ?");
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                InputStream in = rs.getBinaryStream("media");
                if (in != null) {
                    BufferedInputStream bin = new BufferedInputStream(in);
                    return ImageIO.read(bin);
                }
            }
            rs.close();
            ps.close();
            conn.close();
        
        return null;
    }
}
