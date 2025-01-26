package com.example.featuretogglelibrary;

import android.content.Context;

import com.example.featuretogglelibrary.callbacks.Callback_CreateFeature;
import com.example.featuretogglelibrary.callbacks.Callback_Features;
import com.example.featuretogglelibrary.callbacks.Callback_SingleFeature;
import com.example.featuretogglelibrary.model.CreateFeatureRequest;
import com.example.featuretogglelibrary.model.Feature;

import java.util.List;

public class FeatureToggle {
    private static final FeatureController featureController = new FeatureController();

    public interface Callback_Data<T> {
        void data(T value);
    }

    public static void getActiveFeatures(Context context, Callback_Data<List<Feature>> callBack) {
        if (callBack == null) {
            return;
        }

        featureController
                .fetchAllActiveFeatures(
                        context.getPackageName(),
                        new Callback_Features() {
                            @Override
                            public void ready(List<Feature> features) {
                                callBack.data(features);
                            }

                            @Override
                            public void fail(String message) {

                            }
                        });
    }


    public static void createFeature(Context context, String name, String description,
                                     String beginningDate, String expirationDate,
                                     Callback_Data<String> callback) {
        if (callback == null) {
            return;
        }

        CreateFeatureRequest request = new CreateFeatureRequest(
                context.getPackageName(),
                name,
                description,
                beginningDate,
                expirationDate
        );

        featureController.createFeature(request, new Callback_CreateFeature() {
            @Override
            public void onSuccess(String featureId) {
                callback.data(featureId);
            }

            @Override
            public void onError(String message) {
                callback.data(null);
            }
        });
    }


    public static void getAllFeatures(Context context, Callback_Data<List<Feature>> callback) {
        if (callback == null) {
            return;
        }

        featureController.fetchAllFeatures(
                context.getPackageName(),
                new Callback_Features() {
                    @Override
                    public void ready(List<Feature> features) {
                        callback.data(features);
                    }

                    @Override
                    public void fail(String message) {
                        // Return null to indicate failure
                        callback.data(null);
                    }
                });
    }


    public static void getFeatureById(Context context, String featureId,
                                      Callback_Data<Feature> callback) {
        if (callback == null) {
            return;
        }

        featureController.fetchFeatureById(
                context.getPackageName(),
                featureId,
                new Callback_SingleFeature() {
                    @Override
                    public void ready(Feature feature) {
                        callback.data(feature);
                    }

                    @Override
                    public void fail(String message) {
                        callback.data(null);
                    }
                });
    }




}
