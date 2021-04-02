package com.example.apttitude_crack.Api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
//import android.support.annotation.RequiresApi;
//import android.support.annotation.RequiresApi;

//import androidx.annotation.RequiresApi;

//import com.borntoplay.wordsearch.features.SplashScreenActivity;
import androidx.annotation.RequiresApi;

import com.example.apttitude_crack.BuildConfig;
import com.jakewharton.picasso.OkHttp3Downloader;
//import com.tryoninfosoft.garagewala.BuildConfig;
//import com.tryoninfosoft.garagewala.LoginActivity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import androidx.annotation.RequiresApi;

//import android.support.annotation.RequiresApi;
//import okhttp3.logging.HttpLoggingInterceptor;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;


/**
 * Created by hsn on 28/09/2017.
 */


public class apiClient {
    private static Retrofit retrofit = null;
    private static Retrofit retrofit2 = null;
    private static final String CACHE_CONTROL = "Cache-Control";

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public static Retrofit initClient() {
        String text = "";
        byte[] data = android.util.Base64.decode(apiClient.retrofit_id, android.util.Base64.DEFAULT);
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(text)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


    public static Retrofit getNewClient(Context context) {
        if (retrofit2 == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
            httpClient.addInterceptor(logging);  // <-- this is the important line!
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .addInterceptor(logging)
                    .addInterceptor(provideOfflineCacheInterceptor(context))
                    .addNetworkInterceptor(provideCacheInterceptor())

                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .build();

            OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(okHttpClient);

            retrofit2 = new Retrofit.Builder()
                    .baseUrl("http://api.aptitudecrack.com/API/")
//                    .baseUrl("http://192.168.0.105/aptitude_crack/api/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }



    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
//                        Log.d("", "" + message);
                    }
                });
        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? BODY : BODY);
        return httpLoggingInterceptor;


    }

    public static Interceptor provideCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                // re-write response header to force use of cache
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(2, TimeUnit.SECONDS)
                        .build();
                return response.newBuilder()
                        .header(CACHE_CONTROL, cacheControl.toString())
                        .build();
            }
        };
    }

    public static String retrofit_id = "aHR0cDovL2xpY2Vuc2UucmlzdGFuYS5jb20vYXBpLw==";

    public static Interceptor provideOfflineCacheInterceptor(final Context context) {
        return new Interceptor() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!checkIfHasNetwork(context)) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(30, TimeUnit.DAYS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }


    public static boolean checkIfHasNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
