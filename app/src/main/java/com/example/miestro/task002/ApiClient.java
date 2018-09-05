package com.example.miestro.task002;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MIESTRO on 16/08/2018.
 */

public class ApiClient {

    private static final String Base_Url = "http://192.168.1.3:800/imageupload/";
    private static Retrofit retrofit ;

    public static Retrofit getApiClient(){



        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG){

            okHttpClient.addInterceptor(interceptor);
        }





        if(retrofit == null){

            retrofit = new Retrofit.Builder(). baseUrl(Base_Url).
                           client(okHttpClient.build()).
                           addConverterFactory(GsonConverterFactory.create()).
                           build();

        }

        return retrofit;
    }

}
