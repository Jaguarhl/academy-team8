package academy.team8.com.footballfanlocator.interactors;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Observable;

import academy.team8.com.footballfanlocator.Holivar;

public class HolivarSendInteractor extends Observable {

    private static final String TAG = "HolivarSendInteractor";

    public void sendHolivarMessage(Holivar holivar) {
        Log.i(TAG, "Sending Message");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference();
        myRef.child("holivar").setValue(holivar);
        setChanged();
        notifyObservers();
    }
}
