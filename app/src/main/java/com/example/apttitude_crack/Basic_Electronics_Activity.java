package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Basic_Electronics_Activity extends AppCompatActivity {

    GridView sub_Baicele_Grid;
    String[] title = {" Voltage and Current"};

//    int [] timg ={
//            R.drawable.arithmetic,
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic__electronics_);

        sub_Baicele_Grid =(GridView) findViewById(R.id.basicelegrid);

//        Arithmetic_aptitude_Adepter customGrid = new Arithmetic_aptitude_Adepter(Basic_Electronics_Activity.this,title,timg);
//        sub_Baicele_Grid.setAdapter(customGrid);

        sub_Baicele_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Basic_Electronics_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
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
