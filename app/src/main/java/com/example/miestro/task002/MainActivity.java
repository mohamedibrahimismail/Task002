package com.example.miestro.task002;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    Spinner code,country,city;

    ArrayAdapter<String> code_adapter;
    ArrayAdapter<String> country_adapter;
    ArrayAdapter<String> city_adapter;

    List<String> code_list = new ArrayList<>();
    List<String> country_list = new ArrayList<>();
    List<String> city_list = new ArrayList<>();


    List<CountryModel> arrayList = new ArrayList<>();
    List<CitiesModel> cities_list = new ArrayList<>();

    final static String URL_1= "http://souq.hardtask.co/app/app.asmx/GetCountries";

    TextView showcode_textview;

    ScrollView scrollView;
    WebView webView;

    boolean webview_inflated=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showcode_textview = (TextView)findViewById(R.id.showcode);
        webView = (WebView)findViewById(R.id.webview);
        scrollView=(ScrollView)findViewById(R.id.scrollview);


        setup_code_spinner();

        setup_country_spinner();

        GetCountriesInfo();




    }

    public void GetCountriesInfo(){

        Retrofit retrofit = ApiClient.getApiClient();

        Countriesinterface countriesinterface = retrofit.create(Countriesinterface.class);

        Call<List<CountryModel>> call = countriesinterface.getdata(URL_1);
        call.enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                arrayList = response.body();

                if(!arrayList.isEmpty()){

                    add_data_to_codespinner();
                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void setup_code_spinner(){
        code_list.add("Code");

        code = (Spinner)findViewById(R.id.code);
        code.setPrompt("Code");


        code_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,code_list);
        code_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        code.setAdapter(code_adapter);
        code.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(!adapterView.getItemAtPosition(i).toString().equals("Code")) {
                    showcode_textview.setText(adapterView.getItemAtPosition(i)+"");
                  //  Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i) + "", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void setup_country_spinner(){
        country_list.add("Country");

        country = (Spinner)findViewById(R.id.country);
        country.setPrompt("Country");


        country_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,country_list);
        country_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(country_adapter);
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (!adapterView.getItemAtPosition(i).equals("Country")) {
                    String x = get_ID_OF_Country(adapterView.getItemAtPosition(i) + "");

                 //   Toast.makeText(MainActivity.this, get_ID_OF_Country(adapterView.getItemAtPosition(i) + ""), Toast.LENGTH_SHORT).show();


                    if (!x.isEmpty() && !x.equals(null) && !x.equals("")) {
                        get_cities_data(x);

                    }

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public void add_data_to_codespinner(){


        for(CountryModel countryModel : arrayList){
            code_list.add(countryModel.getCode());
            if(Locale.getDefault().getLanguage().equals("ar")){
               country_list.add(countryModel.getTitleAR());
            }else {
                country_list.add(countryModel.getTitleEN());
            }
           Log.d("MainActivity",countryModel.getCode()+"_______________________________");
        }




    }

    public String get_ID_OF_Country(String s){

        String id=null ;
        for(int a=0;a<arrayList.size();a++){
            if(s.equals(arrayList.get(a).getTitleAR())||s.equals(arrayList.get(a).getTitleEN())){
                id = arrayList.get(a).getId();
                break;
            }
        }

        return id;

    }


    public void get_cities_data(String id){
        String URL_Cities="http://souq.hardtask.co/app/app.asmx/GetCities?countryId="+id;

        Retrofit retrofit = ApiClient.getApiClient();

        Countriesinterface countriesinterface = retrofit.create(Countriesinterface.class);

        Call<List<CitiesModel>> call = countriesinterface.get_cities_data(URL_Cities);
        call.enqueue(new Callback<List<CitiesModel>>() {
            @Override
            public void onResponse(Call<List<CitiesModel>> call, Response<List<CitiesModel>> response) {


                cities_list = response.body();

                if(!cities_list.isEmpty()){

                    setup_cities_list();

                }
            }

            @Override
            public void onFailure(Call<List<CitiesModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });




    }

    public void setup_cities_list(){

        city_list.clear();
        if(Locale.getDefault().getLanguage().equals("ar")){


        for(CitiesModel city:cities_list){

                city_list.add(city.getTitleAR());
            }
        }else{

            for(CitiesModel city:cities_list){

                city_list.add(city.getTitleEN());
            }


        }
        setup_city_spinner();
    }

    public void setup_city_spinner(){


        city = (Spinner)findViewById(R.id.City);
        city.setPrompt("City");


        city_adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,city_list);
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(city_adapter);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                  //  Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i) + "", Toast.LENGTH_SHORT).show();





            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void goto_terms_conditions(View view) {


        webview_inflated = true;
        scrollView.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://termsfeed.com/blog/sample-terms-and-conditions-template/");

    }

    @Override
    public void onBackPressed() {

        if(webview_inflated){
        webView.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
            webview_inflated=false;
        }else {
            super.onBackPressed();

        }
    }
}



