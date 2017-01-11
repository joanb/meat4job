package net.andapps.meat4job.ui.di;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import net.andapps.meat4job.model.di.DomainModule;

/**
 * Created by joanbarroso on 11/01/2017.
 */


@Module(
        includes = {
                ViewModule.class,
                DomainModule.class
        }
)
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }
}
