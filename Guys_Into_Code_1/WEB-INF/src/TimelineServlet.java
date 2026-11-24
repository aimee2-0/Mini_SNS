import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;

import qa.QAview;
import qa.Questions;
import sns.SNSview;
import sns.Posts;

public class TimelineServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, IOException {
                    doPost(req, res);
                }

    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
                    try{
                        req.setCharacterEncoding("utf-8");

                        HttpSession session = req.getSession(false);
                        if (session == null || session.getAttribute("user_id") == null) {
                            res.sendRedirect("LoginPage");
                            return;
                        }

                        QAview qa = new QAview();
                        List<Questions> QATimeline = qa.showQA();
                        req.setAttribute("QATimeline",QATimeline);

                        SNSview sns = new SNSview();
                        List<Posts> SNSTimeline = sns.showSNS();
                        req.setAttribute("SNSTimeline", SNSTimeline);

                        int qaCount = (QATimeline != null) ? QATimeline.size() : 0;
                        int snsCount = (SNSTimeline != null) ? SNSTimeline.size() : 0;
                        req.setAttribute("qaCount", qaCount);
                        req.setAttribute("snsCount", snsCount);

                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("TopPage");
                        dispatcher.forward(req,res);
                    }catch(ClassNotFoundException e){
                        req.setAttribute("message","エラーが発生しました。ログインページに戻ります。");
                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("LoginPage");
                        dispatcher.forward(req,res);
                    }catch(SQLException e){
                        req.setAttribute("message","エラーが発生しました。ログインページに戻ります。");
                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("LoginPage");
                        dispatcher.forward(req,res);
                    }catch(Exception e){
                        req.setAttribute("message","エラーが発生しました。ログインページに戻ります。");
                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("LoginPage");
                        dispatcher.forward(req,res);
                    }
    }
}