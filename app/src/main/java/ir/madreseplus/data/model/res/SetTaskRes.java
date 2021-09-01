package ir.madreseplus.data.model.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ir.madreseplus.data.model.enums.EventStateEnum;

public class SetTaskRes {

    @SerializedName("situation")
    @Expose
    private EventStateEnum situation;


}