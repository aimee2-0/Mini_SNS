package account;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
                    req.setCharacterEncoding("utf-8");

                    String username = req.getParameter("name");
                    String password = req.getParameter("pass");


                    LoginCheck lc = new LoginCheck();
                    lc.setName(username);
                    lc.setPass(password);

                    req.setAttribute("name",username);
                    req.setAttribute("pass",password);

                    if(lc.Check() == 0){
                        int user_id = lc.getUserId();
                        HttpSession session = req.getSession();
                        session.setAttribute("username",username);
                        session.setAttribute("user_id",user_id);
                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("Top");
                        dispatcher.forward(req,res);
                    }else if(lc.Check() == 1){
                        req.setAttribute("errortext","※Password is wrong");
                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("LoginPage");
                        dispatcher.forward(req,res);
                    }else if(lc.Check() == 2){
                        req.setAttribute("errortext","※This user is not registered");
                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("LoginPage");
                        dispatcher.forward(req,res);
                    }
                }
}