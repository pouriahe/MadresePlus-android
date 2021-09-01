package ir.madreseplus.data.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplyRes {
    @SerializedName("created_on")
    @Expose
    public String createdOn;

    @SerializedName("creator")
    @Expose
    public Integer creator;

    @SerializedName("creator_avatar")
    @Expose
    public Object creatorAvatar;

    @SerializedName("creator_full_name")
    @Expose
    public String creatorFullName;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("owner")
    @Expose
    public Boolean owner;

    @SerializedName("text")
    @Expose
    public String text;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public Integer getCreator() {
        return this.creator;
    }

    public void setCreator(Integer num) {
        this.creator = num;
    }

    public String getCreatorFullName() {
        return this.creatorFullName;
    }

    public void setCreatorFullName(String str) {
        this.creatorFullName = str;
    }

    public Object getCreatorAvatar() {
        return this.creatorAvatar;
    }

    public void setCreatorAvatar(Object obj) {
        this.creatorAvatar = obj;
    }

    public String getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(String str) {
        this.createdOn = str;
    }

    public Boolean getOwner() {
        return this.owner;
    }

    public void setOwner(Boolean bool) {
        this.owner = bool;
    }
}
