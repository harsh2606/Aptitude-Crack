package com.example.apttitude_crack;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.database.Cursor;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apttitude_crack.Api.PrefManager;
import com.example.apttitude_crack.GetterSetter.TestSubTitleData;
import com.example.apttitude_crack.GetterSetter.TestTitleData;
import com.example.apttitude_crack.GetterSetter.TitleData;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.List;

public class TestAdapter extends BaseExpandableListAdapter {
    String string[]={};
    Context c;
    List<TestTitleData> titleData;
    private LayoutInflater inf;
    PrefManager pref;
    long time = 900000;
    boolean isPauseExam = false;
    int pauseExamId = -1;
    private long givenTime =-1;

//    public TestAdapter(String title,FragmentActivity activity){
//        string=title;
//        c=activity;
//    }

    public TestAdapter(List<TestTitleData> titleData, Context context) {
       this.titleData=titleData;
        c=context;
        inf=LayoutInflater.from(context);
        pref = new PrefManager(context);
    }

//    public TestAdapter(List<TitleData> titleData, FragmentActivity activity) {
//        String=title;
//        list=titleData;
//        context=activity;
//    }
//
//    public TestAdapter(Context context, List<TitleData> titleData) {
//
//    }

    @Override
    public int getGroupCount() {
        return titleData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return titleData.get(groupPosition).getSubTitle().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return titleData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return titleData.get(groupPosition).getSubTitle().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = this.inf.inflate(R.layout.list_theory_group, parent, false);
            viewHolder=new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.gettitle);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(titleData.get(groupPosition).getTitlename());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = this.inf.inflate(R.layout.list_subtitle_data, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.subtitle);
           convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
       final TestSubTitleData testSubTitleData = (TestSubTitleData)getChild(groupPosition,childPosition);
        viewHolder.textView.setText(testSubTitleData.getSubTitleName());

        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(c,"My Click",Toast.LENGTH_SHORT).show();

//                Intent intent=new Intent(c,GoogleSignActivity.class);
//                c.startActivity(intent);
                if (pref.getString("aptc_user_id")!=null)
                {
//                    Intent intent=new Intent(c,TestActivity.class);
//                    Log.w("krn","sub_title_id adapter:-"+testSubTitleData.getSubTitleId());
//                    intent.putExtra("sub_title_id",testSubTitleData.getSubTitleId());
//                    c.startActivity(intent);
                    Rules(testSubTitleData.getSubTitleId());
                }
                else
                {
                    Intent intent=new Intent(c,GoogleSignActivity.class);
                    intent.putExtra("sub_title_id",testSubTitleData.getSubTitleId());
                    c.startActivity(intent);
                }
            }
        });
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ViewHolder{

        TextView textView;
        ImageView imageView;

        private ViewHolder(){

        }
    }

    private void GoogleSign(){

        final Dialog alertDialog1 = new Dialog(c, R.style.CustomDialog);
        alertDialog1.setContentView(R.layout.whatsapp_share);
    }

    private void Rules(final String sub_title_id) {

        CheckAnyPauseExam( sub_title_id);

        final Dialog alertDialog1 = new Dialog(c, R.style.CustomDialog);
        alertDialog1.setContentView(R.layout.rules_layout);
        ImageView wrong= alertDialog1.findViewById(R.id.wrong);
        ImageView wrong1= alertDialog1.findViewById(R.id.wrong1);
        IndicatorSeekBar seekbar= alertDialog1.findViewById(R.id.seekbar);
        WebView text= alertDialog1.findViewById(R.id.text);

        text.loadDataWithBaseURL("",
                "<html>\n" +
                        "<head>\n" +
                        "<style>\n" +
                        "table, th, td {\n" +
                        "  border: 1px solid #e8eaeb;\n" +
                        "  border-collapse: collapse;\n" +
                        "}\n" +
                        "</style>\n" +
                        "</head>\n" +
                        "<body>\n" +

                        "<ul style=\"list-style-type: disc;margin-left: -25px;line-height: 24px;margin-top: -5px;\">\n" +
                        "<li>Test Consists of Total 15 Questions with Total 30 Marks.</li>\n" +
                        "<li>Negative Marking.</li>\n" +


                        "<li>Three Levels of Difficulty:\n" +
                        "<ul style=\"list-style-type: circle;margin-left: -25px;\">\n" +
                        "<li><strong>Easy:</strong> You will get 15 minutes to solve the test.</li>\n" +
                        "<li><strong>Medium:</strong> You will get 12 minutes to solve the test.</li>\n" +
                        "<li><strong>Hard:</strong> You will get 10 minutes to solve the test.</li>\n" +
                        "</ul>\n" +
                        "</li>\n" +
                        "</ul>\n" +
                        "<table style=\"background-color: #fff;box-shadow: 0 1px 2px rgba(0,0,0,.225);margin-top: -5px;\">\n" +
                        "<tbody>\n" +
                        "<tr><th colspan=\"3\" style=\"background-color: #f5f1f1;text-align: center;padding: 3px 15px 3px 15px;\">Marking System</th></tr><tr>\n" +
                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\"><strong>Correct</strong></td>\n" +
                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\"><strong>Incorrect</strong></td>\n" +
                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\"><strong>Not Attempt</strong></td>\n" +
                        "</tr>\n" +
                        "<tr style=\"text-align: center;\">\n" +
                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\">2 Mark</td>\n" +
                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\">-1 Mark</td>\n" +
                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\">0 Mark</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>\n" +
                        "</body>\n" +
                        "</html>",
                "text/html", "UTF-8", "");


        alertDialog1.setCancelable(false);
        alertDialog1.show();

        if (isPauseExam)
        {
            if (givenTime == 600000)
            {
                seekbar.setProgress(100);
                time = 600000;
            }
            else if (givenTime == 720000)
            {
                seekbar.setProgress(50);
                time = 720000;
            }
            else
            {
                seekbar.setProgress(0);
                time = 900000;

            }
            seekbar.setEnabled(false);

        }

        seekbar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                Log.w("my","seekbarparams:-"+seekParams.tickText);

                if(seekParams.tickText.equals("Hard"))
                {
                    time = 600000;
                }
                else if (seekParams.tickText.equals("Medium"))
                {
                    time = 720000;
                }
                else
                {
                    time = 900000;
                }

            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
            }
        });

        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();

                if (isPauseExam && pauseExamId != -1) {
                    Intent intent=new Intent(c,TestActivity.class);
                    intent.putExtra("sub_title_id", sub_title_id);
                    intent.putExtra("pause_exam_id", pauseExamId);
                    intent.putExtra("time",time);
                    c.startActivity(intent);

                }
                else
                {
                    Intent intent=new Intent(c,TestActivity.class);
                    intent.putExtra("sub_title_id", sub_title_id);
                    intent.putExtra("time",time);
                    c.startActivity(intent);
                }

//                CheckAnyPauseExam(sub_title_id);


//                    Intent intent=new Intent(c,TestActivity.class);
//                    Log.w("krn","sub_title_id adapter:-"+sub_title_id);
//                    intent.putExtra("sub_title_id",sub_title_id);
//                    c.startActivity(intent);

            }
        });

        wrong1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });
    }

    private void CheckAnyPauseExam(String sub_title_id)
    {
        TestAdapter1 mDbHelper = new TestAdapter1(c);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.CheckAnyPauseExam(sub_title_id);

        if (cursor.moveToFirst()) {
            isPauseExam = true;
            pauseExamId = cursor.getInt(0);
            givenTime = cursor.getLong(5);
        }

        mDbHelper.close();
    }
}
