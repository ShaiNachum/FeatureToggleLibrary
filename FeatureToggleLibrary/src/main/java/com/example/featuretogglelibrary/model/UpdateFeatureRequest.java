package com.example.featuretogglelibrary.model;

public class UpdateFeatureRequest {
    private String beginning_date;
    private String expiration_date;


    public UpdateFeatureRequest(String beginning_date, String expiration_date) {
        this.beginning_date = beginning_date;
        this.expiration_date = expiration_date;
    }


    public String getBeginning_date() {
        return beginning_date;
    }

    public void setBeginning_date(String beginning_date) {
        this.beginning_date = beginning_date;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }
}