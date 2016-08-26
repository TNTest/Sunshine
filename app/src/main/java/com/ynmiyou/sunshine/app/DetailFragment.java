package com.ynmiyou.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    private ShareActionProvider mShareActionProvider;
    public static String LOG_TAG = DetailFragment.class.getSimpleName();
    private String forecastStr = "";
    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        String message = intent.getStringExtra(ForecastFragment.DETAIL_MSG);
        ((TextView)rootView.findViewById(R.id.detail_text)).setText(message);
        forecastStr = message;
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detailfragment,menu);
        mShareActionProvider = new ShareActionProvider(getActivity());
        MenuItemCompat.setActionProvider(menu.findItem(R.id.action_share),mShareActionProvider);

        if (mShareActionProvider != null) {
            Log.v(LOG_TAG, "share provider: " +mShareActionProvider.toString());
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        } else {
            Log.w(LOG_TAG, "share provider is null!" );
        }
    }

    private Intent createShareForecastIntent() {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT,forecastStr + FORECAST_SHARE_HASHTAG);
                    return shareIntent;
                }



}
