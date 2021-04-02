package com.example.apttitude_crack;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.apttitude_crack.Api.apiClient;
import com.example.apttitude_crack.Api.apiRest;
import com.example.apttitude_crack.GetterSetter.TestSubTitleData;
import com.example.apttitude_crack.GetterSetter.TestTitleData;
import com.example.apttitude_crack.GetterSetter.TestTitleDataResponse;
import com.example.apttitude_crack.GetterSetter.TitleData;
import com.example.apttitude_crack.GetterSetter.TitleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TestFragment extends Fragment {

    String[] title = {"Arithmetic Aptitude", "Data Interpretation", "Logical Reasoning", "Networking Questions",
            "Digital Electronics", "Microbiology", "Database Questions", "Basic Electronics", "Mechanical Engineering",
            "Civil Engineering", "EEE", "biochemistry", "General Knowledge"};
    ExpandableListView listview;
    List<TestTitleData> titleData;
    List<TestSubTitleData> testSubTitleData;
    TestAdapter testAdapter;

    public static TestFragment newInstance(int page, String title) {
        TestFragment fragmentFirst = new TestFragment();
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
//		page = getArguments().getInt("someInt", 0);
//		title = getArguments().getString("someTitle");

//		Log.d("My ::: ", "HELLLO CALLING ON CREATE OF FRAGMENT");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        listview=(ExpandableListView)view.findViewById(R.id.listview);
        titleData = new ArrayList<>();

  //getAllTitle();
        TestTitlefromDB();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), "You clicked at" + titleData.get(position), Toast.LENGTH_SHORT).show();

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



//        Button startbutton=(Button)view.findViewById(R.id.button);
//        //        Button aboutbutton=(Button)findViewById(R.id.button2);
//        final EditText nametext=(EditText)view.findViewById(R.id.editName);
//        startbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name=nametext.getText().toString();
//                Intent intent=new Intent(getActivity(),QuestionsActivity.class);
//                intent.putExtra("myname",name);
//                startActivity(intent);
//            }
//        });

        return view;
    }

    private void getAllTitle() {
        Retrofit retrofit = apiClient.getNewClient(getActivity());
        apiRest service = retrofit.create(apiRest.class);
        Call<TestTitleDataResponse> call = service.getTestTitles();
        call.enqueue(new Callback<TestTitleDataResponse>() {
            @Override
            public void onResponse(Call<TestTitleDataResponse> call, Response<TestTitleDataResponse> response) {
                Log.d("My :: ", "***************************1");
                if (response.body() != null) {
                    Log.d("My :: ", "***************************2");
                    if (response.body().getSuccess().equals(1))
                    {
                        Log.d("My :: ", "***************************");
                        titleData = response.body().getTitle();
                        //testAdapter  = new TestAdapter( titleData,getContext());

                       // listview .setAdapter(testAdapter);
                      testAdapter=new TestAdapter(titleData,getActivity());
                        listview.setAdapter(testAdapter);
                    }

                } else {
                    Log.d("My ::  :: ", "*** RESPONSE IS BLANK") ;
                }
            }

            @Override
            public void onFailure(Call<TestTitleDataResponse> call, Throwable t) {
                Log.d("ERROR :: ", " ******* " + t.getMessage());
            }
        });

    }

    private void TestTitlefromDB()
    {
        TestAdapter1 mDbHelper = new TestAdapter1(getContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.fetchTitles();

        if (cursor.moveToFirst())
        {
            do {
                TestTitleData td=new TestTitleData();
                td.setTitleId(cursor.getString(1));
                td.setTitlename(cursor.getString(2));
                td.setTitleimg(cursor.getString(4));

                Cursor cur = mDbHelper.fetchSubTitles(cursor.getString(1));
                testSubTitleData = new ArrayList<>();
                if (cur.moveToFirst()) {
                    do {
                        TestSubTitleData tsd = new TestSubTitleData();
                        tsd.setSubTitleId(cur.getString(1));
                        tsd.setSubTitleName(cur.getString(2));
                        tsd.setTitleId(cur.getString(3));
                        tsd.setSubtitleimg(cur.getString(4));
                        testSubTitleData.add(tsd);
                    } while (cur.moveToNext());
                }
                td.setSubTitle(testSubTitleData);
                titleData.add(td);
            }
            while (cursor.moveToNext());
        }

        mDbHelper.close();
        testAdapter = new TestAdapter(titleData,getContext());
        listview.setAdapter(testAdapter);

    }

}
