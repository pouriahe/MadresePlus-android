package ir.madreseplus.data.model.enums;

import android.icu.util.Freezable;

import com.google.gson.annotations.SerializedName;

public enum EventTypeEnum {
    @SerializedName("0")
    READ(0, "مطالعه"),
    @SerializedName("1")
    REVIEW(1, "مرور"),
    @SerializedName("2")
    PRACTICE(2, "تست"),
    @SerializedName("3")
    FREE(3, "متفرقه"),
    @SerializedName("4")
    SUMMARIZE(4, "خلاصه‌نویسی"),
    @SerializedName("5")
    HOMEWORK(5, "تمرین"),
    @SerializedName("6")
    BUFFER(6, "جبرانی"),
    @SerializedName("7")
    ANALYSIS(7, "تحلیل"),
    @SerializedName("8")
    EXAM(8, "آزمون");


    private int id;
    private String text;

    EventTypeEnum(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public static EventTypeEnum fromValue(int id) {
        switch (id) {
            case 0:
                return READ;
            case 1:
                return REVIEW;
            case 2:
                return PRACTICE;
            case 3:
                return FREE;
            case 4:
                return SUMMARIZE;
            case 5:
                return HOMEWORK;
            case 6:
                return BUFFER;
            case 7:
                return ANALYSIS;
            case 8:
                return EXAM;
            default:
                return null;
        }
    }
}
