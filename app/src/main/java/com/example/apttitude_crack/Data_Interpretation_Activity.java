package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Data_Interpretation_Activity extends AppCompatActivity {

    GridView sub_data_Grid;
    String[] title = {"Table Charts",
            "Pie Charts",
            "Bar Charts",
            "Line Charts"};

//    int [] timg ={
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__interpretation_);

        sub_data_Grid =(GridView) findViewById(R.id.datagrid);

//        Arithmetic_aptitude_Adepter customGrid = new Arithmetic_aptitude_Adepter(Data_Interpretation_Activity.this,title,timg);
//        sub_data_Grid.setAdapter(customGrid);

        sub_data_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Data_Interpretation_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
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
