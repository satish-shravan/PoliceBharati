package mk.news.policebharati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

public class About extends AppCompatActivity {

    ImageView youtube,telegram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_about );

        Objects.requireNonNull ( getSupportActionBar () ).setDisplayHomeAsUpEnabled ( true);

        telegram=(ImageView) findViewById ( R.id.telegram ) ;
        youtube=findViewById ( R.id.youtube );

        telegram.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText ( About.this, "Join Telegram !", Toast.LENGTH_SHORT ).show ();
                //open a browser with a link
                Intent externalIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/MKALLNEWS"));
                startActivity(externalIntent);
            }
        } );

        youtube.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText ( About.this, "Subscribe us on YouTube !", Toast.LENGTH_SHORT ).show ();
                //open a browser with a link
                Intent externalIntent = new Intent ( Intent.ACTION_VIEW, Uri.parse ( "https://www.youtube.com/channel/UCX1jcugvFOeoPKa81bZwxhw" ) );
                startActivity ( externalIntent );

            }
        } );

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId () == android.R.id.home)
        {
            finish ();
            return  true;
        }
        return super.onOptionsItemSelected ( item );
    }
}
