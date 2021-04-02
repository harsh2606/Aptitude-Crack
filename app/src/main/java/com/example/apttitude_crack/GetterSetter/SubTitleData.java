package com.example.apttitude_crack.GetterSetter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubTitleData {





        @SerializedName("sub_title_id")
        @Expose
        private String subTitleId;
        @SerializedName("sub_title_name")
        @Expose
        private String subTitleName;
        @SerializedName("title_id")
        @Expose
        private String titleId;
        @SerializedName("titlename")
        @Expose
        private String titlename;
        @SerializedName("subtitleimg")
        @Expose
        private String subtitleimg;
        @SerializedName("tquestion")
        @Expose
        private String tquestion;

        @SerializedName("visited")
        @Expose
    private String visited;

        public String getSubTitleId() {
            return subTitleId;
        }

        public void setSubTitleId(String subTitleId) {
            this.subTitleId = subTitleId;
        }

        public String getSubTitleName() {
            return subTitleName;
        }

        public void setSubTitleName(String subTitleName) {
            this.subTitleName = subTitleName;
        }

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

        public String getSubtitleimg() {
            return subtitleimg;
        }

        public void setSubtitleimg(String subtitleimg) {
            this.subtitleimg = subtitleimg;
        }

    public String getTquestion() {
        return tquestion;
    }

    public void setTquestion(String tquestion) {
        this.tquestion = tquestion;
    }

    public String getVisited() {
        return visited;
    }

    public void setVisited(String visited) {
        this.visited = visited;
    }
}

