package academy.team8.com.footballfanlocator;

import java.util.Date;

public class Holivar {
    private String login;
    private Date moment;
    private String message;

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
}