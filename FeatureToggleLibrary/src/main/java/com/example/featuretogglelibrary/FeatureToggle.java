package com.example.featuretogglelibrary;

import android.content.Context;

import com.example.featuretogglelibrary.callbacks.Callback_Features;
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
}
