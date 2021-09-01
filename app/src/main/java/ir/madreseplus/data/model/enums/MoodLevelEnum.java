package ir.madreseplus.data.model.enums;

import com.google.gson.annotations.SerializedName;

public enum MoodLevelEnum {
    @SerializedName("1")
    VERY_BAD(1, "VERY_BAD"),
    @SerializedName("2")
    BAD(2, "BAD"),
    @SerializedName("3")
    NORMAL(3, "NORMAL"),
    @SerializedName("4")
    GOOD(4, "GOOD"),
    @SerializedName("5")
    VERY_GOOD(5, "VERY_GOOD");

    private int id;
    private String text;

    MoodLevelEnum(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
