package ir.madreseplus.data.model.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Student {

    @SerializedName("user_profile")
    @Expose
    private UserProfile userProfile = null;

    @SerializedName("get_field_display")
    @Expose
    private String getFieldDisplay;

    @SerializedName("get_grade_display")
    @Expose
    private String getGradeDisplay;

    @SerializedName("school_name")
    @Expose
    private String schoolName;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getGetFieldDisplay() {
        return getFieldDisplay;
    }

    public void setGetFieldDisplay(String getFieldDisplay) {
        this.getFieldDisplay = getFieldDisplay;
    }

    public String getGetGradeDisplay() {
        return getGradeDisplay;
    }

    public void setGetGradeDisplay(String getGradeDisplay) {
        this.getGradeDisplay = getGradeDisplay;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

}