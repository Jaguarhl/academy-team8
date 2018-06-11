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

import academy.team8.com.footballfanlocator.Holivar;

public class HolivarGetInteractor extends Observable {

    private List<Holivar> myList;

    public void initializeDatabaseListener() {
        FirebaseDatabase mFirebaseDb = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDb.getReference("holivar");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getHolivarsListFromFirebase(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getHolivarsListFromFirebase(DataSnapshot dataSnapshot) {
        myList = new ArrayList<>();

        for (DataSnapshot item : dataSnapshot.getChildren()) {
            Holivar holivar = item.getValue(Holivar.class);
            myList.add(holivar);
        }

        this.setChanged();
        notifyObservers();
    }

    public List<Holivar> getHolivarList() {
        return myList;
    }

}
