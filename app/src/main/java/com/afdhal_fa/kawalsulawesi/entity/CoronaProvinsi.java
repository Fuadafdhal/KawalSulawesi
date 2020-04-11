package com.afdhal_fa.kawalsulawesi.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CoronaProvinsi implements Parcelable {
    private int id;
    private String provinsi;
    private String kasusPosi;
    private String kasusSemb;
    private String kasusMeni;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinsi() {
        return provinsi;
    }

     public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKasusPosi() {
        return kasusPosi;
    }

    public void setKasusPosi(String kasusPosi) {
        this.kasusPosi = kasusPosi;
    }

    public String getKasusSemb() {
        return kasusSemb;
    }

    public void setKasusSemb(String kasusSemb) {
        this.kasusSemb = kasusSemb;
    }

    public String getKasusMeni() {
        return kasusMeni;
    }

    public void setKasusMeni(String kasusMeni) {
        this.kasusMeni = kasusMeni;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.provinsi);
        dest.writeString(this.kasusPosi);
        dest.writeString(this.kasusSemb);
        dest.writeString(this.kasusMeni);
    }

    public CoronaProvinsi() {
    }

    private CoronaProvinsi(Parcel in) {
        this.id = in.readInt();
        this.provinsi = in.readString();
        this.kasusPosi = in.readString();
        this.kasusSemb = in.readString();
        this.kasusMeni = in.readString();
    }

    public static final Creator<CoronaProvinsi> CREATOR = new Creator<CoronaProvinsi>() {
        @Override
        public CoronaProvinsi createFromParcel(Parcel source) {
            return new CoronaProvinsi(source);
        }

        @Override
        public CoronaProvinsi[] newArray(int size) {
            return new CoronaProvinsi[size];
        }
    };
}
