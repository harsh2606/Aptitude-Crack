package com.example.apttitude_crack.GetterSetter;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TitleResponse {

    @SerializedName("title")
    @Expose
    private List<TitleData> title = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<TitleData> getTitle() {
        return title;
    }

    public void setTitle(List<TitleData> title) {

        this.title = title;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
