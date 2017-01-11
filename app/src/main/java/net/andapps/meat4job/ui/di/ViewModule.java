package net.andapps.meat4job.ui.di;

import dagger.Module;
import dagger.Provides;
import net.andapps.meat4job.ui.fragments.maps.MapsView;
import net.andapps.meat4job.ui.menu.MenuView;

/**
 * Created by joanbarroso on 11/01/2017.
 */


@Module
public class ViewModule {


    private MenuView menuView;
    private MapsView mapsView;

    public ViewModule(MenuView menuView) {
        this.menuView = menuView;
    }

    public ViewModule(MapsView mapsView) {
        this.mapsView = mapsView;
    }
    @Provides
    MenuView providesMenuView() {
        return menuView;
    }

    @Provides
    MapsView providesMapsView() {
        return mapsView;
    }
}

