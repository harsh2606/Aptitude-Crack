package com.example.apttitude_crack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apttitude_crack.Api.PrefManager;
import com.example.apttitude_crack.Api.apiClient;
import com.example.apttitude_crack.Api.apiRest;
import com.example.apttitude_crack.GetterSetter.Option;
import com.example.apttitude_crack.GetterSetter.Question;
import com.example.apttitude_crack.GetterSetter.QuestionBySubTitleResponse;
import com.example.apttitude_crack.GetterSetter.UserLoginResponse;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class A_Question_Activity extends AppCompatActivity implements View.OnTouchListener {

    RelativeLayout r1, r2, r3, r4, r5,snap;
    ImageView right1, right2, right3, right4, right5,sharesnap,backimg,report,solution, next, finish, previous;

    WebView descussion_que, question, wbsol,opationaweb,opationbweb,opationcweb,opationdweb,opationeweb;
    RadioButton ans1, ans2, ans3, ans4, ans5;

    Animation blink;
    RadioGroup rdg;
    TextView q_id, s,sub,r;
    private String title_id;
    Question questions;
    int count = 1;
    private Object subAptitudeTitle;
    int question_counter = 0;
    String question_id = null;
    PrefManager pref;
    private String title_name,titles;
    private String sub_title_id;
    private int position = 0;
    private Boolean no_sync;
    MediaPlayer mp,wrong,camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__question_);

        blink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        mp = MediaPlayer.create(this, R.raw.click);
        wrong = MediaPlayer.create(this, R.raw.drag_right);
        camera = MediaPlayer.create(this, R.raw.camera_1);

        pref = new PrefManager(A_Question_Activity.this);
        final Intent intent = getIntent();
        title_id = intent.getStringExtra("title_id");
        title_name = intent.getStringExtra("title_name");
        sub_title_id = intent.getStringExtra("sub_title_id");
        titles = intent.getStringExtra("titles");
        no_sync = intent.getBooleanExtra("no_sync",true);

        question_id = pref.getString("SUB_TITLE_" + sub_title_id);
        Log.w("my", "title_id:- " + title_id);
        Log.w("my", "sub_title_id:- " + sub_title_id);
        Log.w("my", "question_id:- " + question_id);

//        Intent intent = getIntent();
//        title_id = intent.getStringExtra("title_id");
//        sub_title_id = intent.getStringExtra("sub_title_id");
//        questions = new ArrayList<>();


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
        Typeface custom_font = Typeface.createFromAsset(getAssets(),"GothamMedium.ttf");
        ans1.setTypeface(custom_font);
        ans2 = (RadioButton) findViewById(R.id.ans2);
        ans2.setTypeface(custom_font);
        ans3 = (RadioButton) findViewById(R.id.ans3);
        ans3.setTypeface(custom_font);
        ans4 = (RadioButton) findViewById(R.id.ans4);
        ans4.setTypeface(custom_font);
        ans5 = (RadioButton) findViewById(R.id.ans5);
        ans5.setTypeface(custom_font);

        q_id = (TextView) findViewById(R.id.q_id);
        r = (TextView) findViewById(R.id.r);
        r.setText(titles);
        s = (TextView) findViewById(R.id.s);
        s.setTypeface(custom_font);
        sub = (TextView) findViewById(R.id.sub);
        sub.setText(title_name);

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
        report = (ImageView) findViewById(R.id.report);
        backimg = (ImageView) findViewById(R.id.backimg);
        solution = (ImageView) findViewById(R.id.solution);
        next = (ImageView) findViewById(R.id.next);
        finish = (ImageView) findViewById(R.id.finish);
        previous = (ImageView) findViewById(R.id.previous);






        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
//                finish();
                AlertDialog.Builder builder = new AlertDialog.Builder(A_Question_Activity.this);
                builder.setMessage("Do You want To Sure Exit??");
                builder.setTitle("Exit");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(A_Question_Activity.this ,Arithmetic_Aptitude_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("title_id",title_id);
                        intent.putExtra("title_name",title_name);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
                builder.show();
            }
        });
        sharesnap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                camera.start();
                Bitmap bitmap1 = loadBitmapFromView(snap, snap.getWidth(), snap.getHeight());
                saveBitmap(bitmap1);
                String completePath = Environment.getExternalStorageDirectory() + "/" + "screenshotdemo.jpg";
                File file = new File(completePath);
                A_Question_Activity.this.shareImage(file, "Share Challenge on", "", "");
            }
        });

