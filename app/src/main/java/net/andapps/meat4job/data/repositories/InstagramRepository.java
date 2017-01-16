/*
Created by Helm  13/01/2017.
*/


package net.andapps.meat4job.data.repositories;

import android.util.Log;
import net.andapps.meat4job.data.model.InstagramData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

import javax.inject.Inject;
import javax.inject.Named;

public class InstagramRepository implements InstagramDataRepository {

    private final Retrofit retrofit;

    @Inject
    public InstagramRepository(@Named("InstagramRetrofit")Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void getPicture(final GetPictureCallback callback, String clientId) {
        Call<InstagramData> call = retrofit.create(InstagramPictureService.class).getInstagramPictureById(clientId);
        call.enqueue(new Callback<InstagramData>() {
            @Override
            public void onResponse(Call<InstagramData> call, Response<InstagramData> response) {
                callback.onSuccess(response.body().getData().get(0).getImages().getStandard().getUrl());
            }

            @Override
            public void onFailure(Call<InstagramData> call, Throwable t) {
                Log.v("WRONG","wrong");
            }
        });
    }


    public interface InstagramPictureService {


        @GET("/v1/users/self/media/recent/")
        Call<InstagramData> getInstagramPictureById(@Query("access_token") String accessToken);
    }
}
