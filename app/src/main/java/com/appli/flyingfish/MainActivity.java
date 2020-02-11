package com.appli.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private FlyingFishView gameView;
    private Handler handler = new Handler();
    private final static long INTERVAL = 30;

    /**
     * Création de l'activité
     * 1 : Initialisation de la vue.
     * 2 : Minuteur qui recharge la vue à interval régulier.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1 : Initialisation de la vue
        gameView = new FlyingFishView(this);
        setContentView(gameView);

        //2 : Minuteur qui recharge la vue à interval régulier
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, INTERVAL);
    }
}
