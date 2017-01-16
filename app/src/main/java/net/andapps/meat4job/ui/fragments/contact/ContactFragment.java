package net.andapps.meat4job.ui.fragments.contact;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import net.andapps.meat4job.Meat4Job;
import net.andapps.meat4job.R;
import net.andapps.meat4job.ui.di.ActivityModule;
import net.andapps.meat4job.ui.di.DaggerActivityComponent;
import net.andapps.meat4job.ui.di.ViewModule;
import net.andapps.meat4job.ui.menu.MenuActivity;

import javax.inject.Inject;

public class ContactFragment extends Fragment implements ContactView {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.imageView)
    ImageView imageView;

    @Inject
    ContactPresenter presenter;

    private Context parentActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, rootView);
        initializeInjection();
        presenter.onStart();

        return rootView;
    }

    @Override
    public void initializeWebView(String authURL) {
        // Enable Javascript
        webView.loadUrl(authURL);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                presenter.onPageFinished(url);
            }
        });
    }

    @Override
    public void showPicture(String pictureUrl) {
        Picasso.with(getActivity()).load(pictureUrl).into(imageView);
        imageView.setVisibility(View.VISIBLE);
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
