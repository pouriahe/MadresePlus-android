package ir.madreseplus.data.model.enums;

import com.google.gson.annotations.SerializedName;

public enum GenderTypeEnum {
    @SerializedName("0")
    MALE(0, "مرد"),
    @SerializedName("1")
    FEMALE(1, "زن");

    private int id;
    private String text;

    GenderTypeEnum(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public static GenderTypeEnum fromValue(int id) {
        switch (id) {
            case 0:
                return MALE;
            case 1:
                return FEMALE;
            default:
                return null;
        }
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
