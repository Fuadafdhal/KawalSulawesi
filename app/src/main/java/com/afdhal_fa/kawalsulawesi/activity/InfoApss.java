package com.afdhal_fa.kawalsulawesi.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afdhal_fa.kawalsulawesi.R;

public class InfoApss extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_apss);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ImageView sendEmail = findViewById(R.id.email);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mailto = "mailto:fuad.afdal.fa@gmail.com" +
                        "?cc=" + "fuad.afdal.fa@gmail.com" +
                        "&subject=" + Uri.encode("Colaborasi Apps KawalSulawesi") +
                        "&body=" + Uri.encode("");

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));
                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(InfoApss.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
