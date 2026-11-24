package sns;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;


@MultipartConfig
public class SNSPostServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
                    try{
                    req.setCharacterEncoding("utf-8");

                    HttpSession session = req.getSession();
                    if (session == null || session.getAttribute("user_id") == null) {
                        res.sendRedirect("LoginPage");
                        return;
                    }
                    int user_id = (int)session.getAttribute("user_id");
                    String body = req.getParameter("body");

                    PRegister pr = new PRegister();
                    pr.setUserId(user_id);
                    pr.setBody(body);

                    Part imagePart = req.getPart("postImage");
                    if (imagePart != null && imagePart.getSize() > 0) {
                        InputStream imageStream = imagePart.getInputStream();
                        pr.setImageStream(imageStream);
                    }

                    pr.registPost();

                    RequestDispatcher dispatcher =
                        req.getRequestDispatcher("Top");
                    dispatcher.forward(req,res);
                }catch(ClassNotFoundException e){
                e.printStackTrace();
                req.setAttribute("message", "エラー:投稿できませんでした。");
                RequestDispatcher rd = req.getRequestDispatcher("SNSPost");
                rd.forward(req, res);
                 }catch(SQLException e){
                e.printStackTrace();
                req.setAttribute("message", "エラー:投稿できませんでした。");
                RequestDispatcher rd = req.getRequestDispatcher("SNSPost");
                rd.forward(req, res);
                 }catch(Exception e){
                e.printStackTrace();
                req.setAttribute("message", "エラー:投稿できませんでした。");
                RequestDispatcher rd = req.getRequestDispatcher("SNSPost");
                rd.forward(req, res);
                }
    }
}