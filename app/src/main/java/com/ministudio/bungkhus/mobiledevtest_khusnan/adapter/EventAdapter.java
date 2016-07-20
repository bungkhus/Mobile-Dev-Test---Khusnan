package com.ministudio.bungkhus.mobiledevtest_khusnan.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    private OnItemClickListener listener;
    private List<Event> detailList;
    private Context context;

    public EventAdapter(Context context) {
        this.context = context;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setDetailList(List<Event> detailList) {
        this.detailList = detailList;
        notifyDataSetChanged();
    }

    // Clean all elements of the recycler
    public void clear() {
        detailList.clear();
        notifyDataSetChanged();
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item_event, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        holder.click(detailList.get(position), listener);

        String pola = "MMM dd yyyy";
        Date date = detailList.get(position).getTanggal();
        String tanggalStr = tampilkanTanggalDanWaktu(
                date, pola);

        holder.textLabelEmail.setBackground(setColorFilter(position+1));
        holder.textLabelEmail.setImageDrawable(ContextCompat.getDrawable(context,detailList.get(position).getPhoto()));
        holder.textTitleEmail.setText(detailList.get(position).getNama());
        holder.textMessageEmail.setText(tanggalStr);
        holder.tvDesc.setText(detailList.get(position).getDesc());

        final int N = detailList.get(position).getTag().size();
        final TextView[] myTextViews = new TextView[N];
        for (int i = 0; i < N; i++) {
            final TextView rowTextView = new TextView(context);

            rowTextView.setText("#" + detailList.get(position).getTag().get(i));
            rowTextView.setTextColor(context.getResources().getColor(R.color.white));
            rowTextView.setTextSize(10);
            rowTextView.setPadding(3,3,3,3);
            rowTextView.setBackgroundColor(context.getResources().getColor(R.color.tag));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            params.setMargins(0,0,3, 0);
            rowTextView.setLayoutParams(params);

            holder.tag_view.addView(rowTextView);

            myTextViews[i] = rowTextView;
        }
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
        TextView tvDesc;
        LinearLayout tag_view;

        public ViewHolder(View itemView) {
            super(itemView);
            textLabelEmail = (ImageView) itemView.findViewById(R.id.text_label_email);
            textTitleEmail = (TextView) itemView.findViewById(R.id.text_title_email);
            textMessageEmail = (TextView) itemView.findViewById(R.id.text_message_email);
            tvDesc = (TextView) itemView.findViewById(R.id.desc);
            tag_view = (LinearLayout) itemView.findViewById(R.id.tag_view);
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
