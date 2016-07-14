package com.ministudio.bungkhus.mobiledevtest_khusnan.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ministudio.bungkhus.mobiledevtest_khusnan.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btMasuk;
    private EditText etNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNama = (EditText) findViewById(R.id.editText);
        btMasuk = (Button) findViewById(R.id.button);

        btMasuk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String nama = etNama.getText().toString().trim();
        if(!nama.isEmpty()){
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            i.putExtra("nama", nama);
            startActivity(i);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            finish();
        }else {
            Toast.makeText(LoginActivity.this, "Silahkan isi nama anda!", Toast.LENGTH_LONG).show();
        }
    }
}
