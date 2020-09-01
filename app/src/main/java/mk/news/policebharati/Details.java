package mk.news.policebharati;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

public class Details extends AppCompatActivity {

    WebView webView;

    //ads
    private AdView mAdView;
    private PublisherInterstitialAd mPublisherInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_details );

        showAd();
        mPublisherInterstitialAd.setAdListener ( new AdListener ()
        {
            @Override
            public void onAdClosed() {
                mPublisherInterstitialAd.loadAd ( new PublisherAdRequest.Builder ().build () );
            }
        });


        webView = findViewById ( R.id.webview );
        webView.getSettings ().setJavaScriptEnabled ( true);
        webView.setWebViewClient ( new WebViewClient () );
         webView.loadUrl ( "file:///android_asset/nivad.html" );

    }

    public void showAd()
    {
        mPublisherInterstitialAd = new PublisherInterstitialAd (this);
        mPublisherInterstitialAd.setAdUnitId( getString ( R.string.admob_intestial_id) );
        mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
    }

    @Override
    public void onBackPressed() {
        if (mPublisherInterstitialAd.isLoaded()) {
            mPublisherInterstitialAd.show();
            mPublisherInterstitialAd.setAdListener(new AdListener () {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }else{
            super.onBackPressed();
        }

    }

}
