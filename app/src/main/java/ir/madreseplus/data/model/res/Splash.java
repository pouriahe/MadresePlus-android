package ir.madreseplus.data.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Splash {

    @SerializedName("error_code")
    @Expose
    private Integer error_code;

    @SerializedName("error_text")
    @Expose
    private String error_text;

    @SerializedName("next_state")
    @Expose
    private Integer nextState;

    @SerializedName("app_version")
    @Expose
    private AppVersionRes app_version;


    public Integer getError_code() {
        return error_code;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public String getError_text() {
        return error_text;
    }

    public void setError_text(String error_text) {
        this.error_text = error_text;
    }

    public Integer getNextState() {
        return nextState;
    }

    public void setNextState(Integer nextState) {
        this.nextState = nextState;
    }

    public AppVersionRes getApp_version() {
        return app_version;
    }

    public void setApp_version(AppVersionRes app_version) {
        this.app_version = app_version;
    }
}