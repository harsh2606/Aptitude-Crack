package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Digital_Electronics_Activity extends AppCompatActivity {

    GridView sub_digital_Grid;
    String[] title = {"Digital Concepts",
            "Logic Gates",
            "Boolean Algebra and Logic Simplification",
            "Combinational Logic Analysis",
            "Signals and Switches",
            "Digital Arithmetic Operations and Circuits",
            "Shift Registers"};

//    int [] timg ={
//            R.drawable.arithmetic,
//            R.drawable.data_interpretation,
//            R.drawable.logical,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital__electronics_);


        sub_digital_Grid =(GridView) findViewById(R.id.digitalgrid);

//        Arithmetic_aptitude_Adepter customGrid = new Arithmetic_aptitude_Adepter(Digital_Electronics_Activity.this,title,timg);
//        sub_digital_Grid.setAdapter(customGrid);

        sub_digital_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Digital_Electronics_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
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
