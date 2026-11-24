package prof;

public class UserAnswer {
    private int questionId;
    private String questionTitle;
    private String answerBody;
    private String answerCreatedAt;

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }

    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerCreatedAt(String answerCreatedAt) {
        this.answerCreatedAt = answerCreatedAt;
    }

    public String getAnswerCreatedAt() {
        return answerCreatedAt;
    }
}

