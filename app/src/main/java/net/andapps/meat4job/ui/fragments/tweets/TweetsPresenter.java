/*
Created by Helm  16/01/2017.
*/


package net.andapps.meat4job.ui.fragments.tweets;

import javax.inject.Inject;

public class TweetsPresenter {


    private final TweetsView view;

    @Inject
    public TweetsPresenter(TweetsView view) {
        this.view = view;
    }

    public void onStart() {
        view.initializeTweetsAdapter();
    }
}
