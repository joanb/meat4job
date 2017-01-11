package net.andapps.meat4job.ui.di;

import android.content.Context;
import dagger.Component;
import net.andapps.meat4job.Meat4Job;
import net.andapps.meat4job.data.repositories.MapsDataRepository;

import javax.inject.Singleton;

/**
 * Created by joanbarroso on 11/01/2017.
 */


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Meat4Job getApplication();
    Context context();
    MapsDataRepository getMapsRepository();


    void inject(Meat4Job application);

}
