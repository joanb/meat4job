package net.andapps.meat4job.ui.di;

import dagger.Module;
import dagger.Provides;
import net.andapps.meat4job.ui.menu.MenuView;

/**
 * Created by joanbarroso on 11/01/2017.
 */


@Module
public class ViewModule {


    private final MenuView menuView;

    public ViewModule(MenuView menuView) {
        this.menuView = menuView;
    }

    @Provides
    MenuView providesMenuview() {
        return menuView;
    }
}

