package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Logical_Reasoning_Activity extends AppCompatActivity {

    GridView sub_Logical_Grid;
    String[] title = {  "Number Series",
            "Verbal Classification",
            "Analogies",
            "Matching Definitions",
            "Verbal Reasoning",
            "Logical Games",
            "Statement and Assumption",
            "Statement and Conclusion",
            "Cause and Effect",
            "Logical Deduction",
            "Letter and Symbol Series",
            "Essential Part",
            "Artificial Language",
            "Making Judgments",
            "Logical Problems",
            "Analyzing Arguments",
            "Course of Action",
            "Theme Detection",
            "Statement and Argument"};

//    int [] timg ={
//            R.drawable.arithmetic,
//            R.drawable.data_interpretation,
//            R.drawable.logical,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//            R.drawable.networking,
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logical__reasoning_);


        sub_Logical_Grid =(GridView) findViewById(R.id.logicalgrid);

//        Arithmetic_aptitude_Adepter customGrid = new Arithmetic_aptitude_Adepter(Logical_Reasoning_Activity.this,title,timg);
//        sub_Logical_Grid.setAdapter(customGrid);

        sub_Logical_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Logical_Reasoning_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
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
