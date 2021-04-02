package com.example.apttitude_crack.GetterSetter;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionBySubTitleResponse {

        @SerializedName("question")
        @Expose
        private List<Question> question = null;
        @SerializedName("success")
        @Expose
        private Integer success;

        public List<Question> getQuestion() {
            return question;
        }

        public void setQuestion(List<Question> question) {
            this.question = question;
        }

        public Integer getSuccess() {
            return success;
        }

        public void setSuccess(Integer success) {
            this.success = success;
        }

    }


