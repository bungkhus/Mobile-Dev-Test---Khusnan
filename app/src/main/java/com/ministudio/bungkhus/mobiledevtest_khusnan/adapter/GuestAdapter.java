package com.ministudio.bungkhus.mobiledevtest_khusnan.adapter;

/**
 * Created by bungkhus on 7/14/16.
 */
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ministudio.bungkhus.mobiledevtest_khusnan.R;
import com.ministudio.bungkhus.mobiledevtest_khusnan.model.Guest;

import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.RecyclerViewHolders> {

    private final OnItemClickListener listener;
    private List<Guest> itemList;
    private Context context;

    public GuestAdapter(Context context, List<Guest> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.click(itemList.get(position), listener);
        holder.countryName.setText(itemList.get(position).getNama());
        holder.countryPhoto.setImageDrawable(setSrcFilter(position+1));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    private Drawable setSrcFilter(int color){
        if((color % 5) == 0) {
            return ContextCompat.getDrawable(context, R.drawable.dj);
        }if((color % 4) == 0) {
            return ContextCompat.getDrawable(context, R.drawable.businessman);
        }if((color % 3) == 0) {
            return ContextCompat.getDrawable(context, R.drawable.engineer);
        }if((color % 2) == 0) {
            return ContextCompat.getDrawable(context, R.drawable.pilot);
        }else {
            return ContextCompat.getDrawable(context, R.drawable.thief);
        }
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView countryName;
        public ImageView countryPhoto;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            countryName = (TextView)itemView.findViewById(R.id.country_name);
            countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
        }

        public void click(final Guest rumpun, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(rumpun);
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }

    public interface OnItemClickListener {
        void onClick(Guest item);
    }
}