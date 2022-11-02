package com.projectname.api.client.data.model.users.get;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBooksRequest implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = 3233723609723365525L;

    public GetBooksRequest() {
    }

    public GetBooksRequest(Integer id) {
        super();
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
