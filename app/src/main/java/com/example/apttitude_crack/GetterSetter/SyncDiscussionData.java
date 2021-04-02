package com.example.apttitude_crack.GetterSetter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncDiscussionData {
        @SerializedName("discussion_id")
        @Expose
        private String discussionId;
        @SerializedName("title_id")
        @Expose
        private String titleId;
        @SerializedName("sub_title_id")
        @Expose
        private String subTitleId;
        @SerializedName("discussion_title")
        @Expose
        private String discussionTitle;
        @SerializedName("exam_valid")
        @Expose
        private String examValid;

        public String getDiscussionId() {
            return discussionId;
        }

        public void setDiscussionId(String discussionId) {
            this.discussionId = discussionId;
        }

        public String getTitleId() {
            return titleId;
        }

        public void setTitleId(String titleId) {
            this.titleId = titleId;
        }

        public String getSubTitleId() {
            return subTitleId;
        }

        public void setSubTitleId(String subTitleId) {
            this.subTitleId = subTitleId;
        }

        public String getDiscussionTitle() {
            return discussionTitle;
        }

        public void setDiscussionTitle(String discussionTitle) {
            this.discussionTitle = discussionTitle;
        }

        public String getExamValid() {
            return examValid;
        }

        public void setExamValid(String examValid) {
            this.examValid = examValid;
        }

    }


