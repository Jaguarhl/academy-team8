package academy.team8.com.footballfanlocator;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import academy.team8.com.footballfanlocator.interfaces.MapUpdate;

public class MapActivity extends AppCompatActivity implements MapUpdate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, MapActivity.class));
    }

    @Override
    public void updateCurrentPosition(Location location) {
        return;
    }

    @Override
    public void updateListUsersPositions(Location location) {
        return;
    }
}
