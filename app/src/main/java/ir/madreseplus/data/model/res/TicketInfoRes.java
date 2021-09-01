package ir.madreseplus.data.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketInfoRes {
    @SerializedName("cluster")
    @Expose
    public Integer cluster;

    @SerializedName("created_on")
    @Expose
    public String createdOn;

    @SerializedName("get_cluster_display")
    @Expose
    public String getClusterDisplay;

    @SerializedName("get_priority_display")
    @Expose
    public String getPriorityDisplay;

    @SerializedName("get_status_display")
    @Expose
    public String getStatusDisplay;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("priority")
    @Expose
    public Integer priority;

    @SerializedName("replies")
    @Expose
    public List<ReplyRes> replies = null;

    @SerializedName("status")
    @Expose
    public Integer status;

    @SerializedName("text")
    @Expose
    public String text;

    @SerializedName("title")
    @Expose
    public String title;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public Integer getCluster() {
        return this.cluster;
    }

    public void setCluster(Integer num) {
        this.cluster = num;
    }

    public String getGetClusterDisplay() {
        return this.getClusterDisplay;
    }

    public void setGetClusterDisplay(String str) {
        this.getClusterDisplay = str;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public String getGetStatusDisplay() {
        return this.getStatusDisplay;
    }

    public void setGetStatusDisplay(String str) {
        this.getStatusDisplay = str;
    }

    public String getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(String str) {
        this.createdOn = str;
    }

    public List<ReplyRes> getReplies() {
        return this.replies;
    }

    public void setReplies(List<ReplyRes> list) {
        this.replies = list;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer num) {
        this.priority = num;
    }

    public String getGetPriorityDisplay() {
        return this.getPriorityDisplay;
    }

    public void setGetPriorityDisplay(String str) {
        this.getPriorityDisplay = str;
    }
}