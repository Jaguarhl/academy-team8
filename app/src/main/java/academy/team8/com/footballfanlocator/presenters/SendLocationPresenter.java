package academy.team8.com.footballfanlocator.presenters;

import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import academy.team8.com.footballfanlocator.User;
import academy.team8.com.footballfanlocator.interactors.FirebaseUserLocationInteractor;
import academy.team8.com.footballfanlocator.interactors.FirebaseUsersListInteractor;
import academy.team8.com.footballfanlocator.interfaces.MapVIew;

public class SendLocationPresenter implements Observer {

    private static final String TAG = "SendLocationPresenter";
    private LocationManager locationManager;
    private MapVIew mapVIew;
    private FirebaseUserLocationInteractor firebaseUserLocationInteractor = new FirebaseUserLocationInteractor();
    private FirebaseUsersListInteractor firebaseUsersListInteractor = new FirebaseUsersListInteractor();
    private User user;

    public SendLocationPresenter(MapVIew mapVIew, LocationManager locationManager, User user) {
        this.user = user;
        this.mapVIew = mapVIew;
        this.locationManager = locationManager;

        firebaseUsersListInteractor.updateUserCoordinates(this.user);
        firebaseUserLocationInteractor.addObserver(this);
    }

    public void initialize() {
        try {
            if (locationManager == null){
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 50, firebaseUserLocationInteractor);
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

            float latitude = (float) location.getLatitude();
            float longtitude = (float) location.getLongitude();

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
