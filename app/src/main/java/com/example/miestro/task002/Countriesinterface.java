package com.example.miestro.task002;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/**
 * Created by MIESTRO on 16/08/2018.
 */

public interface Countriesinterface {





    @GET
    Call<List<CountryModel>> getdata(@Url String url);



    @GET
    Call<List<CitiesModel>> get_cities_data(@Url String url);



}
