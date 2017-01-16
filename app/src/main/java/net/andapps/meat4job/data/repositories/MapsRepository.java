/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job.data.repositories;

import android.util.Log;
import net.andapps.meat4job.data.model.PlaceLocations;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

public class MapsRepository implements MapsDataRepository{

    private final Retrofit retrofit;

    @Inject
    public MapsRepository(@Named("GoogleMapsRetrofit") Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void getPlaces(final GetPlacesCalback callback) {
        Map<String, String> data = new HashMap<>();
        data.put("location", "41.390782,2.170132");
        data.put("radius", "500");
        data.put("types", "food");
        data.put("key", "AIzaSyAmVXeAXKYaAj4KWqJ_gr9xUp2PH5QmrjU");

        Call<PlaceLocations> call = retrofit.create(GooglePlacesService.class).getAllFoodplacesNerabyCenterOfBarcelona(data);
        call.enqueue(new Callback<PlaceLocations> () {

            @Override
            public void onResponse(Call<PlaceLocations> call, Response<PlaceLocations> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PlaceLocations> call, Throwable t) {
                Log.v("WRONG","wrong");
            }
        });
    }



    public interface GooglePlacesService {

        @GET("/maps/api/place/nearbysearch/json?location=41.390782,2.170132&radius=500&types=food&key=AIzaSyAmVXeAXKYaAj4KWqJ_gr9xUp2PH5QmrjU")
        Call<PlaceLocations> getAllFoodplacesNerabyCenterOfBarcelona(
                @QueryMap Map<String, String> queries
        );
    }
}
