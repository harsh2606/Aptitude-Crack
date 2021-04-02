package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Mechanical_Engineering_Activity extends AppCompatActivity {


    GridView sub_Mechanical_Grid;

    String[] title = { "Engineering Mechanics",
            "Hydraulics and Fluid Mechanics",
            "Thermodynamics",
            "Steam Nozzles and Turbines",
            "Strength of Materials",
            "Machine Design"

    };

//    int [] timg ={
//            R.drawable.arithmetic,
//            R.drawable.data_interpretation,
//            R.drawable.logical,
//            R.drawable.logical,
//            R.drawable.logical,
//            R.drawable.logical,
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanical__engineering_);

        sub_Mechanical_Grid =(GridView) findViewById(R.id.mechanicalgrid);

//        Arithmetic_aptitude_Adepter customGrid = new Arithmetic_aptitude_Adepter(Mechanical_Engineering_Activity.this,title,timg);
//        sub_Mechanical_Grid.setAdapter(customGrid);

        sub_Mechanical_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Mechanical_Engineering_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
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
