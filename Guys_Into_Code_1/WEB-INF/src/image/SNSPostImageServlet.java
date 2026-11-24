package image;
import jakarta.servlet.RequestDispatcher;
import java.sql.SQLException;
import java.io.IOException;
import java.io.OutputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SNSPostImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try{
        String id = req.getParameter("post_id");
        if (id == null || id.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int postId = Integer.parseInt(id);
        SNSPostImage spi = new SNSPostImage();
        spi.setPostId(postId);
        BufferedImage img = spi.viewImage();
        if (img == null) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        OutputStream out = res.getOutputStream();
        ImageIO.write(img, "jpg", out);
        res.setContentType("image/jpeg");
        out.flush();
        }catch(ClassNotFoundException e){
        e.printStackTrace();
        req.setAttribute("message", "投稿に失敗しました");
        RequestDispatcher rd = req.getRequestDispatcher("SNSPost");
        rd.forward(req, res);
        }catch(SQLException e){
        e.printStackTrace();
        req.setAttribute("message", "投稿に失敗しました。");
        RequestDispatcher rd = req.getRequestDispatcher("SNSPost");
        rd.forward(req, res);
        }catch(Exception e){
        e.printStackTrace();
        req.setAttribute("message", "投稿に失敗しました");
        RequestDispatcher rd = req.getRequestDispatcher("SNSPost");
        rd.forward(req, res);
        }
    }
}
