package com.example.apttitude_crack;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.apttitude_crack.Api.apiClient;
import com.example.apttitude_crack.Api.apiRest;
import com.example.apttitude_crack.GetterSetter.SubTitleData;
import com.example.apttitude_crack.GetterSetter.SubTitleResponse;
import com.example.apttitude_crack.GetterSetter.SyncAnswerData;
import com.example.apttitude_crack.GetterSetter.SyncDescriptionData;
import com.example.apttitude_crack.GetterSetter.SyncDiscussionData;
import com.example.apttitude_crack.GetterSetter.SyncOptionData;
import com.example.apttitude_crack.GetterSetter.SyncQuestionData;
import com.example.apttitude_crack.GetterSetter.SyncQuestionResponse;
import com.example.apttitude_crack.GetterSetter.TitleData;
import com.example.apttitude_crack.GetterSetter.TitleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SyncSystemActivity extends AppCompatActivity {

    List<TitleData> titleData;
    List<SubTitleData> subTitleData;

    List<SyncQuestionData> questionData;

    List<Integer> allSubtitleIds;
    Context mContext;
    ProgressDialog p;
    private String title_id;
    private String sub_title_id,title_name,titles;
    private int last_question_id;

    private  boolean splash_flag = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_system);
        mContext = SyncSystemActivity.this;

        allSubtitleIds = new ArrayList<>();

        Intent intent = getIntent();


            if (intent.hasExtra("SplashSync"))
            {
                Log.d("ABCDEFG", "****>> SyncSystemActivity::  SplashSync ::   ::" + intent.getBooleanExtra("SplashSync", false));
                ArrayList<Integer> sub_title_ids = (ArrayList<Integer>) getIntent().getSerializableExtra("sub_title_ids");
                Log.d("ABCDEFG", "****>> SyncSystemActivity:: sub_title_ids.size() :: "+ sub_title_ids.size());
                if (sub_title_ids.size() > 0)
                {
                    allSubtitleIds.addAll(sub_title_ids);
                }
            }
            else {

                title_id = intent.getStringExtra("title_id");
                sub_title_id = intent.getStringExtra("sub_title_id");
                title_name = intent.getStringExtra("title_name");
                titles = intent.getStringExtra("titles");
                last_question_id = intent.getIntExtra("last_question_id", -1);
            }

            if (allSubtitleIds.size() ==0)
            {
                if (last_question_id > 0)
                {
                    p = new ProgressDialog(SyncSystemActivity.this);
                    p.setMessage("Please wait...More Questions Are Downloading");
                    p.setIndeterminate(false);
                    p.setCancelable(false);
                    p.show();
                    getQuestionForSync(Integer.parseInt(sub_title_id), last_question_id, 25);
                }

            }
            else
            {
                Log.d("ABCDEFG", "****>> SyncSystemActivity:: STARTING PROGRESS BAR :: ");
                p = new ProgressDialog(SyncSystemActivity.this);
                p.setMessage("Please wait...More Questions Are Downloading");
                p.setIndeterminate(false);
                p.setCancelable(false);
                p.show();
                splash_flag = true;
                getQuestionForSync(allSubtitleIds.get(0), 0, 25);
            }
    }


    public void getAllTitle() {
        Retrofit retrofit = apiClient.getNewClient(mContext);
        apiRest service = retrofit.create(apiRest.class);
        Call<TitleResponse> call = service.getAllAptitudeTitle();
        call.enqueue(new Callback<TitleResponse>() {
            @Override
            public void onResponse(Call<TitleResponse> call, Response<TitleResponse> response) {
                Log.d("My :: ", "***************************1");
                if (response.body() != null) {
                    Log.d("My :: ", "***************************2");
                    if (response.body().getSuccess().equals(1))
                    {
                        Log.d("My :: ", "***************************");
                        titleData = new ArrayList<>();
                        titleData = response.body().getTitle();
                        SyncTitlesToDb();


                    }

                } else {
                    Log.d("My ::  :: ", "*** RESPONSE IS BLANK") ;
                }
            }

            @Override
            public void onFailure(Call<TitleResponse> call, Throwable t) {
                Log.d("ERROR :: ", " ******* " + t.getMessage());
            }
        });
    }

