package academy.team8.com.footballfanlocator.interactors;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import academy.team8.com.footballfanlocator.User;

public class FirebaseUsersListInteractor extends Observable {

    private String TAG = "FirebaseListInteractor";
    private List<User> myList;

    public void updateUserCoordinates(User user) {
        Log.i(TAG, "пришел!!!");
        //  new Firebase("https://<your-firebase>/currentUsers");
        FirebaseDatabase mFirebaseDb = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDb.getReference("england");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getUsersListFromFirebase(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getUsersListFromFirebase(DataSnapshot dataSnapshot) {
        //уже получили по рефу ингленд
        myList = new ArrayList<>();
        for (DataSnapshot item : dataSnapshot.getChildren()) {
            User user = item.getValue(User.class);
            myList.add(user);
            this.setChanged();
            notifyObservers();
        }
    }

    public List<User> getMyList() {
        return myList;
    }
}
