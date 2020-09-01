package mk.news.policebharati;



import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VideoGallary extends AppCompatActivity {
    RecyclerView recyclerView;


    DatabaseReference mdataref;

    ProgressDialog progressDialog;

    List<VideoData> list;

    LinearLayoutManager ly;

    //ads
    private AdView mAdView;
    private PublisherInterstitialAd mPublisherInterstitialAd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_video_gallary );

        getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );


        //ads
        AdView adView = new AdView ( this );
        adView.setAdSize ( AdSize.BANNER );
        mAdView = (AdView) findViewById ( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder ().build ();
        mAdView.loadAd ( adRequest );
        //call interstial ad
        showAd();
        mPublisherInterstitialAd.setAdListener ( new AdListener ()
        {
            @Override
            public void onAdClosed() {
                mPublisherInterstitialAd.loadAd ( new PublisherAdRequest.Builder ().build () );
            }
        });



        recyclerView = findViewById ( R.id.recyclerView );

        ly = new LinearLayoutManager ( this );
        ly.setStackFromEnd ( true );
        ly.setReverseLayout ( true );

        recyclerView.setLayoutManager ( ly );

        progressDialog = new ProgressDialog ( this );

        list = new ArrayList<> (  );

        //display progress dialog while fetching images
        progressDialog.setMessage ( "Feching data " + "\nEnsure Mobile data is ON" );
        progressDialog.setCancelable ( false );
        progressDialog.show ();

        mdataref = FirebaseDatabase.getInstance ().getReference ("Videos");


        mdataref.addValueEventListener ( new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss ();

                for(DataSnapshot postdata : dataSnapshot.getChildren ())
                {
                    VideoData videoData = postdata.getValue (VideoData.class);
                    list.add ( videoData );
                }
                //set the value in the adapter
                recyclerView.setAdapter ( new VideoAdapter ( getApplicationContext (),list) );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss ();
            }
        } );





    }

    public void showAd()
    {
        mPublisherInterstitialAd = new PublisherInterstitialAd(this);
        mPublisherInterstitialAd.setAdUnitId( getString ( R.string.admob_intestial_id ) );
        mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId () == android.R.id.home)
        {
            finish ();
            return  true;
        }
        return super.onOptionsItemSelected ( item );
    }

}