//    private void getSubAptitudeTitle() {
//        Retrofit retrofit = apiClient.getNewClient(mContext);
//        apiRest service = retrofit.create(apiRest.class);
//        Call<SubTitleResponse> call = service.getSubTitleWithPostMethod();
//        call.enqueue(new Callback<SubTitleResponse>() {
//            @Override
//            public void onResponse(Call<SubTitleResponse> call, Response<SubTitleResponse> response) {
//                Log.d("My :: ", "***************************1");
//                if (response.body() != null) {
//                    Log.d("My :: ", "***************************2");
//                    if (response.body().getSuccess().equals(1))
//                    {
//                        Log.d("My :: ", "***************************"+ response.body().getSubTitle());
//                        subTitleData = new ArrayList<>();
//                        subTitleData = response.body().getSubTitle();
//                        SyncSubTitlesToDb();
//                    }
//
//                } else {
//                    Log.d("My ::  :: ", "*** RESPONSE IS BLANK") ;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SubTitleResponse> call, Throwable t) {
//                Log.d("ERROR :: ", " ******* " + t.getMessage());
//            }
//        });
//    }

    public void SyncTitlesToDb()
    {
        if (titleData.size() > 0)
        {
            for (int i=0; i<titleData.size(); i++)
            {
                TestAdapter1 mDbHelper = new TestAdapter1(mContext);
                mDbHelper.createDatabase();
                mDbHelper.open();

                mDbHelper.SaveTitleData(titleData.get(i).getTitleId(), titleData.get(i).getTitlename(), titleData.get(i).getTitleimg());

                mDbHelper.close();
            }
        }
    }

    public void SyncSubTitlesToDb()
    {
        if (subTitleData.size() > 0)
        {
            for (int i=0; i<subTitleData.size(); i++)
            {
                TestAdapter1 mDbHelper = new TestAdapter1(mContext);
                mDbHelper.createDatabase();
                mDbHelper.open();

                mDbHelper.SaveSubTitleData(subTitleData.get(i).getSubTitleId(), subTitleData.get(i).getSubTitleName(), subTitleData.get(i).getSubtitleimg(), subTitleData.get(i).getTitleId());

                mDbHelper.close();
            }
        }
    }

    private void GetAllSubTitles()
    {
        allSubtitleIds = new ArrayList<>();
        TestAdapter1 mDbHelper = new TestAdapter1(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();
        Cursor mCur = mDbHelper.getAllSubTitleIds();
        if (mCur.moveToFirst()) {
            do {
                try {
                    if (mCur.getInt(0) > 102)
                    {
                        allSubtitleIds.add(mCur.getInt(0));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } while (mCur.moveToNext());

        }
        mDbHelper.close();
        if (allSubtitleIds.size() > 0)
        {
            getQuestionForSync(allSubtitleIds.get(0), 0, 25);
        }
    }


    public void getQuestionForSync(int sub_title_id, int start, int end) {
        Log.d("SYNCINGDATA :: ", "**************GETTING QUESTION FOR SUBTITLE*************" + sub_title_id);
        Retrofit retrofit = apiClient.getNewClient(mContext);
        apiRest service = retrofit.create(apiRest.class);
        Call<SyncQuestionResponse> call = service.getQuestionsForSync(sub_title_id, start, end);
        call.enqueue(new Callback<SyncQuestionResponse>() {
            @Override
            public void onResponse(Call<SyncQuestionResponse> call, Response<SyncQuestionResponse> response) {
//                Log.d("My :: ", "***************************1");
                if (response.body() != null) {
//                    Log.d("My :: ", "***************************2");
                    if (response.body().getSuccess().equals(1))
                    {
//                        Log.d("My :: ", "***************************"+ response.body().getSubTitle());
                        questionData = new ArrayList<>();
                        questionData = response.body().getQuestions();
                        SyncQuestion();
                    }
                    if (response.body().getSuccess().equals(0))
                    {
                        if (!splash_flag)
                        {   p.hide();
                            p.cancel();
//                        Log.d("My :: ", "***************************"+ response.body().getSubTitle());

                            Toast.makeText(SyncSystemActivity.this, "No More Question Found!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SyncSystemActivity.this, A_Question_Activity.class);
                            intent.putExtra("title_id", title_id);
                            intent.putExtra("sub_title_id", SyncSystemActivity.this.sub_title_id);
                            intent.putExtra("title_name", title_name);
                            intent.putExtra("titles", titles);
                            intent.putExtra("no_sync", false);
                            startActivity(intent);
                            finish();

                        }


                    }
//                    else
//                    {
//                        doNext();
//                    }

                } else {
//                    Log.d("My ::  :: ", "*** RESPONSE IS BLANK") ;
                }
            }

            @Override
            public void onFailure(Call<SyncQuestionResponse> call, Throwable t) {
                Log.d("ERROR :: ", " ******* " + t.getMessage());
                p.hide();
                p.cancel();
                finish();
            }
        });
    }

    public void doNext()
    {
        allSubtitleIds.remove(0);
        if (allSubtitleIds.size()> 0)
        {
            getQuestionForSync(allSubtitleIds.get(0), 0, 25);
        }
        else {
            p.hide();
            p.cancel();
            Intent intent = new Intent(SyncSystemActivity.this,splash.class);
            startActivity(intent);
            finish();
        }
    }

    public void SyncQuestion()
    {
        if (questionData.size() > 0)
        {
            for (int i=0; i < questionData.size(); i++)
            {
                TestAdapter1 mDbHelper = new TestAdapter1(mContext);
                mDbHelper.createDatabase();
                mDbHelper.open();

                mDbHelper.SaveQuestionData(questionData.get(i).getQuestionId(), questionData.get(i).getQuestion(), questionData.get(i).getSubTitleId(), questionData.get(i).getDiscussionId());
                mDbHelper.close();

               SyncOption(questionData.get(i).getOptions());
                SyncDiscussion(questionData.get(i).getDiscussion());
                SyncDescription(questionData.get(i).getDescription());
                SyncAnswer(questionData.get(i).getAnswer());

            }
        }
       if (splash_flag)
       {
        doNext();
       }



    }

    public void SyncOption(List<SyncOptionData> opdata)
    {
        if (opdata.size() > 0)
        {
            for (int i=0; i<opdata.size(); i++)
            {
                TestAdapter1 mDbHelper = new TestAdapter1(mContext);
                mDbHelper.createDatabase();
                mDbHelper.open();
                mDbHelper.SaveOptionData(opdata.get(i).getObjid(), opdata.get(i).getOptions(), opdata.get(i).getQuestionId());
                mDbHelper.close();
            }
        }
    }

    public void SyncDiscussion(List<SyncDiscussionData> disdata)
    {
        if (disdata.size() > 0)
        {
            for (int i=0; i<disdata.size(); i++)
            {
                TestAdapter1 mDbHelper = new TestAdapter1(mContext);
                mDbHelper.createDatabase();
                mDbHelper.open();

                mDbHelper.SaveDiscussionData(disdata.get(i).getDiscussionId(), disdata.get(i).getTitleId(), disdata.get(i).getSubTitleId(), disdata.get(i).getDiscussionTitle(), disdata.get(i).getExamValid());

                mDbHelper.close();
            }
        }
    }

    public void SyncDescription(List<SyncDescriptionData> descdata)
    {
        if (descdata.size() > 0)
        {
            for (int i=0; i<descdata.size(); i++)
            {
                TestAdapter1 mDbHelper = new TestAdapter1(mContext);
                mDbHelper.createDatabase();
                mDbHelper.open();
                mDbHelper.SaveDescriptionData(descdata.get(i).getDescriptionId(), descdata.get(i).getDescriptionDetail(), descdata.get(i).getDiscussionId());
                mDbHelper.close();
            }
        }
    }

    public void SyncAnswer(List<SyncAnswerData> ansdata)
    {
        if (ansdata.size() > 0)
        {
            for (int i=0; i<ansdata.size(); i++)
            {
                TestAdapter1 mDbHelper = new TestAdapter1(mContext);
                mDbHelper.createDatabase();
                mDbHelper.open();

                mDbHelper.SaveAnswerData(ansdata.get(i).getAnswerid(), ansdata.get(i).getObjid(), ansdata.get(i).getAnswerDescription(), ansdata.get(i).getQuestionId());

                mDbHelper.close();
            }

            if(!splash_flag) {
                Intent intent = new Intent(SyncSystemActivity.this, A_Question_Activity.class);
                intent.putExtra("title_id", title_id);
                intent.putExtra("sub_title_id", sub_title_id);
                intent.putExtra("title_name", title_name);
                startActivity(intent);
            }

        }
    }


}
