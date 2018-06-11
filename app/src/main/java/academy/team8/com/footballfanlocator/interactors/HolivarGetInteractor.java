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

import academy.team8.com.footballfanlocator.model.ChatMessage;

public class HolivarGetInteractor extends Observable {

    private List<ChatMessage> myList;

    public void initializeDatabaseListener() {
        FirebaseDatabase mFirebaseDb = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDb.getReference("chatMessage");
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
            ChatMessage chatMessage = item.getValue(ChatMessage.class);
            myList.add(chatMessage);
        }

        this.setChanged();
        notifyObservers();
    }

    public List<ChatMessage> getHolivarList() {
        return myList;
    }

}
