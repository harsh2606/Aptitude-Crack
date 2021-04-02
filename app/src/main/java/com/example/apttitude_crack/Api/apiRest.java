package com.example.apttitude_crack.Api;


import com.example.apttitude_crack.GetterSetter.QuestionBySubTitleResponse;
import com.example.apttitude_crack.GetterSetter.SubTitleResponse;
import com.example.apttitude_crack.GetterSetter.SyncQuestionResponse;
import com.example.apttitude_crack.GetterSetter.TestTitleDataResponse;
import com.example.apttitude_crack.GetterSetter.TitleResponse;
import com.example.apttitude_crack.GetterSetter.UserLoginResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface apiRest {


//    @FormUrlEncoded
//    @POST("api.php")
//    Call<UserReward> UserScoreUpdate(@Header("Authorization") String token, @Field("scoreUpdate") boolean scoreUpdate, @Field("updateMyPoints") int updateMyPoint, @Field("wsID") int wsID, @Field("updateMyDollar") String updateMyDollar);
//

    @GET("getTitleImg.php")
    Call<TitleResponse> getAllAptitudeTitle();

    @GET("getSubTitleByTitle.php")
    Call<SubTitleResponse> getSubAptitudeTitle(@Query("title_id") String title_id);


    @FormUrlEncoded
    @POST("getSubTitleByTitle.php")
    Call<SubTitleResponse> getSubTitleWithPostMethod(@Field("title_id") String title_id);

    @GET("getTest.php")
    Call<TestTitleDataResponse> getTestTitles();

    @GET("getQuestionBySubTitle.php")
    Call<QuestionBySubTitleResponse> getQuestionBySubTitle(@Query("sub_title_id") String title_id);

    @POST("getSubTitleByTitle_tbl_api.php")
    Call<SubTitleResponse> getSubTitleWithPostMethod();

    @GET("getQuestion_tbl_api.php")
    Call<SyncQuestionResponse> getQuestionsForSync(@Query("sub_title_id") int sub_title_id,
                                                   @Query("start_at") int start_at,
                                                   @Query("end_at") int end_at);

    @FormUrlEncoded
    @POST("registerAppUser.php")
    Call<UserLoginResponse> register_app_user(@Field("user_id") String user_id,
                                              @Field("user_name") String user_name,
                                              @Field("user_email") String user_email,
                                              @Field("user_profile") String user_profile);

    @FormUrlEncoded
    @POST("report_question.php")
    Call<UserLoginResponse> report_question(@Field("user_id") String user_id,
                                              @Field("question_id") int question_id);

    @GET("add_extra_title.php")
    Call<TitleResponse> getExtraTitle(@Query("title_ids") String title_ids);

    @GET("add_extra_subtitle.php")
    Call<SubTitleResponse> getExtraSubTitle(@Query("sub_title_ids") String sub_title_ids);






}


