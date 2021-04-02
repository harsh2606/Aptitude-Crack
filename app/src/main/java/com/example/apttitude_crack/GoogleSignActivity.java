package com.example.apttitude_crack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apttitude_crack.Api.PrefManager;
import com.example.apttitude_crack.Api.apiClient;
import com.example.apttitude_crack.Api.apiRest;
import com.example.apttitude_crack.GetterSetter.UserLoginResponse;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GoogleSignActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    ImageView signInButton,fb;
    private GoogleApiClient googleApiClient;
    //    TextView textView;
    private static final int RC_SIGN_IN = 1;
    PrefManager pref;
    private String sub_title_id;
    CallbackManager callbackManager;
    private LoginButton loginButton;
    long time = 900000;
    boolean isPauseExam = false;
    int pauseExamId = -1;
    private long givenTime =-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign);
        pref = new PrefManager(this);

        printHashKey(GoogleSignActivity.this);

        Intent intent = getIntent();
        sub_title_id = intent.getStringExtra("sub_title_id");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (ImageView) findViewById(R.id.sign_in_button);
        fb = (ImageView) findViewById(R.id.fb);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);
            }
        });

        callbackManager = CallbackManager.Factory.create();

        String EMAIL = "email";

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.w("harsh","onSuccess:-"+loginResult);
                getUserDetails(loginResult);
                // App code
            }

            @Override
            public void onCancel() {
                Log.w("harsh","onCancel:-");
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                Log.w("harsh","onError:-"+exception);
                // App code
            }
        });

//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));




        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loginButton.performClick();
            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
//            gotoProfile();
            RegisterAppUser(result);
        } else {
            Toast.makeText(getApplicationContext(), "Sign in cancel", Toast.LENGTH_LONG).show();
        }
    }

    private void gotoProfile() {
        Rules();
//        Intent intent=new Intent(GoogleSignActivity.this,TestActivity.class);
//        intent.putExtra("sub_title_id",sub_title_id);
//        startActivity(intent);

    }


    private void RegisterAppUser(GoogleSignInResult result) {
        GoogleSignInAccount account = result.getSignInAccount();
        final String user_id = account.getId();
        String user_name = account.getDisplayName();
        String user_email = account.getEmail();
//        String user_profile = account.getPhotoUrl().toString();

        String user_profile = "https://i.stack.imgur.com/34AD2.jpg";

        Log.d("my ::", "user_profile"+account.getPhotoUrl());

        if (account.getPhotoUrl() == null || account.getPhotoUrl().equals(""))
        {
            user_profile = "https://i.stack.imgur.com/34AD2.jpg";
        }
        else
        {
            user_profile = account.getPhotoUrl().toString();
        }
//        if (user_profile == null || user_profile.equals("")) {
//            user_profile = "https://i.stack.imgur.com/34AD2.jpg";
//        }

        Retrofit retrofit = apiClient.getNewClient(GoogleSignActivity.this);
        apiRest service = retrofit.create(apiRest.class);
        Call<UserLoginResponse> call = service.register_app_user(user_id, user_name, user_email, user_profile);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                Log.d("my ::", "Wrong Data");
                if (response.body() != null) {
                    Log.d("my ::", "Wrong Data2");
                    if (response.body().getSuccess().equals(1)) ;
                    {
                        Log.w("my", "user_id:=" + user_id);
                        pref.setString("aptc_user_id", user_id);
                        gotoProfile();
                    }

                } else {
                    Log.d("my ::", "Response Blank");
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Log.d("Error ::", "**********" + t.getMessage());
            }
        });

    }

