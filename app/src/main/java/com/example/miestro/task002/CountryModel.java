package com.example.miestro.task002;


import com.google.gson.annotations.SerializedName;

/**
 * Created by MIESTRO on 05/09/2018.
 */

public class CountryModel {
   @SerializedName("Id")
    String Id;
    @SerializedName("TitleEN")
    String TitleEN;
    @SerializedName("TitleAR")
    String TitleAR;
    @SerializedName("CurrencyId")
    String CurrencyId;
    @SerializedName("CurrencyEN")
    String CurrencyEN;
    @SerializedName("CurrencyAR")
    String CurrencyAR;
    @SerializedName("CodeEN")
    String CodeEN;
    @SerializedName("CodeAR")
    String CodeAR;
    @SerializedName("Code")
    String Code;

    public String getId() {
        return Id;
    }

    public String getTitleEN() {
        return TitleEN;
    }

    public String getTitleAR() {
        return TitleAR;
    }

    public String getCurrencyId() {
        return CurrencyId;
    }

    public String getCurrencyEN() {
        return CurrencyEN;
    }

    public String getCurrencyAR() {
        return CurrencyAR;
    }

    public String getCodeEN() {
        return CodeEN;
    }

    public String getCodeAR() {
        return CodeAR;
    }

    public String getCode() {
        return Code;
    }
}
