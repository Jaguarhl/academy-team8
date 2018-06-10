package academy.team8.com.footballfanlocator.interactors;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Observable;

import academy.team8.com.footballfanlocator.User;

public class FirebaseUsersListInteractor extends Observable {

    private String TAG = "FirebaseListInteractor";

    public void init(){
        Log.i(TAG, "пришел!!!");
        //  new Firebase("https://<your-firebase>/currentUsers");
        FirebaseDatabase mFirebaseDb = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDb.getReference();
        User user = new User("ash_williams@mail.ru");
        myRef.child("users").child(user.getPrimaryKey()).setValue(user);
    }

}
