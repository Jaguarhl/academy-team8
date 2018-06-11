package academy.team8.com.footballfanlocator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.List;

import academy.team8.com.footballfanlocator.interfaces.MapVIew;
import academy.team8.com.footballfanlocator.presenters.SendLocationPresenter;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapVIew, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    private static final int PERMISSION_REQUEST_ID = 12345;
    private static final String TAG = "MapActivity";
    private GoogleMap googleMap;
    LocationManager locationManager;
    SendLocationPresenter presenter;
    BitmapDescriptor icon;
    private DrawerLayout mDrawerLayout;

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
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.relogin_menuitem:
                                SignInActivity.start(getActivity());
                                break;

                            case R.id.chat_menuitem:
                                //HolyWarActivity.start(getActivity());
                                break;
                        }
                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });
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

    private MapActivity getActivity() {
        return this;
    }

    private void initializeMap(GoogleMap googleMap) {
        if (this.googleMap == null) {
            this.googleMap = googleMap;

            googleMap.setOnMarkerClickListener(this);
            googleMap.setOnInfoWindowClickListener(this);
            googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        }
    }

    public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private View view;

        public CustomInfoWindowAdapter() {
            view = getLayoutInflater().inflate(R.layout.custom_info_window, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            final String title = marker.getTitle();
            final AppCompatTextView titleUi = view.findViewById(R.id.info_window_text);
            if (title != null) {
                titleUi.setText(title);
            } else {
                titleUi.setText("");
            }

            AppCompatButton btn = view.findViewById(R.id.info_window_button);
            if (btn != null) {
                final String contact = marker.getSnippet();
                if (StringUtil.isNullOrEmpty(contact)) {
                    btn.setVisibility(View.GONE);
                } else {
                    btn.setVisibility(View.VISIBLE);
                }
            }
            return view;
        }


        @Override
        public View getInfoContents(Marker marker) {
            if (marker != null
                    && marker.isInfoWindowShown()) {
                marker.hideInfoWindow();
                marker.showInfoWindow();
            }
            return null;
        }
    }

    public String checkAndGetTelegramApp() {
        final String appName = "org.telegram.messenger";

        PackageManager pm = this.getPackageManager();
        try {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return appName;
        } catch (Exception e) {
            Toast.makeText(this, "Telegram not Installed", Toast.LENGTH_SHORT).show();
            return null;
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
        Log.i(TAG, "updateListUsersPositions: users count=" + users.size());
        Calendar now = Calendar.getInstance();
        for (User user : users) {
            {
                Calendar userCal = Calendar.getInstance();
                userCal.setTime(user.getDate());
                long diff = now.getTimeInMillis() - userCal.getTimeInMillis();
                if (diff / (60 * 1000) > 10000) //diff in minutes
                    continue;
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
            //TODO тут протекли абстрации в умершего котёнка и нарушили SRP
            initialize();
        }
    }

    public static void start(Activity activity) {
        Intent mapActivity = new Intent(activity, MapActivity.class);
        activity.startActivity(mapActivity);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(StringUtil.isNullOrEmpty(marker.getTitle()))
            return true;
        return false;
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        if(!StringUtil.isNullOrEmpty(marker.getSnippet()))
        {
            String contact = marker.getSnippet();
            if (contact.indexOf("@") == 0) {
                contact = contact.replace("@","");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://telegram.me/" + contact));
                String appName = checkAndGetTelegramApp();
                if (appName != null)
                    i.setPackage(appName);
                getActivity().startActivity(i);
            } else if (contact.indexOf("+") == 0) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", contact, null)));
            }
        }
    }
}