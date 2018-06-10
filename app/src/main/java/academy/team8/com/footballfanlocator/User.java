package academy.team8.com.footballfanlocator;

import java.util.Date;
import java.util.UUID;

/**
 * Created by anton.gorbunov on 10.06.2018.
 */

public class User {
    private UUID id;
    private String login;
    private Date moment;
    private float latitude, longitude;
    private String primaryKey;

    public float getLatitude(){
        return latitude;
    }

    public float getLongitude(){
        return longitude;
    }

    public Date getDate(){
        return moment;
    }

    public UUID getId(){
        return id;
    }

    public User(String login)
    {
        this.id = UUID.randomUUID();
        this.login = login;
        this.moment = new Date();
        this.primaryKey = this.id.toString();
    }

    public User(String login, float latitude, float longitude)
    {
        this(login);
        SetLocation(latitude, longitude);
    }

    public void SetLocation(float latitude, float longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPrimaryKey(){
        return  this.primaryKey;
    }

}