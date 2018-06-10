package academy.team8.com.footballfanlocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start_activity_sign_in).setOnClickListener(this);
        findViewById(R.id.start_activity_map).setOnClickListener(this);
        findViewById(R.id.start_activity_send_current_location).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_activity_sign_in:
                Log.i(TAG, "onClick: opening AsyncTaskImplementation");
                SignInActivity.start(v.getContext());
                break;
            case R.id.start_activity_map:
                Log.i(TAG, "onClick: opening Hander Implementation");
                MapActivity.start(v.getContext());
                break;
        }
    }
}
