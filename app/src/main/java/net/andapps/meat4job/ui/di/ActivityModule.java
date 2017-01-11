package net.andapps.meat4job.ui.di;

import android.app.Activity;
import android.support.v4.app.Fragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by joanbarroso on 11/01/2017.
 */


@Module(
        includes = {
                ViewModule.class,
        }
)
public class ActivityModule {

    private Activity activity;
    private Fragment fragment;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }
    public ActivityModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    Fragment provideFragment(){
        return fragment;
    }
}