//        getSubAptitudeTitle();
//        loadQuestionfromDB();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                AlertDialog.Builder builder = new AlertDialog.Builder(A_Question_Activity.this);
                builder.setMessage("Do You want To sure Exit??");
                builder.setTitle("Exit");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(A_Question_Activity.this ,Arithmetic_Aptitude_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("title_id",title_id);
                        intent.putExtra("title_name",title_name);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
                 builder.show();
            }
        });




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                position = position +1;
                question_id = pref.getString("SUB_TITLE_" + sub_title_id);
                if (CheckDoWeNeedNewQuestions()) {
                    SyncNewQuestionFromServer(sub_title_id);
                } else {
                    INVISIBALE();
                    loadQuestionfromDB("next");
                }

//                question_counter = question_counter + 1;
//                Log.w("my", "question_counter:-" + question_counter);
//
//                if (questions.size() > question_counter) {
//                    INVISIBLE();
//                    setQuestions(question_counter);
//                } else {
//                    next.setEnabled(false);
//                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                position = position -1;
                question_id = pref.getString("SUB_TITLE_" + sub_title_id);
                INVISIBALE();
                loadQuestionfromDB("prev");

//                question_counter = question_counter - 1;
//                Log.w("my", "question_counter:-" + question_counter);
//
//                if (questions.size() > question_counter) {
//
//                    INVISIBLE();
//                    setQuestions(question_counter);
//                } else {
//                    previous.setEnabled(false);
//                }
            }
        });

        if (CheckDoWeNeedNewQuestions()) {
            loadQuestionfromDB("");
            if (no_sync)
            {
                SyncNewQuestionFromServer(sub_title_id);
            }
        } else {
            loadQuestionfromDB("");

        }

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ReportQuestion();

//                if(isNetworkConnected()){
//                    Toast.makeText(A_Question_Activity.this, pref.getString("SUB_TITLE_" + sub_title_id)+"Thank you for Report.", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(A_Question_Activity.this, "please connect to internet", Toast.LENGTH_SHORT).show();
//                }

