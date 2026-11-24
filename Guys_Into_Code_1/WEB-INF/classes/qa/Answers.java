package qa;

public class Answers{
    private int answer_id;
    private int user_id;
    private String user_name;
    private String a_body;
    private String a_created_at;

    public void setAid(int answer_id) {
        this.answer_id = answer_id;
    }
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
    public void setUserName(String user_name){
        this.user_name=user_name;
    }
    public void setAbody(String a_body){
        this.a_body=a_body;
    }
    public void setAcreatedAt(String a_created_at){
        this.a_created_at=a_created_at;
    }


    public int getAid() {
        return answer_id;
    }
    public int getUserId() {
        return user_id;
    }
    public String getUserName(){
        return user_name;
    }
    public String getAbody(){
        return a_body;
    }
    public String getAcreatedAt(){
        return a_created_at;
    }
}