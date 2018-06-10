package academy.team8.com.footballfanlocator;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import academy.team8.com.footballfanlocator.interfaces.MapUpdate;
import academy.team8.com.footballfanlocator.presenters.SendLocationPresenter;

public class SendLocationActivity extends AppCompatActivity implements MapUpdate {
//
//    protected LocationManager locationManager;
//    protected LocationListener locationListener;
//    protected Context context;
//    String lat;
//    String provider;
//    protected String latitude, longitude;
//    protected boolean gps_enabled, network_enabled;
    private SendLocationPresenter sendLocationPresenter;
    private static final String TAG = "SendLocationActivity";
    private static final int REQEST_ID = 12345;

    public static void start(Context context) {
        context.startActivity(new Intent(context, SendLocationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_location);

        //запрашиваем пермишн
        boolean needToRequest = false;
        String[] permissions = new String[2];
        if (
                ContextCompat.checkSelfPermission(
                this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions[0] = Manifest.permission.ACCESS_FINE_LOCATION;
            needToRequest = true;
        }

        if (ContextCompat.checkSelfPermission(
                this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            int i = permissions.length;
            permissions[1] = Manifest.permission.ACCESS_COARSE_LOCATION;
            needToRequest = true;
        }

        Log.d(TAG, "тест1");
        if (needToRequest) {
            requestPermissions(permissions);
        }

        sendLocationPresenter = new SendLocationPresenter(this);

    }

    public void requestPermissions(String[] permissions) {
        ActivityCompat.requestPermissions(this, permissions, REQEST_ID);
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
