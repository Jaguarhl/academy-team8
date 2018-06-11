package academy.team8.com.footballfanlocator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ApplicationSettings settings = new ApplicationSettings(this.getApplicationContext());

        if (settings.getLogin() == null) {
            SignInActivity.start(this);
        } else if (settings.getCountry() == null) {
            ActivityChooseCountry.start(this);
        } else {
            MapActivity.start(this);
        }
    }
}
