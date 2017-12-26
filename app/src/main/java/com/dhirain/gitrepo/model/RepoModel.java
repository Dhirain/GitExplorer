package com.dhirain.gitrepo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

/**
 * Created by Dhirain Jain on 24-12-2017.
 */

public class RepoModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("fork")
    @Expose
    private Boolean fork;
    @SerializedName("contributors_url")
    @Expose
    private String contributorsUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("pushed_at")
    @Expose
    private String pushedAt;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("stargazers_count")
    @Expose
    private Integer stargazersCount;
    @SerializedName("watchers_count")
    @Expose
    private Integer watchersCount;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("watchers")
    @Expose
    private Integer watchers;
    @SerializedName("score")
    @Expose
    private Double score;

    @SerializedName("owner")
    @Expose
    private OwnerModel owner;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getFork() {
        return fork;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public Integer getWatchersCount() {
        return watchersCount;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public String getScore() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(score);
    }

    public OwnerModel getOnwer() {
        return owner;
    }

    @Override
    public String toString() {
        return "RepoModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", description='" + description + '\'' +
                ", fork=" + fork +
                ", contributorsUrl='" + contributorsUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", pushedAt='" + pushedAt + '\'' +
                ", size=" + size +
                ", stargazersCount=" + stargazersCount +
                ", watchersCount=" + watchersCount +
                ", language='" + language + '\'' +
                ", watchers=" + watchers +
                ", score=" + score +
                '}';
    }

    protected RepoModel(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        name = in.readString();
        fullName = in.readString();
        htmlUrl = in.readString();
        description = in.readString();
        byte forkVal = in.readByte();
        fork = forkVal == 0x02 ? null : forkVal != 0x00;
        contributorsUrl = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        pushedAt = in.readString();
        size = in.readByte() == 0x00 ? null : in.readInt();
        stargazersCount = in.readByte() == 0x00 ? null : in.readInt();
        watchersCount = in.readByte() == 0x00 ? null : in.readInt();
        language = in.readString();
        watchers = in.readByte() == 0x00 ? null : in.readInt();
        score = in.readByte() == 0x00 ? null : in.readDouble();
        owner = (OwnerModel) in.readValue(OwnerModel.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(htmlUrl);
        dest.writeString(description);
        if (fork == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (fork ? 0x01 : 0x00));
        }
        dest.writeString(contributorsUrl);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(pushedAt);
        if (size == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(size);
        }
        if (stargazersCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(stargazersCount);
        }
        if (watchersCount == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(watchersCount);
        }
        dest.writeString(language);
        if (watchers == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(watchers);
        }
        if (score == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(score);
        }
        dest.writeValue(owner);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RepoModel> CREATOR = new Parcelable.Creator<RepoModel>() {
        @Override
        public RepoModel createFromParcel(Parcel in) {
            return new RepoModel(in);
        }

        @Override
        public RepoModel[] newArray(int size) {
            return new RepoModel[size];
        }
    };
}