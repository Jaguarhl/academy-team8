package academy.team8.com.footballfanlocator.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import academy.team8.com.footballfanlocator.R;
import academy.team8.com.footballfanlocator.interfaces.OnItemClickListener;
import academy.team8.com.footballfanlocator.model.CountryItem;


/**
 * Created by dmitry on 10.06.2018.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> {
    List<CountryItem> countryItemList = new ArrayList<>();

    OnItemClickListener listener;


    private final View.OnClickListener internalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //here is whaterver i wanna do on click
        }
    };


    public CountryAdapter(@NonNull OnItemClickListener onItemClickListener, List<CountryItem> countryItemList) {

        this.listener = onItemClickListener;
        this.countryItemList = countryItemList;
    }

    @NonNull
    @Override
    public CountryAdapter.CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CountryHolder(inflater.inflate(R.layout.item_country, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryHolder holder, final int position) {
        CountryItem country = countryItemList.get(position);
        holder.country.setText(country.getCountryName());
        holder.layoutCountryTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryItemList.size();
    }

    static class CountryHolder extends RecyclerView.ViewHolder {

        TextView country;
        ImageView flag;
        LinearLayout layoutCountryTitle;

        CountryHolder(@NonNull View itemView) {
            super(itemView);

            layoutCountryTitle = itemView.findViewById(R.id.layoutCountry);
            flag = itemView.findViewById(R.id.imageFlag);
            country = itemView.findViewById(R.id.textCountry);
        }
    }


}


