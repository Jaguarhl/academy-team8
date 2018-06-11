package academy.team8.com.footballfanlocator.presenters;

import android.location.LocationManager;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import academy.team8.com.footballfanlocator.model.ChatMessage;
import academy.team8.com.footballfanlocator.interactors.HolivarGetInteractor;
import academy.team8.com.footballfanlocator.interactors.HolivarSendInteractor;
import academy.team8.com.footballfanlocator.interfaces.HolywarView;

public class HolywarPresenter implements Observer {

    private static final String TAG = "SendLocationPresenter";
    private LocationManager locationManager;
    private HolywarView holywarView;
    private HolivarGetInteractor holivarGetInteractor = new HolivarGetInteractor();
    private HolivarSendInteractor holivarSendInteractor = new HolivarSendInteractor();
    private ChatMessage chatMessage;

    public HolywarPresenter(HolywarView mapVIew, ChatMessage chatMessage) {
        this.holywarView = mapVIew;
        this.chatMessage = chatMessage;
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
            holivarSendInteractor.sendHolivarMessage(chatMessage);
        }

        if (subject instanceof HolivarGetInteractor) {
            Log.i(TAG, "HolivarGetInteractor");
            HolivarGetInteractor holivarGetInteractor = (HolivarGetInteractor) subject;
            holywarView.updateListUsersPositions(holivarGetInteractor.getHolivarList());
        }
    }
}
