package prof;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class UpdateAboutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
        throws IOException, ServletException {
           
            HttpSession session = req.getSession();
            if (session == null || session.getAttribute("user_id") == null) {
                res.sendRedirect("LoginPage");
                return;
            }
            
            int userid = (int) session.getAttribute("user_id"); 
            String about =req.getParameter("about");

            UpdateAbout up = new UpdateAbout(); //インスタンス化するためにコンストラクタ()を呼び出す。コンストラクタ：インスタンスを初期化するために呼び出す。

            up.setAbout(about);
            up.setUserid(userid);
            up.userAbout();

            RequestDispatcher dispatcher = 
            req.getRequestDispatcher("ProfileServlet");

            dispatcher.forward(req,res);
            
        }


}