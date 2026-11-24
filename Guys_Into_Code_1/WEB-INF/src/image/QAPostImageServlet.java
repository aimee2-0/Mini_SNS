package image;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import jakarta.servlet.RequestDispatcher;

public class QAPostImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try{
        String id = req.getParameter("qid");
        if (id == null || id.isEmpty()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int questionId = Integer.parseInt(id);
        QAPostImage qpi = new QAPostImage();
        qpi.setQuestionId(questionId);
        BufferedImage img = qpi.viewImage();
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
                req.setAttribute("message", "エラー:投稿できませんでした。");
                RequestDispatcher rd = req.getRequestDispatcher("QAPost");
                rd.forward(req, res);
        }catch(SQLException e){
                e.printStackTrace();
                req.setAttribute("message", "エラー:投稿できませんでした。");
                RequestDispatcher rd = req.getRequestDispatcher("QAPost");
                rd.forward(req, res);
        }catch(Exception e){
                e.printStackTrace();
                req.setAttribute("message", "エラー:投稿できませんでした。");
                RequestDispatcher rd = req.getRequestDispatcher("QAPost");
                rd.forward(req, res);
        }
    }
}
