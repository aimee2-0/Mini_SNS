package account;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
                    try{
                        req.setCharacterEncoding("utf-8");

                        String username = req.getParameter("name");
                        String password = req.getParameter("pass");


                        LoginCheck lc = new LoginCheck();
                        lc.setName(username);
                        lc.setPass(password);

                        req.setAttribute("name",username);
                        req.setAttribute("pass",password);

                        int check = lc.check();

                        if(check == 0){
                            int user_id = lc.getUserId();
                            HttpSession session = req.getSession();
                            session.setAttribute("username",username);
                            session.setAttribute("user_id",user_id);
                            RequestDispatcher dispatcher =
                                req.getRequestDispatcher("Top");
                            dispatcher.forward(req,res);
                        }else if(check == 1){
                            req.setAttribute("errortext","※Username or Password is wrong");
                            RequestDispatcher dispatcher =
                                req.getRequestDispatcher("LoginPage");
                            dispatcher.forward(req,res);
                        }
                    }catch(ClassNotFoundException e){
                        e.printStackTrace();
                        req.setAttribute("message", "エラー:ログインできませんでした。");
                        RequestDispatcher rd = req.getRequestDispatcher("LoginPage");
                        rd.forward(req, res);
                    }catch(SQLException e){
                        e.printStackTrace();
                        req.setAttribute("message", "エラー:ログインできませんでした。");
                        RequestDispatcher rd = req.getRequestDispatcher("LoginPage");
                        rd.forward(req, res);
                    }catch(Exception e){
                        e.printStackTrace();
                        req.setAttribute("message", "エラー:ログインできませんでした。");
                        RequestDispatcher rd = req.getRequestDispatcher("LoginPage");
                        rd.forward(req, res);
                    }
                }
}