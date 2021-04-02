package com.example.apttitude_crack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.internal.view.SupportMenu;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apttitude_crack.Api.PrefManager;
import com.example.apttitude_crack.GetterSetter.ExamQuestion;
import com.example.apttitude_crack.GetterSetter.Option;
import com.example.apttitude_crack.GetterSetter.Question;
import com.example.apttitude_crack.GetterSetter.QuestionTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnTouchListener {

    TextView txttotal,txttime,tid,sub,time;
    WebView descussion_que, question,wbsol,opationaweb,opationbweb,opationcweb,opationdweb,opationeweb;
    RadioButton ans1, ans2, ans3, ans4, ans5;
    CheckBox cb;
    ProgressBar progress_bar_item_image;
    MediaPlayer mp,camera;
    ImageView right1, right2, right3, right4, right5,sharesnap,txtreview, next, finish, previous;



    RadioGroup rdg;
//    MyCounter timer;
    private int mins=10;
    private int sec=0;
    private PrefManager pref;
    private String sub_title_id;
    private int exam_id = -1;
    List<Integer>question_ids;
    private QuestionTest questions;
    RelativeLayout r1, r2, r3, r4, r5,snap;

    private int count;
    private int position=0;

    List<ExamQuestion> examQuestions;
    final static long INTERVAL = 1000;
     static long TIMEOUT = 900000;
    static long millisecondsleft;
    boolean isPause =false;

    CountDownTimer countDownTimer;
    CountDownTimer countDownTimeronResume;

    int pause_exam_id = -1;
    int last_question_id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mp = MediaPlayer.create(this, R.raw.click);
        camera = MediaPlayer.create(this, R.raw.camera_1);

        pref = new PrefManager(this);
        Intent intent = getIntent();
        sub_title_id = intent.getStringExtra("sub_title_id");

        if (intent.hasExtra("pause_exam_id"))
        {
            pause_exam_id = intent.getIntExtra("pause_exam_id", -1);
        }
        if (intent.hasExtra("time"))
        {
            TIMEOUT = intent.getLongExtra("time", 900000);
        }

        Log.w("krn","sub_title_id:-"+sub_title_id);
        txttotal = (TextView) findViewById(R.id.txttotal);
        sub = (TextView) findViewById(R.id.sub);
        txttime = (TextView) findViewById(R.id.txttime);
        tid = (TextView) findViewById(R.id.tid);
        time = (TextView) findViewById(R.id.time);

        descussion_que = (WebView) findViewById(R.id.descussion_que);
        question = (WebView) findViewById(R.id.question);
        wbsol = (WebView) findViewById(R.id.wbsol);
        opationaweb = (WebView) findViewById(R.id.opationaweb);
        opationaweb.setOnTouchListener(this);
        opationbweb = (WebView) findViewById(R.id.opationbweb);
        opationbweb.setOnTouchListener(this);
        opationcweb = (WebView) findViewById(R.id.opationcweb);
        opationcweb.setOnTouchListener(this);
        opationdweb = (WebView) findViewById(R.id.opationdweb);
        opationdweb.setOnTouchListener(this);
        opationeweb = (WebView) findViewById(R.id.opationeweb);
        opationeweb.setOnTouchListener(this);

        rdg = (RadioGroup) findViewById(R.id.rdg);
        ans1 = (RadioButton) findViewById(R.id.ans1);
        ans2 = (RadioButton) findViewById(R.id.ans2);
        ans3 = (RadioButton) findViewById(R.id.ans3);
        ans4 = (RadioButton) findViewById(R.id.ans4);
        ans5 = (RadioButton) findViewById(R.id.ans5);

        cb = (CheckBox) findViewById(R.id.cb);

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
        sharesnap = (ImageView) findViewById(R.id.sharesnap);

        txtreview = (ImageView) findViewById(R.id.txtreview);
        next = (ImageView) findViewById(R.id.next);
        finish = (ImageView) findViewById(R.id.finish);
        previous = (ImageView) findViewById(R.id.previous);
        progress_bar_item_image = (ProgressBar) findViewById(R.id.progress_bar_item_image);

//        this.timer = new MyCounter((long) (((this.mins * 60) * 1000) + (this.sec * 1000)), 1000);
//        this.timer.start();
         if(TIMEOUT == 900000)
         {

             time.setText("Easy");
         }
        if(TIMEOUT == 720000)
        {

            time.setText("Medium");
        }
        if(TIMEOUT == 600000)
        {

            time.setText("Hard");
        }
        if (pause_exam_id != -1)
        {
            Log.d("HELLO", " **** PAUSE EXAM ID :: " + pause_exam_id);
            exam_id = pause_exam_id;
            getExamDetails(exam_id);
            FetchQuestionforExam(last_question_id);

        }
        else
        {
            SetRandomQuestionForExam();
        }
//        SetRandomQuestionForExam();

        sharesnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.start();
                Bitmap bitmap1 = loadBitmapFromView(snap, snap.getWidth(), snap.getHeight());
                saveBitmap(bitmap1);
                String completePath = Environment.getExternalStorageDirectory() + "/" + "screenshotdemo.jpg";
                File file = new File(completePath);
                TestActivity.this.shareImage(file, "Share Challenge on", "", "");
            }
        });

        txtreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                MarkReview();
                UpdateUserAnswer();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                UpdateUserAnswer();
                SubmitGame();
                SetGameState("FINISHED");
                if(countDownTimer!=null){
                    countDownTimer.cancel();
                }
                if(countDownTimeronResume!=null){
                    countDownTimeronResume.cancel();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                builder.setMessage("Are You Sure You Want To Submit Test??");
                builder.setTitle("Submit Test");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(TestActivity.this,ResultActivity.class);
                        intent.putExtra("exam_id",exam_id);
                        intent.putExtra("sub_title_id",sub_title_id);
                        intent.putExtra("sub_title_name", questions.getSubTitleName());
                        intent.putExtra("time", TIMEOUT);
                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

//                TestActivity.this.finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                UpdateUserAnswer();
                position = position+1;
                if (position <= 15)
                {
                    INVISIBLE();
                    loadQuestionfromDB("next",null);
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                UpdateUserAnswer();
                position = position-1;
                if (position >= 0)
                {
                    INVISIBLE();
                    loadQuestionfromDB("prev",null);
                }
            }
        });

    }

    private void MarkReview() {


        final Dialog alertDialog1 = new Dialog(TestActivity.this, R.style.CustomDialog);
        alertDialog1.setContentView(R.layout.markasreview);
        GridView attempted= alertDialog1.findViewById(R.id.attempted);
        ImageView wrong= alertDialog1.findViewById(R.id.wrong);

        examQuestions = new ArrayList<>();
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getRecordMarkedAsReview(exam_id);

        if (cursor.moveToFirst()) {
            do {
                examQuestions.add(new ExamQuestion(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());

        }
        mDbHelper.close();
        if (examQuestions.size() > 0)
        {
            MarkReviewAdepter markas_review_adapter = new MarkReviewAdepter(this, examQuestions);
            attempted.setAdapter(markas_review_adapter);
        }



        attempted.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TestActivity.this.position = position;
                Log.w("my ","harsh:-"+position);
                loadQuestionfromDB("", String.valueOf(examQuestions.get(position).getQuestion_id()));
                alertDialog1.dismiss();
            }
        });

        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });

        alertDialog1.setCancelable(false);
        alertDialog1.show();

    }



        private void SetRandomQuestionForExam()
        {
            String user_id = pref.getString("aptc_user_id");
//            String currentTime = Calendar.getInstance().getTime().toString();
            long time = System.currentTimeMillis();
            if(user_id!= null)
            {
                TestAdapter1 mDbHelper = new TestAdapter1(this);
                mDbHelper.createDatabase();
                mDbHelper.open();
                Log.w("krn","sub_title_id inset:-"+sub_title_id);
                exam_id = mDbHelper.InsertExamData(sub_title_id, user_id,time,TIMEOUT);
                Log.w("krn","exam_id:-"+exam_id);
                mDbHelper.close();
                FetchQuestionforExam(-1);
            }
        }

        private void FetchQuestionforExam(int question_id)
        {
            if (exam_id != -1)
            {
                Log.w("my","FetchQuestionforExam");
                TestAdapter1 mDbHelper1 = new TestAdapter1(this);
                mDbHelper1.createDatabase();
                mDbHelper1.open();
                question_ids = new ArrayList<>();
                Cursor cursor =  mDbHelper1.FetchQuestionOfExam(exam_id);
                Log.w("my","question_idsup:-"+question_ids);
                Log.w("my","exam_id:-"+exam_id);
                if (cursor.moveToFirst()) {
                    Log.w("my","question_ids:-"+question_ids);
                    do {
//                        question_ids.add(cursor.getInt(0));
                        question_ids.add(cursor.getInt(0));

                    } while (cursor.moveToNext());

                }
                Log.w("my","exam_id down:-"+exam_id);
                mDbHelper1.close();
            }

            if (question_ids.size() > 0)
            {
                if (question_id != -1)
                {
                    position = question_ids.indexOf(question_id);
                    loadQuestionfromDB("", null);
                    isPause = true;
                }
                else
                {
                    loadQuestionfromDB("", null);
                }
            }
        }

    private void loadQuestionfromDB(String action,String question_id) {
        TestAdapter1 mDbHelper = new TestAdapter1(TestActivity.this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor mCur;
        Log.d("my ::::", "============== >> position ::  " + position);
        Log.d("krn ::::", "============== >> position ::  " + position);

        if (question_id != null)
        {
            mCur = mDbHelper.getTestQuestionByID(exam_id, sub_title_id, question_id);
        }
        else {
            mCur = mDbHelper.getTestQuestionByID(exam_id, sub_title_id, String.valueOf( question_ids.get(position)));
        }

//        if (action.equals("next")) {

//        mCur = mDbHelper.getQuestionByID(sub_title_id, String.valueOf( question_ids.get(position)));
//        mCur = mDbHelper.getTestQuestionByID(exam_id, sub_title_id, String.valueOf( question_ids.get(position)));

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

    private void GetOptionForQuestion() {
        if (questions.getQuestionId() != null) {
            TestAdapter1 mDbHelper = new TestAdapter1(TestActivity.this);
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
    private void Unchecked(RadioButton finished) {
        if (ans1.getId() == finished.getId()) {

            ans2.setChecked(false);
            ans3.setChecked(false);
            ans4.setChecked(false);
            ans5.setChecked(false);
//            r2.setBackgroundResource(R.drawable.corners);
//            r3.setBackgroundResource(R.drawable.corners);
//            r4.setBackgroundResource(R.drawable.corners);
//            r5.setBackgroundResource(R.drawable.corners);

//            right2.setVisibility(View.INVISIBLE);
//            right3.setVisibility(View.INVISIBLE);
//            right4.setVisibility(View.INVISIBLE);
//            right5.setVisibility(View.INVISIBLE);
        } else if (finished.getId() == ans2.getId()) {
            ans1.setChecked(false);
            ans3.setChecked(false);
            ans4.setChecked(false);
            ans5.setChecked(false);
//            r1.setBackgroundResource(R.drawable.corners);
//            r3.setBackgroundResource(R.drawable.corners);
//            r4.setBackgroundResource(R.drawable.corners);
//            r5.setBackgroundResource(R.drawable.corners);

//            right1.setVisibility(View.INVISIBLE);
//            right3.setVisibility(View.INVISIBLE);
//            right4.setVisibility(View.INVISIBLE);
//            right5.setVisibility(View.INVISIBLE);
        } else if (finished.getId() == ans3.getId()) {
            ans1.setChecked(false);
            ans2.setChecked(false);
            ans4.setChecked(false);
            ans5.setChecked(false);
//            r2.setBackgroundResource(R.drawable.corners);
//            r1.setBackgroundResource(R.drawable.corners);
//            r4.setBackgroundResource(R.drawable.corners);
//            r5.setBackgroundResource(R.drawable.corners);
//
//            right1.setVisibility(View.INVISIBLE);
//            right2.setVisibility(View.INVISIBLE);
//            right4.setVisibility(View.INVISIBLE);
//            right5.setVisibility(View.INVISIBLE);
        } else if (finished.getId() == ans4.getId()) {
            ans1.setChecked(false);
            ans3.setChecked(false);
            ans2.setChecked(false);
            ans5.setChecked(false);
//            r2.setBackgroundResource(R.drawable.corners);
//            r3.setBackgroundResource(R.drawable.corners);
//            r1.setBackgroundResource(R.drawable.corners);
//            r5.setBackgroundResource(R.drawable.corners);
//
//            right1.setVisibility(View.INVISIBLE);
//            right2.setVisibility(View.INVISIBLE);
//            right3.setVisibility(View.INVISIBLE);
//            right5.setVisibility(View.INVISIBLE);
        } else if (finished.getId() == ans5.getId()) {
            ans1.setChecked(false);
            ans3.setChecked(false);
            ans4.setChecked(false);
            ans2.setChecked(false);
//            r2.setBackgroundResource(R.drawable.corners);
//            r3.setBackgroundResource(R.drawable.corners);
//            r4.setBackgroundResource(R.drawable.corners);
//            r1.setBackgroundResource(R.drawable.corners);
//
//            right2.setVisibility(View.INVISIBLE);
//            right3.setVisibility(View.INVISIBLE);
//            right4.setVisibility(View.INVISIBLE);
//            right1.setVisibility(View.INVISIBLE);
        }
    }

    private void setQuestions() {

        Log.d("my ===============", " ========= position :: ");

        if (questions.getDescriptionId() != null) {
            descussion_que.loadDataWithBaseURL("", questions.getDescriptionDetail().replace("localhost:80/aptitude_crack/admin/", "api.aptitudecrack.com/"), "text/html", "UTF-8", "");
            descussion_que.setVisibility(View.VISIBLE);
        }
        if (questions.getQuestion() != null) {
            Log.w("my = ", questions.getQuestion().replace("localhost:80", "192.168.0.3:80"));
            question.loadDataWithBaseURL("", " " + questions.getQuestion().replace("localhost:80/aptitude_crack/admin/", "api.aptitudecrack.com/"), "text/html", "UTF-8", "");
            question.setVisibility(View.VISIBLE);
        }

        if (questions.getOptions().size() > 0) {
            for (int i = 0; i < questions.getOptions().size(); i++) {
                if (i == 0) {
//                                    ans1.setText("A");
                    Log.w("my", "Que:-A=" + questions.getOptions().get(i).getOptions());



                    ans1.setChecked(false);
                    opationaweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans1.setTag(questions.getOptions().get(i).getObjid());
                    ans1.setVisibility(View.VISIBLE);
                    r1.setVisibility(View.VISIBLE);


                    ans1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Unchecked(ans1);
//                            if (ans1.getTag().equals(questions.getObjid())) {
//                                right1.setVisibility(View.VISIBLE);
//                                r1.setBackgroundResource(R.drawable.cornersright);
//                                ans1.setButtonDrawable(R.drawable.circle);
//                                right1.setImageResource(R.drawable.check);
//                                Toast.makeText(QuestionActivity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
//                            } else {
//                                right1.setVisibility(View.VISIBLE);
//                                r1.setBackgroundResource(R.drawable.cornerwrong);
//                                ans1.setButtonDrawable(R.drawable.circleworng);
//                                right1.setImageResource(R.drawable.wrong);
//                                Toast.makeText(QuestionActivity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    });

                    if (questions.getObjSelectedId() != 0)
                    {
                        if (questions.getObjSelectedId() == Integer.parseInt(ans1.getTag().toString()))
                        {
                            ans1.setChecked(true);
                        }
                    }
                }
                if (i == 1) {
//                                    Remove UnderLine Code for  Radio Button

                    ans2.setChecked(false);
                    opationbweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans2.setTag(questions.getOptions().get(i).getObjid());
//                                    ans2.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans2.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                    ans2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Unchecked(ans2);
//                            if (ans2.getTag().equals(questions.getObjid())) {
//                                right2.setVisibility(View.VISIBLE);
//                                r2.setBackgroundResource(R.drawable.cornersright);
//                                ans2.setButtonDrawable(R.drawable.circle);
//                                right2.setImageResource(R.drawable.check);
//                                Toast.makeText(QuestionActivity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
//                            } else {
//                                right2.setVisibility(View.VISIBLE);
//                                r2.setBackgroundResource(R.drawable.cornerwrong);
//                                ans2.setButtonDrawable(R.drawable.circleworng);
//                                right2.setImageResource(R.drawable.wrong);
//                                Toast.makeText(QuestionActivity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    });
                    if (questions.getObjSelectedId() != 0)
                    {
                        if (questions.getObjSelectedId() == Integer.parseInt(ans2.getTag().toString()))
                        {
                            ans2.setChecked(true);
                        }
                    }
                }
                if (i == 2) {
                    r3.setVisibility(View.VISIBLE);

                    ans3.setChecked(false);
                    opationcweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans3.setVisibility(View.VISIBLE);
                    ans3.setTag(questions.getOptions().get(i).getObjid());
//                                    ans3.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));

                    ans3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Unchecked(ans3);
//                            if (ans3.getTag().equals(questions.getObjid())) {
//                                right3.setVisibility(View.VISIBLE);
//                                r3.setBackgroundResource(R.drawable.cornersright);
//                                ans3.setButtonDrawable(R.drawable.circle);
//                                right3.setImageResource(R.drawable.check);
//                                Toast.makeText(QuestionActivity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
//                            } else {
//                                right3.setVisibility(View.VISIBLE);
//                                r3.setBackgroundResource(R.drawable.cornerwrong);
//                                ans3.setButtonDrawable(R.drawable.circleworng);
//                                right3.setImageResource(R.drawable.wrong);
//                                Toast.makeText(QuestionActivity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    });
                    if (questions.getObjSelectedId() != 0)
                    {
                        if (questions.getObjSelectedId() == Integer.parseInt(ans3.getTag().toString()))
                        {
                            ans3.setChecked(true);
                        }
                    }
                }
                if (i == 3) {

                    ans4.setChecked(false);
                    opationdweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans4.setTag(questions.getOptions().get(i).getObjid());
//                                    ans4.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans4.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.VISIBLE);
                    ans4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Unchecked(ans4);
//                            if (ans4.getTag().equals(questions.getObjid())) {
//                                right4.setVisibility(View.VISIBLE);
//                                r4.setBackgroundResource(R.drawable.cornersright);
//                                ans4.setButtonDrawable(R.drawable.circle);
//                                right4.setImageResource(R.drawable.check);
//                                Toast.makeText(QuestionActivity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
//                            } else {
//                                right4.setVisibility(View.VISIBLE);
//                                r4.setBackgroundResource(R.drawable.cornerwrong);
//                                ans4.setButtonDrawable(R.drawable.circleworng);
//                                right4.setImageResource(R.drawable.wrong);
//                                Toast.makeText(QuestionActivity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    });
                    if (questions.getObjSelectedId() != 0)
                    {
                        if (questions.getObjSelectedId() == Integer.parseInt(ans4.getTag().toString()))
                        {
                            ans4.setChecked(true);
                        }
                    }
                }
                if (i == 4) {

                    ans5.setChecked(false);
                    opationeweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans5.setTag(questions.getOptions().get(i).getObjid());
//                                    ans5.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans5.setVisibility(View.VISIBLE);
                    r5.setVisibility(View.VISIBLE);

                    ans5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Unchecked(ans5);
//                            if (ans5.getTag().equals(questions.getObjid())) {
//                                right5.setVisibility(View.VISIBLE);
//                                r5.setBackgroundResource(R.drawable.cornersright);
//                                ans5.setButtonDrawable(R.drawable.circle);
//                                right5.setImageResource(R.drawable.check);
//                                Toast.makeText(QuestionActivity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
//                            } else {
//                                right5.setVisibility(View.VISIBLE);
//                                r5.setBackgroundResource(R.drawable.cornerwrong);
//                                ans5.setButtonDrawable(R.drawable.circleworng);
//                                right5.setImageResource(R.drawable.wrong);
//                                Toast.makeText(QuestionActivity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
//                            }
                        }
                    });
                    if (questions.getObjSelectedId() != 0)
                    {
                        if (questions.getObjSelectedId() == Integer.parseInt(ans5.getTag().toString()))
                        {
                            ans5.setChecked(true);
                        }
                    }
                }
            }
        }

//        if (questions.getAnswerDescription() != null) {
//            websolution.loadDataWithBaseURL("", questions.getAnswerDescription(), "text/html", "UTF-8", "");
//
//        }
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

        cb.setChecked(false);
        txttotal.setText(position+1 + "/15");
        SetPrevNextButtonAction();
//        position = FindIndex(Integer.parseInt(questions.getQuestionId()));
          if (questions.getIsreview()!=0)
          {
              cb.setChecked(true);
          }
        SetExamLastQuestion(exam_id, Integer.parseInt( questions.getQuestionId()));
    }

    private void SetPrevNextButtonAction() {
        if (position > 0)
        {
            previous.setImageResource(R.drawable.previous_press_unpress);
            previous.setEnabled(true);
        }
        else
        {
            previous.setImageResource(R.drawable.previous_disable);
            previous.setEnabled(false);
        }

        if (position < 14)
        {
            next.setImageResource(R.drawable.next_press_unpress);
            next.setEnabled(true);
        }
        else
        {
            next.setImageResource(R.drawable.next_disable);
            next.setEnabled(false);
        }
    }

    private void UpdateUserAnswer()
    {
        String obj_id = null;
        if (ans1.isChecked())
        {
            obj_id = ans1.getTag().toString();
        }
        else if (ans2.isChecked())
        {
            obj_id = ans2.getTag().toString();
        }
        else if (ans3.isChecked())
        {
            obj_id = ans3.getTag().toString();
        }
        else if (ans4.isChecked())
        {
            obj_id = ans4.getTag().toString();
        }
        else if (ans5.isChecked())
        {
            obj_id = ans5.getTag().toString();
        }



//        if (obj_id != null)
//        {
//            int isforReview = cb.isChecked() ? 1 : 0;
//            TestAdapter1 mDbHelper = new TestAdapter1(this);
//            mDbHelper.createDatabase();
//            mDbHelper.open();
//
//            mDbHelper.UpdateUserAns(exam_id, questions.getQuestionId(), obj_id, isforReview);
//
//            mDbHelper.close();
//
//
//        }

        int isforReview = cb.isChecked() ? 1 : 0;
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        mDbHelper.UpdateUserAns(exam_id, questions.getQuestionId(), obj_id, isforReview);

        mDbHelper.close();


    }

    @Override
    protected void onPause() {
        super.onPause();

        SetGameState("PAUSE");
        SubmitGame();


        if(countDownTimer!=null){
            countDownTimer.cancel();
            isPause = true;
        }
        if(countDownTimeronResume!=null){
            countDownTimeronResume.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SetGameState("RUNNING");
        if(isPause == false) {
            countDownTimer = new CountDownTimer(TIMEOUT, INTERVAL) {
                @Override
                public void onTick(long millisUntilFinished) {
                    millisecondsleft = millisUntilFinished;

                    int percentage = (int) ((millisecondsleft * 100)/TIMEOUT);
                    progress_bar_item_image.setProgress(percentage);

//                    TestActivity.this.txttime.setText(":" + String.format("%02d", millisUntilFinished / 1000));
                    TestActivity.this.txttime.setText(ResultActivity.GetTimeTaken(millisUntilFinished));
                    if (millisUntilFinished / 60000 < 2) {
                        TestActivity.this.txttime.setTextColor(SupportMenu.CATEGORY_MASK);
                    }
                }

                @Override
                public void onFinish() {
//                    TestActivity.this.txttime.setText("DONE!");
                    UpdateUserAnswer();
                    SubmitGame();

                    SetGameState("FINISHID");
                    countDownTimer.cancel();
//                    TestActivity.this.timer.cancel();

                    Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                    intent.putExtra("exam_id", exam_id);
                    intent.putExtra("sub_title_id", sub_title_id);
                    intent.putExtra("sub_title_name", questions.getSubTitleName());
                    intent.putExtra("time", TIMEOUT);

                    startActivity(intent);
//            TestActivity.this.startActivity(new Intent(TestActivity.this.getApplicationContext(), ResultActivity.class));
                    TestActivity.this.finish();
                }
            }.start();
        } else{
            countDownTimeronResume = new CountDownTimer(millisecondsleft, INTERVAL) {
                @Override
                public void onTick(long millisUntilFinished) {
                    millisecondsleft = millisUntilFinished;
                    int percentage = (int) ((millisecondsleft * 100)/TIMEOUT);
                    progress_bar_item_image.setProgress(percentage);

                    if (millisUntilFinished / 60000 < 2) {
                        TestActivity.this.txttime.setTextColor(SupportMenu.CATEGORY_MASK);
                    }
//                    TestActivity.this.txttime.setText(":" + String.format("%02d", millisUntilFinished / 1000));
                    TestActivity.this.txttime.setText(ResultActivity.GetTimeTaken(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    UpdateUserAnswer();
                    SubmitGame();
                    countDownTimer.cancel();
                    Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                    intent.putExtra("exam_id", exam_id);
                    intent.putExtra("sub_title_id", sub_title_id);
                    intent.putExtra("sub_title_name", questions.getSubTitleName());
                    intent.putExtra("time", TIMEOUT);
                    startActivity(intent);
                    TestActivity.this.finish();

                }
            }.start();
        }
    }

    private void SubmitGame() {
        long time_taken = TIMEOUT-millisecondsleft;
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        mDbHelper.Exam_Finished(exam_id, time_taken);

        mDbHelper.close();


    }

    @SuppressLint("WrongConstant")
    private void shareImage(File file, String title, String subject, String body) {
        Uri contentUri = FileProvider.getUriForFile(this, "com.example.apttitude_crack.fileprovider", new File(new File(getCacheDir(), "images"), "screenshotdemo.jpg"));
//        Uri contentUri = FileProvider.getUriForFile(this, "com.tryoninfosoft.aptitude_crack.fileprovider",file);
        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction("android.intent.action.SEND");
            shareIntent.addFlags(1);
            shareIntent.setDataAndType(contentUri, getContentResolver().getType(contentUri));
//            shareIntent.putExtra("android.intent.extra.SUBJECT", subject + "\n"  + body);
//            shareIntent.putExtra("android.intent.extra.TEXT", subject + "\n"  + body);
            shareIntent.putExtra("android.intent.extra.STREAM", contentUri);
            startActivity(Intent.createChooser(shareIntent, "Choose an app"));
        }
    }

    public void saveBitmap(Bitmap bitmap) {
        File cachePath = new File(getCacheDir(), "images");
        cachePath.mkdirs();
        File imagePath = new File(cachePath + "/screenshotdemo.jpg");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(getApplicationContext(),imagePath.getAbsolutePath()+"",Toast.LENGTH_SHORT).show();
//            boolean_save = true;

//            btn_screenshot.setText("Check image");

            Log.e("ImageSave", "Saveimage");
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void SetGameState(String state)
    {
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.SetGameState(exam_id, state);
        mDbHelper.close();
    }

    private void SetExamLastQuestion(int exam_id, int question_id)
    {
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.SetExamLastQuestion(exam_id, question_id);
        mDbHelper.close();
    }

    private void getExamDetails(int exam_id)
    {
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor cursor = mDbHelper.getExamDetails(exam_id);
        if (cursor.moveToFirst())
        {
            last_question_id = cursor.getInt(6);
            millisecondsleft = TIMEOUT - cursor.getInt(4);
        }
        mDbHelper.close();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
        builder.setMessage("Sure You Finished Exam  ?");
        builder.setTitle("Exit Dialog");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SetGameState("CANCLED");
                finish();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });
        builder.show();

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == R.id.opationaweb && motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            ans1.performClick();
        }
        if (view.getId() == R.id.opationbweb && motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            ans2.performClick();
        }
        if (view.getId() == R.id.opationcweb && motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            ans3.performClick();
        }
        if (view.getId() == R.id.opationdweb && motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            ans4.performClick();
        }
        if (view.getId() == R.id.opationeweb && motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            ans5.performClick();
        }
        return false;
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
