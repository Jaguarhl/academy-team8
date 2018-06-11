package academy.team8.com.footballfanlocator.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import academy.team8.com.footballfanlocator.R;
import academy.team8.com.footballfanlocator.Utils.ApplicationSettings;
import academy.team8.com.footballfanlocator.Utils.ChatMessageComarator;
import academy.team8.com.footballfanlocator.interfaces.HolywarView;
import academy.team8.com.footballfanlocator.model.ChatMessage;
import academy.team8.com.footballfanlocator.model.User;
import academy.team8.com.footballfanlocator.presenters.HolywarPresenter;

public class ChatActivity extends AppCompatActivity implements HolywarView {
    HolywarPresenter presenter;
    User currentUser;
    TextView textView;
    EditText textEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ApplicationSettings settings = new ApplicationSettings(this.getApplicationContext());
        currentUser = settings.getCurrentUser();
        presenter = new HolywarPresenter(this, currentUser);

        Button sendBtn = findViewById(R.id.chat_send_button);
        textView = findViewById(R.id.chat_text_view);
        textEdit = findViewById(R.id.chat_edit_text);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = textEdit.getText().toString();
                textEdit.setText("");
                presenter.sendMessage(msg);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        presenter.initialize();
    }

    @Override
    public void onChatUpdate(List<ChatMessage> messages) {
        if(messages == null)
            return;
        showMessages(messages);
    }

    private void showMessages(List<ChatMessage> messages) {
        StringBuilder output = new StringBuilder();
        String rn = System.getProperty("line.separator");
        Collections.sort(messages, ChatMessageComarator.byDate());
        for(ChatMessage msg : messages)
        {
            output.append(msg.getDate().toString()).append(" <b>").append(msg.getLogin()).append(":</b>").append(msg.getMessage()).append(rn);
        }

        textView.setText(Html.fromHtml(output.toString()));
    }

    public static void start(Activity activity) {
        Intent chatActivity = new Intent(activity, ChatActivity.class);
        activity.startActivity(chatActivity);
    }
}
