package ir.madreseplus.data.model.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ir.madreseplus.data.model.enums.EventStateEnum;
import ir.madreseplus.data.model.enums.EventTypeEnum;

public class Event {

    @SerializedName("pk")
    @Expose
    private Integer pk;

    @SerializedName("kind")
    @Expose
    private EventTypeEnum kind;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("learn_part")
    @Expose
    private Integer learnPart;

    @SerializedName("learn_part_code")
    @Expose
    private String learnPartCode;

    @SerializedName("book_name")
    @Expose
    private String bookName;

    @SerializedName("chapter_name")
    @Expose
    private String chapterName;

    @SerializedName("part_name")
    @Expose
    private String partName;

    @SerializedName("suggested_count")
    @Expose
    private Integer suggestedCount;

    @SerializedName("suggested_time")
    @Expose
    private String suggestedTime;

    @SerializedName("suggested_start_time")
    @Expose
    private String suggestedStartTime;

    @SerializedName("situation")
    @Expose
    private EventStateEnum situation;

    @SerializedName("date")
    @Expose
    private String date;

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public EventTypeEnum getKind() {
        return kind;
    }

    public void setKind(EventTypeEnum kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLearnPart() {
        return learnPart;
    }

    public void setLearnPart(Integer learnPart) {
        this.learnPart = learnPart;
    }

    public String getLearnPartCode() {
        return learnPartCode;
    }

    public void setLearnPartCode(String learnPartCode) {
        this.learnPartCode = learnPartCode;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getSuggestedCount() {
        return suggestedCount;
    }

    public void setSuggestedCount(Integer suggestedCount) {
        this.suggestedCount = suggestedCount;
    }

    public String getSuggestedTime() {
        return suggestedTime;
    }

    public void setSuggestedTime(String suggestedTime) {
        this.suggestedTime = suggestedTime;
    }

    public String getSuggestedStartTime() {
        return suggestedStartTime;
    }

    public void setSuggestedStartTime(String suggestedStartTime) {
        this.suggestedStartTime = suggestedStartTime;
    }

    public EventStateEnum getSituation() {
        return situation;
    }

    public void setSituation(EventStateEnum situation) {
        this.situation = situation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}