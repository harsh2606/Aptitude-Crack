package com.example.apttitude_crack;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.apttitude_crack.GetterSetter.ExamQuestion;

import java.util.List;

class MarkReviewAdepter extends BaseAdapter {
    Context mContext;
   List<ExamQuestion> examQuestions;

    public MarkReviewAdepter(Context c, List<ExamQuestion> examQuestions) {
        mContext = c;
        this.examQuestions = examQuestions;
    }

    @Override
    public int getCount() {
        return examQuestions.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//       View view;


        // if (convertView!=null)
        // {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//           view= new View(mContext);
        View inflate = layoutInflater.inflate(R.layout.markasreview_item, null);
        TextView mark_question_id = inflate.findViewById(R.id.mark_question_id);
        CardView card = inflate.findViewById(R.id.card);

        String is_review = "Not Mark As Review";
        if (examQuestions.get(position).getIsreview() == 1)
        {
            is_review = "Mark As Review";
            card.setCardBackgroundColor(Color.parseColor("#DEE2F3"));
        }

        String detail = "Question " + (position+1) + " | " +is_review;

        mark_question_id.setText(detail);

        return inflate;
    }
}
