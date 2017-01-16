package net.andapps.meat4job.data.di;

import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import net.andapps.meat4job.data.repositories.InstagramDataRepository;
import net.andapps.meat4job.data.repositories.InstagramRepository;
import net.andapps.meat4job.data.repositories.MapsDataRepository;
import net.andapps.meat4job.data.repositories.MapsRepository;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by joanbarroso on 11/01/2017.
 */

@Module
public class DataModule {

    private final static String MAPS_URL = "https://maps.googleapis.com";
    private final static String INSTAGRAM_URL = "https://api.instagram.com/v1/";

    @Provides
    @Named("GoogleMapsRetrofit")
    Retrofit providesMapsRetrofit() {
        return getRetrofit(MAPS_URL);
    }
    @Provides
    @Named("InstagramRetrofit")
    Retrofit providesInstagramRetrofit(){
        return getRetrofit(INSTAGRAM_URL);
    }

    @Provides
    @Singleton
    MapsDataRepository providesMapsRepository(MapsRepository mapsRepository) {
        return mapsRepository;
    }

    @Provides
    @Singleton
    InstagramDataRepository providesInstagramRepository(InstagramRepository instagramRepository) {
        return instagramRepository;
    }

    @NonNull
    private Retrofit getRetrofit(String url) {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logger);
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

}
