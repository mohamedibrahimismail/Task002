package com.example.miestro.task002;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MIESTRO on 05/09/2018.
 */

public class CitiesModel {
    @SerializedName("Id")
    String Id;
    @SerializedName("TitleEN")
    String TitleEN;
    @SerializedName("TitleAR")
    String TitleAR;
    @SerializedName("CountryId")
    String CountryId;

    public String getId() {
        return Id;
    }

    public String getTitleEN() {
        return TitleEN;
    }

    public String getTitleAR() {
        return TitleAR;
    }

    public String getCountryId() {
        return CountryId;
    }
}
