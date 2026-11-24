package sns;

import java.util.List;
import java.util.ArrayList;

public class Posts {
    private int post_id;
    private String user_name;
    private int user_id;
    private String title;
    private String p_body;
    private String p_created_at;

    public List<Comments> commentsList = new ArrayList<>();

    public void setPid(int post_id){
        this.post_id = post_id;
    }

    public void setUserName(String user_name){
        this.user_name = user_name;
    }

    public void setUid(int user_id){
        this.user_id = user_id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setPbody(String p_body){
        this.p_body = p_body;
    }

    public void setPcreatedAt(String p_created_at){
        this.p_created_at = p_created_at;
    }

    public int getPid(){
        return post_id;
    }

    public String getUserName(){
        return user_name;
    }

    public int getUid(){
        return user_id;
    }

    public String getTitle(){
        return title;
    }

    public String getPbody(){
        return p_body;
    }

    public String getPcreatedAt(){
        return p_created_at;
    }

    public List<Comments> getCommentsList(){
        return commentsList;
    }
}
