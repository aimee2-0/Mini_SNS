package account;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LogoutServlet extends HttpServlet{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)
            throws ServletException,IOException{
                HttpSession session = req.getSession();
                session.invalidate();
                res.sendRedirect("LoginPage");
            }
}