package com.example.apttitude_crack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.CountDownTimer;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.internal.view.SupportMenu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cunoraz.gifview.library.GifView;
import com.cunoraz.gifview.library.GifView;
import com.cunoraz.gifview.library.GifView;
import com.example.apttitude_crack.Api.apiClient;
import com.example.apttitude_crack.Api.apiRest;
import com.example.apttitude_crack.GetterSetter.SubTitleData;
import com.example.apttitude_crack.GetterSetter.SubTitleResponse;
import com.example.apttitude_crack.GetterSetter.TitleData;
import com.example.apttitude_crack.GetterSetter.TitleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.apttitude_crack.TestActivity.millisecondsleft;

public class login extends AppCompatActivity {
    List<TitleData> titleData;
    List<TitleData> titleDataServer;
    List<SubTitleData> subTitleData;
    ArrayList<Integer> subTitleDataServer;
    TextView per;
    ProgressBar progress_bar_item_image;
    private CountDownTimer countDownTimer;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        per = (TextView) findViewById(R.id.per);
        progress_bar_item_image = (ProgressBar) findViewById(R.id.progress_bar_item_image);


        titleData = new ArrayList<>();

        if (isNetworkConnected()) {
            progress_bar_item_image.setProgress(20);
            per.setText("20%");
            loadTitlefromDB();
        } else {
            countDownTimer = new CountDownTimer(5000, 100) {
                @Override
                public void onTick(long millisUntilFinished) {


                    int percentage = (int) (((5000 - millisUntilFinished) * 100) / 5000);
                    progress_bar_item_image.setProgress(percentage);

//                    TestActivity.this.txttime.setText(":" + String.format("%02d", millisUntilFinished / 1000));
                    per.setText(percentage + "%");

                }

                @Override
                public void onFinish() {
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                    }

                    progress_bar_item_image.setProgress(100);
                    per.setText("100%");
                    Intent intent = new Intent(login.this, splash.class);
                    startActivity(intent);
                    finish();


                }
            }.start();
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    public void getAllTitle() {
        String var = "";
        if (titleData.size() > 0) {
            for (int i = 0; i < titleData.size(); i++) {
                var = var + titleData.get(i).getTitleId() + ",";
            }
            var = var.substring(0, var.length() - 1);
        }

        Retrofit retrofit = apiClient.getNewClient(login.this);
        apiRest service = retrofit.create(apiRest.class);
        Call<TitleResponse> call = service.getExtraTitle(var);
        call.enqueue(new Callback<TitleResponse>() {
            @Override
            public void onResponse(Call<TitleResponse> call, Response<TitleResponse> response) {
                Log.d("My :: ", "***************************1");
                if (response.body() != null) {
                    Log.d("My :: ", "***************************2");
                    if (response.body().getSuccess().equals(1)) {
                        Log.d("My :: ", "***************************");
                        titleDataServer = new ArrayList<>();
                        titleDataServer = response.body().getTitle();
                        check = true;
                        AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                        builder.setMessage("Do You want To Download Aptitude Crack New Category??");
                        builder.setTitle("Download Dialog");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SyncTitlesToDb();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(login.this, splash.class);
                                startActivity(intent);
                                finish();
                            }

                        });
                        builder.show();
                    } else {
                        loadSubTitlefromDB();
                    }
                } else {
                    loadSubTitlefromDB();
                }
            }

