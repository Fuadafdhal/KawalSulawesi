package com.afdhal_fa.kawalsulawesi.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.afdhal_fa.kawalsulawesi.R;
import com.afdhal_fa.kawalsulawesi.custom_onclik_listener.CustomOnItemClickListener;

import java.util.ArrayList;

import static com.afdhal_fa.kawalsulawesi.activity.ListPrivinsiActivity.EXTRA_SELECTED_VALUE;
import static com.afdhal_fa.kawalsulawesi.activity.ListPrivinsiActivity.RESULT_CODE;

public class ProvinsiAdapter extends RecyclerView.Adapter<ProvinsiAdapter.ProvinsiViewHolder> {
    private ArrayList<String> listProvinsi;
    private Activity activity;

    public ProvinsiAdapter(ArrayList<String> list) {
        this.listProvinsi = list;
    }

    public void setListActivity(Activity activity) {
        this.activity = activity;
    }


    @NonNull
    @Override
    public ProvinsiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_provinsi, parent, false);
        return new ProvinsiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProvinsiViewHolder holder, int position) {
        String value = String.valueOf(position + 1) + ". " + listProvinsi.get(position);
        holder.txtProvinsi.setText(value);
        holder.itemView.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                String value = listProvinsi.get(position);
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value);
                activity.setResult(RESULT_CODE, resultIntent);
                activity.finish();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listProvinsi.size();
    }

    static class ProvinsiViewHolder extends RecyclerView.ViewHolder {
        TextView txtProvinsi;

        ProvinsiViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProvinsi = itemView.findViewById(R.id.txt_provinsi);
        }
    }
}
