package com.afdhal_fa.kawalsulawesi.viewm_model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.afdhal_fa.kawalsulawesi.entity.*;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<CoronaProvinsi>> listCoronaDataProvinsi = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Corona>> listCoronaData = new MutableLiveData<>();

    public void setDataCoronaProvinsi() {
        final AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<CoronaProvinsi> listProvinsiItems = new ArrayList<>();
        String url = "https://api.kawalcorona.com/indonesia/provinsi";


        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject corona = jsonArray.getJSONObject(i);
                        JSONObject coronaDataAPI = corona.getJSONObject("attributes");
                        CoronaProvinsi coronaProvinsiData = new CoronaProvinsi();
                        coronaProvinsiData.setId(coronaDataAPI.getInt("FID"));
                        coronaProvinsiData.setProvinsi(coronaDataAPI.getString("Provinsi"));
                        coronaProvinsiData.setKasusPosi(coronaDataAPI.getString("Kasus_Posi"));
                        coronaProvinsiData.setKasusSemb(coronaDataAPI.getString("Kasus_Semb"));
                        coronaProvinsiData.setKasusMeni(coronaDataAPI.getString("Kasus_Meni"));
                        listProvinsiItems.add(coronaProvinsiData);

                    }
                    listCoronaDataProvinsi.postValue(listProvinsiItems);
                } catch (Exception e) {
                    Log.d("Exeption", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }
        });
    }

    public void setDataCorona() {
        final AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Corona> listItems = new ArrayList<>();
        String url = "https://api.kawalcorona.com/indonesia";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        System.out.println();
                        JSONObject corona = jsonArray.getJSONObject(i);
                        Corona coronaData = new Corona();
                        coronaData.setKasusPosi(corona.getString("positif"));
                        coronaData.setKasusSemb(corona.getString("sembuh"));
                        coronaData.setKasusMeni(corona.getString("meninggal"));
                        listItems.add(coronaData);
                    }
                    listCoronaData.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exeption", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }
        });
    }

    public LiveData<ArrayList<CoronaProvinsi>> getCoronaProvinsi() {
        return listCoronaDataProvinsi;
    }

    public LiveData<ArrayList<Corona>> getCorona() {
        return listCoronaData;
    }

}
