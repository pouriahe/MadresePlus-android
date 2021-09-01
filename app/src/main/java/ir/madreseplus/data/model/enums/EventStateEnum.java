package ir.madreseplus.data.model.enums;

import com.google.gson.annotations.SerializedName;

public enum EventStateEnum {
    @SerializedName("0")
    TODO(0, "To Do"),
    @SerializedName("1")
    DOING(1, "Doing"),
    @SerializedName("2")
    DONE(2, "Done"),
    @SerializedName("3")
    OVER_DO(3, "Over Do"),
    @SerializedName("4")
    FORGOTTEN(4, "Forgotten");


    private int id;
    private String text;

    EventStateEnum(int id, String text) {
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
