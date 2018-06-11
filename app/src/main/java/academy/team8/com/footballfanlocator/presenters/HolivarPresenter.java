package academy.team8.com.footballfanlocator.presenters;

import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import academy.team8.com.footballfanlocator.Holivar;
import academy.team8.com.footballfanlocator.User;
import academy.team8.com.footballfanlocator.interactors.FirebaseUserLocationInteractor;
import academy.team8.com.footballfanlocator.interactors.FirebaseUsersListInteractor;
import academy.team8.com.footballfanlocator.interactors.HolivarGetInteractor;
import academy.team8.com.footballfanlocator.interactors.HolivarSendInteractor;
import academy.team8.com.footballfanlocator.interfaces.HolivarView;
import academy.team8.com.footballfanlocator.interfaces.MapVIew;

public class HolivarPresenter implements Observer {

    private static final String TAG = "SendLocationPresenter";
    private LocationManager locationManager;
    private HolivarView holivarView;
    private HolivarGetInteractor holivarGetInteractor = new HolivarGetInteractor();
    private HolivarSendInteractor holivarSendInteractor = new HolivarSendInteractor();
    private Holivar holivar;

    public HolivarPresenter(HolivarView mapVIew, Holivar holivar) {
        this.holivarView = mapVIew;
        this.holivar = holivar;
    }

    public void initialize() {
        holivarSendInteractor.addObserver(this);
        holivarGetInteractor.addObserver(this);
        holivarGetInteractor.initializeDatabaseListener();
    }

    @Override
    public void update(Observable subject, Object arg) {
        Log.i(TAG, "update ");
        if (subject instanceof HolivarSendInteractor) {
            Log.i(TAG, "HolivarSendInUpdate");
            HolivarSendInteractor holivarSendInteractor = (HolivarSendInteractor) subject;
            holivarSendInteractor.sendHolivarMessage(holivar);
        }

        if (subject instanceof HolivarGetInteractor) {
            Log.i(TAG, "HolivarGetInteractor");
            HolivarGetInteractor holivarGetInteractor = (HolivarGetInteractor) subject;
            holivarView.updateListUsersPositions(holivarGetInteractor.getHolivarList());
        }
    }
}
