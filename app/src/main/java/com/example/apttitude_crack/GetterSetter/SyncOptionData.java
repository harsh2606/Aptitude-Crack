package com.example.apttitude_crack.GetterSetter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncOptionData {

        @SerializedName("objid")
        @Expose
        private String objid;
        @SerializedName("options")
        @Expose
        private String options;
        @SerializedName("question_id")
        @Expose
        private String questionId;

        public String getObjid() {
            return objid;
        }

        public void setObjid(String objid) {
            this.objid = objid;
        }

        public String getOptions() {
            return options;
        }

        public void setOptions(String options) {
            this.options = options;
        }

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }




}
