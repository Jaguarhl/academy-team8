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
        this.mapVIew = mapVIew;
        this.locationManager = locationManager;
        this.user = user;
    }

    public void initialize() {
        firebaseUsersListInteractor.initializeDatabaseListener(this.user.getCountry());
        firebaseUserLocationInteractor.addObserver(this);
        firebaseUsersListInteractor.addObserver(this);
        Log.i(TAG, "Location init");
        try {
            if (locationManager == null) {
                Log.i(TAG, "LocationManager is null");
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 50, firebaseUserLocationInteractor);
        } catch (SecurityException e) {
            Log.i(TAG, "Эх, нихера себе!", e);
        } catch (NullPointerException e) {
            Log.i(TAG, "null pointer", e);
        }
    }

    @Override
    public void update(Observable subject, Object arg) {
        Log.i(TAG, "update ");
        if (subject instanceof FirebaseUserLocationInteractor) {
            Log.i(TAG, "FirebaseUserLocationInteractor ");
            FirebaseUserLocationInteractor userLocationInteractor = (FirebaseUserLocationInteractor) subject;
            Location location = userLocationInteractor.getCurrentLocation();

            float latitude = (float) location.getLatitude();
            float longtitude = (float) location.getLongitude();
            Log.i(TAG, "getCurrentLocation: latitude=" + latitude + "; longtitude="+longtitude);
            user.setLocation(latitude, longtitude);
            firebaseUserLocationInteractor.updateUserCoordinates(user);
            mapVIew.updateCurrentPosition(location);
        }

        if (subject instanceof FirebaseUsersListInteractor) {
            Log.i(TAG, "usersLocationListInteractor ");
            FirebaseUsersListInteractor usersLocationListInteractor = (FirebaseUsersListInteractor) subject;
            mapVIew.updateListUsersPositions(firebaseUsersListInteractor.getMyList());
        }
    }
}
