package prof;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DBAccess;


public class CountCheck{
    
    private int user_id;
    private int sns_posts;
    private int questions;
    private int answers;

    public void setUser_id(int user_id){
        this.user_id = user_id;
    }

    public int getUser_id(){
        return user_id;
    }    

    public void setSns_posts(int sns_posts){
         this.sns_posts = sns_posts;
    }

    public int getSns_posts(){
        return sns_posts;
    } 

    public void setQuestions(int questions){
         this.questions = questions;
    }

    public int getQuestions(){
        return questions;
    }

    public void setAnswers(int answers){
         this.answers = answers;
    }

    public int getAnswers(){
        return answers;
    }

    
    public  void acountGet() throws ClassNotFoundException,SQLException{
        
        //JDBC関連の変数を宣言
        ResultSet re1= null;
        ResultSet re2= null;
        ResultSet re3= null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
    

        //JDBCドライバのロード
            Connection conn = null;
            DBAccess db=new DBAccess();
            if(db.getConnection() == null || db.getConnection().isClosed()){
                conn = db.accessOracle();
            }else{
                conn = db.getConnection();
            }
            
            ps1 = conn.prepareStatement( "SELECT COUNT(*) FROM sns_posts WHERE user_id = ?");
            ps1.setInt(1,user_id);
            ps2 = conn.prepareStatement( "SELECT COUNT(*) FROM qa_questions WHERE user_id = ?");
            ps2.setInt(1,user_id);
            ps3 = conn.prepareStatement( "SELECT COUNT(*) FROM qa_answers WHERE user_id = ?");
            ps3.setInt(1,user_id);

            re1 =ps1.executeQuery();
            re1.next();
            setSns_posts(re1.getInt(1));

            re2 =ps2.executeQuery();
            re2.next();
            setQuestions(re2.getInt(1));

            re3 =ps3.executeQuery();
            re3.next();
            setAnswers(re3.getInt(1));

            re1.close();
            re2.close();
            re3.close();
            ps1.close();
            ps2.close();
            ps3.close();

            conn.close();
            }
}