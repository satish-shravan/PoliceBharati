package mk.news.policebharati;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags( WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView ( R.layout.activity_splash_screen );

        new Handler (  ).postDelayed ( new Runnable () {
            @Override
            public void run() {
                startActivity ( new Intent ( SplashScreen.this,MainActivity.class ) );
                finish ();
            }
        },2500 );
    }
}
