package com.example.featuretogglelibrary.callbacks;

public interface Callback_CreateFeature {
    void onSuccess(String featureId);
    void onError(String message);
}