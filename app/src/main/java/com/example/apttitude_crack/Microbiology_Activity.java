package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Microbiology_Activity extends AppCompatActivity {

    GridView sub_Micro_Grid;

    String[] title = { "Micro Organisms",
            "Bacteria Morphology",
            "Cell Cultures and Characteristics"

    };

//    int [] timg ={
//            R.drawable.arithmetic,
//            R.drawable.data_interpretation,
//            R.drawable.logical,
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microbiology_);

        sub_Micro_Grid =(GridView) findViewById(R.id.microgrid);

//        Arithmetic_aptitude_Adepter customGrid = new Arithmetic_aptitude_Adepter(Microbiology_Activity.this,title,timg);
//        sub_Micro_Grid.setAdapter(customGrid);

        sub_Micro_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Microbiology_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
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
