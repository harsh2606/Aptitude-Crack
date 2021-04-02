package com.example.apttitude_crack.GetterSetter;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SyncQuestionData {

        @SerializedName("options")
        @Expose
        private List<SyncOptionData> options = null;
        @SerializedName("discussion")
        @Expose
        private List<SyncDiscussionData> discussion = null;
        @SerializedName("description")
        @Expose
        private List<SyncDescriptionData> description = null;
        @SerializedName("answer")
        @Expose
        private List<SyncAnswerData> answer = null;
        @SerializedName("question_id")
        @Expose
        private String questionId;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("sub_title_id")
        @Expose
        private String subTitleId;
        @SerializedName("discussion_id")
        @Expose
        private String discussionId;

        public List<SyncOptionData> getOptions() {
            return options;
        }

        public void setOptions(List<SyncOptionData> options) {
            this.options = options;
        }

        public List<SyncDiscussionData> getDiscussion() {
            return discussion;
        }

        public void setDiscussion(List<SyncDiscussionData> discussion) {
            this.discussion = discussion;
        }

        public List<SyncDescriptionData> getDescription() {
            return description;
        }

        public void setDescription(List<SyncDescriptionData> description) {
            this.description = description;
        }

        public List<SyncAnswerData> getAnswer() {
            return answer;
        }

        public void setAnswer(List<SyncAnswerData> answer) {
            this.answer = answer;
        }

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getSubTitleId() {
            return subTitleId;
        }

        public void setSubTitleId(String subTitleId) {
            this.subTitleId = subTitleId;
        }

        public String getDiscussionId() {
            return discussionId;
        }

        public void setDiscussionId(String discussionId) {
            this.discussionId = discussionId;
        }



}
