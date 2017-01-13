/*
Created by Helm  11/01/2017.
*/


package net.andapps.meat4job;

import android.app.Application;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import net.andapps.meat4job.ui.di.ApplicationComponent;
import net.andapps.meat4job.ui.di.ApplicationModule;
import net.andapps.meat4job.ui.di.DaggerApplicationComponent;

public class Meat4Job extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "symU4FHRwLk4CQSCwRjXe71Fe";
    private static final String TWITTER_SECRET = "G5Ge6VSnLDJLDdPHQekTE22zDAv8NmPofY0N0RnQiWl3ILHfv6";



    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        initializeInjection();
    }

    private void initializeInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
