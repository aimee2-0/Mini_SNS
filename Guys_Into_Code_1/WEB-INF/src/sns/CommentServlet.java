package sns;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CommentServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
                    try{
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

                        CR.registComment();

                        String redirect = req.getParameter("redirect");
                        if ("SNSDetail".equals(redirect)) {
                            res.sendRedirect("SNSDetail?pid=" + post_id);
                            return;
                        }

                        RequestDispatcher dispatcher = req.getRequestDispatcher("Top");
                        dispatcher.forward(req,res);
                    }catch(ClassNotFoundException e){
                        e.printStackTrace();
                        req.setAttribute("message", "Sessionが失敗しました。");
                        RequestDispatcher rd = req.getRequestDispatcher("input.jsp");
                        rd.forward(req, res);
                    }catch(SQLException e){
                        e.printStackTrace();
                        req.setAttribute("message", "SQL接続に失敗しました。");
                        RequestDispatcher rd = req.getRequestDispatcher("input.jsp");
                        rd.forward(req, res);
                    }catch(Exception e){
                        e.printStackTrace();
                        req.setAttribute("message", "Sessionが失敗しました。");
                        RequestDispatcher rd = req.getRequestDispatcher("input.jsp");
                        rd.forward(req, res);
                    }
                }
}