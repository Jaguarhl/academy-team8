package academy.team8.com.footballfanlocator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;

import academy.team8.com.footballfanlocator.adapters.CountryAdapter;
import academy.team8.com.footballfanlocator.interfaces.OnItemClickListener;
import academy.team8.com.footballfanlocator.model.CountryItem;

public class ActivityChooseCountry extends AppCompatActivity implements OnItemClickListener {
    private RecyclerView myRecylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosecountry);
        myRecylerView = (RecyclerView) findViewById(R.id.recyclerView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        myRecylerView.setHasFixedSize(true);
        // use a linear layout manager
        myRecylerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<CountryItem> countryLists = new ArrayList<>();
        countryLists.add(new CountryItem(1, "russia", "Russia", "rus"));
        countryLists.add(new CountryItem(2, "germany", "Germany", "ger"));
        countryLists.add(new CountryItem(3, "brazil", "Brazil", "braz"));
        countryLists.add(new CountryItem(4, "portugal", "Portugal", "port"));
        countryLists.add(new CountryItem(5, "argentina", "Argentina", "arge"));
        countryLists.add(new CountryItem(6, "belgium", "Belgium", "belg"));
        countryLists.add(new CountryItem(7, "poland", "Poland", "pola"));
        countryLists.add(new CountryItem(8, "france", "France", "fran"));
        countryLists.add(new CountryItem(9, "spain", "Spain", "spai"));
        countryLists.add(new CountryItem(10, "peru", "Peru", "peru"));
        countryLists.add(new CountryItem(11, "switzerland", "Switzerland", "switz"));
        countryLists.add(new CountryItem(12, "england", "England", "eng"));
        countryLists.add(new CountryItem(13, "colombia", "Colombia", "colom"));
        countryLists.add(new CountryItem(14, "mexico", "Mexico", "mexi"));
        countryLists.add(new CountryItem(15, "uruguay", "Uruguay", "urug"));
        countryLists.add(new CountryItem(16, "croatia", "Croatia", "croat"));
        countryLists.add(new CountryItem(17, "denmark", "Denmark", "denm"));
        countryLists.add(new CountryItem(18, "iceland", "Iceland", "icel"));
        countryLists.add(new CountryItem(19, "costa_rica", "Costa Rica", "costr"));
        countryLists.add(new CountryItem(20, "sweden", "Sweden", "swed"));
        countryLists.add(new CountryItem(21, "tunisia", "Tunisia", "tunis"));
        countryLists.add(new CountryItem(22, "egypt", "Egypt", "egypt"));
        countryLists.add(new CountryItem(23, "senegal", "Senegal", "seneg"));
        countryLists.add(new CountryItem(24, "iran", "Iran", "iran"));
        countryLists.add(new CountryItem(25, "serbia", "Serbia", "serb"));
        countryLists.add(new CountryItem(26, "nigeria", "Nigeria", "nigeria"));
        countryLists.add(new CountryItem(27, "australia", "Australia", "austr"));
        countryLists.add(new CountryItem(28, "japan", "Japan", "japa"));
        countryLists.add(new CountryItem(29, "morocco", "Morocco", "moroc"));
        countryLists.add(new CountryItem(30, "panama", "Panama", "panam"));
        countryLists.add(new CountryItem(31, "south_korea", "South Korea", "soukor"));
        countryLists.add(new CountryItem(32, "saudi_arabia", "Saudi Arabia", "saudi"));

        RecyclerView.Adapter adapter = new CountryAdapter(countryLists);
        myRecylerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {

    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, ActivityChooseCountry.class));
    }
}
