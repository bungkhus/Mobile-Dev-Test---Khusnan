package com.ministudio.bungkhus.mobiledevtest_khusnan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ministudio.bungkhus.mobiledevtest_khusnan.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private String nama, event, guest;
    private Button buttonEvent, buttonGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nama = getIntent().getStringExtra("nama");
        event = getIntent().getStringExtra("event");
        guest = getIntent().getStringExtra("guest");
        buttonEvent = (Button) findViewById(R.id.buttonEvent);
        buttonGuest = (Button) findViewById(R.id.buttonGuest);

        getSupportActionBar().setElevation(3);
        getSupportActionBar().setTitle(nama);

        buttonEvent.setOnClickListener(this);
        buttonGuest.setOnClickListener(this);
        if(event != null){
            buttonEvent.setText(event);
        }
        if(guest != null){
            buttonGuest.setText(guest);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonEvent:
                Intent i = new Intent(HomeActivity.this, EventActivity.class);
                i.putExtra("nama", nama);
                i.putExtra("event", event);
                i.putExtra("guest", guest);
                startActivity(i);
                finish();
                break;

            case R.id.buttonGuest:
                Intent in = new Intent(HomeActivity.this, GuestActivity.class);
                in.putExtra("nama", nama);
                in.putExtra("event", event);
                in.putExtra("guest", guest);
                startActivity(in);
                finish();
                break;
        }
    }
}
