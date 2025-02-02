package com.example.featuretogglelibrary.model;

public class Feature {
    private String _id;
    private String beginning_date;
    private String created_at;
    private String description;
    private String expiration_date;
    private String name;
    private String updated_at;


    public Feature() {
    }


    public Feature(String beginning_date, String created_at, String description, String expiration_date, String name, String updated_at) {
        this.beginning_date = beginning_date;
        this.created_at = created_at;
        this.description = description;
        this.expiration_date = expiration_date;
        this.name = name;
        this.updated_at = updated_at;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBeginning_date() {
        return beginning_date;
    }

    public void setBeginning_date(String beginning_date) {
        this.beginning_date = beginning_date;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}