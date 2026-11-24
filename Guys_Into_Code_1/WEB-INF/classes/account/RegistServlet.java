package account;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
                    req.setCharacterEncoding("utf-8");

                    String username = req.getParameter("name");
                    String password = req.getParameter("pass");
                    String confirm_password = req.getParameter("confirm_pass");




                    RegistCheck regist = new RegistCheck();
                    regist.setNewName(username);
                    regist.setNewPass(password);
                    regist.setConfirmPass(confirm_password);

                    int result = regist.Regist();
                    if(result == 0){
                        req.setAttribute("errortext","Please enter at least one character");
                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("UserRegist");
                        dispatcher.forward(req,res);
                    }else if(result == 1){
                        req.setAttribute("errortext","Please enter the same password");
                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("UserRegist");
                        dispatcher.forward(req,res);
                    }else if(result == 2){
                        req.setAttribute("errortext","This username is already in use");
                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("UserRegist");
                        dispatcher.forward(req,res);
                    }else if(result == 3){
                        req.setAttribute("successtext","Registration successful");
                        RequestDispatcher dispatcher =
                            req.getRequestDispatcher("LoginPage");
                        dispatcher.forward(req,res);
                    }
                }
}