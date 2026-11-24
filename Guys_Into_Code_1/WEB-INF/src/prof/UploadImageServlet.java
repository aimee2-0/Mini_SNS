package prof;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;


@MultipartConfig
public class UploadImageServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
                    try{
                        req.setCharacterEncoding("utf-8");

                        HttpSession session = req.getSession(false);
                        if (session == null || session.getAttribute("user_id") == null) {
                            res.sendRedirect("LoginPage");
                            return;
                        }
                        int user_id = (int)session.getAttribute("user_id");
                        Part part = req.getPart("profilePhoto");
                        InputStream in = part.getInputStream();

                        ImageRegister ir = new ImageRegister();
                        ir.setId(user_id);
                        ir.setStream(in);
                        ir.updateImage();

                        RequestDispatcher dispatcher = req.getRequestDispatcher("ProfileServlet");
                        dispatcher.forward(req,res);
                    }catch(ClassNotFoundException e){
                        e.printStackTrace();
                        req.setAttribute("message", "アップロードに失敗しました。");
                        RequestDispatcher rd = req.getRequestDispatcher("ProfileServlet");
                        rd.forward(req, res);
                    }catch(SQLException e){
                        e.printStackTrace();
                        req.setAttribute("message", "アップロードに失敗しました。");
                        RequestDispatcher rd = req.getRequestDispatcher("ProfileServlet");
                        rd.forward(req, res);
                    }catch(Exception e){
                        e.printStackTrace();
                        req.setAttribute("message", "アップロードに失敗しました。");
                        RequestDispatcher rd = req.getRequestDispatcher("ProfileServlet");
                        rd.forward(req, res);
                    }
                }
}