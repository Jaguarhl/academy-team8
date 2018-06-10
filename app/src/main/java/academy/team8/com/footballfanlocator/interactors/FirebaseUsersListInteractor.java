package academy.team8.com.footballfanlocator.interactors;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Observable;

import academy.team8.com.footballfanlocator.User;

public class FirebaseUsersListInteractor extends Observable {

    private String TAG = "FirebaseListInteractor";

    public void updateUserCoordinates(User user){
        Log.i(TAG, "пришел!!!");
        //  new Firebase("https://<your-firebase>/currentUsers");
        FirebaseDatabase mFirebaseDb = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDb.getReference();
        myRef.child("users").child(user.getId()).setValue(user);
    }

}
