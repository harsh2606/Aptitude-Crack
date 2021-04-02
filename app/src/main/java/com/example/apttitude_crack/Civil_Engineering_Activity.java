package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Civil_Engineering_Activity extends AppCompatActivity {

    GridView sub_civil_Grid;
    String[] title = {"Indian Geography",
            "Surveying",
            "Soil Mechanics and Foundation Engineering",
            "Applied Mechanics",
            "Irrigation",
            "Railways",
            "Construction Management",
            "Estimating and Costing",
            "UPSC Civil Service Exam Questions"};

//    int [] timg ={
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civil__engineering_);

        sub_civil_Grid =(GridView) findViewById(R.id.civilgrid);

//        Arithmetic_aptitude_Adepter customGrid = new Arithmetic_aptitude_Adepter(Civil_Engineering_Activity.this,title,timg);
//        sub_civil_Grid.setAdapter(customGrid);

        sub_civil_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Civil_Engineering_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
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
