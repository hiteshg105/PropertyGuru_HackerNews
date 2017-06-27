package propertyguru.hackernews.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hitesh on 6/21/17.
 */

public class Story implements Parcelable {

    @SerializedName("id")
    private Long            id;
    @SerializedName("title")
    private String          title;
    @SerializedName("time")
    private Long            time;
    @SerializedName("score")
    private Long            score;
    @SerializedName("by")
    private String          by;
    @SerializedName("kids")
    private ArrayList<Long> kids;
    @SerializedName("type")
    private String          type;
    @SerializedName("url")
    private String          url;
    @SerializedName("parent")
    private String          parent;
    @SerializedName("parts")
    private String[]        parts;
    @SerializedName("descendants")
    private String          descendants;
    @SerializedName("dead")
    private boolean         dead;

    public Story() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public List<Long> getKids() {
        return kids;
    }

    public void setKids(ArrayList<Long> kids) {
        this.kids = kids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String[] getParts() {
        return parts;
    }

    public void setParts(String[] parts) {
        this.parts = parts;
    }

    public String getDescendants() {
        return descendants;
    }

    public void setDescendants(String descendants) {
        this.descendants = descendants;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", title = " + title + ", time = " + time + "," + " score = " + score + ", descendants = " + descendants + ", by = " + by + ", kids = "
                + kids + ", type = " + type + ", url = " + url + "]";
    }

    @Override
    public int hashCode() {
        int result = by != null ? by.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (kids != null ? kids.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.by);
        dest.writeValue(this.id);
        dest.writeValue(this.time);
        dest.writeSerializable(this.kids);
        dest.writeString(this.url);
        dest.writeValue(this.score);
        dest.writeString(this.title);
    }

    private Story(Parcel in) {
        this.by = in.readString();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.time = (Long) in.readValue(Long.class.getClassLoader());
        this.kids = (ArrayList<Long>) in.readSerializable();
        this.url = in.readString();
        this.score = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
    }

    public static final Parcelable.Creator<Story> CREATOR = new Parcelable.Creator<Story>() {
        public Story createFromParcel(Parcel source) {
            return new Story(source);
        }

        public Story[] newArray(int size) {
            return new Story[size];
        }
    };
}