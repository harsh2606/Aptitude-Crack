package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Biochemistry_Activity extends AppCompatActivity {


    GridView sub_Biochemistry_Grid;
    String[] title = { "Water, pH and Macromolecules",
            "Structure and Properties of Amino Acids"};

//    int [] timg ={
//            R.drawable.arithmetic,
//            R.drawable.arithmetic,
//
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biochemistry_);

        sub_Biochemistry_Grid =(GridView) findViewById(R.id.biochemistrygrid);

//        Arithmetic_aptitude_Adepter customGrid = new Arithmetic_aptitude_Adepter(Biochemistry_Activity.this,title,timg);
//        sub_Biochemistry_Grid.setAdapter(customGrid);

        sub_Biochemistry_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Biochemistry_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
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
