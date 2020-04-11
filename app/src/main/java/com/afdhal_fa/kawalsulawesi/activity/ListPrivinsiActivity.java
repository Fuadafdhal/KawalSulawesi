package com.afdhal_fa.kawalsulawesi.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afdhal_fa.kawalsulawesi.R;
import com.afdhal_fa.kawalsulawesi.adapter.ProvinsiAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class ListPrivinsiActivity extends AppCompatActivity {

    public static final String EXTRA_PROVINSI = "extar_provinsi";

    public static final String EXTRA_SELECTED_VALUE = "extra_selected_value";
    public static final int RESULT_CODE = 100;

    private static String[] dataName = {
            "Sulawesi Barat",
            "Sulawesi Utara",
            "Sulawesi Tengah",
            "Sulawesi Selatan",
            "Sulawesi Tenggara",
            "Gorontalo"
    };
    private ArrayList<String> list = new ArrayList<>();

    ProvinsiAdapter provinsiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_privinsi);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        RecyclerView rvProvinsi = findViewById(R.id.rv_provinsi);
        rvProvinsi.setHasFixedSize(true);


        Collections.addAll(list, dataName);
        rvProvinsi.setLayoutManager(new LinearLayoutManager(this));
        provinsiAdapter = new ProvinsiAdapter(list);
        provinsiAdapter.setListActivity(this);
        rvProvinsi.setAdapter(provinsiAdapter);
    }

}

