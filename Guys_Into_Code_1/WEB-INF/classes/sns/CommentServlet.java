package sns;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CommentServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
                    req.setCharacterEncoding("utf-8");

                    HttpSession session = req.getSession(false);
                    if (session == null || session.getAttribute("user_id") == null) {
                        res.sendRedirect("LoginPage");
                        return;
                    }
                    int user_id = (int)session.getAttribute("user_id");
                    String post = req.getParameter("post_id");
                    int post_id = Integer.parseInt(post);
                    String body = req.getParameter("comment");

                    CRegister CR = new CRegister();

                    CR.setUserId(user_id);
                    CR.setPid(post_id);
                    CR.setBody(body);

                    CR.RegistComment();

                    String redirect = req.getParameter("redirect");
                    if ("SNSDetail".equals(redirect)) {
                        res.sendRedirect("SNSDetail?pid=" + post_id);
                        return;
                    }

                    RequestDispatcher dispatcher = req.getRequestDispatcher("Top");
                    dispatcher.forward(req,res);
                }
}