package com.example.apttitude_crack;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Networking_Questions_Activity extends AppCompatActivity {

    GridView sub_Networking_Grid;
    String[] title = {"IOS and Security Device Manager",
            "IP Routing",
            "Spanning Tree Protocol",
            "Security",
            "Wireless Technologies",
            "Wide Area Networks",
            "Internetworking",
            "Subnetting",
            "Managing a Cisco Internetwork",
            "EIGRP and OSPF",
            "Virtual LANs",
            "Network Address Translation",
            "IPv6",
            "Networking Basic",
            "TCP IP"

    };

//    int [] timg = {
//            R.drawable.arithmetic,
//            R.drawable.data_interpretation,
//            R.drawable.logical,
//            R.drawable.networking,
//            R.drawable.digital,
//            R.drawable.microbiology,
//            R.drawable.database_questions,
//            R.drawable.basic_electronics,
//            R.drawable.mechanical,
//            R.drawable.civil_engineering,
//            R.drawable.eee,
//            R.drawable.biochemistry,
//            R.drawable.gk,
//            R.drawable.gk
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking__questions_);

        sub_Networking_Grid =(GridView) findViewById(R.id.netgrid);

//      Networking_Question_Adepter customGrid = new Networking_Question_Adepter(Networking_Questions_Activity.this,title,timg);
//        sub_Networking_Grid.setAdapter(customGrid);

        sub_Networking_Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Networking_Questions_Activity.this, "You clicked at" + title[position], Toast.LENGTH_SHORT).show();
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
