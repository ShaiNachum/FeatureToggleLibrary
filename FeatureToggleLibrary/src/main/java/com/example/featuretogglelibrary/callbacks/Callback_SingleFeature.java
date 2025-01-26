package com.example.featuretogglelibrary.callbacks;

import com.example.featuretogglelibrary.model.Feature;

public interface Callback_SingleFeature {
    void ready(Feature feature);
    void fail(String message);
}