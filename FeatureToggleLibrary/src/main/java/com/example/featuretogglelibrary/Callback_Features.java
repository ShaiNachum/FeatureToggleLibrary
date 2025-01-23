package com.example.featuretogglelibrary;

import java.util.List;

public interface Callback_Features {
    void ready(List<Feature> features);
    void fail(String message);
}
