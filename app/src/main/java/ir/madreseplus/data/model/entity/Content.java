package ir.madreseplus.data.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import ir.madreseplus.utilities.ListTableConverter;


@Entity(tableName = "content")

public class Content {

    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull
    private Integer id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "viewed")
    private Integer viewed;

    @ColumnInfo(name = "context")
    private String context;

    @ColumnInfo(name = "get_category_name")
    private String getCategoryName;

    @TypeConverters(ListTableConverter.class)
    @ColumnInfo(name = "tags")
    private List<String> tags;

    @ColumnInfo(name = "get_image_url")
    private String getImageUrl;


    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getGetCategoryName() {
        return getCategoryName;
    }

    public void setGetCategoryName(String getCategoryName) {
        this.getCategoryName = getCategoryName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getGetImageUrl() {
        return getImageUrl;
    }

    public void setGetImageUrl(String getImageUrl) {
        this.getImageUrl = getImageUrl;
    }
}
