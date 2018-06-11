package academy.team8.com.footballfanlocator.interfaces;

import android.location.Location;

import java.util.List;

import academy.team8.com.footballfanlocator.model.User;

public interface MapVIew {
    void updateCurrentPosition(Location location);

    void updateListUsersPositions(List<User> location);

    void requestPermissions();
}
