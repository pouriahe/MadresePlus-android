package ir.madreseplus.data.model.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ir.madreseplus.data.model.enums.EventStateEnum;
import ir.madreseplus.data.model.enums.EventTypeEnum;

public class Practice {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("event")
    @Expose
    private EventTypeEnum event;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("suggested_time")
    @Expose
    private String suggestedTime;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("start_time")
    @Expose
    private String startTime;

    @SerializedName("end_time")
    @Expose
    private String endTime;

    @SerializedName("reading_time")
    @Expose
    private String readingTime;

    @SerializedName("situation")
    @Expose
    private EventStateEnum situation;

    @SerializedName("assigned_by")
    @Expose
    private String assignedBy;

    @SerializedName("creator")
    @Expose
    private String creator;

    @SerializedName("suggested_count")
    @Expose
    private Integer suggestedCount;

    @SerializedName("learn_part_code")
    @Expose
    private String learnPartCode;

    @SerializedName("learn_part")
    @Expose
    private String learnPart;

    @SerializedName("learn_part_book_name")
    @Expose
    private String learnPartBookName;

    @SerializedName("learn_part_chapter")
    @Expose
    private String learnPartChapter;

    @SerializedName("learn_part_name")
    @Expose
    private String learnPartName;

    @SerializedName("learn_part_difficulty_level")
    @Expose
    private String learnPartDifficultyLevel;

    @SerializedName("suggested_book_name")
    @Expose
    private String suggestedBookName;

    @SerializedName("suggested_book_publisher")
    @Expose
    private String suggestedBookPublisher;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventTypeEnum getEvent() {
        return event;
    }

    public void setEvent(EventTypeEnum event) {
        this.event = event;
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

    public Object getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public Object getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getSuggestedCount() {
        return suggestedCount;
    }

    public void setSuggestedCount(Integer suggestedCount) {
        this.suggestedCount = suggestedCount;
    }

    public String getLearnPartCode() {
        return learnPartCode;
    }

    public void setLearnPartCode(String learnPartCode) {
        this.learnPartCode = learnPartCode;
    }

    public String getLearnPart() {
        return learnPart;
    }

    public void setLearnPart(String learnPart) {
        this.learnPart = learnPart;
    }

    public String getLearnPartBookName() {
        return learnPartBookName;
    }

    public void setLearnPartBookName(String learnPartBookName) {
        this.learnPartBookName = learnPartBookName;
    }

    public String getLearnPartChapter() {
        return learnPartChapter;
    }

    public void setLearnPartChapter(String learnPartChapter) {
        this.learnPartChapter = learnPartChapter;
    }

    public String getLearnPartName() {
        return learnPartName;
    }

    public void setLearnPartName(String learnPartName) {
        this.learnPartName = learnPartName;
    }

    public Object getLearnPartDifficultyLevel() {
        return learnPartDifficultyLevel;
    }

    public void setLearnPartDifficultyLevel(String learnPartDifficultyLevel) {
        this.learnPartDifficultyLevel = learnPartDifficultyLevel;
    }

    public String getSuggestedBookName() {
        return suggestedBookName;
    }

    public void setSuggestedBookName(String suggestedBookName) {
        this.suggestedBookName = suggestedBookName;
    }

    public String getSuggestedBookPublisher() {
        return suggestedBookPublisher;
    }

    public void setSuggestedBookPublisher(String suggestedBookPublisher) {
        this.suggestedBookPublisher = suggestedBookPublisher;
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