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
        SharedPreferences preferences = getSharedPreferences("FANS_LOCATOR_PREFS", Context.MODE_PRIVATE);
        if (preferences.getString("LOGIN", null) == null) {
            SignInActivity.start(this);
        } else {
            MapActivity.start(this);
        }
    }
}
