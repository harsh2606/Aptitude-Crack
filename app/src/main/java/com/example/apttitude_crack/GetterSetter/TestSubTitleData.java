package com.example.apttitude_crack.GetterSetter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class TestSubTitleData {




        @SerializedName("sub_title_id")
        @Expose
        private String subTitleId;
        @SerializedName("sub_title_name")
        @Expose
        private String subTitleName;
        @SerializedName("title_id")
        @Expose
        private String titleId;
        @SerializedName("subtitleimg")
        @Expose
        private String subtitleimg;

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

        public String getSubtitleimg() {
            return subtitleimg;
        }

        public void setSubtitleimg(String subtitleimg) {
            this.subtitleimg = subtitleimg;
        }


}
