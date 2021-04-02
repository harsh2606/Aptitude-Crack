package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class EEE_Activity extends AppCompatActivity {

    GridView sub_eee_Grid;
    String[] title = { "Quantities and Units",
            "Series Circuits",
            "Transformers"};

//    int [] timg ={
//            R.drawable.arithmetic,
//            R.drawable.data_interpretation,
//            R.drawable.logical,
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eee_);

        sub_eee_Grid =(GridView) findViewById(R.id.eeegrid);

//        Arithmetic_aptitude_Adepter customGrid = new Arithmetic_aptitude_Adepter(EEE_Activity.this,title,timg);
//        sub_eee_Grid.setAdapter(customGrid);

        sub_eee_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(EEE_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
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