            @Override
            public void onFailure(Call<TitleResponse> call, Throwable t) {
                Intent intent = new Intent(login.this, splash.class);
                startActivity(intent);
                finish();
                Log.d("ERROR :: ", " ******* " + t.getMessage());
            }
        });
    }


    private void getSubAptitudeTitle() {
        String subvar = "";
        if (subTitleData.size() > 0) {

            for (int i = 0; i < subTitleData.size(); i++) {
                subvar = subvar + subTitleData.get(i).getSubTitleId() + ",";
                Log.w("harsh", "Sub Title id" + subTitleData.get(i).getSubTitleId());
            }

            subvar = subvar.substring(0, subvar.length() - 1);
        }
        Retrofit retrofit = apiClient.getNewClient(login.this);
        apiRest service = retrofit.create(apiRest.class);
        Call<SubTitleResponse> call = service.getExtraSubTitle(subvar);
        call.enqueue(new Callback<SubTitleResponse>() {
            @Override
            public void onResponse(Call<SubTitleResponse> call, Response<SubTitleResponse> response) {
                Log.d("My :: ", "***************************1");
                if (response.body() != null) {
                    Log.d("My :: ", "***************************2");
                    if (response.body().getSuccess().equals(1)) {
                        Log.d("My :: ", "***************************" + response.body().getSubTitle());

                        subTitleData = new ArrayList<>();
                        subTitleData = response.body().getSubTitle();
                        if (check == false) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                            builder.setMessage("Do You want To Download Aptitude Crack New Sub Category??");
                            builder.setTitle("Download Dialog");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    SyncSubTitlesToDb();

                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    progress_bar_item_image.setProgress(100);
                                    per.setText("100%");
                                    Intent intent = new Intent(login.this, splash.class);
                                    startActivity(intent);
                                    finish();

                                }

                            });
                            builder.show();


                        } else {
                            SyncSubTitlesToDb();
                        }
                    } else {
                        progress_bar_item_image.setProgress(100);
                        per.setText("100%");
                        Intent intent = new Intent(login.this, splash.class);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    Log.d("My ::  :: ", "*** RESPONSE IS BLANK");
                    progress_bar_item_image.setProgress(100);
                    per.setText("100%");
                    Intent intent = new Intent(login.this, splash.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<SubTitleResponse> call, Throwable t) {
                progress_bar_item_image.setProgress(100);
                per.setText("100%");
                Intent intent = new Intent(login.this, splash.class);
                startActivity(intent);
                finish();
                Log.d("ERROR :: ", " ******* " + t.getMessage());
            }
        });
    }

    public void SyncTitlesToDb() {
        if (titleDataServer.size() > 0) {
            for (int i = 0; i < titleDataServer.size(); i++) {
                TestAdapter1 mDbHelper = new TestAdapter1(login.this);
                mDbHelper.createDatabase();
                mDbHelper.open();
                mDbHelper.SaveTitleData(titleDataServer.get(i).getTitleId(), titleDataServer.get(i).getTitlename(), titleDataServer.get(i).getTitleimg());
                mDbHelper.close();
            }
        }
        loadSubTitlefromDB();
    }

    public void SyncSubTitlesToDb() {
        subTitleDataServer = new ArrayList<>();
        if (subTitleData.size() > 0) {
            for (int i = 0; i < subTitleData.size(); i++) {
                TestAdapter1 mDbHelper = new TestAdapter1(login.this);
                mDbHelper.createDatabase();
                mDbHelper.open();
                mDbHelper.SaveSubTitleData(subTitleData.get(i).getSubTitleId(), subTitleData.get(i).getSubTitleName(), subTitleData.get(i).getSubtitleimg(), subTitleData.get(i).getTitleId());
                subTitleDataServer.add(Integer.parseInt(subTitleData.get(i).getSubTitleId()));
                mDbHelper.close();
            }
        }
        Log.d("ABCDEFG", "****>> getSubAptitudeTitle ::  subtitleDataServer.size() ::" + subTitleDataServer.size());
        if (subTitleDataServer.size() > 0) {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            Intent intent = new Intent(login.this, SyncSystemActivity.class);
            intent.putExtra("SplashSync", true);
            intent.putExtra("sub_title_ids", subTitleDataServer);
            startActivity(intent);
            finish();
        }
    }

    private void loadSubTitlefromDB() {
        subTitleData = new ArrayList<>();
        TestAdapter1 mDbHelper = new TestAdapter1(login.this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.fetchSubTitles();

        if (cursor.moveToFirst()) {
            do {
                SubTitleData std = new SubTitleData();
                std.setSubTitleId(cursor.getString(1));
                subTitleData.add(std);
            }
            while (cursor.moveToNext());
        }
        mDbHelper.close();
        getSubAptitudeTitle();
    }

    private void loadTitlefromDB() {
        TestAdapter1 mDbHelper = new TestAdapter1(login.this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.fetchTitles();

        if (cursor.moveToFirst()) {
            do {
                TitleData td = new TitleData();
                td.setTitleId(cursor.getString(1));
                titleData.add(td);
            }
            while (cursor.moveToNext());
        }

        mDbHelper.close();
        getAllTitle();

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}
