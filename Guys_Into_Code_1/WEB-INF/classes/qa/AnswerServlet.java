package qa;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class AnswerServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
                    req.setCharacterEncoding("utf-8");

                    HttpSession session = req.getSession(false);
                    if (session == null || session.getAttribute("user_id") == null) {
                        res.sendRedirect("LoginPage");
                        return;
                    }
                    int user_id = (int)session.getAttribute("user_id");
                    String question = req.getParameter("question_id");
                    int question_id = Integer.parseInt(question);
                    String body = req.getParameter("comment");

                    ARegister AR = new ARegister();

                    AR.setUserId(user_id);
                    AR.setQid(question_id);
                    AR.setBody(body);

                    AR.RegistAnswer();

                    String redirect = req.getParameter("redirect");
                    if ("QADetail".equals(redirect)) {
                        res.sendRedirect("QADetail?qid=" + question_id);
                        return;
                    }

                    RequestDispatcher dispatcher = req.getRequestDispatcher("Top");
                    dispatcher.forward(req,res);
                }
}