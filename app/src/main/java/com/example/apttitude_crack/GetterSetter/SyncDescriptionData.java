package com.example.apttitude_crack.GetterSetter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncDescriptionData {

        @SerializedName("description_id")
        @Expose
        private String descriptionId;
        @SerializedName("description_detail")
        @Expose
        private String descriptionDetail;
        @SerializedName("discussion_id")
        @Expose
        private String discussionId;

        public String getDescriptionId() {
            return descriptionId;
        }

        public void setDescriptionId(String descriptionId) {
            this.descriptionId = descriptionId;
        }

        public String getDescriptionDetail() {
            return descriptionDetail;
        }

        public void setDescriptionDetail(String descriptionDetail) {
            this.descriptionDetail = descriptionDetail;
        }

        public String getDiscussionId() {
            return discussionId;
        }

        public void setDiscussionId(String discussionId) {
            this.discussionId = discussionId;
        }



}
