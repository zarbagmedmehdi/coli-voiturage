package bean;

import java.io.Serializable;

public class FeedBack implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id ;
    private String feedback ;
    private User user ;

    public FeedBack(Long id, String feedback, User user) {
        this.id = id;
        this.feedback = feedback;
        this.user = user;
    }

    public FeedBack(Long id) {

        this.id = id;
    }

    public FeedBack() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FeedBack{" +
                "id=" + id +
                ", feedback='" + feedback + '\'' +
                ", user=" + user +
                '}';
    }
}
