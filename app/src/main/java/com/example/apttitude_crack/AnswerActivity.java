package com.example.apttitude_crack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apttitude_crack.Api.PrefManager;
import com.example.apttitude_crack.GetterSetter.Option;
import com.example.apttitude_crack.GetterSetter.Question;
import com.example.apttitude_crack.GetterSetter.QuestionTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnswerActivity extends AppCompatActivity {
    RelativeLayout r1, r2, r3, r4, r5, snap;
    ImageView right1, right2, right3, right4, right5, backimg, not, next, finish, previous;

    WebView descussion_que, question, wbsol, opationaweb, opationbweb, opationcweb, opationdweb, opationeweb;
    RadioButton ans1, ans2, ans3, ans4, ans5;

    RadioGroup rdg;
    TextView q_id, s, sub, time;
    private String title_id;
    private QuestionTest questions;
    int count = 1;
    private Object subAptitudeTitle;
    int question_counter = 0;
    String question_id = null;
    PrefManager pref;
    private String title_name;
    private String sub_title_id;
    MediaPlayer mp;
    long TIMEOUT = 900000;

    private int position = 0;
    List<Integer> question_ids;
    int exam_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        mp = MediaPlayer.create(this, R.raw.click);

        pref = new PrefManager(AnswerActivity.this);
        Intent intent = getIntent();

        sub_title_id = intent.getStringExtra("sub_title_id");
        exam_id = intent.getIntExtra("exam_id", 0);

        if (intent.hasExtra("time")) {
            TIMEOUT = intent.getLongExtra("time", 900000);
        }

        question_id = pref.getString("SUB_TITLE_" + sub_title_id);
        Log.w("my", "title_id:- " + title_id);
        Log.w("my", "sub_title_id:- " + sub_title_id);

//        Intent intent = getIntent();
//        title_id = intent.getStringExtra("title_id");
//        sub_title_id = intent.getStringExtra("sub_title_id");
//        questions = new ArrayList<>();


        descussion_que = (WebView) findViewById(R.id.descussion_que);
        question = (WebView) findViewById(R.id.question);
        wbsol = (WebView) findViewById(R.id.wbsol);
        opationaweb = (WebView) findViewById(R.id.opationaweb);

        opationbweb = (WebView) findViewById(R.id.opationbweb);

        opationcweb = (WebView) findViewById(R.id.opationcweb);

        opationdweb = (WebView) findViewById(R.id.opationdweb);

        opationeweb = (WebView) findViewById(R.id.opationeweb);

        rdg = (RadioGroup) findViewById(R.id.rdg);
        ans1 = (RadioButton) findViewById(R.id.ans1);
        ans2 = (RadioButton) findViewById(R.id.ans2);
        ans3 = (RadioButton) findViewById(R.id.ans3);
        ans4 = (RadioButton) findViewById(R.id.ans4);
        ans5 = (RadioButton) findViewById(R.id.ans5);
        next = (ImageView) findViewById(R.id.next);
        finish = (ImageView) findViewById(R.id.finish);
        previous = (ImageView) findViewById(R.id.previous);

        q_id = (TextView) findViewById(R.id.q_id);
        s = (TextView) findViewById(R.id.s);
        sub = (TextView) findViewById(R.id.sub);
        time = (TextView) findViewById(R.id.time);


        r1 = (RelativeLayout) findViewById(R.id.r1);
        r2 = (RelativeLayout) findViewById(R.id.r2);
        r3 = (RelativeLayout) findViewById(R.id.r3);
        r4 = (RelativeLayout) findViewById(R.id.r4);
        r5 = (RelativeLayout) findViewById(R.id.r5);
        snap = (RelativeLayout) findViewById(R.id.snap);

        right1 = (ImageView) findViewById(R.id.right1);
        right2 = (ImageView) findViewById(R.id.right2);
        right3 = (ImageView) findViewById(R.id.right3);
        right4 = (ImageView) findViewById(R.id.right4);
        right5 = (ImageView) findViewById(R.id.right5);
        not = (ImageView) findViewById(R.id.not);


        backimg = (ImageView) findViewById(R.id.backimg);

        if (TIMEOUT == 900000) {

            time.setText("Easy");
        }
        if (TIMEOUT == 720000) {

            time.setText("Medium");
        }
        if (TIMEOUT == 600000) {

            time.setText("Hard");
        }

        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                AlertDialog.Builder builder = new AlertDialog.Builder(AnswerActivity.this);
//                builder.setMessage("Do You want To Exit??");
//                builder.setTitle("Exit Dialog");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Intent intent = new Intent(AnswerActivity.this ,ResultActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.putExtra("title_id",title_id);
//                        intent.putExtra("title_name",title_name);
//                        startActivity(intent);
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//
//                });
//                builder.show();
                finish();
            }
        });


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
//                Intent intent1 = new Intent(AnswerActivity.this,ResultActivity.class);
//                startActivity(intent1);
                onBackPressed();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();


                position = position + 1;
                if (position <= 15) {
                    INVISIBLE();
                    loadQuestionfromDB();
                }

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                position = position - 1;
                if (position >= 0) {
                    INVISIBLE();
                    loadQuestionfromDB();
                }
            }
        });

        FetchQuestionforExam();
    }

    private void loadQuestionfromDB() {
        TestAdapter1 mDbHelper = new TestAdapter1(AnswerActivity.this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor mCur;
        Log.d("my ::::", "============== >> position ::  " + position);
//        if (action.equals("next")) {

        mCur = mDbHelper.getTestQuestionByID(exam_id, sub_title_id, String.valueOf(question_ids.get(position)));

//        } else if (action.equals("prev")) {
//
//            mCur = mDbHelper.getQuestionByID(sub_title_id, String.valueOf( question_ids.get(position)));
//
//        } else {
//
//            mCur = mDbHelper.getQuestionByID(sub_title_id, String.valueOf( question_ids.get(position)));
//
//        }


//        Cursor mCur = mDbHelper.getNextQuestion(Integer.parseInt(sub_title_id));

        questions = new QuestionTest();


        if (mCur.moveToFirst()) {
            do {
//                Question que = new Question();
                //TitleData td = new TitleData();
                questions.setQuestionId(String.valueOf(mCur.getInt(1)));
                questions.setQuestion(mCur.getString(2));
                questions.setSubTitleId(mCur.getString(3));
                questions.setDiscussionId(mCur.getString(4));
                questions.setSubTitleName(mCur.getString(6));
                questions.setAnswerid(mCur.getString(7));
                questions.setObjid(mCur.getString(8));
                questions.setAnswerDescription(mCur.getString(9));
                questions.setDiscussionTitle(mCur.getString(10));
                questions.setDescriptionDetail(mCur.getString(11));
                questions.setDescriptionId(mCur.getString(12));
                questions.setIsreview(mCur.getInt(13));
                questions.setObjSelectedId(mCur.getInt(14));
//                 que.setDiscussion(cursor.getString(4));

            } while (mCur.moveToNext());
        }
        sub.setText(questions.getSubTitleName());
        mDbHelper.close();

        GetOptionForQuestion();


//        basicElectronic_adapter = new BasicElectronic_Adapter(Arithmetic_Aptitude_Activity.this,SubTitleData);
//
//        sub_Arithmetic_Grid.setAdapter(basicElectronic_adapter);
    }

    private void FetchQuestionforExam() {
        if (exam_id != -1) {
            TestAdapter1 mDbHelper = new TestAdapter1(this);
            mDbHelper.createDatabase();
            mDbHelper.open();
            question_ids = new ArrayList<>();
            Cursor cursor = mDbHelper.FetchQuestionOfExam(exam_id);
            if (cursor.moveToFirst()) {
                do {
                    question_ids.add(cursor.getInt(0));
                } while (cursor.moveToNext());

            }

            mDbHelper.close();
        }

        if (question_ids.size() > 0) {
            loadQuestionfromDB();
        }
    }

    private void GetOptionForQuestion() {
        if (questions.getQuestionId() != null) {
            TestAdapter1 mDbHelper = new TestAdapter1(AnswerActivity.this);
            mDbHelper.createDatabase();
            mDbHelper.open();
            Cursor mCur = mDbHelper.getOptionsofQuestion(Integer.parseInt(questions.getQuestionId()));
            List<Option> options = new ArrayList<>();
            if (mCur.moveToFirst()) {
                do {
                    try {
//                        Option option = new Option();
//                        option.setObjid(mCur.getString(1));
//                        option.setOptions(mCur.getString(2));
                        options.add(new Option(mCur.getString(1), mCur.getString(2)));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } while (mCur.moveToNext());
                questions.setOptions(options);
            }
            mDbHelper.close();
        }
        setQuestions();
    }

    private void setQuestions() {

        Log.d("my ===============", " ========= position :: ");

        if (questions.getDescriptionId() != null) {
            descussion_que.loadDataWithBaseURL("", questions.getDescriptionDetail().replace("localhost:80/aptitude_crack/admin/", "api.aptitudecrack.com/"), "text/html", "UTF-8", "");
            descussion_que.setVisibility(View.VISIBLE);
        }
        if (questions.getQuestion() != null) {
            Log.w("my = ", questions.getQuestion().replace("localhost:80", "192.168.0.3:80"));
            question.loadDataWithBaseURL("", "Question:- " + (position + 1) + questions.getQuestion().replace("localhost:80/aptitude_crack/admin/", "api.aptitudecrack.com/"), "text/html", "UTF-8", "");
            question.setVisibility(View.VISIBLE);
        }
        if (questions.getObjSelectedId() != 0) {
            not.setVisibility(View.GONE);
        } else {
            not.setVisibility(View.VISIBLE);

        }

        if (questions.getOptions().size() > 0) {
            for (int i = 0; i < questions.getOptions().size(); i++) {
                if (i == 0) {
//                                    ans1.setText("A");
                    Log.w("my", "Que:-A=" + questions.getOptions().get(i).getOptions());


                    setRadioButtons(ans1, r1, right1, "clear");
                    ans1.setChecked(false);
                    opationaweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans1.setTag(questions.getOptions().get(i).getObjid());
                    ans1.setVisibility(View.VISIBLE);
                    r1.setVisibility(View.VISIBLE);


//                    ans1.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
////                            Unchecked(ans1);
////                            if (ans1.getTag().equals(questions.getObjid())) {
////                                right1.setVisibility(View.VISIBLE);
////                                r1.setBackgroundResource(R.drawable.cornersright);
////                                ans1.setButtonDrawable(R.drawable.circle);
////                                right1.setImageResource(R.drawable.check);
////                                Toast.makeText(QuestionActivity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
////                            } else {
////                                right1.setVisibility(View.VISIBLE);
////                                r1.setBackgroundResource(R.drawable.cornerwrong);
////                                ans1.setButtonDrawable(R.drawable.circleworng);
////                                right1.setImageResource(R.drawable.wrong);
////                                Toast.makeText(QuestionActivity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
////                            }
//                        }
//                    });

                    if (questions.getObjSelectedId() != 0) {
                        if (questions.getObjSelectedId() == Integer.parseInt(ans1.getTag().toString())) {
                            ans1.setChecked(true);
                            if (ans1.getTag().equals(questions.getObjid())) {
                                setRadioButtons(ans1, r1, right1, "right");
                            } else {
                                setRadioButtons(ans1, r1, right1, "wrong");
                            }
                        } else {
                            if (ans1.getTag().equals(questions.getObjid())) {
                                ans1.setChecked(true);
                                setRadioButtons(ans1, r1, right1, "right");
                            }
                        }

                    }
                }
                if (i == 1) {
//                                    Remove UnderLine Code for  Radio Button

                    setRadioButtons(ans2, r2, right2, "clear");
                    ans2.setChecked(false);
                    opationbweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans2.setTag(questions.getOptions().get(i).getObjid());
//                                    ans2.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans2.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
//                    ans2.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
////                            Unchecked(ans2);
////                            if (ans2.getTag().equals(questions.getObjid())) {
////                                right2.setVisibility(View.VISIBLE);
////                                r2.setBackgroundResource(R.drawable.cornersright);
////                                ans2.setButtonDrawable(R.drawable.circle);
////                                right2.setImageResource(R.drawable.check);
////                                Toast.makeText(QuestionActivity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
////                            } else {
////                                right2.setVisibility(View.VISIBLE);
////                                r2.setBackgroundResource(R.drawable.cornerwrong);
////                                ans2.setButtonDrawable(R.drawable.circleworng);
////                                right2.setImageResource(R.drawable.wrong);
////                                Toast.makeText(QuestionActivity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
////                            }
//                        }
//                    });

                    if (questions.getObjSelectedId() != 0) {
                        if (questions.getObjSelectedId() == Integer.parseInt(ans2.getTag().toString())) {
                            ans2.setChecked(true);
                            if (ans2.getTag().equals(questions.getObjid())) {
                                setRadioButtons(ans2, r2, right2, "right");
                            } else {
                                setRadioButtons(ans2, r2, right2, "wrong");
                            }
                        } else {
                            if (ans2.getTag().equals(questions.getObjid())) {
                                ans2.setChecked(true);
                                setRadioButtons(ans2, r2, right2, "right");
                            }
                        }

                    }

                }
                if (i == 2) {
                    r3.setVisibility(View.VISIBLE);

                    setRadioButtons(ans3, r3, right3, "clear");
                    ans3.setChecked(false);
                    opationcweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans3.setVisibility(View.VISIBLE);
                    ans3.setTag(questions.getOptions().get(i).getObjid());
//                                    ans3.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));

//                    ans3.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
////                            Unchecked(ans3);
////                            if (ans3.getTag().equals(questions.getObjid())) {
////                                right3.setVisibility(View.VISIBLE);
////                                r3.setBackgroundResource(R.drawable.cornersright);
////                                ans3.setButtonDrawable(R.drawable.circle);
////                                right3.setImageResource(R.drawable.check);
////                                Toast.makeText(QuestionActivity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
////                            } else {
////                                right3.setVisibility(View.VISIBLE);
////                                r3.setBackgroundResource(R.drawable.cornerwrong);
////                                ans3.setButtonDrawable(R.drawable.circleworng);
////                                right3.setImageResource(R.drawable.wrong);
////                                Toast.makeText(QuestionActivity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
////                            }
//                        }
//                    });

                    if (questions.getObjSelectedId() != 0) {
                        if (questions.getObjSelectedId() == Integer.parseInt(ans3.getTag().toString())) {
                            ans3.setChecked(true);
                            if (ans3.getTag().equals(questions.getObjid())) {
                                setRadioButtons(ans3, r3, right3, "right");
                            } else {
                                setRadioButtons(ans3, r3, right3, "wrong");
                            }
                        } else {
                            if (ans3.getTag().equals(questions.getObjid())) {
                                ans3.setChecked(true);
                                setRadioButtons(ans3, r3, right3, "right");
                            }
                        }
                    }
                }
                if (i == 3) {

                    setRadioButtons(ans4, r4, right4, "clear");
                    ans4.setChecked(false);
                    opationdweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans4.setTag(questions.getOptions().get(i).getObjid());
//                                    ans4.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans4.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.VISIBLE);
//                    ans4.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
////                            Unchecked(ans4);
////                            if (ans4.getTag().equals(questions.getObjid())) {
////                                right4.setVisibility(View.VISIBLE);
////                                r4.setBackgroundResource(R.drawable.cornersright);
////                                ans4.setButtonDrawable(R.drawable.circle);
////                                right4.setImageResource(R.drawable.check);
////                                Toast.makeText(QuestionActivity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
////                            } else {
////                                right4.setVisibility(View.VISIBLE);
////                                r4.setBackgroundResource(R.drawable.cornerwrong);
////                                ans4.setButtonDrawable(R.drawable.circleworng);
////                                right4.setImageResource(R.drawable.wrong);
////                                Toast.makeText(QuestionActivity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
////                            }
//                        }
//                    });
                    if (questions.getObjSelectedId() != 0) {
                        if (questions.getObjSelectedId() == Integer.parseInt(ans4.getTag().toString())) {
                            ans4.setChecked(true);
                            if (ans4.getTag().equals(questions.getObjid())) {
                                setRadioButtons(ans4, r4, right4, "right");
                            } else {
                                setRadioButtons(ans4, r4, right4, "wrong");
                            }
                        } else {
                            if (ans4.getTag().equals(questions.getObjid())) {
                                ans4.setChecked(true);
                                setRadioButtons(ans4, r4, right4, "right");
                            }
                        }
                    }
                }
                if (i == 4) {

                    setRadioButtons(ans5, r5, right5, "clear");
                    ans5.setChecked(false);
                    opationeweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans5.setTag(questions.getOptions().get(i).getObjid());
//                                    ans5.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans5.setVisibility(View.VISIBLE);
                    r5.setVisibility(View.VISIBLE);

//                    ans5.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
////                            Unchecked(ans5);
////                            if (ans5.getTag().equals(questions.getObjid())) {
////                                right5.setVisibility(View.VISIBLE);
////                                r5.setBackgroundResource(R.drawable.cornersright);
////                                ans5.setButtonDrawable(R.drawable.circle);
////                                right5.setImageResource(R.drawable.check);
////                                Toast.makeText(QuestionActivity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
////                            } else {
////                                right5.setVisibility(View.VISIBLE);
////                                r5.setBackgroundResource(R.drawable.cornerwrong);
////                                ans5.setButtonDrawable(R.drawable.circleworng);
////                                right5.setImageResource(R.drawable.wrong);
////                                Toast.makeText(QuestionActivity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
////                            }
//                        }
//                    });

                    if (questions.getObjSelectedId() != 0) {
                        if (questions.getObjSelectedId() == Integer.parseInt(ans5.getTag().toString())) {
                            ans5.setChecked(true);
                            if (ans5.getTag().equals(questions.getObjid())) {
                                setRadioButtons(ans5, r5, right5, "right");
                            } else {
                                setRadioButtons(ans5, r5, right5, "wrong");
                            }
                        } else {
                            if (ans5.getTag().equals(questions.getObjid())) {
                                ans5.setChecked(true);
                                setRadioButtons(ans5, r5, right5, "right");
                            }
                        }
                    }
                }
            }
        }

        if (questions.getAnswerDescription() != null) {
            wbsol.loadDataWithBaseURL("", questions.getAnswerDescription(), "text/html", "UTF-8", "");

        }
        count = 1;
//        websolution.setVisibility(View.GONE);
//
//        solution.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.d("my", "count:-" + count);
////
//                if (count == 0) {
//                    count = 1;
//                    websolution.setVisibility(View.GONE);
//                    txts.setVisibility(View.GONE);
//                } else {
//                    count = 0;
//                    Log.d("my", "count1:-" + count);
//                    websolution.setVisibility(View.VISIBLE);
//                    txts.setVisibility(View.VISIBLE);
//
//                }
//
//            }
//
//        });


//        SetQuestionISUB_TITLE_sVisited(questions.getQuestionId());

//        cb.setChecked(false);
//      txttotal.setText(position+1 + "/10");
        SetPrevNextButtonAction();

//       position = FindIndex(Integer.parseInt(questions.getQuestionId()));
//
//        if (questions.getIsreview() != 0)
//        {
//            cb.setChecked(true);
//        }


    }

    private void setRadioButtons(RadioButton rb, RelativeLayout rl, ImageView ig, String action) {
        if (action.equals("right")) {
            rb.setVisibility(View.VISIBLE);
            rl.setBackgroundResource(R.drawable.cornersright);
            rb.setButtonDrawable(R.drawable.circle);
            ig.setImageResource(R.drawable.check);
        } else if (action.equals("wrong")) {
            rb.setVisibility(View.VISIBLE);
            rl.setBackgroundResource(R.drawable.cornerwrong);
            rb.setButtonDrawable(R.drawable.circleworng);
            ig.setImageResource(R.drawable.wrong);
        } else {
            rb.setVisibility(View.VISIBLE);
            rl.setBackgroundResource(R.drawable.corners);
            rb.setButtonDrawable(R.drawable.circle);
            ig.setVisibility(View.GONE);
        }
    }

    private void SetPrevNextButtonAction() {
        if (position > 0) {
            previous.setImageResource(R.drawable.previous_press_unpress);
            previous.setEnabled(true);
        } else {
            previous.setImageResource(R.drawable.previous_disable);
            previous.setEnabled(false);
        }

        if (position < 14) {
            next.setImageResource(R.drawable.next_press_unpress);
            next.setEnabled(true);
        } else {
            next.setImageResource(R.drawable.next_disable);
            next.setEnabled(false);
        }
    }


    public String RemoveHtmlUrl(String data) {
        if (data.contains("<a")) {
            int index = data.indexOf("<a");
            int endindex = index + (data.substring(index).indexOf(">") + 1);
            String a = data.substring(0, index) + data.substring(endindex);
            return RemoveHtmlUrl(a);
        } else {
            return data;
        }
    }

    private void INVISIBLE() {
        r1.setVisibility(View.GONE);
        r2.setVisibility(View.GONE);
        r3.setVisibility(View.GONE);
        r4.setVisibility(View.GONE);
        r5.setVisibility(View.GONE);

    }
}
