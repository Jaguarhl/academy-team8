package academy.team8.com.footballfanlocator.model;

import java.util.Date;
import java.util.UUID;

public class ChatMessage {
    private String login;
    private Date moment;
    private String message;
    private String country;
    private String primaryKey;

    public ChatMessage()
    {
        primaryKey = UUID.randomUUID().toString();
    }
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

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
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