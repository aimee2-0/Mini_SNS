package sns;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class SNSPostServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
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

                    pr.RegistPost();

                    RequestDispatcher dispatcher =
                        req.getRequestDispatcher("Top");
                    dispatcher.forward(req,res);
                }
}