package academy.team8.com.footballfanlocator.interactors;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import java.util.Observable;

import academy.team8.com.footballfanlocator.presenters.SendLocationPresenter;

public class FirebaseUserLocationInteractor extends Observable implements LocationListener {
    private static final String TAG = "ExerciseInteractor";


    private SendLocationPresenter presenter;
    private Location currentLocation;

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "changesd");
        currentLocation = location;
        Log.i(TAG, "тест1" + location.getLatitude() + " test1 "+location.getLongitude());
        this.setChanged();
        notifyObservers();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    public Location getCurrentLocation() {
        return currentLocation;
    }
}
