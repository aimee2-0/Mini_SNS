package account;
import java.sql.SQLException;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
                throws ServletException, IOException {
                    try{
                    req.setCharacterEncoding("utf-8");

                    String username = req.getParameter("name");
                    String password = req.getParameter("pass");
                    String confirm_password = req.getParameter("confirm_pass");




                    RegistCheck regist = new RegistCheck();
                    regist.setNewName(username);
                    regist.setNewPass(password);
                    regist.setConfirmPass(confirm_password);

                    int result = regist.registAccount();
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
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                    req.setAttribute("message", "エラー:登録できませんでした。");
                    RequestDispatcher rd = req.getRequestDispatcher("UserRegist");
                    rd.forward(req, res);
                }catch(SQLException e){
                    e.printStackTrace();
                    req.setAttribute("message", "エラー:登録できませんでした。");
                    RequestDispatcher rd = req.getRequestDispatcher("UserRegist");
                    rd.forward(req, res);
                }catch(Exception e){
                    e.printStackTrace();
                    req.setAttribute("message", "エラー:登録できませんでした。");
                    RequestDispatcher rd = req.getRequestDispatcher("UserRegist");
                    rd.forward(req, res);
                }
    }
}