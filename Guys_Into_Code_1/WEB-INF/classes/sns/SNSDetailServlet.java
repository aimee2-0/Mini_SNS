package sns;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SNSDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            res.sendRedirect("LoginPage");
            return;
        }

        String pidParam = req.getParameter("pid");
        if (pidParam == null || pidParam.isEmpty()) {
            pidParam = req.getParameter("post_id");
        }
        if (pidParam == null || pidParam.isEmpty()) {
            res.sendRedirect("Top");
            return;
        }

        int pid;
        try {
            pid = Integer.parseInt(pidParam);
        } catch (NumberFormatException e) {
            res.sendRedirect("Top");
            return;
        }

        SNSview sns = new SNSview();
        Posts post = sns.getPostById(pid);
        if (post == null) {
            res.sendRedirect("Top");
            return;
        }

        req.setAttribute("post", post);
        RequestDispatcher dispatcher = req.getRequestDispatcher("SNSDetailPage");
        dispatcher.forward(req, res);
    }
}

