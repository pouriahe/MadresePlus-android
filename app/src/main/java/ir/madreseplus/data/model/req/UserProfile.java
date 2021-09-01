package ir.madreseplus.data.model.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ir.madreseplus.data.model.enums.GenderTypeEnum;

public class UserProfile {

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("gender")
    @Expose
    private GenderTypeEnum gender;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("national_code")
    @Expose
    private String nationalCode;

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

//    @SerializedName("get_base64_avatar")
//    @Expose
//    private String getBase64Avatar;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GenderTypeEnum getGender() {
        return gender;
    }

    public void setGender(GenderTypeEnum gender) {
        this.gender = gender;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Object getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }


}