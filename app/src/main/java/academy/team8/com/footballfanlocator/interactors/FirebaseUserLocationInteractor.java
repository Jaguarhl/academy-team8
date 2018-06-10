package academy.team8.com.footballfanlocator.interactors;


import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Observable;

import academy.team8.com.footballfanlocator.User;
import academy.team8.com.footballfanlocator.presenters.SendLocationPresenter;

public class FirebaseUserLocationInteractor extends Observable implements LocationListener {

    private static final String TAG = "FirebaseLocationInter";


    private SendLocationPresenter presenter;
    private Location currentLocation;

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "changesd");
        currentLocation = location;
        Log.i(TAG, "тест1" + location.getLatitude() + " test1 " + location.getLongitude());
        this.setChanged();
        notifyObservers();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i(TAG, "onStatusChanged");

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i(TAG, "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i(TAG, "onProviderDisabled");
    }

    public Location getCurrentLocation() {
        Log.i(TAG, "getCurrentLocation");
        return currentLocation;
    }

    public void updateUserCoordinates(User user) {
        Log.i(TAG, "пришел!!!");
        //  new Firebase("https://<your-firebase>/currentUsers");
        FirebaseDatabase mFirebaseDb = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDb.getReference();
        String country =  user.getCountry();
        country = "england";
        myRef.child(country).child(user.getId()).setValue(user);
    }
}
