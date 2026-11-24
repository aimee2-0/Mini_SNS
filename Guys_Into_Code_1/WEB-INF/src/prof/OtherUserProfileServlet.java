package prof;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class OtherUserProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
        throws IOException, ServletException {
            try{
                HttpSession session = req.getSession();
                if (session == null || session.getAttribute("user_id") == null) {
                    res.sendRedirect("LoginPage");
                    return;
                }
                String userNamae = req.getParameter("userName");
                String id = req.getParameter("user_id");
                int userid = Integer.parseInt(id);

                Profile pro = new Profile();
                pro.setUsername(userNamae);
                
                List<String> list = pro.getusersList();

                CountCheck cou = new CountCheck();
                cou.setUser_id(userid);
                cou.acountGet();

                int sns_posts= cou.getSns_posts();
                int question = cou.getQuestions();
                int answer = cou.getAnswers();

                UserActivity activity = new UserActivity();
                List<qa.Questions> myQuestions = activity.getUserQuestions(userid);
                List<sns.Posts> myPosts = activity.getUserPosts(userid);
                List<UserAnswer> myAnswers = activity.getUserAnswers(userid);

                req.setAttribute("userList", list);
                req.setAttribute("snsPostCount",sns_posts);
                req.setAttribute("questionsCount",question);
                req.setAttribute("answerCount",answer);
                req.setAttribute("myQuestions", myQuestions);
                req.setAttribute("myPosts", myPosts);
                req.setAttribute("myAnswers", myAnswers);

                RequestDispatcher dispatcher = 
                req.getRequestDispatcher("OtherProfilePage");

                dispatcher.forward(req,res);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                    req.setAttribute("message", "エラーが発生しました。トップに戻ります。");
                    RequestDispatcher rd = req.getRequestDispatcher("Top");
                    rd.forward(req, res);
                }catch(SQLException e){
                    e.printStackTrace();
                    req.setAttribute("message", "エラーが発生しました。トップに戻ります。");
                    RequestDispatcher rd = req.getRequestDispatcher("Top");
                    rd.forward(req, res);
                }catch(Exception e){
                    e.printStackTrace();
                    req.setAttribute("message", "エラーが発生しました。トップに戻ります。");
                    RequestDispatcher rd = req.getRequestDispatcher("Top");
                    rd.forward(req, res);
                }
        }


}