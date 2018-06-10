package academy.team8.com.footballfanlocator;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by anton.gorbunov on 10.06.2018.
 */

class ApplicationSettings {
    private static final String APPLICATION_PREFS_NAME = "FANS_LOCATOR_PREFS";
    private static final String ID = "ID";
    private static final String LOGIN = "LOGIN";
    private static final String LATITUDE = "LATITUDE";
    private static final String LONGITUDE = "LONGITUDE";
    private static final String COUNTRY = "COUNTRY";
    SharedPreferences preferences;

    public ApplicationSettings(Application application) {
        preferences = application.getSharedPreferences(APPLICATION_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setCurrentUser(User user)
    {
        setId(user.getId().toString());
        setUserInfo(user.getLogin(), user.getCountry());
        setLocation(user.getLatitude(), user.getLongitude());
    }

    public void setId(String id)
    {
        preferences.edit().putString(ID, id.toString());
        preferences.edit().commit();
    }

    public void setUserInfo(String login, String country)
    {
        preferences.edit().putString(LOGIN, login);
        preferences.edit().putString(COUNTRY, country);
        preferences.edit().commit();
    }

    public void setLocation(float latitude, float longitude){
        preferences.edit().putFloat(LONGITUDE, longitude);
        preferences.edit().putFloat(LATITUDE, latitude);
        preferences.edit().commit();
    }

    public User getCurrentUser()
    {
        String login = preferences.getString(LOGIN, null);
        if(login == null)
        {
            return null;
        }
        User user = new User();
        user.setId(preferences.getString(ID, null));
        user.setLogin(preferences.getString(LOGIN, null));
        user.setCountry(preferences.getString(COUNTRY, null));
        user.setLocation(
                preferences.getFloat(LATITUDE, 0),
                preferences.getFloat(LONGITUDE, 0));
        return user;
    }
}
