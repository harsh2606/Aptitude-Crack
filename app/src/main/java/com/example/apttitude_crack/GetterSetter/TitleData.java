package com.example.apttitude_crack.GetterSetter;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TitleData {

    @SerializedName("title_id")
    @Expose
    private String titleId;
    @SerializedName("titlename")
    @Expose
    private String titlename;


    @SerializedName("titleimg")
    @Expose
    private String titleimg;

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTitlename() {
        return titlename;
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }

    public String getTitleimg() {
        return titleimg;
    }

    public void setTitleimg(String titleimg) {
        this.titleimg = titleimg;
    }


}
