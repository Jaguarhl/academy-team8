package academy.team8.com.footballfanlocator;

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_sign_in_button:
                Log.i(TAG, "onClick: opening AsyncTaskImplementation");

               // SignInActivity.start(v.getContext());
                break;
            case R.id.login_register_button:
                Log.i(TAG, "onClick: opening Hander Implementation");
                ActivityChooseCountry.start(v.getContext());
                break;
        }
    }
}
