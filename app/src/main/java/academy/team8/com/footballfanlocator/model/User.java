package academy.team8.com.footballfanlocator.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class User {
    private String id;
    private String login;
    private Date moment;
    private float latitude, longitude;
    private String country;
    private String primaryKey;
    private String contact;

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

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void updateDate() {
        setDate(new Date());
    }

    public void setDate(Date date) {
        this.moment = date;
    }

    public Date getDate(){
        return moment;
    }

    public String getContact() {
        return contact;
    }

    public LatLng getLatLng() {
        return  new LatLng(latitude, longitude);
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}