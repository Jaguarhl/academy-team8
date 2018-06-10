package academy.team8.com.footballfanlocator.presenters;

import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import academy.team8.com.footballfanlocator.User;
import academy.team8.com.footballfanlocator.interactors.FirebaseUserLocationInteractor;
import academy.team8.com.footballfanlocator.interfaces.MapVIew;
import academy.team8.com.footballfanlocator.interactors.FirebaseUsersListInteractor;

public class SendLocationPresenter implements Observer {

    private static final String TAG = "SendLocationPresenter";
    private LocationManager locationManager;
    private MapVIew mapVIew;
    private FirebaseUserLocationInteractor firebaseUserLocationInteractor = new FirebaseUserLocationInteractor();
    private FirebaseUsersListInteractor firebaseUsersListInteractor = new FirebaseUsersListInteractor();
    private User user;

    public SendLocationPresenter(MapVIew mapVIew, LocationManager locationManager, User user) {
        this.mapVIew = mapVIew;
        this.locationManager = locationManager;
        this.user = user;
    }

    public void initialize() {
        firebaseUsersListInteractor.updateUserCoordinates(this.user);
        firebaseUserLocationInteractor.addObserver(this);
        Log.i(TAG, "Location init");
        try {
            if (locationManager == null) {
                Log.i(TAG, "LocationManager is null");
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 50, firebaseUserLocationInteractor);
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
            mapVIew.updateCurrentPosition(location);
        }

        if (subject instanceof FirebaseUsersListInteractor) {
            FirebaseUsersListInteractor usersLocationListInteractor = (FirebaseUsersListInteractor) subject;
            Location location = firebaseUserLocationInteractor.getCurrentLocation();
//            Message msg = new Message();
//            msg.obj = bt;
            mapVIew.updateCurrentPosition(location);
            mapVIew.updateListUsersPositions(location);
        }
    }
}