//    private void Rules() {
//        final Dialog alertDialog1 = new Dialog(GoogleSignActivity.this, R.style.CustomDialog);
//        alertDialog1.setContentView(R.layout.rules_layout);
//        ImageView wrong = alertDialog1.findViewById(R.id.wrong);
//        ImageView wrong1 = alertDialog1.findViewById(R.id.wrong1);
//
//        WebView text= alertDialog1.findViewById(R.id.text);
//
//        text.loadDataWithBaseURL("",
//                "<html>\n" +
//                        "<head>\n" +
//                        "<style>\n" +
//                        "table, th, td {\n" +
//                        "  border: 1px solid #e8eaeb;\n" +
//                        "  border-collapse: collapse;\n" +
//                        "}\n" +
//                        "</style>\n" +
//                        "</head>\n" +
//                        "<body>\n" +
//
//                        "<ul style=\"list-style-type: disc;margin-left: -25px;line-height: 24px;margin-top: -5px;\">\n" +
//                        "<li>Test Consists of Total 15 Questions with Total 30 Marks.</li>\n" +
//                        "<li>Negative Marking.</li>\n" +
//
//
//                        "<li>Three Levels of Difficulty:\n" +
//                        "<ul style=\"list-style-type: circle;margin-left: -25px;\">\n" +
//                        "<li><strong>Easy:</strong> You will get 15 minutes to solve the test.</li>\n" +
//                        "<li><strong>Medium:</strong> You will get 12 minutes to solve the test.</li>\n" +
//                        "<li><strong>Hard:</strong> You will get 10 minutes to solve the test.</li>\n" +
//                        "</ul>\n" +
//                        "</li>\n" +
//                        "</ul>\n" +
//                        "<table style=\"background-color: #fff;box-shadow: 0 1px 2px rgba(0,0,0,.225);margin-top: -5px;\">\n" +
//                        "<tbody>\n" +
//                        "<tr><th colspan=\"3\" style=\"background-color: #f5f1f1;text-align: center;padding: 3px 15px 3px 15px;\">Marking System</th></tr><tr>\n" +
//                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\"><strong>Correct</strong></td>\n" +
//                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\"><strong>Incorrect</strong></td>\n" +
//                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\"><strong>Not Attempt</strong></td>\n" +
//                        "</tr>\n" +
//                        "<tr style=\"text-align: center;\">\n" +
//                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\">2 Mark</td>\n" +
//                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\">-1 Mark</td>\n" +
//                        "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\">0 Mark</td>\n" +
//                        "</tr>\n" +
//                        "</tbody>\n" +
//                        "</table>\n" +
//                        "</body>\n" +
//                        "</html>",
//                "text/html", "UTF-8", "");
//
//
//        alertDialog1.setCancelable(false);
//        alertDialog1.show();
//
//        wrong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog1.dismiss();
//                Intent intent = new Intent(GoogleSignActivity.this, TestActivity.class);
//                Log.w("krn", "sub_title_id adapter:-" + sub_title_id);
//                intent.putExtra("sub_title_id", sub_title_id);
//                startActivity(intent);
//                finish();
//
//            }
//        });
//        wrong1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog1.dismiss();
//            }
//        });
//    }
private void Rules() {

    CheckAnyPauseExam( sub_title_id);

    final Dialog alertDialog1 = new Dialog(GoogleSignActivity.this, R.style.CustomDialog);
    alertDialog1.setContentView(R.layout.rules_layout);
    ImageView wrong= alertDialog1.findViewById(R.id.wrong);
    ImageView wrong1= alertDialog1.findViewById(R.id.wrong1);
    IndicatorSeekBar seekbar= alertDialog1.findViewById(R.id.seekbar);
    WebView text= alertDialog1.findViewById(R.id.text);

    text.loadDataWithBaseURL("",
            "<html>\n" +
                    "<head>\n" +
                    "<style>\n" +
                    "table, th, td {\n" +
                    "  border: 1px solid #e8eaeb;\n" +
                    "  border-collapse: collapse;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +

                    "<ul style=\"list-style-type: disc;margin-left: -25px;line-height: 24px;margin-top: -5px;\">\n" +
                    "<li>Test Consists of Total 15 Questions with Total 30 Marks.</li>\n" +
                    "<li>Negative Marking.</li>\n" +


                    "<li>Three Levels of Difficulty:\n" +
                    "<ul style=\"list-style-type: circle;margin-left: -25px;\">\n" +
                    "<li><strong>Easy:</strong> You will get 15 minutes to solve the test.</li>\n" +
                    "<li><strong>Medium:</strong> You will get 12 minutes to solve the test.</li>\n" +
                    "<li><strong>Hard:</strong> You will get 10 minutes to solve the test.</li>\n" +
                    "</ul>\n" +
                    "</li>\n" +
                    "</ul>\n" +
                    "<table style=\"background-color: #fff;box-shadow: 0 1px 2px rgba(0,0,0,.225);margin-top: -5px;\">\n" +
                    "<tbody>\n" +
                    "<tr><th colspan=\"3\" style=\"background-color: #f5f1f1;text-align: center;padding: 3px 15px 3px 15px;\">Marking System</th></tr><tr>\n" +
                    "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\"><strong>Correct</strong></td>\n" +
                    "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\"><strong>Incorrect</strong></td>\n" +
                    "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\"><strong>Not Attempt</strong></td>\n" +
                    "</tr>\n" +
                    "<tr style=\"text-align: center;\">\n" +
                    "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\">2 Mark</td>\n" +
                    "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\">-1 Mark</td>\n" +
                    "<td style=\"text-align: center;padding: 3px 15px 3px 15px;\">0 Mark</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n" +
                    "</body>\n" +
                    "</html>",
            "text/html", "UTF-8", "");


    alertDialog1.setCancelable(false);
    alertDialog1.show();

    if (isPauseExam)
    {
        if (givenTime == 600000)
        {
            seekbar.setProgress(100);
            time = 600000;
        }
        else if (givenTime == 720000)
        {
            seekbar.setProgress(50);
            time = 720000;
        }
        else
        {
            seekbar.setProgress(0);
            time = 900000;

        }
        seekbar.setEnabled(false);

    }

    seekbar.setOnSeekChangeListener(new OnSeekChangeListener() {
        @Override
        public void onSeeking(SeekParams seekParams) {
            Log.w("my","seekbarparams:-"+seekParams.tickText);

            if(seekParams.tickText.equals("Hard"))
            {
                time = 600000;
            }
            else if (seekParams.tickText.equals("Medium"))
            {
                time = 720000;
            }
            else
            {
                time = 900000;
            }

        }

        @Override
        public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
        }
    });

    wrong.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            alertDialog1.dismiss();

            if (isPauseExam && pauseExamId != -1) {
                Intent intent=new Intent(GoogleSignActivity.this,TestActivity.class);
                intent.putExtra("sub_title_id", sub_title_id);
                intent.putExtra("pause_exam_id", pauseExamId);
                intent.putExtra("time",time);
                startActivity(intent);

            }
            else
            {
                Intent intent=new Intent(GoogleSignActivity.this,TestActivity.class);
                intent.putExtra("sub_title_id", sub_title_id);
                intent.putExtra("time",time);
                startActivity(intent);
            }

