package qa;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QADetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            res.sendRedirect("LoginPage");
            return;
        }

        String qidParam = req.getParameter("qid");
        if (qidParam == null || qidParam.isEmpty()) {
            qidParam = req.getParameter("question_id");
        }
        if (qidParam == null || qidParam.isEmpty()) {
            res.sendRedirect("Top");
            return;
        }

        int qid;
        try {
            qid = Integer.parseInt(qidParam);
        } catch (NumberFormatException e) {
            res.sendRedirect("Top");
            return;
        }

        QAview qa = new QAview();
        Questions question = qa.getQuestionById(qid);
        if (question == null) {
            res.sendRedirect("Top");
            return;
        }

        req.setAttribute("QA", question);
        RequestDispatcher dispatcher = req.getRequestDispatcher("QADetailPage");
        dispatcher.forward(req, res);
    }
}

