package prof;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ProfileEditServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
        throws IOException, ServletException {
            try{
                HttpSession session = req.getSession();
                if (session == null || session.getAttribute("user_id") == null) {
                    res.sendRedirect("LoginPage");
                    return;
                }
                int userid = (int) session.getAttribute("user_id"); 
                String fullname =req.getParameter("fullname");
                String skills =req.getParameter("skills");
                String school =req.getParameter("school");

                UpdateProfile up = new UpdateProfile(); //インスタンス化するためにコンストラクタ()を呼び出す。コンストラクタ：インスタンスを初期化するために呼び出す。

                up.setUser_id(userid);
                up.setFullname(fullname);
                up.setSkills(skills);
                up.setSchool(school);
                up.userUpdate();

                RequestDispatcher dispatcher = 
                req.getRequestDispatcher("ProfileServlet");

                dispatcher.forward(req,res);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
                req.setAttribute("message", "エラー:プロフィールの編集に失敗しました。");
                RequestDispatcher rd = req.getRequestDispatcher("ProfileServlet");
                rd.forward(req, res);
            }catch(SQLException e){
                e.printStackTrace();
                req.setAttribute("message", "エラー:プロフィールの編集に失敗しました。");
                RequestDispatcher rd = req.getRequestDispatcher("ProfileServlet");
                rd.forward(req, res);
            }catch(Exception e){
                e.printStackTrace();
                req.setAttribute("message", "エラー:プロフィールの編集に失敗しました。");
                RequestDispatcher rd = req.getRequestDispatcher("ProfileServlet");
                rd.forward(req, res);
            }
        }


}