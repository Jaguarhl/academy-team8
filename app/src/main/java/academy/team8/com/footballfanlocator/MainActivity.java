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

        findViewById(R.id.login_sign_in_button).setOnClickListener(this);
        findViewById(R.id.login_register_button).setOnClickListener(this);
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
