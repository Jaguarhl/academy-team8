package academy.team8.com.footballfanlocator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SendLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_location);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, SendLocationActivity.class));
    }
}
