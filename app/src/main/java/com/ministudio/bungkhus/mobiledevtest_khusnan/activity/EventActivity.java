package com.ministudio.bungkhus.mobiledevtest_khusnan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.ministudio.bungkhus.mobiledevtest_khusnan.R;
import com.ministudio.bungkhus.mobiledevtest_khusnan.adapter.EventAdapter;
import com.ministudio.bungkhus.mobiledevtest_khusnan.model.Event;

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
                Intent i = new Intent(EventActivity.this, HomeActivity.class);
                i.putExtra("nama", nama);
                i.putExtra("guest", guest);
                i.putExtra("event", item.getNama());
                startActivity(i);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                finish();
            }
        });
        recyclerView.setAdapter(adapter);
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
        Intent i = new Intent(EventActivity.this, HomeActivity.class);
        i.putExtra("nama", nama);
        i.putExtra("guest", guest);
        i.putExtra("event", event);
        startActivity(i);
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
}
