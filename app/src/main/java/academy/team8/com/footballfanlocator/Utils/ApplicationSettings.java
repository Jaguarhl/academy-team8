package academy.team8.com.footballfanlocator.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

import academy.team8.com.footballfanlocator.model.User;

public class ApplicationSettings {
    private static final String APPLICATION_PREFS_NAME = "FANS_LOCATOR_PREFS";
    private static final String ID = "ID";
    private static final String LOGIN = "LOGIN";
    private static final String LATITUDE = "LATITUDE";
    private static final String LONGITUDE = "LONGITUDE";
    private static final String COUNTRY = "COUNTRY";
    private static final String CONTACT = "CONTACT";
    private SharedPreferences preferences;

    public ApplicationSettings(Context application) {
        preferences = application.getSharedPreferences(APPLICATION_PREFS_NAME, Context.MODE_PRIVATE);
        if (getId() == null) {
            setId(UUID.randomUUID().toString());
        }
    }

    public void setCurrentUser(User user) {
        setId(user.getId());
        setUserInfo(user.getLogin(), user.getCountry());
        setLocation(user.getLatitude(), user.getLongitude());
    }

    private void setId(String id) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(ID, id).commit();
    }

    private String getId() {
        return preferences.getString(ID, null);
    }


    public void setUserInfo(String login, String contact) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(LOGIN, login);
        edit.putString(CONTACT, contact);
        edit.commit();
    }

    public void setCountry(String country) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(COUNTRY, country);
        edit.commit();
    }

    public void setLocation(float latitude, float longitude) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putFloat(LONGITUDE, longitude);
        edit.putFloat(LATITUDE, latitude);
        edit.commit();
    }

    public User getCurrentUser() {
        String login = preferences.getString(LOGIN, null);
        if (login == null) {
            return null;
        }
        User user = new User();
        user.setId(getId());
        user.setLogin(getLogin());
        user.setCountry(getCountry());
        user.setContact(getContact());
        user.setLocation(
                preferences.getFloat(LATITUDE, 0),
                preferences.getFloat(LONGITUDE, 0));
        return user;
    }

    public String getLogin() {
        return preferences.getString(LOGIN, null);
    }

    public String getCountry() {
        return preferences.getString(COUNTRY, null);
    }

    public String getContact() {
        return preferences.getString(CONTACT, null);
    }
}
