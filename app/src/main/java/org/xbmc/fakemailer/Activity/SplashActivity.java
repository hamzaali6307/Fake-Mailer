package org.xbmc.fakemailer.Activity;import androidx.appcompat.app.AppCompatActivity;import android.content.Intent;import android.os.Bundle;import android.os.Handler;import org.xbmc.fakemailer.R;public class SplashActivity extends AppCompatActivity {    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate ( savedInstanceState );        setContentView ( R.layout.activity_splash );        new Handler (  ).postDelayed ( new Runnable ( ) {            @Override            public void run() {                startActivity ( new Intent ( getApplicationContext (),MainActivity.class ) );                overridePendingTransition ( R.anim.slide_in, R.anim.slide_out );                finish ();            }        },2000 );    }}