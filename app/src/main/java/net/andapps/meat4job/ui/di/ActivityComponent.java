package net.andapps.meat4job.ui.di;

import dagger.Component;
import net.andapps.meat4job.ui.fragments.maps.MapsFragment;
import net.andapps.meat4job.ui.menu.MenuActivity;

/**
 * Created by joanbarroso on 11/01/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MenuActivity menuActivity);
    void inject(MapsFragment mapsFragment);
}
