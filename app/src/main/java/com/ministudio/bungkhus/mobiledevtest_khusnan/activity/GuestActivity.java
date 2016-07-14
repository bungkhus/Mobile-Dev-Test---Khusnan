package com.ministudio.bungkhus.mobiledevtest_khusnan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ministudio.bungkhus.mobiledevtest_khusnan.R;
import com.ministudio.bungkhus.mobiledevtest_khusnan.adapter.GuestAdapter;
import com.ministudio.bungkhus.mobiledevtest_khusnan.model.Guest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class GuestActivity extends AppCompatActivity {

    private GridLayoutManager lLayout;
    private String nama, event, guest;
    private SpotsDialog pDialog;
    private GuestAdapter rcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        nama = getIntent().getStringExtra("nama");
        event = getIntent().getStringExtra("event");
        guest = getIntent().getStringExtra("guest");
        pDialog = new SpotsDialog(GuestActivity.this);

        getSupportActionBar().setTitle("GUEST");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(3);

        List<Guest> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(GuestActivity.this, 3);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rcAdapter = new GuestAdapter(GuestActivity.this, rowListItem, new GuestAdapter.OnItemClickListener() {
            @Override
            public void onClick(Guest item) {
                Intent i = new Intent(GuestActivity.this, HomeActivity.class);
                i.putExtra("nama", nama);
                i.putExtra("event", event);
                i.putExtra("guest", item.getNama());
                startActivity(i);
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                finish();

                String pola = "dd";
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = formatter.parse(item.getBirthdate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String tanggalStr = tampilkanTanggalDanWaktu(
                        date, pola);
                int birthdate = Integer.parseInt(tanggalStr);
                String phone;
                if(birthdate % 3 == 0 && birthdate % 2 == 0){
                    phone = "iOS";
                }else if(birthdate % 2 == 0){
                    phone = "blackbarry";
                }else if(birthdate % 3 == 0){
                    phone = "android";
                }else{
                    phone = "feature phone";
                }
                System.out.println("TANGGAL LAHIR = "+birthdate);
                Toast.makeText(GuestActivity.this, phone, Toast.LENGTH_LONG).show();

            }
        });
        rView.setAdapter(rcAdapter);
    }

    public static String tampilkanTanggalDanWaktu(Date tanggalDanWaktu,
                                                  String pola) {
        String tanggalStr;
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(pola);
        tanggalStr = formatter.format(tanggalDanWaktu);

        return tanggalStr;
    }

    private List<Guest> getAllItemList(){

        final List<Guest> allItems = new ArrayList<Guest>();

        showDialog();
        pDialog.setCancelable(false);

        RequestQueue queue = Volley.newRequestQueue(GuestActivity.this);
        StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://dry-sierra-6832.herokuapp.com/api/people", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();
                Log.d("LOG", "Response: " + response.toString());

                try {
                    JSONArray array_success = new JSONArray(response);
                    int size = array_success.length();
                    allItems.clear();
                    Guest model = new Guest();
                    for (int i = 0; i<size; i++){
                        JSONObject data = array_success.getJSONObject(i);
                        String id = data.getString("id");
                        String name = data.getString("name");
                        String birthdate = data.getString("birthdate");

                        model = new Guest(id, name, birthdate, R.drawable.tree);
                        allItems.add(model);
                        rcAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                Log.e("ERROR", "Submit Error: " + error.getMessage());
                Toast.makeText(GuestActivity.this,
                        "Gagal Menampilkan Data.", Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        queue.add(strReq);

        return allItems;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(GuestActivity.this, HomeActivity.class);
        i.putExtra("nama", nama);
        i.putExtra("event", event);
        i.putExtra("guest", guest);
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

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
