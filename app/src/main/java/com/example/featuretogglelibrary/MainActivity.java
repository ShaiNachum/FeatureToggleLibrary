package com.example.featuretogglelibrary;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;


import com.example.featuretogglelibrary.model.Feature;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example usage of create featureToggle

//        FeatureToggle.createFeature(
//                this,
//                "my_feature",
//                "This is a test feature",
//                "2024-01-26 00:00:00",
//                "2024-12-31 23:59:59",
//                new FeatureToggle.Callback_Data<String>() {
//                    @Override
//                    public void data(String featureId) {
//                        if (featureId != null) {
//                            // Feature created successfully
//                            Log.d("FeatureToggle", "Created feature with ID: " + featureId);
//                        } else {
//                            // Feature creation failed
//                            Log.e("FeatureToggle", "Failed to create feature");
//                        }
//                    }
//                }
//        );


        // Example usage of get all features
        FeatureToggle.getAllFeatures(this, new FeatureToggle.Callback_Data<List<Feature>>() {
            @Override
            public void data(List<Feature> features) {
                Log.d("AllFeatureToggle", "Callback received");

                if (features != null) {
                    Log.d("AllFeatureToggle", "Retrieved " + features.size() + " features");

                    if (features.isEmpty()) {
                        Log.d("AllFeatureToggle", "Feature list is empty");
                        return;
                    }

                    for (Feature feature : features) {
                        Log.d("AllFeatureToggle", "Feature found:");
                        Log.d("AllFeatureToggle", "  Name: " + feature.getName());
                        Log.d("AllFeatureToggle", "  ID: " + feature.get_id());
                        Log.d("AllFeatureToggle", "  Description: " + feature.getDescription());
                    }
                } else {
                    Log.e("AllFeatureToggle", "Failed to retrieve features - received null");
                }
            }
        });
        // TODO: fix the # Find active feature toggles in the backend


        // Example usage of get all features
        FeatureToggle.getActiveFeatures(this, new FeatureToggle.Callback_Data<List<Feature>>() {
            @Override
            public void data(List<Feature> features) {
                if (features != null) {
                    Log.d("ActiveFeatureToggle", "Retrieved " + features.size() + " features");

                    if (features.isEmpty()) {
                        Log.d("ActiveFeatureToggle", "Feature list is empty");
                        return;
                    }

                    for (Feature feature : features) {
                        Log.d("ActiveFeatureToggle", "Feature found:");
                        Log.d("ActiveFeatureToggle", "  Name: " + feature.getName());
                        Log.d("ActiveFeatureToggle", "  ID: " + feature.get_id());
                        Log.d("ActiveFeatureToggle", "  Description: " + feature.getDescription());
                    }
                } else {
                    Log.e("ActiveFeatureToggle", "Failed to retrieve features - received null");
                }
            }
        });


    }
}