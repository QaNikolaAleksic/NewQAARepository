package com.projectname.api.client.data.model.users.create;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.projectname.api.client.data.model.users.update.UpdateUserRequest;
import com.projectname.api.client.data.model.users.update.UpdateUserResponse;

public class CreateBooksResponse implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("excerpt")
    @Expose
    private String excerpt;
    @SerializedName("publishDate")
    @Expose
    private String publishDate;
    private final static long serialVersionUID = 765285635079952721L;

    public CreateBooksResponse() {
    }

    public CreateBooksResponse(Integer id, String title, String description, Integer pageCount, String excerpt, String publishDate) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageCount = pageCount;
        this.excerpt = excerpt;
        this.publishDate = publishDate;
    }

    public static CreateBooksResponse parseExpectedCreateBooksResponse(CreateBooksRequest createBooksRequest) {
        CreateBooksResponse createBooksResponse = new CreateBooksResponse();
        createBooksResponse.setTitle(createBooksRequest.getTitle());
        createBooksResponse.setDescription(createBooksRequest.getDescription());
        createBooksResponse.setPageCount(createBooksRequest.getPageCount());
        createBooksResponse.setExcerpt(createBooksRequest.getExcerpt());
        createBooksResponse.setPublishDate(createBooksRequest.getPublishDate());
        return createBooksResponse;
    }

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

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

}
