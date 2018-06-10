package academy.team8.com.footballfanlocator.presenters;

import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import academy.team8.com.footballfanlocator.MapActivity;
import academy.team8.com.footballfanlocator.SendLocationActivity;
import academy.team8.com.footballfanlocator.User;
import academy.team8.com.footballfanlocator.interactors.FirebaseUserLocationInteractor;
import academy.team8.com.footballfanlocator.interactors.FirebaseUsersListInteractor;
import academy.team8.com.footballfanlocator.interfaces.MapUpdate;

public class SendLocationPresenter implements Observer {

    private static final String TAG = "SendLocationPresenter";
    private LocationManager locationManager;
    private MapUpdate mapActivity;
    private FirebaseUserLocationInteractor firebaseUserLocationInteractor = new FirebaseUserLocationInteractor();
    private FirebaseUsersListInteractor firebaseUsersListInteractor = new FirebaseUsersListInteractor();
    private User user;

    public SendLocationPresenter(SendLocationActivity mapActivity, User user) {
        this.user = user;
        this.mapActivity = mapActivity;

        firebaseUsersListInteractor.updateUserCoordinates(this.user);
        firebaseUserLocationInteractor.addObserver(this);
        Log.i(TAG, "Location init");
        LocationManager locationManager = (LocationManager) mapActivity.getApplicationContext().getSystemService(mapActivity.LOCATION_SERVICE);
        //https://stackoverflow.com/questions/32491960/android-check-permission-for-locationmanager?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
        try {
            if (locationManager == null) {
                Log.i(TAG, "LocationManager is null");
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    100, 500, firebaseUserLocationInteractor);
            //Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //Log.i(TAG, "Latitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude());
        } catch (SecurityException e) {
            Log.i(TAG, "Эх, нихера себе!", e);
        } catch (NullPointerException e) {
            Log.i(TAG, "null pointer", e);
        }


    }

    @Override
    public void update(Observable subject, Object arg) {
        Log.i(TAG, "update: пришел!!! ");
        if (subject instanceof FirebaseUserLocationInteractor) {
            FirebaseUserLocationInteractor userLocationInteractor = (FirebaseUserLocationInteractor) subject;
            Location location = userLocationInteractor.getCurrentLocation();
            /////////////////////////////////
            //СУПЕР КОД ОТ адепта PHP
            double a = location.getLatitude();
            float latitude = (float) a;
            double b = location.getLongitude();
            float longtitude = (float) b;
            ////////////////////////////////
            user.setLocation(latitude, longtitude);
            firebaseUserLocationInteractor.updateUserCoordinates(user);
            mapActivity.updateCurrentPosition(location);
        }

        if (subject instanceof FirebaseUsersListInteractor) {
            FirebaseUsersListInteractor usersLocationListInteractor = (FirebaseUsersListInteractor) subject;
            Location location = firebaseUserLocationInteractor.getCurrentLocation();
//            Message msg = new Message();
//            msg.obj = bt;
            mapActivity.updateCurrentPosition(location);
            mapActivity.updateListUsersPositions(location);

        }
    }
}
