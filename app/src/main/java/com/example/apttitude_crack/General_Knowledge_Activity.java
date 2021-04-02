package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class General_Knowledge_Activity extends AppCompatActivity {
    GridView sub_General_Grid;
    String[] title = { "Basic General Knowledge",
            "General Science",
            "Indian Politics",
            "World Geography",
            "Chemistry"};

//    int [] timg ={
//            R.drawable.arithmetic,
//            R.drawable.data_interpretation,
//            R.drawable.logical,
//            R.drawable.networking,
//            R.drawable.networking,
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general__knowledge_);


        sub_General_Grid =(GridView) findViewById(R.id.genralgrid);

//        Arithmetic_aptitude_Adepter customGrid = new Arithmetic_aptitude_Adepter(General_Knowledge_Activity.this,title,timg);
//        sub_General_Grid.setAdapter(customGrid);

        sub_General_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(General_Knowledge_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.backimg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
