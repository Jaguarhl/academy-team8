package academy.team8.com.footballfanlocator.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import academy.team8.com.footballfanlocator.ActivityChooseCountry;
import academy.team8.com.footballfanlocator.R;
import academy.team8.com.footballfanlocator.model.CountryItem;


/**
 * Created by dmitry on 10.06.2018.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.FighterImageViewHolder> {
    private ArrayList<CountryItem> countryList;
//    private final View.OnClickListener mOnClickListener = new MyOnClickListener();
    private ActivityChooseCountry activity;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CountryAdapter(ArrayList<CountryItem> fightersList, ActivityChooseCountry activity) {
        this.countryList = fightersList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FighterImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_country, parent, false);
        //itemView.setOnClickListener(mOnClickListener);
        return new FighterImageViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    public void onBindViewHolder(@NonNull FighterImageViewHolder holder, final int position) {
        CountryItem countryItem = countryList.get(position);
        final Context context = holder.countryFlag.getContext();
        int id = context.getResources().getIdentifier(countryItem.getFlagPic(), "drawable", context.getPackageName());
        holder.countryFlag.setImageResource(id);
        holder.countryName.setText(countryItem.getCountryName());
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Get over here!", Toast.LENGTH_LONG).show();
                        activity.onItemClicked(position);
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class FighterImageViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        ImageView countryFlag;
        TextView countryName;

        FighterImageViewHolder(View itemView) {
            super(itemView);
            countryFlag = itemView.findViewById(R.id.country_flag_image_view);
            countryName = itemView.findViewById(R.id.country_name_text_view);
        }
    }

//    private class MyOnClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(final View view) {
//            Toast.makeText(view.getContext(), "Get over here!", Toast.LENGTH_LONG).show();
//            activity.onItemClicked();
//            //MediaPlayer mPlayer = MediaPlayer.create(view.getContext(), R.raw.mk);
//            //mPlayer.start();
//        }
//    }
}






