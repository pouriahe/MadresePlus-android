package ir.madreseplus.data.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ir.madreseplus.data.model.enums.MoodLevelEnum;

/**
 * Ahmad Nosratian created on 8/21/20
 */

public class DailyReportRes {

    @SerializedName("mood")
    @Expose
    private MoodLevelEnum moodLevel;

    @SerializedName("text")
    @Expose
    private String moodText;

    @SerializedName("can_send")
    @Expose
    private Boolean can_send;

    public MoodLevelEnum getMoodLevel() {
        return moodLevel;
    }

    public void setMoodLevel(MoodLevelEnum moodLevel) {
        this.moodLevel = moodLevel;
    }

    public String getMoodText() {
        return moodText;
    }

    public void setMoodText(String moodText) {
        this.moodText = moodText;
    }

    public Boolean getCan_send() {
        return can_send;
    }

    public void setCan_send(Boolean can_send) {
        this.can_send = can_send;
    }
}
