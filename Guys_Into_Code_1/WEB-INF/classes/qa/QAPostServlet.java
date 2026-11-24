package qa;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QAPostServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
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

                    QR.RegistQuestion();

                    RequestDispatcher dispatcher =
                        req.getRequestDispatcher("Top");
                    dispatcher.forward(req,res);
                }
}