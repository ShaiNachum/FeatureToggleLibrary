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

        FeatureToggle.createFeature(
                this,
                "my_feature",
                "This is a test feature",
                "2024-01-26 00:00:00",
                "2024-12-31 23:59:59",
                new FeatureToggle.Callback_Data<String>() {
                    @Override
                    public void data(String featureId) {
                        if (featureId != null) {
                            // Feature created successfully
                            Log.d("FeatureToggle", "Created feature with ID: " + featureId);
                        } else {
                            // Feature creation failed
                            Log.e("FeatureToggle", "Failed to create feature");
                        }
                    }
                }
        );


    }
}