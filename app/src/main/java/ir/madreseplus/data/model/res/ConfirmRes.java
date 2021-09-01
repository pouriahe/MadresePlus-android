package ir.madreseplus.data.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConfirmRes {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("next_state")
    @Expose
    private Integer nextState;

    @SerializedName("next_state_text")
    @Expose
    private String nextStateText;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("error")
    @Expose
    private String error;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNextState() {
        return nextState;
    }

    public void setNextState(Integer nextState) {
        this.nextState = nextState;
    }

    public String getNextStateText() {
        return nextStateText;
    }

    public void setNextStateText(String nextStateText) {
        this.nextStateText = nextStateText;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}