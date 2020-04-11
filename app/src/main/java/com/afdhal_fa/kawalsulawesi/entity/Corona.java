package com.afdhal_fa.kawalsulawesi.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Corona implements Parcelable {
    private String kasusPosi;
    private String kasusSemb;
    private String kasusMeni;

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
        dest.writeString(this.kasusPosi);
        dest.writeString(this.kasusSemb);
        dest.writeString(this.kasusMeni);
    }

    public Corona() {
    }

    private Corona(Parcel in) {
        this.kasusPosi = in.readString();
        this.kasusSemb = in.readString();
        this.kasusMeni = in.readString();
    }

    public static final Creator<Corona> CREATOR = new Creator<Corona>() {
        @Override
        public Corona createFromParcel(Parcel source) {
            return new Corona(source);
        }

        @Override
        public Corona[] newArray(int size) {
            return new Corona[size];
        }
    };
}