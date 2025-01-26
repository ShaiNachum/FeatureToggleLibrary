package com.example.featuretogglelibrary.callbacks;

import com.example.featuretogglelibrary.model.Feature;

import java.util.List;

public interface Callback_Features {
    void ready(List<Feature> features);
    void fail(String message);
}
