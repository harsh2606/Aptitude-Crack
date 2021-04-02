package com.example.apttitude_crack;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AllResultActivity extends AppCompatActivity {


    private String sub_title_id;
    List<Integer> exam_ids;
    private GridView gridans;
    TextView sub;
    ImageView back;
    private AllResultAdepter allResultAdapter;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_result);
        mp = MediaPlayer.create(this, R.raw.click);

        Intent intent = getIntent();
        sub_title_id = intent.getStringExtra("sub_title_id");

        exam_ids = new ArrayList<>();

        gridans = (GridView) findViewById(R.id.gridans);
        sub = (TextView) findViewById(R.id.sub);
        back = (ImageView) findViewById(R.id.back);


        getExamHistory();
        getSubTitle();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                finish();
            }
        });
    }



    private void getExamHistory(){
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getExamHistory(sub_title_id);
        if (cursor.moveToFirst())
        {

            do
                {
                exam_ids.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
        mDbHelper.close();

        if (exam_ids.size()> 0)
        {
            allResultAdapter = new  AllResultAdepter(this, exam_ids);
            gridans.setAdapter(allResultAdapter);
        }
    }

    private void getSubTitle(){
        TestAdapter1 mDbHelper = new TestAdapter1(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getSubTitle(sub_title_id);
        if (cursor.moveToFirst())
        {
            sub.setText(cursor.getString(2));
        }
        mDbHelper.close();
    }

}
