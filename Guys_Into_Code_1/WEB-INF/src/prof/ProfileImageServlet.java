package prof;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import image.UserImage;

public class ProfileImageServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        try{
            HttpSession session = req.getSession(false);
                        if (session == null || session.getAttribute("user_id") == null) {
                            res.sendRedirect("LoginPage");
                            return;
                        }
            int user_id = (int)session.getAttribute("user_id");
            UserImage ui = new UserImage();
            ui.setId(user_id);
            BufferedImage img = ui.viewImage();
            if (img == null) {
                res.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            OutputStream out = res.getOutputStream();
            ImageIO.write(img,"jpg",out);
            res.setContentType("image/jpg");
            out.flush();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            req.setAttribute("message", "エラーが発生しました。");
            RequestDispatcher rd = req.getRequestDispatcher("Top");
            rd.forward(req, res);
        }catch(SQLException e){
            e.printStackTrace();
            req.setAttribute("message", "エラーが発生しました。");
            RequestDispatcher rd = req.getRequestDispatcher("Top");
            rd.forward(req, res);
        }catch(Exception e){
            e.printStackTrace();
            req.setAttribute("message", "エラーが発生しました。");
            RequestDispatcher rd = req.getRequestDispatcher("Top");
            rd.forward(req, res);
        }
    }
}
