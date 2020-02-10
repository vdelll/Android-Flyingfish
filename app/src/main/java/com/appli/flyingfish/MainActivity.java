package com.appli.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private FlyingFishView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    /**
     * Initialisation de la vue avec le poisson.
     */
    private void init() {
        gameView = new FlyingFishView(this);
        setContentView(gameView);
    }
}