//                Intent intent1=new Intent(A_Question_Activity.this,ReportActivity.class);
//                startActivity(intent1);
            }
        });
    }

    private void INVISIBALE() {
        r1.setVisibility(View.GONE);
        r2.setVisibility(View.GONE);
        r3.setVisibility(View.GONE);
        r4.setVisibility(View.GONE);
        r5.setVisibility(View.GONE);
        r1.setBackgroundResource(R.drawable.corners);
        r2.setBackgroundResource(R.drawable.corners);
        r4.setBackgroundResource(R.drawable.corners);
        r5.setBackgroundResource(R.drawable.corners);
        r3.setBackgroundResource(R.drawable.corners);

        right1.setVisibility(View.INVISIBLE);
        right2.setVisibility(View.INVISIBLE);
        right3.setVisibility(View.INVISIBLE);
        right4.setVisibility(View.INVISIBLE);
        right5.setVisibility(View.INVISIBLE);
    }

    private void Uncheck(RadioButton finished) {
        if (finished.getId() == ans1.getId()) {
            ans2.setChecked(false);
            ans3.setChecked(false);
            ans4.setChecked(false);
            ans5.setChecked(false);
            r2.setBackgroundResource(R.drawable.corners);
            r3.setBackgroundResource(R.drawable.corners);
            r4.setBackgroundResource(R.drawable.corners);
            r5.setBackgroundResource(R.drawable.corners);

            right2.setVisibility(View.INVISIBLE);
            right3.setVisibility(View.INVISIBLE);
            right4.setVisibility(View.INVISIBLE);
            right5.setVisibility(View.INVISIBLE);


        } else if (finished.getId() == ans2.getId()) {
            ans1.setChecked(false);
            ans3.setChecked(false);
            ans4.setChecked(false);
            ans5.setChecked(false);
            r1.setBackgroundResource(R.drawable.corners);
            r3.setBackgroundResource(R.drawable.corners);
            r4.setBackgroundResource(R.drawable.corners);
            r5.setBackgroundResource(R.drawable.corners);

            right1.setVisibility(View.INVISIBLE);
            right3.setVisibility(View.INVISIBLE);
            right4.setVisibility(View.INVISIBLE);
            right5.setVisibility(View.INVISIBLE);


        } else if (finished.getId() == ans3.getId()) {
            ans2.setChecked(false);
            ans1.setChecked(false);
            ans4.setChecked(false);
            ans5.setChecked(false);
            r1.setBackgroundResource(R.drawable.corners);
            r2.setBackgroundResource(R.drawable.corners);
            r4.setBackgroundResource(R.drawable.corners);
            r5.setBackgroundResource(R.drawable.corners);

            right1.setVisibility(View.INVISIBLE);
            right2.setVisibility(View.INVISIBLE);
            right4.setVisibility(View.INVISIBLE);
            right5.setVisibility(View.INVISIBLE);

        } else if (finished.getId() == ans4.getId()) {
            ans2.setChecked(false);
            ans3.setChecked(false);
            ans1.setChecked(false);
            ans5.setChecked(false);
            r1.setBackgroundResource(R.drawable.corners);
            r2.setBackgroundResource(R.drawable.corners);
            r3.setBackgroundResource(R.drawable.corners);
            r5.setBackgroundResource(R.drawable.corners);

            right1.setVisibility(View.INVISIBLE);
            right2.setVisibility(View.INVISIBLE);
            right3.setVisibility(View.INVISIBLE);
            right5.setVisibility(View.INVISIBLE);

        } else if (finished.getId() == ans5.getId()) {
            ans2.setChecked(false);
            ans3.setChecked(false);
            ans4.setChecked(false);
            ans1.setChecked(false);
            r1.setBackgroundResource(R.drawable.corners);
            r2.setBackgroundResource(R.drawable.corners);
            r4.setBackgroundResource(R.drawable.corners);
            r3.setBackgroundResource(R.drawable.corners);

            right1.setVisibility(View.INVISIBLE);
            right2.setVisibility(View.INVISIBLE);
            right3.setVisibility(View.INVISIBLE);
            right4.setVisibility(View.INVISIBLE);
        }
    }

    private void setQuestions() {

        Log.d("my ===============", " ========= position :: ");
        if (questions.getDescriptionId() != null) {
            descussion_que.loadDataWithBaseURL("", questions.getDescriptionDetail().replace("localhost:80/aptitude_crack/admin/", "api.aptitudecrack.com/"), "text/html", "UTF-8", "");
            descussion_que.setVisibility(View.VISIBLE);
        }
        if (questions.getQuestion() != null) {
            question.loadDataWithBaseURL("","Question:- "+""+questions.getQrow()+""+ questions.getQuestion().replace("localhost:80/aptitude_crack/admin/", "api.aptitudecrack.com/"), "text/html", "UTF-8", "");
            question.setVisibility(View.VISIBLE);
        }

        if (questions.getOptions().size() > 0) {
            for (int i = 0; i < questions.getOptions().size(); i++) {
                if (i == 0) {

                    r1.setVisibility(View.VISIBLE);
                    ans1.setChecked(false);
                    opationaweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans1.setTag(questions.getOptions().get(i).getObjid());

//                                    optiona.setText("A");
                    //ans1.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans1.setVisibility(View.VISIBLE);
                    ans1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uncheck(ans1);

                            if (ans1.getTag().equals(questions.getObjid())) {
                                right1.setVisibility(View.VISIBLE);
                                r1.setBackgroundResource(R.drawable.cornersright);
                                right1.setImageResource(R.drawable.check);
                                ans1.setButtonDrawable(R.drawable.circle);
//                                Toast.makeText(A_Question_Activity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    if (wrong.isPlaying()) {
                                        wrong.stop();
                                        wrong.release();
                                        wrong = MediaPlayer.create(A_Question_Activity.this, R.raw.drag_right);
                                    } wrong.start();
                                } catch(Exception e) { e.printStackTrace(); }

                                right1.setVisibility(View.VISIBLE);
                                r1.setBackgroundResource(R.drawable.cornerwrong);
                                right1.setImageResource(R.drawable.wrong);
                                ans1.setButtonDrawable(R.drawable.circleworng);
                                r1.setAnimation(blink);

//                                Toast.makeText(A_Question_Activity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                if (i == 1) {

                    r2.setVisibility(View.VISIBLE);

                    ans2.setChecked(false);
                    opationbweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans2.setTag(questions.getOptions().get(i).getObjid());
//                                    ans2.setText(questions.get(0).getOptions().get(i).getOptions());
                    // ans2.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans2.setVisibility(View.VISIBLE);
                    ans2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uncheck(ans2);

                            if (ans2.getTag().equals(questions.getObjid())) {
                                right2.setVisibility(View.VISIBLE);
                                r2.setBackgroundResource(R.drawable.cornersright);
                                right2.setImageResource(R.drawable.check);
                                ans2.setButtonDrawable(R.drawable.circle);
//                                Toast.makeText(A_Question_Activity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    if (wrong.isPlaying()) {
                                        wrong.stop();
                                        wrong.release();
                                        wrong = MediaPlayer.create(A_Question_Activity.this, R.raw.drag_right);
                                    } wrong.start();
                                } catch(Exception e) { e.printStackTrace(); }
                                right2.setVisibility(View.VISIBLE);
                                r2.setBackgroundResource(R.drawable.cornerwrong);
                                right2.setImageResource(R.drawable.wrong);
                                ans2.setButtonDrawable(R.drawable.circleworng);
                                r2.setAnimation(blink);

//                                Toast.makeText(A_Question_Activity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                if (i == 2) {

                    r3.setVisibility(View.VISIBLE);
                    ans3.setChecked(false);
                    opationcweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans3.setTag(questions.getOptions().get(i).getObjid());
                    //ans3.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans3.setVisibility(View.VISIBLE);
                    ans3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uncheck(ans3);
                            if (ans3.getTag().equals(questions.getObjid())) {
                                right3.setVisibility(View.VISIBLE);
                                r3.setBackgroundResource(R.drawable.cornersright);
                                right3.setImageResource(R.drawable.check);
                                ans3.setButtonDrawable(R.drawable.circle);
//                                Toast.makeText(A_Question_Activity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    if (wrong.isPlaying()) {
                                        wrong.stop();
                                        wrong.release();
                                        wrong = MediaPlayer.create(A_Question_Activity.this, R.raw.drag_right);
                                    } wrong.start();
                                } catch(Exception e) { e.printStackTrace(); }
                                right3.setVisibility(View.VISIBLE);
                                r3.setBackgroundResource(R.drawable.cornerwrong);
                                right3.setImageResource(R.drawable.wrong);
                                ans3.setButtonDrawable(R.drawable.circleworng);
                                r3.setAnimation(blink);

//                                Toast.makeText(A_Question_Activity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                if (i == 3) {

                    r4.setVisibility(View.VISIBLE);
                    ans4.setChecked(false);
                    opationdweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans4.setTag(questions.getOptions().get(i).getObjid());
                    //ans4.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans4.setVisibility(View.VISIBLE);
                    ans4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uncheck(ans4);
                            if (ans4.getTag().equals(questions.getObjid())) {
                                right4.setVisibility(View.VISIBLE);
                                r4.setBackgroundResource(R.drawable.cornersright);
                                right4.setImageResource(R.drawable.check);
                                ans4.setButtonDrawable(R.drawable.circle);
//                                Toast.makeText(A_Question_Activity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    if (wrong.isPlaying()) {
                                        wrong.stop();
                                        wrong.release();
                                        wrong = MediaPlayer.create(A_Question_Activity.this, R.raw.drag_right);
                                    } wrong.start();
                                } catch(Exception e) { e.printStackTrace(); }
                                right4.setVisibility(View.VISIBLE);
                                r4.setBackgroundResource(R.drawable.cornerwrong);
                                right4.setImageResource(R.drawable.wrong);
                                ans4.setButtonDrawable(R.drawable.circleworng);
                                r4.setAnimation(blink);

