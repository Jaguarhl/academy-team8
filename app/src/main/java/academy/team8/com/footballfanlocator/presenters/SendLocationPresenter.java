package academy.team8.com.footballfanlocator.presenters;

import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import academy.team8.com.footballfanlocator.interactors.FirebaseUserLocationInteractor;
import academy.team8.com.footballfanlocator.interfaces.MapVIew;

public class SendLocationPresenter implements Observer {

    private static final String TAG = "SendLocationPresenter";
    private LocationManager locationManager;
    private MapVIew mapVIew;
    private FirebaseUserLocationInteractor firebaseUserLocationInteractor = new FirebaseUserLocationInteractor();

    public SendLocationPresenter(MapVIew mapVIew, LocationManager locationManager){
        this.mapVIew = mapVIew;
        this.locationManager =  locationManager;
    }

    public void initialize() {
        try {
            if (locationManager == null){
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 50, firebaseUserLocationInteractor);
        } catch (SecurityException e) {
            Log.e(TAG, "Эх, нихера себе!",e);
        } catch (NullPointerException e){
            Log.e(TAG, "null pointer",e);
        }
    }

    @Override
    public void update(Observable subject, Object arg) {
        if(subject instanceof FirebaseUserLocationInteractor){
            FirebaseUserLocationInteractor userLocationInteractor = (FirebaseUserLocationInteractor) subject;
            Location location = userLocationInteractor.getCurrentLocation();
//            Message msg = new Message();
//            msg.obj = bt;
            mapVIew.updateCurrentPosition(location);
        }

        if(subject instanceof FirebaseUserLocationInteractor){
            FirebaseUserLocationInteractor usersLocationListInteractor = (FirebaseUserLocationInteractor) subject;
            Location location = usersLocationListInteractor.getCurrentLocation();
//            Message msg = new Message();
//            msg.obj = bt;
            mapVIew.updateCurrentPosition(location);
            mapVIew.updateListUsersPositions(location);
        }
    }
}
