package com.dhirain.gitrepo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dhirain Jain on 26-12-2017.
 */

public class FilterModel implements Parcelable {
    private String sort_by;
    private String order_by;
    private String language;

    public FilterModel(String sort_by, String order_by, String language) {
        this.sort_by = sort_by;
        this.order_by = order_by;
        this.language = language;
    }

    public String getSort_by() {
        return sort_by;
    }

    public String getOrder_by() {
        return order_by;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "FilterModel{" +
                "sort_by='" + sort_by + '\'' +
                ", order_by='" + order_by + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    protected FilterModel(Parcel in) {
        sort_by = in.readString();
        order_by = in.readString();
        language = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sort_by);
        dest.writeString(order_by);
        dest.writeString(language);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FilterModel> CREATOR = new Parcelable.Creator<FilterModel>() {
        @Override
        public FilterModel createFromParcel(Parcel in) {
            return new FilterModel(in);
        }

        @Override
        public FilterModel[] newArray(int size) {
            return new FilterModel[size];
        }
    };
}
