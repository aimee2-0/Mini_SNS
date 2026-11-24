import qa.Answers;
import qa.Questions;
import sns.Posts;

import usersprof.*;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;

public class UsersProfServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws IOException, ServletException {
            
            HttpSession ss = req.getSession(false);
            if (ss == null || ss.getAttribute("user_id") == null) {
                res.sendRedirect("LoginPage");
                return;
            }


            int userid = (int)ss.getAttribute("userid");
            String username = (String)ss.getAttribute("username");

            UsersProfileCheck upc = new UsersProfileCheck();
            upc.setUsername(username);
            List<String> list = upc.UsersProfList();

            UsersCountCheck ucc = new UsersCountCheck();
            ucc.UsersCounts();

            HyojiANSWER ha = new HyojiANSWER();
            List<Answers> answerList = ha.SelectAnswersByUser(userid);

            HyojiQUESTION hq = new HyojiQUESTION();
            List<Questions> questionList = hq.SelectQuestionsByUser(userid);

            HyojiSNS hs = new HyojiSNS();
            List<Posts> postList = hs.SelectPostsByUser(userid);
            

            req.setAttribute("snsPostCount", ucc.getSnsPost());
            req.setAttribute("questionsCount", ucc.getQuestions());
            req.setAttribute("answerCount", ucc.getAnswers());

            req.setAttribute("usersProfList", list);

            req.setAttribute("answerList", answerList);
            req.setAttribute("questionList", questionList);
            req.setAttribute("questionList", questionList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("UsersProfServlet");
            dispatcher.forward(req, res);

        }
}