package ir.madreseplus.data.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppVersionRes {

    @SerializedName("version_name")
    @Expose
    private String versionName;

    @SerializedName("version_code")
    @Expose
    private Integer versionCode;

    @SerializedName("min_sdk_version")
    @Expose
    private Integer minSdkVersion;

    @SerializedName("target_sdk_version")
    @Expose
    private Integer targetSdkVersion;

    @SerializedName("change_note")
    @Expose
    private String changeNote;

    @SerializedName("force_update")
    @Expose
    private Boolean forceUpdate;

    @SerializedName("active")
    @Expose
    private Boolean active;

    @SerializedName("accept_by_bazaar")
    @Expose
    private Boolean acceptByBazaar;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public Integer getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(Integer minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    public Integer getTargetSdkVersion() {
        return targetSdkVersion;
    }

    public void setTargetSdkVersion(Integer targetSdkVersion) {
        this.targetSdkVersion = targetSdkVersion;
    }

    public String getChangeNote() {
        return changeNote;
    }

    public void setChangeNote(String changeNote) {
        this.changeNote = changeNote;
    }

    public Boolean getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getAcceptByBazaar() {
        return acceptByBazaar;
    }

    public void setAcceptByBazaar(Boolean acceptByBazaar) {
        this.acceptByBazaar = acceptByBazaar;
    }

}