//                                Toast.makeText(A_Question_Activity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                if (i == 4) {

                    r5.setVisibility(View.VISIBLE);
                    ans5.setChecked(false);
                    opationeweb.loadDataWithBaseURL("", RemoveHtmlUrl(questions.getOptions().get(i).getOptions()).replace("</a>", ""), "text/html", "UTF-8", "");
                    ans5.setTag(questions.getOptions().get(i).getObjid());
                    //  ans5.setText(Html.fromHtml(questions.get(0).getOptions().get(i).getOptions()));
                    ans5.setVisibility(View.VISIBLE);

                    ans5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uncheck(ans5);
                            if (ans5.getTag().equals(questions.getObjid())) {
                                right5.setVisibility(View.VISIBLE);
                                r5.setBackgroundResource(R.drawable.cornersright);
                                right5.setImageResource(R.drawable.check);
                                ans5.setButtonDrawable(R.drawable.circle);
//                                Toast.makeText(A_Question_Activity.this, "Correct ANS", Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    if (wrong.isPlaying()) {
                                        wrong.stop();
                                        wrong.release();
                                        wrong = MediaPlayer.create(A_Question_Activity.this, R.raw.drag_right);
                                    } wrong.start();
                                } catch(Exception e) { e.printStackTrace(); }
                                right5.setVisibility(View.VISIBLE);
                                r5.setBackgroundResource(R.drawable.cornerwrong);
                                right5.setImageResource(R.drawable.wrong);
                                ans5.setButtonDrawable(R.drawable.circleworng);
                                r5.setAnimation(blink);


