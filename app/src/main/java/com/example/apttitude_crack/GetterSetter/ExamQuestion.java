package com.example.apttitude_crack.GetterSetter;

public class ExamQuestion {

        int id, exam_id, question_id,isreview;

        public ExamQuestion(int id, int exam_id, int question_id, int isreview) {
            this.id = id;
            this.exam_id = exam_id;
            this.question_id = question_id;
            this.isreview = isreview;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getExam_id() {
            return exam_id;
        }

        public void setExam_id(int exam_id) {
            this.exam_id = exam_id;
        }

        public int getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(int question_id) {
            this.question_id = question_id;
        }

        public int getIsreview() {
            return isreview;
        }

        public void setIsreview(int isreview) {
            this.isreview = isreview;
        }


}
