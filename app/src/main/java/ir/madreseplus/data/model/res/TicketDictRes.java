package ir.madreseplus.data.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketDictRes {

    @SerializedName("clusters")
    @Expose
    private List<ClusterRes> clusters = null;

    @SerializedName("priority")
    @Expose
    private List<PriorityRes> priority = null;

    public List<ClusterRes> getClusters() {
        return clusters;
    }

    public void setClusters(List<ClusterRes> clusters) {
        this.clusters = clusters;
    }

    public List<PriorityRes> getPriority() {
        return priority;
    }

    public void setPriority(List<PriorityRes> priority) {
        this.priority = priority;
    }

}