//                                Toast.makeText(A_Question_Activity.this, "Wrong ANS", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }



        if (questions.getAnswerDescription() != null) {

            wbsol.loadDataWithBaseURL("", questions.getAnswerDescription(), "text/html", "UTF-8", "");

        }
        count = 1;
        wbsol.setVisibility(View.GONE);
        s.setVisibility(View.GONE);

        solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                if (count == 0) {
                    count = 1;
                    wbsol.setVisibility(View.GONE);
                    s.setVisibility(View.GONE);
                } else {
                    count = 0;
                    wbsol.setVisibility(View.VISIBLE);
                    s.setVisibility(View.VISIBLE);
                }

            }
        });

        pref.setString("SUB_TITLE_" + sub_title_id, questions.getQuestionId());
        SetQuestionIsVisited(questions.getQuestionId());
        SetPreviousButtonAction(questions.getQuestionId(), questions.getSubTitleId());

        question_id = questions.getQuestionId();

//        wrong.stop();
    }

//    private void getSubAptitudeTitle() {
//        Retrofit retrofit = apiClient.getNewClient(A_Question_Activity.this);
//        apiRest service = retrofit.create(apiRest.class);
//        Call<QuestionBySubTitleResponse> call = service.getQuestionBySubTitle(sub_title_id);
//        call.enqueue(new Callback<QuestionBySubTitleResponse>() {
//            @Override
//            public void onResponse(Call<QuestionBySubTitleResponse> call, Response<QuestionBySubTitleResponse> response) {
//                Log.d("my ::", "Wrong Data");
//                if (response.body() != null) {
//                    Log.d("my ::", "Wrong Data2");
//                    if (response.body().getSuccess().equals(1)) ;
//                    {
//
//                        questions = response.body().getQuestion();
//                        if (question != null) {
//                            setQuestions(0);
//                        }
//
//                    }
//
//                } else {
//                    Log.d("my ::", "Response Blank");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<QuestionBySubTitleResponse> call, Throwable t) {
//                Log.d("Error ::", "**********" + t.getMessage());
//            }
//        });
//    }

    private void loadQuestionfromDB(String action) {
        TestAdapter1 mDbHelper = new TestAdapter1(A_Question_Activity.this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor mCur;
        if (action.equals("next")) {
            if (question_id == null) {
                mCur = mDbHelper.getNextQuestion(Integer.parseInt(sub_title_id));
            } else {
                mCur = mDbHelper.getNextQuestionByID(sub_title_id, question_id);
            }
        } else if (action.equals("prev")) {
            if (question_id == null) {
                mCur = mDbHelper.getNextQuestion(Integer.parseInt(sub_title_id));
            } else {
                mCur = mDbHelper.getPreviousQuestionByID(sub_title_id, question_id);
            }
        } else {
            if (question_id == null) {
                mCur = mDbHelper.getNextQuestion(Integer.parseInt(sub_title_id));
            } else {
                mCur = mDbHelper.getQuestionByID(sub_title_id, question_id);
            }
        }


//        Cursor mCur = mDbHelper.getNextQuestion(Integer.parseInt(sub_title_id));

    questions = new Question();

        if(mCur.moveToFirst())

    {
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
            questions.setQrow(mCur.getString(13));
//                 que.setDiscussion(cursor.getString(4));

        } while (mCur.moveToNext());
    }

         mDbHelper.close();

    GetOptionForQuestion();

}


    private void GetOptionForQuestion() {
        if (questions.getQuestionId() != null) {
            TestAdapter1 mDbHelper = new TestAdapter1(A_Question_Activity.this);
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

    public boolean CheckDoWeNeedNewQuestions() {
        boolean flag = false;
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        if (question_id == null) {
            flag = mDbHelper.CheckDoWeNeedSync(sub_title_id);
        } else {
            flag = mDbHelper.CheckDoWeNeedSync(sub_title_id, question_id);
        }


        mDbHelper.close();
        Log.d("my ::: FLAG", "IS DATA :: " + flag);
        return flag;
    }

    private void SyncNewQuestionFromServer(String sub_title_id) {
        if (sub_title_id != null) {
            Log.d("my ::: FLAG", "SyncNewQuestionFromServer :: " + sub_title_id);
            int last_question_id = GetLastQuestionIdBySubTitle(sub_title_id);
            Log.d("my ::: FLAG", "SyncNewQuestionFromServer :: last_question_id :: " + last_question_id);
            if (last_question_id > 0) {
                Log.d("my ::: FLAG", "SyncNewQuestionFromServer :: last_question_id :: IN ::: " + last_question_id);
                Intent intent = new Intent(A_Question_Activity.this, SyncSystemActivity.class);
                intent.putExtra("title_id", title_id);
                intent.putExtra("sub_title_id", sub_title_id);
                intent.putExtra("last_question_id", last_question_id);
                intent.putExtra("title_name", title_name);
                intent.putExtra("titles", titles);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//                getQuestionForSync(Integer.parseInt(sub_title_id), last_question_id, 25);
            }


        }
    }

    public int GetLastQuestionIdBySubTitle(String sub_title_id) {
        int question_id = -1;
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        question_id = mDbHelper.GetLastQuestionIdBySubTitle(sub_title_id);

        mDbHelper.close();
        return question_id;
    }

    private void SetQuestionIsVisited(String question_id) {
        TestAdapter1 mDbHelper = new TestAdapter1(A_Question_Activity.this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.SetQuestionIsVisitedTrue(question_id);
        mDbHelper.close();
    }

    private void SetPreviousButtonAction(String question_id, String sub_title_id) {
        TestAdapter1 mDbHelper = new TestAdapter1(A_Question_Activity.this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        boolean isPrev = mDbHelper.checkAnyPreviousQuestion(sub_title_id, question_id);
        if (isPrev) {
            previous.setImageResource(R.drawable.previous_press_unpress);
            previous.setEnabled(true);
        } else {
            previous.setImageResource(R.drawable.previous_disable);
            previous.setEnabled(false);
        }
        mDbHelper.close();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(A_Question_Activity.this ,Arithmetic_Aptitude_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("title_id",title_id);
        intent.putExtra("title_name",title_name);

        startActivity(intent);
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

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
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

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    public void ReportQuestion()
    {
        String user_id="";
        if(pref.getString("aptc_user_id")!=null)
        {
            user_id = pref.getString("aptc_user_id");
        }
        Retrofit retrofit = apiClient.getNewClient(A_Question_Activity.this);
        apiRest service = retrofit.create(apiRest.class);
        Log.w("harsh","user_id:-"+user_id);
        Log.w("harsh","question_id:-"+question_id);
        Call<UserLoginResponse> call = service.report_question(user_id,Integer.parseInt(question_id));

        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {

                if (response.body() != null) {

                    if (response.body().getSuccess().equals(1)) ;
                    {
                        Toast.makeText(A_Question_Activity.this,"Thank You For Report",Toast.LENGTH_SHORT).show();
                    }
                    if (response.body().getSuccess().equals(200))
                    {
                        Toast.makeText(A_Question_Activity.this,"Something Want Wrong",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d("my ::", "Response Blank");
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Log.d("Error ::", "**********" + t.getMessage());
            }
        });
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
}

