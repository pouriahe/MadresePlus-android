package ir.madreseplus.data.model.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketReplyReq {
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("to")
    @Expose

    /* renamed from: to */
    public Integer f238to;

    public Integer getTo() {
        return this.f238to;
    }

    public void setTo(Integer num) {
        this.f238to = num;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
