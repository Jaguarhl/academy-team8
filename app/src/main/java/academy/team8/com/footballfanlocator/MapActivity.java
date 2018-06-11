package academy.team8.com.footballfanlocator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.Manifest;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import academy.team8.com.footballfanlocator.interfaces.MapVIew;
import academy.team8.com.footballfanlocator.presenters.SendLocationPresenter;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapVIew {
    private static final int PERMISSION_REQUEST_ID = 12345;
    private static final String TAG = "MapActivity";
    private GoogleMap googleMap;
    LocationManager locationManager;
    SendLocationPresenter presenter;
    BitmapDescriptor icon;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
        ApplicationSettings settings = new ApplicationSettings(this.getApplicationContext());

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        presenter = new SendLocationPresenter(this,
                (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE),
                settings.getCurrentUser());

        icon = BitmapDescriptorFactory.fromResource(R.drawable.dot);
    }

    private boolean isApplicationHasPermission(String accessFineLocation) {
        return ContextCompat.checkSelfPermission(
                this.getApplicationContext(), accessFineLocation) != PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
        initialize();
    }

    private void initialize() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
        Location loc;
        try {
            if (locationManager == null) {
                return;
            }
            loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch (SecurityException e) {
            Log.e(TAG, "initialize", e);
            return;
        }
        SetCameraPosition(loc, 12);
        presenter.initialize();
    }

    private void initializeMap(GoogleMap googleMap) {
        if (this.googleMap == null) {
            this.googleMap = googleMap;
        }
    }

    private void SetCameraPosition(final Location location, float zoom) {
        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, zoom));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        initializeMap(googleMap);
        requestPermissions();
    }

    @Override
    public void updateCurrentPosition(Location location) {
        CameraPosition cam = googleMap.getCameraPosition();
        SetCameraPosition(location, cam.zoom);
    }

    @Override
    public void updateListUsersPositions(List<User> users) {
        if (users == null)
            return;
        for (User user : users) {
            {
                googleMap.addMarker(new MarkerOptions()
                        .title(user.getLogin())
                        .snippet(user.getContact())
                        .position(user.getLatLng())
                        .icon(icon));
            }
        }
    }

    @Override
    public void requestPermissions() {
        boolean needToRequest = false;
        String[] permissions = new String[1];

        if (isApplicationHasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissions[0] = Manifest.permission.ACCESS_FINE_LOCATION;
            needToRequest = true;
        }

        if (needToRequest)
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_ID);
        else {
            //TODO тут умер котенок, протекли абстрациии и нарушен SRP
            initialize();
        }
    }

    public static void start(Activity activity) {
        Intent mapActivity = new Intent(activity, MapActivity.class);
        activity.startActivity(mapActivity);
    }
}