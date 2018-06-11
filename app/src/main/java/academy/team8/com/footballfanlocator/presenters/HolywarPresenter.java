package academy.team8.com.footballfanlocator.presenters;

import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import academy.team8.com.footballfanlocator.model.ChatMessage;
import academy.team8.com.footballfanlocator.interactors.HolivarGetInteractor;
import academy.team8.com.footballfanlocator.interactors.HolivarSendInteractor;
import academy.team8.com.footballfanlocator.interfaces.HolywarView;
import academy.team8.com.footballfanlocator.model.User;

public class HolywarPresenter implements Observer {

    private static final String TAG = "SendLocationPresenter";
    private LocationManager locationManager;
    private final HolywarView holywarView;
    private final User currentUser;
    private HolivarGetInteractor holivarGetInteractor = new HolivarGetInteractor();
    private HolivarSendInteractor holivarSendInteractor = new HolivarSendInteractor();

    public HolywarPresenter(HolywarView mapVIew, User currentUser) {
        this.holywarView = mapVIew;
        this.currentUser = currentUser;
    }

    public void initialize() {
        holivarSendInteractor.addObserver(this);
        holivarGetInteractor.addObserver(this);
        holivarGetInteractor.initializeDatabaseListener();
    }

    @Override
    public void update(Observable subject, Object arg) {
        Log.i(TAG, "update HolywarPresenter");
        if (subject instanceof HolivarSendInteractor) {
            Log.i(TAG, "HolivarSendInUpdate");
//            HolivarSendInteractor holivarSendInteractor = (HolivarSendInteractor) subject;
//            holivarSendInteractor.sendHolivarMessage(arg.toString());
        }

        if (subject instanceof HolivarGetInteractor) {
            Log.i(TAG, "HolivarGetInteractor");
            HolivarGetInteractor holivarGetInteractor = (HolivarGetInteractor) subject;
            holywarView.onChatUpdate(holivarGetInteractor.getHolivarList());
        }
    }

    public void sendMessage(String text) {
        ChatMessage chatMessage = createChatMessage(text);
        holivarSendInteractor.sendHolivarMessage(chatMessage);
    }

    @NonNull
    private ChatMessage createChatMessage(String text) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setLogin(currentUser.getLogin());
        chatMessage.setDate(new Date());
        chatMessage.setCountry(currentUser.getCountry());
        chatMessage.setMessage(text);
        return chatMessage;
    }
}
