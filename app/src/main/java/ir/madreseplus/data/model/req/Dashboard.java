package ir.madreseplus.data.model.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dashboard {

    @SerializedName("student")
    @Expose
    private Student student;

    @SerializedName("exam_time")
    @Expose
    private String examTime;

    @SerializedName("today")
    @Expose
    private String today;

    @SerializedName("final_exam_time")
    @Expose
    private Integer finalExamTime;

    @SerializedName("task_progress")
    @Expose
    private Integer taskProgress;

    @SerializedName("events")
    @Expose
    private List<Event> events ;


    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public Integer getFinalExamTime() {
        return finalExamTime;
    }

    public void setFinalExamTime(Integer finalExamTime) {
        this.finalExamTime = finalExamTime;
    }

    public Integer getTaskProgress() {
        return taskProgress;
    }

    public void setTaskProgress(Integer taskProgress) {
        this.taskProgress = taskProgress;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}