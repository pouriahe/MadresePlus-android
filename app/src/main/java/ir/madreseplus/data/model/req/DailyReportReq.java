package ir.madreseplus.data.model.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ir.madreseplus.data.model.enums.MoodLevelEnum;

/**
 * Ahmad Nosratian created on 8/21/20
 */

public class DailyReportReq {

    @SerializedName("mood_level")
    @Expose
    private MoodLevelEnum moodLevel;

    @SerializedName("mood_text")
    @Expose
    private String moodText;

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
}
