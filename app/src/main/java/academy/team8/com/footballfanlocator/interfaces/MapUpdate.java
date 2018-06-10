package academy.team8.com.footballfanlocator.interfaces;

import android.location.Location;

public interface MapUpdate {
    public void updateCurrentPosition(Location location);
    public void updateListUsersPositions(Location location);
}
