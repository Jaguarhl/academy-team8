package academy.team8.com.footballfanlocator.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import java.util.ArrayList;

import academy.team8.com.footballfanlocator.R;
import academy.team8.com.footballfanlocator.adapters.CountryAdapter;
import academy.team8.com.footballfanlocator.interfaces.OnItemClickListener;
import academy.team8.com.footballfanlocator.model.CountryItem;

public class ChooseCountryActivity extends AppCompatActivity implements OnItemClickListener {
    private RecyclerView myRecylerView;
    private ArrayList<CountryItem> countryLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosecountry);
        myRecylerView = (RecyclerView) findViewById(R.id.recyclerView);
        Toast.makeText(getApplicationContext(), "Choose the country you support!", Toast.LENGTH_LONG).show();
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        myRecylerView.setHasFixedSize(true);
        // use a linear layout manager
        myRecylerView.setLayoutManager(new LinearLayoutManager(this));
        countryLists = new ArrayList<>();
        Resources res = getApplicationContext().getResources();
        countryLists.add(new CountryItem(1, "russia", res.getString(R.string.russia), "rus"));
        countryLists.add(new CountryItem(2, "germany", res.getString(R.string.germany), "ger"));
        countryLists.add(new CountryItem(3, "brazil", res.getString(R.string.brazil), "braz"));
        countryLists.add(new CountryItem(4, "portugal", res.getString(R.string.portugal), "port"));
        countryLists.add(new CountryItem(5, "argentina", res.getString(R.string.argentina), "arge"));
        countryLists.add(new CountryItem(6, "belgium", res.getString(R.string.belgium), "belg"));
        countryLists.add(new CountryItem(7, "poland", res.getString(R.string.poland), "pola"));
        countryLists.add(new CountryItem(8, "france", res.getString(R.string.france), "fran"));
        countryLists.add(new CountryItem(9, "spain", res.getString(R.string.spain), "spai"));
        countryLists.add(new CountryItem(10, "peru", res.getString(R.string.peru), "peru"));
        countryLists.add(new CountryItem(11, "switzerland", res.getString(R.string.switzerland), "switz"));
        countryLists.add(new CountryItem(12, "england", res.getString(R.string.england), "eng"));
        countryLists.add(new CountryItem(13, "colombia", res.getString(R.string.colombia), "colom"));
        countryLists.add(new CountryItem(14, "mexico", res.getString(R.string.mexico), "mexi"));
        countryLists.add(new CountryItem(15, "uruguay", res.getString(R.string.uruguay), "urug"));
        countryLists.add(new CountryItem(16, "croatia", res.getString(R.string.croatia), "croat"));
        countryLists.add(new CountryItem(17, "denmark", res.getString(R.string.denmark), "denm"));
        countryLists.add(new CountryItem(18, "iceland", res.getString(R.string.iceland), "icel"));
        countryLists.add(new CountryItem(19, "costa_rica", res.getString(R.string.costa_rica), "costr"));
        countryLists.add(new CountryItem(20, "sweden", res.getString(R.string.sweden), "swed"));
        countryLists.add(new CountryItem(21, "tunisia", res.getString(R.string.tunisia), "tunis"));
        countryLists.add(new CountryItem(22, "egypt", res.getString(R.string.egypt), "egypt"));
        countryLists.add(new CountryItem(23, "senegal", res.getString(R.string.senegal), "seneg"));
        countryLists.add(new CountryItem(24, "iran", res.getString(R.string.iran), "iran"));
        countryLists.add(new CountryItem(25, "serbia", res.getString(R.string.serbia), "serb"));
        countryLists.add(new CountryItem(26, "nigeria", res.getString(R.string.nigeria), "nigeria"));
        countryLists.add(new CountryItem(27, "australia", res.getString(R.string.australia), "austr"));
        countryLists.add(new CountryItem(28, "japan", res.getString(R.string.japan), "japa"));
        countryLists.add(new CountryItem(29, "morocco", res.getString(R.string.morocco), "moroc"));
        countryLists.add(new CountryItem(30, "panama", res.getString(R.string.panama), "panam"));
        countryLists.add(new CountryItem(31, "south_korea", res.getString(R.string.south_korea), "soukor"));
        countryLists.add(new CountryItem(32, "saudi_arabia", res.getString(R.string.saudi_arabia), "saudi"));
        RecyclerView.Adapter adapter = new CountryAdapter(countryLists, this);
        myRecylerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {
        SharedPreferences preferences = getSharedPreferences("FANS_LOCATOR_PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("COUNTRY", countryLists.get(position).getCountryPk()).commit();
        Intent signInActivity = new Intent(this, MapActivity.class);
        this.startActivity(signInActivity);
    }

    public static void start(Activity activity) {
        Intent chooseCountryActivity = new Intent(activity, ChooseCountryActivity.class);
        activity.startActivity(chooseCountryActivity);
    }

//    public void startMaps() {
//        Intent chooseCountryActivity = new Intent(this.getApplicationContext(), MapActivity.class);
//        this.getApplicationContext().startActivity(chooseCountryActivity);
//    }

}
