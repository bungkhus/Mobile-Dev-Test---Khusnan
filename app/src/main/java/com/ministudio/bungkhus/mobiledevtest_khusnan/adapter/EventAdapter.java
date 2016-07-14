package com.ministudio.bungkhus.mobiledevtest_khusnan.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ministudio.bungkhus.mobiledevtest_khusnan.R;
import com.ministudio.bungkhus.mobiledevtest_khusnan.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by bungkhus on 7/14/16.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private List<Event> detailList;
    private Context context;

    public EventAdapter(Context context, List<Event> detailList, OnItemClickListener listener) {
        this.detailList = detailList;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item_event, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        holder.click(detailList.get(position), listener);

        String pola = "dd MMM yyyy";
        Date date = detailList.get(position).getTanggal();
        String tanggalStr = tampilkanTanggalDanWaktu(
                date, pola);

        holder.textLabelEmail.setBackground(setColorFilter(position+1));
        holder.textLabelEmail.setImageDrawable(ContextCompat.getDrawable(context,detailList.get(position).getPhoto()));
        holder.textTitleEmail.setText(detailList.get(position).getNama());
        holder.textMessageEmail.setText(tanggalStr);
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    private Drawable setColorFilter(int color){
        if((color % 4) == 0) {
            return ContextCompat.getDrawable(context, R.drawable.one_round);
        }if((color % 3) == 0) {
            return ContextCompat.getDrawable(context, R.drawable.two_round);
        }if((color % 2) == 0) {
            return ContextCompat.getDrawable(context, R.drawable.three_round);
        }else {
            return ContextCompat.getDrawable(context, R.drawable.four_round);
        }
    }

    public static String tampilkanTanggalDanWaktu(Date tanggalDanWaktu,
                                                   String pola) {
        String tanggalStr;
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(pola);
        tanggalStr = formatter.format(tanggalDanWaktu);

        return tanggalStr;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView textLabelEmail;
        TextView textTitleEmail;
        TextView textMessageEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            textLabelEmail = (ImageView) itemView.findViewById(R.id.text_label_email);
            textTitleEmail = (TextView) itemView.findViewById(R.id.text_title_email);
            textMessageEmail = (TextView) itemView.findViewById(R.id.text_message_email);
        }

        public void click(final Event rumpun, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(rumpun);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(Event item);
    }
}
