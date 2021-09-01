package ir.madreseplus.data.model.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ir.madreseplus.data.model.enums.EventStateEnum;
import ir.madreseplus.data.model.enums.EventTypeEnum;

public class Task {

    @SerializedName("pk")
    @Expose
    private Integer pk;

    @SerializedName("kind")
    @Expose
    private EventTypeEnum kind;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("adv_description")
    @Expose
    private String advDescription;

    @SerializedName("suggested_time")
    @Expose
    private String suggestedTime;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("suggested_start_time")
    @Expose
    private String suggestedStartTime;

    @SerializedName("suggested_count")
    @Expose
    private Integer suggestedCount;

//    @SerializedName("end_time")
//    @Expose
//    private Object endTime;

    @SerializedName("reading_time")
    @Expose
    private String readingTime;

    @SerializedName("situation")
    @Expose
    private EventStateEnum situation;

    @SerializedName("learn_part")
    @Expose
    private String learnPart;

    @SerializedName("book_name")
    @Expose
    private String bookName;

    @SerializedName("chapter_name")
    @Expose
    private String chapterName;

    @SerializedName("part_name")
    @Expose
    private String partName;

    @SerializedName("assigned_by")
    @Expose
    private Integer assignedBy;

    @SerializedName("assigned_by_name")
    @Expose
    private String assignedByName;

    @SerializedName("creator_name")
    @Expose
    private String creatorName;

    @SerializedName("correct")
    @Expose
    private Integer correct;

    @SerializedName("wrong")
    @Expose
    private Integer wrong;

    @SerializedName("no_answer")
    @Expose
    private Integer noAnswer;

    @SerializedName("percent")
    @Expose
    private Double percent;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdvDescription() {
        return advDescription;
    }

    public void setAdvDescription(String advDescription) {
        this.advDescription = advDescription;
    }

    public String getSuggestedTime() {
        return suggestedTime;
    }

    public void setSuggestedTime(String suggestedTime) {
        this.suggestedTime = suggestedTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSuggestedStartTime() {
        return suggestedStartTime;
    }

    public void setSuggestedStartTime(String suggestedStartTime) {
        this.suggestedStartTime = suggestedStartTime;
    }

    public Integer getSuggestedCount() {
        return suggestedCount;
    }

    public void setSuggestedCount(Integer suggestedCount) {
        this.suggestedCount = suggestedCount;
    }

    public String getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(String readingTime) {
        this.readingTime = readingTime;
    }

    public EventStateEnum getSituation() {
        return situation;
    }

    public void setSituation(EventStateEnum situation) {
        this.situation = situation;
    }

    public String getLearnPart() {
        return learnPart;
    }

    public void setLearnPart(String learnPart) {
        this.learnPart = learnPart;
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

    public Integer getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Integer assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getAssignedByName() {
        return assignedByName;
    }

    public void setAssignedByName(String assignedByName) {
        this.assignedByName = assignedByName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Integer getWrong() {
        return wrong;
    }

    public void setWrong(Integer wrong) {
        this.wrong = wrong;
    }

    public Integer getNoAnswer() {
        return noAnswer;
    }

    public void setNoAnswer(Integer noAnswer) {
        this.noAnswer = noAnswer;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }
}