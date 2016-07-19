package com.ministudio.bungkhus.mobiledevtest_khusnan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
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

public class EventActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Event> data;
    private String nama, guest, event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        nama = getIntent().getStringExtra("nama");
        guest = getIntent().getStringExtra("guest");
        event = getIntent().getStringExtra("event");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(EventActivity.this));

        getSupportActionBar().setTitle("EVENT");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(3);

        data = getAllItemList();
        setRecyclerView();
    }

    public void setRecyclerView() {
        EventAdapter adapter = new EventAdapter(EventActivity.this, data, new EventAdapter.OnItemClickListener() {
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
        });
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
        allItems.add(new Event("Party", new Date(), R.drawable.balloons));
        allItems.add(new Event("Dinner",  new Date(), R.drawable.dinner));
        allItems.add(new Event("Playing Game",  new Date(), R.drawable.gamepad));
        allItems.add(new Event("Birthday",  new Date(), R.drawable.gift));
        allItems.add(new Event("Karaoke",  new Date(), R.drawable.karaoke));
        allItems.add(new Event("Hunting Photo",  new Date(), R.drawable.photocamera));

        return allItems;
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
}
