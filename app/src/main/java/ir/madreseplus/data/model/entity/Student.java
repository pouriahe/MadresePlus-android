package ir.madreseplus.data.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import ir.madreseplus.data.model.enums.GenderTypeEnum;

@Entity(tableName = "student")
public class Student {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "schoolName")
    private String schoolName;

    @ColumnInfo(name = "grade")
    private String grade;

    @ColumnInfo(name = "fieldStudy")
    private String fieldStudy;

    @ColumnInfo(name = "gender")
    private GenderTypeEnum genderTypeEnum;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFieldStudy() {
        return fieldStudy;
    }

    public void setFieldStudy(String fieldStudy) {
        this.fieldStudy = fieldStudy;
    }

    public GenderTypeEnum getGenderTypeEnum() {
        return genderTypeEnum;
    }

    public void setGenderTypeEnum(GenderTypeEnum genderTypeEnum) {
        this.genderTypeEnum = genderTypeEnum;
    }
}
