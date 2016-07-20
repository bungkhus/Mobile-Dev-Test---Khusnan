package com.ministudio.bungkhus.mobiledevtest_khusnan.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ministudio.bungkhus.mobiledevtest_khusnan.R;
import com.ministudio.bungkhus.mobiledevtest_khusnan.adapter.EventAdapter;
import com.ministudio.bungkhus.mobiledevtest_khusnan.model.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class EventActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, EventAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private List<Event> data;
    private String nama, guest, event;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        nama = getIntent().getStringExtra("nama");
        guest = getIntent().getStringExtra("guest");
        event = getIntent().getStringExtra("event");

        adapter = new EventAdapter(EventActivity.this);

        getSupportActionBar().setTitle("EVENT");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(3);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_sircle);
        data = getAllItemList();
        setRecyclerView();
        setSwipeRefresh();
        adapter.setListener(this);

    }

    public void setSwipeRefresh(){
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light);
    }

    public void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(EventActivity.this));
        adapter.setDetailList(data);
        recyclerView.setAdapter(adapter);
    }

    public static String tampilkanTanggalDanWaktu(Date tanggalDanWaktu,
                                                  String pola) {
        String tanggalStr;
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(pola);
        tanggalStr = formatter.format(tanggalDanWaktu);

        return tanggalStr;
    }

    private List<Event> getAllItemList(){

        List<Event> allItems = new ArrayList<Event>();

        allItems.add(new Event("Party", getResources().getString(R.string.material), new Date(), R.drawable.balloons, new ArrayList<String>() {{
            add("hollyday");
            add("party");
        }}));
        allItems.add(new Event("Dinner", getResources().getString(R.string.material),  new Date(), R.drawable.dinner, new ArrayList<String>() {{
            add("anniversary");
            add("dinner");
            add("love");
        }}));
        allItems.add(new Event("Playing Game", getResources().getString(R.string.material),  new Date(), R.drawable.gamepad, new ArrayList<String>() {{
            add("refresshing");
        }}));
        allItems.add(new Event("Birthday", getResources().getString(R.string.material),  new Date(), R.drawable.gift, new ArrayList<String>() {{
            add("party");
            add("happy");
        }}));
        allItems.add(new Event("Karaoke", getResources().getString(R.string.material),  new Date(), R.drawable.karaoke, new ArrayList<String>() {{
            add("happy");
            add("song");
        }}));
        allItems.add(new Event("Hunting Photo", getResources().getString(R.string.material),  new Date(), R.drawable.photocamera, new ArrayList<String>() {{
            add("hollyday");
            add("vacation");
        }}));

        return allItems;
    }

    private List<Event> getNewItems() {
        List<Event> newEvents = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            data.get(i).getTag().clear();
            int randomCatNameIndex = new Random().nextInt(data.size() - 1);
            newEvents.add(data.get(randomCatNameIndex));
        }
        mSwipeRefreshLayout.setRefreshing(false);
        return newEvents;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent();
        i.putExtra("nama", nama);
        i.putExtra("guest", guest);
        i.putExtra("event", event);
        setResult(RESULT_OK, i);
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    public static boolean isPrime(int num){
        if ( num > 2 && num%2 == 0 ) {
            System.out.println(num + " is not prime");
            return false;
        }
        int top = (int)Math.sqrt(num) + 1;
        for(int i = 3; i < top; i+=2){
            if(num % i == 0){
                System.out.println(num + " is not prime");
                return false;
            }
        }
        System.out.println(num + " is prime");
        return true;
    }

    @Override
    public void onRefresh() {
        adapter.setDetailList(getNewItems());
    }

    @Override
    public void onClick(Event item) {
        Intent i = new Intent();
        i.putExtra("nama", nama);
        i.putExtra("guest", guest);
        i.putExtra("event", item.getNama());
        setResult(RESULT_OK, i);

        String pola = "MM";
        Date date = item.getTanggal();
        String tanggalStr = tampilkanTanggalDanWaktu(
                date, pola);
        int tanggal = Integer.parseInt(tanggalStr);
        String prime;
        if(isPrime(tanggal)){
            prime = "isPrime";
        }else{
            prime = "not prime";
        }

        System.out.println("TANGGAL = "+tanggal);
        Toast.makeText(EventActivity.this, prime, Toast.LENGTH_LONG).show();

        finish();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
