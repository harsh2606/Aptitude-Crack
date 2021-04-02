package com.example.apttitude_crack;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cunoraz.gifview.library.GifView;

public class Main2Activity_splash extends AppCompatActivity {

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Main2Activity_splash.this,Activity_splash.class);
                startActivity(intent);
                finish();
            }
        },5000);


    }
}
