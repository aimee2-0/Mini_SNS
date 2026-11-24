import prof.Profile;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws IOException, ServletException {
           
            HttpSession session = req.getSession();
            session.setAttribute("userName", "いさか");
            String userNamae = (String) session.getAttribute("userName"); 

             Profile pro = new Profile();
             pro.setUsername(userNamae);
            
            List<String> list = pro.getusersList();
            req.setAttribute("userList", list);
            RequestDispatcher dispatcher = 
            req.getRequestDispatcher("profilePage");

            dispatcher.forward(req,res);
            
        }


}