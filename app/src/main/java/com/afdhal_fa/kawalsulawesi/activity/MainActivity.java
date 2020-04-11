package com.afdhal_fa.kawalsulawesi.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.afdhal_fa.kawalsulawesi.BuildConfig;
import com.afdhal_fa.kawalsulawesi.R;
import com.afdhal_fa.kawalsulawesi.entity.Corona;
import com.afdhal_fa.kawalsulawesi.entity.CoronaProvinsi;
import com.afdhal_fa.kawalsulawesi.viewm_model.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int REQUEST_CODE = 100;
    private TextView txtProinsi;
    MainViewModel mainViewModel;
    TextView positif;
    TextView sembuh;
    TextView meninggal;
    TextView labelProvinsi;


    private String provinsi = "Sulawesi Tenggara";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static String PROVINSI_PREFS = "provinsi";
    public static String dataPreferens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (!isConnect()) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Internet onection Alert")
                    .setMessage("Pleace Check your connection")
                    .setPositiveButton("close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).show();

        } else {
            positif = findViewById(R.id._total_positif);
            sembuh = findViewById(R.id._total_sembuh);
            meninggal = findViewById(R.id._total_meminggal);

            TextView appVersion = findViewById(R.id.app_version);

            appVersion.setText(String.format("App Version %s", BuildConfig.VERSION_NAME));

            final TextView _positif = findViewById(R.id.total_positif);
            final TextView _sembuh = findViewById(R.id.total_sembuh);
            final TextView _meninggal = findViewById(R.id.total_meminggal);

            txtProinsi = findViewById(R.id.set_province);
            labelProvinsi = findViewById(R.id.label_province);

            ImageView expandedMenu = findViewById(R.id.expanded_menu);
            expandedMenu.setOnClickListener(this);
            ImageView infoApss = findViewById(R.id.info_apps);
            infoApss.setOnClickListener(this);


            loadData(provinsi);

            mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
            mainViewModel.getCoronaProvinsi().observe(this, new Observer<ArrayList<CoronaProvinsi>>() {
                @Override
                public void onChanged(ArrayList<CoronaProvinsi> dataCoronaProvinsis) {
                    for (int i = 0; i < dataCoronaProvinsis.size(); i++) {
                        if (dataCoronaProvinsis.get(i).getProvinsi().equals(provinsi)) {
                            positif.setText(dataCoronaProvinsis.get(i).getKasusPosi());
                            sembuh.setText(dataCoronaProvinsis.get(i).getKasusSemb());
                            meninggal.setText(dataCoronaProvinsis.get(i).getKasusMeni());
                            return;
                        }
                    }
                }
            });

            mainViewModel.getCorona().observe(this, new Observer<ArrayList<Corona>>() {
                @Override
                public void onChanged(ArrayList<Corona> coronas) {
                    for (int i = 0; i < coronas.size(); i++) {
                        _positif.setText(coronas.get(i).getKasusPosi());
                        _sembuh.setText(coronas.get(i).getKasusSemb());
                        _meninggal.setText(coronas.get(i).getKasusMeni());
                    }
                }
            });
            mainViewModel.setDataCoronaProvinsi();
            mainViewModel.setDataCorona();

            updateViews();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == ListPrivinsiActivity.RESULT_CODE) {
                if (data != null) {
                    provinsi = data.getStringExtra(ListPrivinsiActivity.EXTRA_SELECTED_VALUE);
                    mainViewModel.getCoronaProvinsi().observe(this, new Observer<ArrayList<CoronaProvinsi>>() {
                        @Override
                        public void onChanged(ArrayList<CoronaProvinsi> dataCoronaProvinsis) {
                            txtProinsi.setText(provinsi);
                            labelProvinsi.setText(String.format("Jumlah Kasus di %s", provinsi));
                            for (int i = 0; i < dataCoronaProvinsis.size(); i++) {
                                if (dataCoronaProvinsis.get(i).getProvinsi().equals(provinsi)) {
                                    positif.setText(dataCoronaProvinsis.get(i).getKasusPosi());
                                    sembuh.setText(dataCoronaProvinsis.get(i).getKasusSemb());
                                    meninggal.setText(dataCoronaProvinsis.get(i).getKasusMeni());
                                    return;
                                }
                            }
                        }
                    });

                    setPreferences(provinsi);
                    loadData(provinsi);
                    updateViews();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean isConnect() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expanded_menu:
                Intent moveForResultIntent = new Intent(MainActivity.this, ListPrivinsiActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
            case R.id.info_apps:
                Intent intentInfoApps = new Intent(MainActivity.this, InfoApss.class);
                startActivity(intentInfoApps);
                break;
        }
    }

    private void setPreferences(String provinsiData) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PROVINSI_PREFS, provinsiData);
        editor.apply();
    }

    public void loadData(String provinsiData) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        dataPreferens = sharedPreferences.getString(PROVINSI_PREFS, provinsiData);
    }

    public void updateViews() {
        txtProinsi.setText(dataPreferens);
        labelProvinsi.setText(String.format("Jumlah Kasus di %s", dataPreferens));
        mainViewModel.getCoronaProvinsi().observe(this, new Observer<ArrayList<CoronaProvinsi>>() {
            @Override
            public void onChanged(ArrayList<CoronaProvinsi> dataCoronaProvinsis) {
                for (int i = 0; i < dataCoronaProvinsis.size(); i++) {
                    if (dataCoronaProvinsis.get(i).getProvinsi().equals(dataPreferens)) {
                        positif.setText(dataCoronaProvinsis.get(i).getKasusPosi());
                        sembuh.setText(dataCoronaProvinsis.get(i).getKasusSemb());
                        meninggal.setText(dataCoronaProvinsis.get(i).getKasusMeni());
                        return;
                    }
                }
            }
        });
    }
}
