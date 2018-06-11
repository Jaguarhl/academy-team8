package academy.team8.com.footballfanlocator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity {
    AutoCompleteTextView nickTextView;
    AutoCompleteTextView contactTextView;

    ApplicationSettings applicationSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Button signInButton = findViewById(R.id.sign_in_button);
        nickTextView = findViewById(R.id.register_username);
        contactTextView = findViewById(R.id.contact);
        applicationSettings = new ApplicationSettings(this.getApplicationContext());
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFieldsAndContinue();
            }
        });
    }

    private void checkFieldsAndContinue() {
        boolean cancel = false;
        View focusView = null;

        nickTextView.setError(null);
        String nick = nickTextView.getText().toString();
        if (TextUtils.isEmpty(nick)) {
            nickTextView.setError(getString(R.string.error_field_required));
            focusView = nickTextView;
            cancel = true;
        }

        contactTextView.setError(null);
        String contact = contactTextView.getText().toString();
        if (TextUtils.isEmpty(contact)) {
            contactTextView.setError(getString(R.string.error_field_required));
            focusView = contactTextView;
            cancel = true;
        }

        if (contact.indexOf("+") != 0 && contact.indexOf("@") != 0) {
            contactTextView.setError(getString(R.string.error_field_invalid));
            focusView = contactTextView;
            cancel = true;
        }

        if (cancel) {
            if (focusView != null) {
                focusView.requestFocus();
            }
        } else {
            applicationSettings.setUserInfo(nick, contact);
            openNextActivity();
        }
    }

    private void openNextActivity() {
        ActivityChooseCountry.start(this);
    }

    public static void start(Activity activity) {
        Intent signInActivity = new Intent(activity, SignInActivity.class);
        activity.startActivity(signInActivity);
    }
}
