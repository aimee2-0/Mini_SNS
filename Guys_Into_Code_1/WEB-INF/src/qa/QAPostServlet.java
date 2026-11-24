package qa;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig
public class QAPostServlet extends HttpServlet{
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
                        String title = req.getParameter("title");
                        String body = req.getParameter("body");
                        String topic = req.getParameter("topic");

                        QRegister QR = new QRegister();
                        QR.setUserId(user_id);
                        QR.setTitle(title);
                        QR.setBody(body);
                        QR.setTopic(topic);

                        Part imagePart = req.getPart("postImage");
                        if (imagePart != null && imagePart.getSize() > 0) {
                            InputStream imageStream = imagePart.getInputStream();
                            QR.setImageStream(imageStream);
                        }

                        QR.registQuestion();

                        RequestDispatcher dispatcher =
                        req.getRequestDispatcher("Top");
                        dispatcher.forward(req,res);
                    }catch(ClassNotFoundException e){
                        e.printStackTrace();
                        req.setAttribute("message", "Questionの投稿に失敗しました。");
                        RequestDispatcher rd = req.getRequestDispatcher("QAPost");
                        rd.forward(req, res);
                    }catch(SQLException e){
                        e.printStackTrace();
                        req.setAttribute("message", "Questionの投稿に失敗しました。");
                        RequestDispatcher rd = req.getRequestDispatcher("QAPost");
                        rd.forward(req, res);
                    }catch(Exception e){
                        e.printStackTrace();
                        req.setAttribute("message", "Questionの投稿に失敗しました。");
                        RequestDispatcher rd = req.getRequestDispatcher("QAPost");
                        rd.forward(req, res);
                    }
            }
}