//                CheckAnyPauseExam(sub_title_id);


//                    Intent intent=new Intent(c,TestActivity.class);
//                    Log.w("krn","sub_title_id adapter:-"+sub_title_id);
//                    intent.putExtra("sub_title_id",sub_title_id);
//                    c.startActivity(intent);

        }
    });

    wrong1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            alertDialog1.dismiss();
        }
    });
}

    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("my", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e("my", "printHashKey()", e);
        } catch (Exception e) {
            Log.e("my", "printHashKey()", e);
        }
    }

    protected void getUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject json_object, GraphResponse response) {
                        RegisterAppUserFB(json_object);
                        Log.d("USERDETAILS :", "json_object ::" + json_object.toString());

//                        Rules();
//                        Intent intent = new Intent(MainActivity.this,
//                                UserProfile.class);
//                        intent.putExtra("userProfile", json_object.toString());
//                        startActivity(intent);
                    }

                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(512).height(512)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();

    }

    private void RegisterAppUserFB(JSONObject json_object) {
//        GoogleSignInAccount account = result.getSignInAccount();
      Log.w("harsh","json_object"+json_object.toString());
        // {"id":"2949940841735499","name":"Divyang Jariwala","email":"divyang.jariwala@ymail.com","picture":{"data":{"height":720,"is_silhouette":false,"url":"https:\/\/platform-lookaside.fbsbx.com\/platform\/profilepic\/?asid=2949940841735499&height=512&width=512&ext=1586148246&hash=AeRasozNIu2G2S4T","width":720}}}
        final String user_id = json_object.optString("id");
        String user_name = json_object.optString("name");
        String user_email = json_object.optString("email");
        String user_profile = "https://graph.facebook.com/"+user_id+"/picture?width=500&width=500";

        Retrofit retrofit = apiClient.getNewClient(GoogleSignActivity.this);
        apiRest service = retrofit.create(apiRest.class);
        Call<UserLoginResponse> call = service.register_app_user(user_id, user_name, user_email,user_profile);
        call.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                Log.d("my ::", "Wrong Data");
                if (response.body() != null) {
                    Log.d("my ::", "Wrong Data2");
                    if (response.body().getSuccess().equals(1)) ;
                    {
                        pref.setString("aptc_user_id" , user_id);
                        gotoProfile();
                    }

                } else {
                    Log.d("my ::", "Response Blank");
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Log.d("Error ::", "**********" + t.getMessage());
            }
        });

    }

    private void CheckAnyPauseExam(String sub_title_id)
    {
        TestAdapter1 mDbHelper = new TestAdapter1(GoogleSignActivity.this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.CheckAnyPauseExam(sub_title_id);

        if (cursor.moveToFirst()) {
            isPauseExam = true;
            pauseExamId = cursor.getInt(0);
            givenTime = cursor.getLong(5);
        }

        mDbHelper.close();
    }


}
