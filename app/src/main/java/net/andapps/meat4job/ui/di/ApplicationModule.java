package net.andapps.meat4job.ui.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import net.andapps.meat4job.Meat4Job;
import net.andapps.meat4job.data.di.DataModule;

import javax.inject.Singleton;

/**
 * Created by joanbarroso on 11/01/2017.
 */

@Module(
        includes = {
                DataModule.class
        }
)
public class ApplicationModule {
    private final Meat4Job meat4JobApplication;


    public ApplicationModule(Meat4Job meat4JobApplication) {
        this.meat4JobApplication = meat4JobApplication;
    }

    @Provides
    @Singleton
    Meat4Job providesApplication() {
        return meat4JobApplication;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return meat4JobApplication;
    }

}
