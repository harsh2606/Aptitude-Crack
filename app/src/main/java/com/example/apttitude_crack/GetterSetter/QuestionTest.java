package com.example.apttitude_crack.GetterSetter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionTest {

        @SerializedName("question_id")
        @Expose
        private String questionId;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("sub_title_id")
        @Expose
        private String subTitleId;
        @SerializedName("sub_title_name")
        @Expose
        private String subTitleName;
        @SerializedName("discussion_id")
        @Expose
        private String discussionId;
        @SerializedName("discussion_title")
        @Expose
        private String discussionTitle;
        @SerializedName("description_id")
        @Expose
        private String descriptionId;
        @SerializedName("description_detail")
        @Expose
        private String descriptionDetail;
        @SerializedName("answerid")
        @Expose
        private String answerid;
        @SerializedName("answer_description")
        @Expose
        private String answerDescription;

        @SerializedName("objid")
        @Expose
        private String objid;

        @SerializedName("isreview")
        @Expose
        private int isreview;

        @SerializedName("obj_selected_id")
        @Expose
        private int objSelectedId;

        @SerializedName("options")
        @Expose
        private List<Option> options = null;

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

        public String getSubTitleName() {
            return subTitleName;
        }

        public void setSubTitleName(String subTitleName) {
            this.subTitleName = subTitleName;
        }

        public String getDiscussionId() {
            return discussionId;
        }

        public void setDiscussionId(String discussionId) {
            this.discussionId = discussionId;
        }

        public String getDiscussionTitle() {
            return discussionTitle;
        }

        public void setDiscussionTitle(String discussionTitle) {
            this.discussionTitle = discussionTitle;
        }

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

        public String getAnswerid() {
            return answerid;
        }

        public void setAnswerid(String answerid) {
            this.answerid = answerid;
        }

        public String getAnswerDescription() {
            return answerDescription;
        }

        public void setAnswerDescription(String answerDescription) {
            this.answerDescription = answerDescription;
        }

        public List<Option> getOptions() {
            return options;
        }

        public void setOptions(List<Option> options) {
            this.options = options;
        }

        public String getObjid() {
            return objid;
        }

        public void setObjid(String objid) {
            this.objid = objid;
        }

        public int getIsreview() {
            return isreview;
        }

        public void setIsreview(int isreview) {
            this.isreview = isreview;
        }

        public int getObjSelectedId() {
            return objSelectedId;
        }

        public void setObjSelectedId(int objSelectedId) {
            this.objSelectedId = objSelectedId;
        }
    }



