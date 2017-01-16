package net.andapps.meat4job.ui.fragments.tweets;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import net.andapps.meat4job.Meat4Job;
import net.andapps.meat4job.R;
import net.andapps.meat4job.ui.di.ActivityModule;
import net.andapps.meat4job.ui.di.DaggerActivityComponent;
import net.andapps.meat4job.ui.di.ViewModule;
import net.andapps.meat4job.ui.menu.MenuActivity;

import javax.inject.Inject;


public class TweetsFragment extends ListFragment implements TweetsView{

    private static final String MEAT_IS_HEALTHY_QUERY = "\"meat  is  healthy\"";
    @Inject
    TweetsPresenter presenter;
    private Context parentActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tweets, container, false);

        initializeInjection();
        presenter.onStart();

        return rootView;
    }

    @Override
    public void initializeTweetsAdapter() {
        final SearchTimeline searchTimeline =
                new SearchTimeline
                        .Builder()
                        .query(MEAT_IS_HEALTHY_QUERY)
                        .build();

        final TweetTimelineListAdapter adapter =
                new TweetTimelineListAdapter
                        .Builder(getActivity())
                        .setTimeline(searchTimeline)
                        .build();
        setListAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = context;
    }


    public void initializeInjection() {
        Meat4Job application = (Meat4Job) ((MenuActivity) parentActivity).getApplication();
        DaggerActivityComponent.builder()
                .applicationComponent(((MenuActivity) parentActivity).getApplicationComponent(application))
                .activityModule(new ActivityModule(this))
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
    }
}


