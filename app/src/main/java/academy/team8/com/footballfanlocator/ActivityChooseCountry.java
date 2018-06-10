package academy.team8.com.footballfanlocator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import academy.team8.com.footballfanlocator.interfaces.OnItemClickListener;

public class ActivityChooseCountry extends AppCompatActivity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosecountry);

        RecyclerView recyclerView= findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onItemClicked(int position) {

    }
}
