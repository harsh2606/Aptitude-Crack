package com.example.apttitude_crack.GetterSetter;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubTitleResponse {

    @SerializedName("sub_title")
    @Expose
    private List<SubTitleData> subTitle = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<SubTitleData> getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(List<SubTitleData> subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }


}
