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

    private Location currentLocation;

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "changesd");
        currentLocation = location;
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
        return currentLocation;
    }

    public void updateUserCoordinates(User user) {
        Log.i(TAG, "initializeDatabaseListener: login=" + user.getLogin() + "; country=" + user.getCountry());
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference();
        String country =  user.getCountry();
        myRef.child(country).child(user.getId()).setValue(user);
    }
}
