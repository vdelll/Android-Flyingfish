package com.appli.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    /**
     * Initialisation du splash screen avec un thread de 5 secondes suivi du
     * passage sur l'activité principale
     */
    private void init() {

        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(5000);
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    Intent goToMain = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(goToMain);
                }
            }
        };

        thread.start();

    }

    /**
     * Ferme l'activité splach une fois l'activité main lancée
     */
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
