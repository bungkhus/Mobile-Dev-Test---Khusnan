package com.ministudio.bungkhus.mobiledevtest_khusnan.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

        if(isPalindrome(nama)){
            showDialog(nama, "isPalindrome");
        }else{
            showDialog(nama, "not palindrome");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                nama = data.getStringExtra("nama");
                event = data.getStringExtra("event");
                guest = data.getStringExtra("guest");
                if(event != null){
                    buttonEvent.setText(event);
                }
                if(guest != null){
                    buttonGuest.setText(guest);
                }
            }
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
                startActivityForResult(i, 1);
                break;

            case R.id.buttonGuest:
                Intent in = new Intent(HomeActivity.this, GuestActivity.class);
                in.putExtra("nama", nama);
                in.putExtra("event", event);
                in.putExtra("guest", guest);
                startActivityForResult(in, 1);
                break;
        }
    }

    private void showDialog(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(
                HomeActivity.this);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setNegativeButton("DISMISS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        //
                    }
                });
        builder.show();
    }

    private boolean isPalindrome(String textCheck) {
        int startPos = 0;
        int endPos = textCheck.length() - 1;
        boolean result = true;

        while (endPos > startPos) {
            char char_1 = Character.toLowerCase(textCheck.charAt(startPos));
            char char_2 = Character.toLowerCase(textCheck.charAt(endPos));

            if (char_1 != char_2) {
                result = false;

                break;
            }

            startPos++;
            endPos--;
        }
        return result;
    }
}
