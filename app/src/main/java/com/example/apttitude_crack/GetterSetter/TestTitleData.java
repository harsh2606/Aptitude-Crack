package com.example.apttitude_crack.GetterSetter;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class TestTitleData {




        @SerializedName("title_id")
        @Expose
        private String titleId;
        @SerializedName("titlename")
        @Expose
        private String titlename;
        @SerializedName("titleimg")
        @Expose
        private String titleimg;
        @SerializedName("sub_title")
        @Expose
        private List<TestSubTitleData> subTitle = null;

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

        public List<TestSubTitleData> getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(List<TestSubTitleData> subTitle) {
            this.subTitle = subTitle;
        }


}
