package image;

import java.io.InputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import db.DBAccess;

public class QAPostImage {
    private int questionId;

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public BufferedImage viewImage()throws ClassNotFoundException,SQLException,IOException{
            Connection conn = null;
            DBAccess db = new DBAccess();
            if (db.getConnection() == null || db.getConnection().isClosed()) {
                conn = db.accessOracle();
            } else {
                conn = db.getConnection();
            }
            PreparedStatement ps = conn.prepareStatement("SELECT media FROM qa_questions WHERE question_id = ?");
            ps.setInt(1, questionId);
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
