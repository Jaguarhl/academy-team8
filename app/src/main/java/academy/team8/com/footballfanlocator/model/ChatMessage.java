package academy.team8.com.footballfanlocator.model;

import java.util.Date;

public class ChatMessage {
    private String login;
    private Date moment;
    private String message;
    private String country;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void updateDate() {
        setDate(new Date());
    }

    public void setDate(Date date) {
        this.moment = date;
    }

    public Date getDate() {
        return moment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}