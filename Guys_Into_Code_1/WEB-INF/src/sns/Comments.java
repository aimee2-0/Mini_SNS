package sns;

public class Comments{
    private int user_id;
    private String user_name;
    private int c_id;
    private String c_body;
    private String c_created_at;

    public void setUserId(int user_id){
        this.user_id = user_id;
    }

    public void setUserName(String user_name){
        this.user_name = user_name;
    }

    public int getUserId(){
        return user_id;
    }

    public void setCommentId(int c_id) {
        this.c_id = c_id;
    }

    public void setCbody(String c_body){
        this.c_body = c_body;
    }

    public void setCcreatedAt(String c_created_at){
        this.c_created_at = c_created_at;
    }

    public String getUserName(){
        return user_name;
    }

    public int getCommentId() {
        return c_id;
    }

    public String getCbody(){
        return c_body;
    }

    public String getCcreatedAt(){
        return c_created_at;
    }
}