package com.example.apttitude_crack;

import android.content.Intent;
import android.database.Cursor;
//import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apttitude_crack.Api.apiClient;
import com.example.apttitude_crack.Api.apiRest;
import com.example.apttitude_crack.GetterSetter.SubTitleData;
import com.example.apttitude_crack.GetterSetter.SubTitleResponse;
import com.example.apttitude_crack.GetterSetter.TitleData;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Arithmetic_Aptitude_Activity extends AppCompatActivity {
   MediaPlayer mp;
    GridView sub_Arithmetic_Grid;
    TextView titletext;
//    String[] title = {"Problems on Trains",
//            "Height and Distance",
//            "Simple Interest",
//            "Profit and Loss",
//            "Calendar",
//            "Average",
//            "Volume and Surface Area",
//            "Numbers",
//            "Problems on HCF and LCM",
//            "Simplification",
//            "Chain Rule",
//            "Logarithm",
//            "Percentage",
//            "Surds and Indices",
//            "Boats and Streams",
//            "Stocks and Shares",
//            "True Discount",
//            "Odd Man Out and Series",
//            "Time and Distance",
//            "Time and Work",
//            "Compound Interest",
//            "Partnership",
//            "Problems on Ages",
//            "Clock",
//            "Permutation and Combination",
//            "Area",
//            "Problems on Numbers",
//            "Decimal Fraction",
//            "Square Root and Cube Root",
//            "Ratio and Proportion",
//            "Pipes and Cistern",
//            "Races and Games",
//            "Probability",
//            "Bankers Discount"};

    List<SubTitleData> subTitleData;


//    int [] timg ={
//            R.drawable.arithmetic,
//            R.drawable.data_interpretation,
//            R.drawable.logical,
//            R.drawable.networking,
//            R.drawable.digital,
//            R.drawable.microbiology,
//            R.drawable.database_questions,
//            R.drawable.basic_electronics,
//            R.drawable.mechanical,
//            R.drawable.civil_engineering,
//            R.drawable.eee,
//            R.drawable.biochemistry,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//            R.drawable.gk,
//
//
//
//    };
    Arithmetic_aptitude_Adepter customGrid;
    private String title_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic__aptitude_);

        mp = MediaPlayer.create(this, R.raw.click);
        Intent intent = getIntent();
        title_id = intent.getStringExtra("title_id");

        sub_Arithmetic_Grid =(GridView) findViewById(R.id.arithgrid);
        titletext =(TextView) findViewById(R.id.titletext);

        subTitleData = new ArrayList<>();

//        getSubAptitudeTitle();
        loadSubTitlefromDB();


//         customGrid = new Arithmetic_aptitude_Adepter(Arithmetic_Aptitude_Activity.this,title,timg);
//        sub_Arithmetic_Grid.setAdapter(customGrid);

        sub_Arithmetic_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//             Intent intent = new Intent(Arithmetic_Aptitude_Activity.this,A_Question_Activity.class);
//               intent.putExtra("title_id",subTitleData.get(position).getTitleId());
//                intent.putExtra("sub_title_id",subTitleData.get(position).getSubTitleId());
//               startActivity(intent);
                Intent intent = new Intent(Arithmetic_Aptitude_Activity.this, A_Question_Activity.class);
                intent.putExtra("title_id", subTitleData.get(position).getTitleId());
                intent.putExtra("sub_title_id", subTitleData.get(position).getSubTitleId());
                intent.putExtra("title_name", subTitleData.get(position).getSubTitleName());
                intent.putExtra("titles", subTitleData.get(position).getTitlename());

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
//               Toast.makeText(Arithmetic_Aptitude_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
            }
        });


        findViewById(R.id.backimg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                finish();
            }
        });
    }

    private void getSubAptitudeTitle() {
        Retrofit retrofit = apiClient.getNewClient(Arithmetic_Aptitude_Activity.this);
        apiRest service = retrofit.create(apiRest.class);
        Call<SubTitleResponse> call = service.getSubTitleWithPostMethod(title_id);
        call.enqueue(new Callback<SubTitleResponse>() {
            @Override
            public void onResponse(Call<SubTitleResponse> call, Response<SubTitleResponse> response) {
                Log.d("My :: ", "***************************1");
                if (response.body() != null) {
                    Log.d("My :: ", "***************************2");
                    if (response.body().getSuccess().equals(1))
                    {
                        Log.d("My :: ", "***************************"+ response.body().getSubTitle());
                        subTitleData = response.body().getSubTitle();

                        customGrid= new Arithmetic_aptitude_Adepter(Arithmetic_Aptitude_Activity.this, subTitleData);

                        sub_Arithmetic_Grid.setAdapter(customGrid);
                    }

                } else {
                    Log.d("My ::  :: ", "*** RESPONSE IS BLANK") ;
                }
            }

            @Override
            public void onFailure(Call<SubTitleResponse> call, Throwable t) {
                Log.d("ERROR :: ", " ******* " + t.getMessage());
            }
        });
    }

    private void loadSubTitlefromDB()
    {
        TestAdapter1 mDbHelper = new TestAdapter1(Arithmetic_Aptitude_Activity.this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.fetchSubTitles(title_id);

        if (cursor.moveToFirst())
        {
            do {
                SubTitleData std=new SubTitleData();
                std.setSubTitleId(cursor.getString(1));
                std.setSubTitleName(cursor.getString(2));
                std.setTitleId(cursor.getString(3));
                std.setSubtitleimg(cursor.getString(4));
                std.setTquestion(cursor.getString(5));
                std.setVisited(cursor.getString(6));
                std.setTitlename(cursor.getString(7));
                subTitleData.add(std);
                titletext.setText(std.getTitlename());
            }
            while (cursor.moveToNext());


        }

        mDbHelper.close();
        customGrid = new Arithmetic_aptitude_Adepter(Arithmetic_Aptitude_Activity.this,subTitleData);
        sub_Arithmetic_Grid.setAdapter(customGrid);

    }
}
