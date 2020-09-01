package mk.news.policebharati;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

public class MainActivity extends AppCompatActivity {

    CardView video,qna,currentaffair,details,new_qna,share,contact;
    TextView scroll;
    ImageView youtube,telegram;

    //ads
    private PublisherInterstitialAd mPublisherInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );


        //ads
        showAd ();

        mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
        mPublisherInterstitialAd.show ();


        mPublisherInterstitialAd.setAdListener(new AdListener () {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mPublisherInterstitialAd.loadAd(new PublisherAdRequest.Builder().build());
            }

        });


        scroll = findViewById ( R.id.text );
        scroll.setSelected ( true );

        video = findViewById ( R.id.videos );
        qna=findViewById ( R.id.QNA );
        currentaffair = findViewById ( R.id.currentAffair );
        details = findViewById ( R.id.details );
        share = findViewById ( R.id.shareapp );
        contact = findViewById ( R.id.contact);


        video.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if(mPublisherInterstitialAd.isLoaded ())
                {
                    mPublisherInterstitialAd.show ();
                    mPublisherInterstitialAd.setAdListener ( new AdListener ()
                    {
                        @Override
                        public void onAdClosed() {
                            startActivity ( new Intent ( getApplicationContext (), VideoGallary.class ) );
                        }
                    });
                }
                else {
                    Intent intent = new Intent ( getApplicationContext (), VideoGallary.class );
                    startActivity ( intent );
                }

            }
        } );
        qna.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity ( new Intent ( MainActivity.this,QNA.class) );

            }
        } );
        currentaffair.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(mPublisherInterstitialAd.isLoaded ())
                {
                    mPublisherInterstitialAd.show ();
                    mPublisherInterstitialAd.setAdListener ( new AdListener ()
                    {
                        @Override
                        public void onAdClosed() {
                            startActivity ( new Intent ( getApplicationContext (), CurrentAffairs.class ) );
                        }
                    });
                }
                else {
                    Intent intent = new Intent ( getApplicationContext (), CurrentAffairs.class );
                    startActivity ( intent );
                }

            }
        } );
        details.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity ( new Intent ( MainActivity.this,Details.class) );
            }
        } );
        contact.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(mPublisherInterstitialAd.isLoaded ())
                {
                    mPublisherInterstitialAd.show ();
                    mPublisherInterstitialAd.setAdListener ( new AdListener ()
                    {
                        @Override
                        public void onAdClosed() {
                            startActivity ( new Intent ( getApplicationContext (), About.class ) );
                        }
                    });
                }
                else {
                    Intent intent = new Intent ( getApplicationContext (), About.class );
                    startActivity ( intent );
                }
            }
        } );

        share.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Toast.makeText ( MainActivity.this,"THANK YOU :)",Toast.LENGTH_LONG ).show ();
                Intent share1=new Intent ( Intent.ACTION_SEND );
                final String pack=getApplicationContext ().getPackageName ();
                String applink1="";

                try
                {
                    applink1="https://play.google.com/store/apps/details?id="+pack;
                }
                catch (android.content.ActivityNotFoundException anfe)
                {
                    applink1="https://play.google.com/store/apps/details?id="+pack;
                }
                share1.setType ( "text/link" );
                String body="\n"+"M K ALL NEWS ह्या  अँप्लिकेशन चा महत्वाचा" +
                        "उद्देश हा आपल्याला ,महाराष्ट्र शासन यांच्या" +
                        "सरकारी नोकरी च्या जाहिराती सर्वप्रथम ,खऱ्या व विस्तृत" +
                        "स्वरूपात पोहचिवणे आहे." +
                        "महत्त्वाचं म्हणजे महाराष्ट्र पोलीस भरती संपूर्ण माहिती...\n\n" +
                        "ह्या छोट्याश्या प्रयत्नात आपली साथ मोलाची आहे।।"+"\n\n"+ " Youtube: " +"\n"+ "https://www.youtube.com/channel/UCX1jcugvFOeoPKa81bZwxhw  " +"\n\n"+ " Telegram " +"\n"+ "https://t.me/MKALLNEWS " +"\n\n"+"आपला M K ALL NEWS...."+"\n\n "+applink1+ "\n★पोलीस भरती★";
                String sub=" ";
                share1.putExtra ( Intent.EXTRA_SUBJECT,sub );
                share1.putExtra ( Intent.EXTRA_TEXT,body );
                startActivity ( Intent.createChooser ( share1,"शेअर करा" ) );

            }
        } );


        telegram=findViewById ( R.id.telegram ) ;
        youtube=findViewById ( R.id.youtube );

        telegram.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText ( MainActivity.this, "Join Telegram !", Toast.LENGTH_SHORT ).show ();
                //open a browser with a link
                Intent externalIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/MKALLNEWS"));
                startActivity(externalIntent);
            }
        } );

        youtube.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText ( MainActivity.this, "Subscribe us on YouTube !", Toast.LENGTH_SHORT ).show ();
                //open a browser with a link
                Intent externalIntent = new Intent ( Intent.ACTION_VIEW, Uri.parse ( "https://www.youtube.com/channel/UCX1jcugvFOeoPKa81bZwxhw" ) );
                startActivity ( externalIntent );

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
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        final  View custom = getLayoutInflater ().inflate ( R.layout.exit_alert,null );
        builder.setView ( custom );

        builder.setPositiveButton("बाहेर पडा", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish ();
            }
        });
        builder.setNegativeButton("Telegram",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent ( Intent.ACTION_VIEW, Uri.parse ( "https://t.me/MKALLNEWS" ) );
                startActivity ( intent );
            }
        });

        builder.setNeutralButton ( "रेटिंग करा", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent ( Intent.ACTION_VIEW, Uri.parse ( "https://play.google.com/store/apps/details?id=mk.news.policebharati" ) );
                startActivity ( intent );
            }
        } );
        AlertDialog alert=builder.create();
        alert.show();
    }
}
