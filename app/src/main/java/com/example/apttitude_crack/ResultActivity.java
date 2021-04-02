package com.example.apttitude_crack;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    AnyChartView anyChartView;
    ArrayList pieEntries;
    TextView txttotal,aq,wr,ri,tm,ct,sub,test;
    int exam_id;
    Pie pie;
    String sub_title_id,sub_title_name;
    ImageView sharesnap,fi,txtans,txtres,backimg;
    RelativeLayout snap;
    MediaPlayer mp,camera;

    long TIMEOUT = 900000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mp = MediaPlayer.create(this, R.raw.click);
        camera = MediaPlayer.create(this, R.raw.camera_1);

        final Intent intent = getIntent();
        exam_id = intent.getIntExtra("exam_id",0);
        sub_title_id = intent.getStringExtra("sub_title_id");
        sub_title_name = intent.getStringExtra("sub_title_name");

        if (intent.hasExtra("time"))
        {
            TIMEOUT = intent.getLongExtra("time", 900000);
        }

        Log.w("rachna","exam_id:-"+exam_id);

        txttotal=(TextView) findViewById(R.id.txttotal);
        aq=(TextView) findViewById(R.id.aq);
        wr=(TextView) findViewById(R.id.wr);
        ri=(TextView) findViewById(R.id.ri);
        tm=(TextView) findViewById(R.id.tm);
        ct=(TextView) findViewById(R.id.ct);
        test=(TextView) findViewById(R.id.test);

        sub=(TextView) findViewById(R.id.sub);
        sub.setText(sub_title_name);

        fi=(ImageView) findViewById(R.id.fi);
        txtans=(ImageView) findViewById(R.id.txtans);
        txtres=(ImageView) findViewById(R.id.txtres);
        sharesnap=(ImageView) findViewById(R.id.sharesnap);
        backimg=(ImageView) findViewById(R.id.backimg);
        snap=(RelativeLayout) findViewById(R.id.snap);

        anyChartView = findViewById(R.id.any_chart_view);
        anyChartView.addCss("file:///android_asset/credit.css");

        pie = AnyChart.pie();
        if(TIMEOUT == 900000)
        {
            test.setText(""+"EASY");
        }
        if(TIMEOUT == 720000)
        {
            test.setText(""+"MEDIUM");
        }
        if(TIMEOUT == 600000)
        {
            test.setText(""+"HARD");
        }

        pie.setOnClickListener(new ListenersInterface.OnClickListener(new String[]{"x", "value"}) {
            @Override
            public void onClick(Event event) {
//                Toast.makeText(ResultActivity.this, event.getData().get("x") + ":" + event.getData().get("value"), Toast.LENGTH_SHORT).show();
            }
        });
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ResultActivity.this,splash.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);

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
                ResultActivity.this.shareImage(file, "Share Challenge on", "", "");
            }
        });



        txtans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(ResultActivity.this,AnswerActivity.class);
                intent.putExtra("exam_id",exam_id);
                intent.putExtra("sub_title_id",sub_title_id);
                intent.putExtra("time",TIMEOUT);
                startActivity(intent);
            }
        });



        StringBuffer sb = new StringBuffer();
//        sb.append("Correct answers: " + QuestionsActivity.correct + "\n");
        StringBuffer sb2 = new StringBuffer();
//        sb2.append("Wrong Answers: " + QuestionsActivity.wrong + "\n");
        StringBuffer sb3 = new StringBuffer();
//        sb3.append("Final Score: " + QuestionsActivity.correct + "\n");
//        wr.setText(sb);
//        ri.setText(sb2);
//        tm.setText(sb3);


//        f = Float.parseFloat(String.valueOf(QuestionsActivity.correct));
//        f2 = Float.parseFloat(String.valueOf(QuestionsActivity.wrong));

//        Log.w("my","f:-"+f);
//        Log.w("my","f2:-"+f2);
//        f3 = Float.parseFloat(String.valueOf(QuestionsActivity.correct));
//        f2 = Float.parseFloat(sb2.toString());
//        f3 = Float.parseFloat(sb3.toString());
        SetGameState("FINISHED");
        GetResult(exam_id);

        fi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent1 = new Intent(ResultActivity.this,splash.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);

            }
        });


        txtres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent intent = new Intent(ResultActivity.this,AllResultActivity.class);
                intent.putExtra("exam_id",exam_id);
                intent.putExtra("sub_title_id",sub_title_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

//        GetResult(exam_id);

    }

    private void Rules() {
        final Dialog alertDialog1 = new Dialog(ResultActivity.this, R.style.CustomDialog);
        alertDialog1.setContentView(R.layout.rules_layout);
        ImageView wrong= alertDialog1.findViewById(R.id.wrong);

        alertDialog1.setCancelable(false);
        alertDialog1.show();

        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });
    }


    private void getEntries(float wrong, float right, float marks) {
        List<DataEntry> data = new ArrayList<>();
        Log.w("harsh","data");
        data.add(new ValueDataEntry("Wrong", wrong));
        data.add(new ValueDataEntry("Right", right));

        pie.data(data);
        pie.background().fill("#E0E4F0");

//        pie.title("Test result");

        pie.labels().position("outside");

        pie.legend().title().enabled(false);
//        pie.legend().title()
//                .text("Result")
//                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        anyChartView.setChart(pie);


    }

    private void GetResult(int exam_id)
    {
        if (exam_id != 0)
        {
            TestAdapter1 mDbHelper = new TestAdapter1(this);
            mDbHelper.createDatabase();
            mDbHelper.open();

            Cursor cursor = mDbHelper.GetResult(exam_id);
            if (cursor.moveToFirst()) {
                int not_attp = cursor.getInt(10);
                int wrong_marks = (cursor.getInt(11) - not_attp) * 1;


                int mark = ((cursor.getInt(12) * 2) - wrong_marks );
                txttotal.setText("" + cursor.getInt(8));
                aq.setText("" +cursor.getInt(9));
                wr.setText("" + cursor.getInt(11));
                ri.setText("" + cursor.getInt(12));
                tm.setText("" + mark);
                if (GetTimeTaken(cursor.getLong(4)) != null)
                {
                    ct.setText("" + GetTimeTaken(cursor.getLong(4)));
                }
                getEntries(cursor.getFloat(11), cursor.getFloat(12), mark);
            }
            mDbHelper.close();
        }
    }

    public static String GetTimeTaken(long millis) {
        String time = null;
        String min, secs;
        int seconds = (int) (millis / 1000) % 60 ;
        int minutes = (int) ((millis / (1000*60)) % 60);
        if (minutes < 10) {
            min = "0" + minutes;
        }
        else
        {
            min = String.valueOf( minutes);
        }
        if (seconds < 10) {
            secs = "0" + seconds;
        }
        else
        {
            secs = String.valueOf( seconds);
        }

        time = min+":"+secs;
        Log.d("EXAMTIME", "===========>> " + time);
        Log.d("EXAMTIME", "===========>> REPLACE >> " + time.replace(" ", ""));
        return  time.replace(" ", "");
    }

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

    private void SetGameState(String state)
    {
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.SetGameState(exam_id, state);
        mDbHelper.close();
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(ResultActivity.this,splash.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);
    }
}
