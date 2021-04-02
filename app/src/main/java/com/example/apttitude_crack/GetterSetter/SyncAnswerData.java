package com.example.apttitude_crack.GetterSetter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncAnswerData {



        @SerializedName("answerid")
        @Expose
        private String answerid;
        @SerializedName("objid")
        @Expose
        private String objid;
        @SerializedName("answer_description")
        @Expose
        private String answerDescription;
        @SerializedName("question_id")
        @Expose
        private String questionId;

        public String getAnswerid() {
            return answerid;
        }

        public void setAnswerid(String answerid) {
            this.answerid = answerid;
        }

        public String getObjid() {
            return objid;
        }

        public void setObjid(String objid) {
            this.objid = objid;
        }

        public String getAnswerDescription() {
            return answerDescription;
        }

        public void setAnswerDescription(String answerDescription) {
            this.answerDescription = answerDescription;
        }

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }


}
