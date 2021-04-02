package com.example.apttitude_crack.GetterSetter;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncQuestionResponse {

         @SerializedName("questions")
        @Expose
        private List<SyncQuestionData> questions = null;
        @SerializedName("success")
        @Expose
        private Integer success;

        public List<SyncQuestionData> getQuestions() {
            return questions;
        }

        public void setQuestions(List<SyncQuestionData> questions) {
            this.questions = questions;
        }

        public Integer getSuccess() {
            return success;
        }

        public void setSuccess(Integer success) {
            this.success = success;
        }

    }


