package net.andapps.meat4job.data.di;

import dagger.Module;
import dagger.Provides;
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

    private final static String URL = "https://maps.googleapis.com";

    @Provides
    @Named("GoogleMapsRetrofit")
    Retrofit providesRetrofit() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logger);
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }


    @Provides
    @Singleton
    MapsDataRepository providesMapsRepository(MapsRepository mapsRepository) {
        return mapsRepository;
    }

}
