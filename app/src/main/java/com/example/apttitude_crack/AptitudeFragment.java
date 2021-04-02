package com.example.apttitude_crack;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
//import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.apttitude_crack.Api.apiClient;
import com.example.apttitude_crack.Api.apiRest;
import com.example.apttitude_crack.GetterSetter.TitleData;
import com.example.apttitude_crack.GetterSetter.TitleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AptitudeFragment extends Fragment {
    GridView gridView;
    String[] demo = {"Arithmetic Aptitude", "Data Interpretation", "Logical Reasoning", "Networking Questions",
            "Digital Electronics", "Microbiology", "Database Questions", "Basic Electronics", "Mechanical Engineering",
            "Civil Engineering", "EEE", "biochemistry", "General Knowledge"};


    List<TitleData> titleData;
    TitleAdapter titleAdapter;

    int[] timg = {

            R.drawable.arithmetic,
            R.drawable.data_interpretaion,
            R.drawable.logical,
            R.drawable.networking,
            R.drawable.digital,
            R.drawable.microbiology,
            R.drawable.database_questions,
            R.drawable.basic_electronics,
            R.drawable.mechanical,
            R.drawable.civil_engineering,
            R.drawable.eee,
            R.drawable.biochemistry,
            R.drawable.gk
    };

     public static AptitudeFragment newInstance(int page, String title) {
         AptitudeFragment fragmentFirst = new AptitudeFragment();
         Bundle args = new Bundle();
         args.putInt("someInt", page);
         args.putString("someTitle", title);
         fragmentFirst.setArguments(args);
         return fragmentFirst;
     }

     // Store instance variables based on arguments passed
     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
     }

     // Inflate the view for the fragment based on layout XML
     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_aptitude, container, false);
         CustomGrid customGrid = new CustomGrid(getContext(), demo, timg);

         gridView = (GridView) view.findViewById(R.id.grid);
         titleData = new ArrayList<>();

//         getAllTitle();
           loadTitlefromDB();

         gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                 Toast.makeText(getContext(), "You clicked at" + demo[position], Toast.LENGTH_SHORT).show();

                 Log.w("my", "position:-" + position);

                 if (position == 0) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);
                 } else if (position == 1) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 2) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 3) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 4) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 5) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 6) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 7) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 8) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 9) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 10) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 11) {
                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 } else if (position == 12) {

                     Intent intent = new Intent(getContext(), Arithmetic_Aptitude_Activity.class);
                     intent.putExtra("title_id",titleData.get(position).getTitleId());
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(intent);

                 }
             }
         });

         return view;
     }


    public void getAllTitle() {
        Retrofit retrofit = apiClient.getNewClient(getActivity());
        apiRest service = retrofit.create(apiRest.class);
        Call<TitleResponse> call = service.getAllAptitudeTitle();
        call.enqueue(new Callback<TitleResponse>() {
            @Override
            public void onResponse(Call<TitleResponse> call, Response<TitleResponse> response) {
                Log.d("My :: ", "***************************1");
                if (response.body() != null) {
                    Log.d("My :: ", "***************************2");
                    if (response.body().getSuccess().equals(1))
                    {
                        Log.d("My :: ", "***************************");
                        titleData = response.body().getTitle();
                        titleAdapter = new TitleAdapter(getContext(), titleData);

                        gridView.setAdapter(titleAdapter);
                    }

                } else {
                    Log.d("My ::  :: ", "*** RESPONSE IS BLANK") ;
                }
            }

            @Override
            public void onFailure(Call<TitleResponse> call, Throwable t) {
                Log.d("ERROR :: ", " ******* " + t.getMessage());
            }
        });
    }

    private void loadTitlefromDB()
    {
        TestAdapter1 mDbHelper = new TestAdapter1(getContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.fetchTitles();

        if (cursor.moveToFirst())
        {
            do {
                TitleData td=new TitleData();
                td.setTitleId(cursor.getString(1));
                td.setTitlename(cursor.getString(2));
                td.setTitleimg(cursor.getString(4));
                titleData.add(td);
            }
            while (cursor.moveToNext());
        }

        mDbHelper.close();
        titleAdapter = new TitleAdapter(getContext(),titleData);
        gridView.setAdapter(titleAdapter);

    }
}
