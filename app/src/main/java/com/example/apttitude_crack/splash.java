package com.example.apttitude_crack;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
//import android.support.design.widget.TabLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apttitude_crack.Api.apiClient;
import com.example.apttitude_crack.Api.apiRest;
import com.example.apttitude_crack.GetterSetter.TitleData;
import com.example.apttitude_crack.GetterSetter.TitleResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class splash extends AppCompatActivity implements TabLayout.OnTabSelectedListener {


    ViewPager viewPager;
    TabLayout tabLayout;
    String[] demo = {"Arithmetic Aptitude", "Data Interpretation", "Logical Reasoning", "Networking Questions",
            "Digital Electronics", "Microbiology", "Database Questions", "Basic Electronics", "Mechanical Engineering",
            "Civil Engineering", "EEE", "biochemistry", "General Knowledge"};



//    int[] timg = {
//
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
//            R.drawable.gk
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayot);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Apptitude"));
        tabLayout.addTab(tabLayout.newTab().setText("Test"));

        viewPager.setAdapter(new MyPageSetAdepter(getSupportFragmentManager(), this));





    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private class MyPageSetAdepter extends FragmentPagerAdapter {
        private Map<Integer, String> map;
        private FragmentManager fragmentManager;
        private Context context;


        public MyPageSetAdepter(FragmentManager fm, Context splash) {
            super(fm);

            fragmentManager = fm;
            context = splash;
            map = new HashMap<>();

        }

        //        public MyPageSetAdepter(FragmentManager supportFragmentManager) {
//            super();
//        }
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return new AptitudeFragment();  //(create first class).newInstance(0,"PROGRESSING");
                case 1:
                    return new TestFragment();  //.newInstance(1,"COMPLETED");
//                    return new TestFragment();  //.newInstance(1,"COMPLETED");
                default:
                    return new AptitudeFragment(); //CompletedFragment.newInstance(1,"PROGRESSING");
            }
        }



        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Aptitude";
                case 1:
                    return "Test";
                default:
                    return "Aptitude";
            }
//            return "OBJECT " + (position + 1);

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object obj = super.instantiateItem(container, position);
            if (obj instanceof Fragment) {
                // record the fragment tag here.
                Fragment f = (Fragment) obj;
                String tag = f.getTag();
                map.put(position, tag);
            }
            return obj;
        }

        public Fragment getFragment(int position) {
            String tag = map.get(position);
            if (tag == null)
                return null;
            return fragmentManager.findFragmentByTag(tag);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                return true;
            case R.id.action_settings:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=Tryon+InfoSoft")));
                return true;
            case R.id.Website:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://aptitudecrack.com")));
                return true;
            case R.id.share:
            {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! ✌\n" +
                        "Download a new cool Application Aptitude Crack. \n" +
                        "It's Fun!!! \uD83D\uDE04\uD83D\uDE04\n" +
                        "Download Aptitude Crack FOR FREE right now!\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDE0D\n" +
                        "\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\uD83D\uDC47\n" +
                        "https://play.google.com/store/apps/details?id="+getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed()  {
//        super.onBackPressed();

        Rules();


    }

    private void Rules() {
        final Dialog alertDialog1 = new Dialog(splash.this, R.style.CustomDialog);
        alertDialog1.setContentView(R.layout.back_progress);
        TextView wrong = alertDialog1.findViewById(R.id.wrong);
        TextView right = alertDialog1.findViewById(R.id.wrong1);

        alertDialog1.setCancelable(false);
        alertDialog1.show();

        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();

            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
                finish();
            }
        });
    }
}
