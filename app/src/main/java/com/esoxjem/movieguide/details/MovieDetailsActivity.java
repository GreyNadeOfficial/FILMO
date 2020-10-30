package com.esoxjem.movieguide.details;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.esoxjem.movieguide.Constants;
import com.esoxjem.movieguide.Movie;
import com.esoxjem.movieguide.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MovieDetailsActivity extends AppCompatActivity {

    private static InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        mInterstitialAd = new InterstitialAd(this);

        //Main INT      cdea1744a45812d9        <-  TEST ID     5f10c20a2495ab0032397958
        // mInterstitialAd.setAdUnitId(getString(R.string.IntAds));

        //TEST INT
        mInterstitialAd.setAdUnitId(getString(R.string.TestInt));


        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
                                          public void onAdLoaded() {
                                            //  Toast.makeText(MovieDetailsActivity.this, "LOADED ADS", Toast.LENGTH_SHORT).show();
                                            //  mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                              // Code to be executed when an ad finishes loading.
                                          }

                                          public void onAdOpened() {
                                              // Code to be executed when the ad is displayed.
                                          }

                                          public void onAdClicked() {
                                              // Code to be executed when the user clicks on an ad.
                                          }

                                          public void onAdLeftApplication() {
                                              // Code to be executed when the user has left the app.
                                          }

                                          public void onAdClosed() {
                                             // mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                              // Code to be executed when the interstitial ad is closed.
                                          }
                                      }
        );



        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null && extras.containsKey(Constants.MOVIE)) {
                Movie movie = extras.getParcelable(Constants.MOVIE);
                if (movie != null) {
                    MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.getInstance(movie);
                    getSupportFragmentManager().beginTransaction().add(R.id.movie_details_container, movieDetailsFragment).commit();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "ADS NOT LOADED", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, " STOP", Toast.LENGTH_SHORT).show();
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
