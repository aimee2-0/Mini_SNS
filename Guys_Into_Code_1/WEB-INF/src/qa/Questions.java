package qa;

import java.util.List;
import java.util.ArrayList;

public class Questions{
    private int question_id;
    private int user_id;
    private String user_name;
    private String subject_name;
    private String title;
    private String q_body;
    private String q_created_at;

    public List<Answers> answersList = new ArrayList<>();

    public void setQid(int question_id){
        this.question_id=question_id;
    }

    public void setUserId(int user_id){
        this.user_id=user_id;
    }

    public void setUserName(String user_name){
        this.user_name=user_name;
    }

    public void setSubjectName(String subject_name){
        this.subject_name=subject_name;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setQbody(String q_body){
        this.q_body=q_body;
    }

    public void setQcreatedAt(String q_created_at){
        this.q_created_at=q_created_at;
    }

    public int getQid(){
        return question_id;
    }

    public int getUserId(){
        return user_id;
    }

    public String getUserName(){
        return user_name;
    }

    public String getSubjectName(){
        return subject_name;
    }

    public String getTitle(){
        return title;
    }

    public String getQbody(){
        return q_body;
    }

    public String getQcreatedAt(){
        return q_created_at;
    }

    public List<Answers> getAnswersList(){
        return answersList;
    }
}