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
    private String country;

    public String getCountry(){
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public float getLatitude(){
        return latitude;
    }

    public float getLongitude(){
        return longitude;
    }

    public void setLocation(float latitude, float longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        updateDate();
    }

    public void setId(UUID id){
        this.id = id;
    }
    public void setId(String id){
        this.id = UUID.fromString(id);
    }

    public UUID getId(){
        return id;
    }

    public void updateDate() {
        setDate(new Date());
    public User(String login)
    {
        this.id = UUID.randomUUID();
        this.login = login;
        this.moment = new Date();
        this.primaryKey = this.id.toString();
    }

    public void setDate(Date date) {
        this.moment = date;
    }

    public Date getDate(){
        return moment;
    }

    public String getPrimaryKey(){
        return  this.primaryKey;
    }

}