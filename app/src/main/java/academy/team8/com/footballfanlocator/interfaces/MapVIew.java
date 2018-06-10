package academy.team8.com.footballfanlocator.interfaces;

import android.location.Location;

public interface MapVIew {
    void updateCurrentPosition(Location location);
    void updateListUsersPositions(Location location);
    void requestPermissions();
}
