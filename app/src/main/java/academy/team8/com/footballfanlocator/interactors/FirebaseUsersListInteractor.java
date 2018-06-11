package academy.team8.com.footballfanlocator.interactors;

import android.support.annotation.NonNull;
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

    public void initializeDatabaseListener(String county) {
        FirebaseDatabase mFirebaseDb = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDb.getReference(county);

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
        myList = new ArrayList<>();
        for (DataSnapshot item : dataSnapshot.getChildren()) {
            User user = item.getValue(User.class);
            myList.add(user);
        }
        this.setChanged();
        notifyObservers();
    }

    public List<User> getMyList() {
        return myList;
    }
}
