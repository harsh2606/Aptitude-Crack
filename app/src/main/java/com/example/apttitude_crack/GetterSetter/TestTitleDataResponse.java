package com.example.apttitude_crack.GetterSetter;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class TestTitleDataResponse {




        @SerializedName("title")
        @Expose
        private List<TestTitleData> title = null;
        @SerializedName("success")
        @Expose
        private Integer success;

        public List<TestTitleData> getTitle() {
            return title;
        }

        public void setTitle(List<TestTitleData> title) {
            this.title = title;
        }

        public Integer getSuccess() {
            return success;
        }

        public void setSuccess(Integer success) {
            this.success = success;
        }
}
