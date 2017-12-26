package com.dhirain.gitrepo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dhirain Jain on 25-12-2017.
 */

public class OwnerModel implements Parcelable {
    @SerializedName("avatar_url")
    @Expose
    private String avatar_url;

    public String getAvatar_url() {
        return avatar_url;
    }

    protected OwnerModel(Parcel in) {
        avatar_url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatar_url);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<OwnerModel> CREATOR = new Parcelable.Creator<OwnerModel>() {
        @Override
        public OwnerModel createFromParcel(Parcel in) {
            return new OwnerModel(in);
        }

        @Override
        public OwnerModel[] newArray(int size) {
            return new OwnerModel[size];
        }
    };
}