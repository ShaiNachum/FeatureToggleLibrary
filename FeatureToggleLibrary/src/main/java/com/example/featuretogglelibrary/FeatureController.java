package com.example.featuretogglelibrary;

import com.example.featuretogglelibrary.callbacks.Callback_CreateFeature;
import com.example.featuretogglelibrary.callbacks.Callback_Features;
import com.example.featuretogglelibrary.callbacks.Callback_SingleFeature;
import com.example.featuretogglelibrary.callbacks.Callback_UpdateFeature;
import com.example.featuretogglelibrary.model.CreateFeatureRequest;
import com.example.featuretogglelibrary.model.CreateFeatureResponse;
import com.example.featuretogglelibrary.model.Feature;
import com.example.featuretogglelibrary.model.UpdateFeatureRequest;
import com.example.featuretogglelibrary.model.UpdateFeatureResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeatureController {
    private static final String BASE_URL = "https://feature-toggle-library-beckend.vercel.app/";

    private Callback_Features callbackFeatures;
    private Callback_CreateFeature createFeatureCallback;
    private Callback_SingleFeature singleFeatureCallback;
    private Callback_UpdateFeature updateFeatureCallback;

    public void setCallbackFeatures(Callback_Features callbackFeatures) {
        this.callbackFeatures = callbackFeatures;
    }

    public void setCreateFeatureCallback(Callback_CreateFeature callback) {
        this.createFeatureCallback = callback;
    }

    public void setSingleFeatureCallback(Callback_SingleFeature callback) {
        this.singleFeatureCallback = callback;
    }

    public void setUpdateFeatureCallback(Callback_UpdateFeature callback) {
        this.updateFeatureCallback = callback;
    }

    private FeatureAPI getAPI(){
        Gson gson  = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(FeatureAPI.class);
    }

    private Callback<List<Feature>> listCallback= new Callback<List<Feature>>() {
        @Override
        public void onResponse(Call<List<Feature>> call, Response<List<Feature>> response) {
            callbackFeatures.ready(response.body());
        }

        @Override
        public void onFailure(Call<List<Feature>> call, Throwable throwable) {
            callbackFeatures.fail(throwable.getMessage());
        }
    };


    public void fetchAllActiveFeatures(String packageName, Callback_Features callbackFeatures){
        setCallbackFeatures(callbackFeatures);
        Call<List<Feature>> call = getAPI().loadActiveFeatures(packageName);
        call.enqueue(listCallback);
    }


    public void createFeature(CreateFeatureRequest request, Callback_CreateFeature callback) {
        setCreateFeatureCallback(callback);
        Call<CreateFeatureResponse> call = getAPI().createFeature(request);

        call.enqueue(new Callback<CreateFeatureResponse>() {
            @Override
            public void onResponse(Call<CreateFeatureResponse> call,
                                   Response<CreateFeatureResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    createFeatureCallback.onSuccess(response.body().get_id());
                } else {
                    createFeatureCallback.onError("Failed to create feature: " +
                            response.code());
                }
            }

            @Override
            public void onFailure(Call<CreateFeatureResponse> call, Throwable t) {
                createFeatureCallback.onError(t.getMessage());
            }
        });
    }


    public void fetchAllFeatures(String packageName, Callback_Features callbackFeatures) {
        // We can reuse the existing callback since it handles the same type of response
        setCallbackFeatures(callbackFeatures);

        // Make the API call to get all features
        Call<List<Feature>> call = getAPI().getAllFeatures(packageName);
        call.enqueue(listCallback);
    }


    public void fetchFeatureById(String packageName, String featureId, Callback_SingleFeature callback) {
        setSingleFeatureCallback(callback);
        Call<Feature> call = getAPI().getFeatureById(packageName, featureId);

        call.enqueue(new Callback<Feature>() {
            @Override
            public void onResponse(Call<Feature> call, Response<Feature> response) {
                if (response.isSuccessful() && response.body() != null) {
                    singleFeatureCallback.ready(response.body());
                } else {
                    singleFeatureCallback.fail("Feature not found or server error: " +
                            response.code());
                }
            }

            @Override
            public void onFailure(Call<Feature> call, Throwable t) {
                singleFeatureCallback.fail(t.getMessage());
            }
        });
    }


    public void fetchFeaturesByDate(String packageName, String date, Callback_Features callbackFeatures) {
        setCallbackFeatures(callbackFeatures);

        // Format validation before making the API call
        try {
            // Validate date format (YYYY-MM-DD)
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                callbackFeatures.fail("Invalid date format. Please use YYYY-MM-DD");
                return;
            }

            Call<List<Feature>> call = getAPI().getFeaturesByDate(packageName, date);

            // We can reuse our existing listCallback since the response structure is the same
            call.enqueue(listCallback);

        } catch (Exception e) {
            callbackFeatures.fail("Error processing date: " + e.getMessage());
        }
    }


    public void updateFeatureDates(String packageName, String featureId,
                                   UpdateFeatureRequest request,
                                   Callback_UpdateFeature callback) {
        setUpdateFeatureCallback(callback);
        Call<UpdateFeatureResponse> call = getAPI().updateFeatureDates(packageName, featureId, request);

        call.enqueue(new Callback<UpdateFeatureResponse>() {
            @Override
            public void onResponse(Call<UpdateFeatureResponse> call,
                                   Response<UpdateFeatureResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateFeatureCallback.onSuccess(response.body().getMessage());
                } else {
                    String errorMessage = "Failed to update feature: ";
                    if (response.code() == 400) {
                        errorMessage += "Invalid date format or date logic";
                    } else if (response.code() == 404) {
                        errorMessage += "Feature not found";
                    } else {
                        errorMessage += "Server error " + response.code();
                    }
                    updateFeatureCallback.onError(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<UpdateFeatureResponse> call, Throwable t) {
                updateFeatureCallback.onError("Network error: " + t.getMessage());
            }
        });
    }



}
