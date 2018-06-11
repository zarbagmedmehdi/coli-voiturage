package bean;

import java.io.Serializable;
import java.util.Date;

public class Message  implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id ;
    private  User  receiver ;
    private  User   sender;
    private Date dateEnvoi ;
    private String message ;

    public Message(Long id, User receiver, User sender, Date dateEnvoi, String message) {
        this.id = id;
        this.receiver = receiver;
        this.sender = sender;
        this.dateEnvoi = dateEnvoi;
        this.message = message;
    }

    public Message() {

    }

    public Message(Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", receiver=" + receiver +
                ", sender=" + sender +
                ", dateEnvoi=" + dateEnvoi +
                ", message='" + message + '\'' +
                '}';
    }
}
