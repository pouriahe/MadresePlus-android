package ir.madreseplus.data.model.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Podcast {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("viewed")
    @Expose
    private Integer viewed;

    @SerializedName("get_category_name")
    @Expose
    private String getCategoryName;

    @SerializedName("tags")
    @Expose
    private List<Tag> tags = null;

    @SerializedName("get_file_url")
    @Expose
    private String getFileUrl;

    @SerializedName("file_format")
    @Expose
    private String fileFormat;

    @SerializedName("file_time_length")
    @Expose
    private String fileTimeLength;

    @SerializedName("get_image_url")
    @Expose
    private String getImageUrl;


    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("author_title")
    @Expose
    private String authorTitle;

    @SerializedName("created_on")
    @Expose
    private String createdOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public String getGetCategoryName() {
        return getCategoryName;
    }

    public void setGetCategoryName(String getCategoryName) {
        this.getCategoryName = getCategoryName;
    }

    public String getGetFileUrl() {
        return getFileUrl;
    }

    public void setGetFileUrl(String getFileUrl) {
        this.getFileUrl = getFileUrl;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getFileTimeLength() {
        return fileTimeLength;
    }

    public void setFileTimeLength(String fileTimeLength) {
        this.fileTimeLength = fileTimeLength;
    }

    public String getGetImageUrl() {
        return getImageUrl;
    }

    public void setGetImageUrl(String getImageUrl) {
        this.getImageUrl = getImageUrl;
    }


    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorTitle() {
        return authorTitle;
    }

    public void setAuthorTitle(String authorTitle) {
        this.authorTitle = authorTitle;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }


}