package com.example.featuretogglelibrary.model;

public class CreateFeatureRequest {
    // These fields match the required fields from the backend API
    private String package_name;
    private String name;
    private String description;
    private String beginning_date;
    private String expiration_date;

    // Constructor for easy object creation
    public CreateFeatureRequest(String package_name, String name, String description,
                                String beginning_date, String expiration_date) {
        this.package_name = package_name;
        this.name = name;
        this.description = description;
        this.beginning_date = beginning_date;
        this.expiration_date = expiration_date;
    }

    // Getters and setters
    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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