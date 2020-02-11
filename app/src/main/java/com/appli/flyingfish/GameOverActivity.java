package com.appli.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    // Bouton pour rejouer
    private Button btnPlayAgain;

    // score réalisés
    private TextView txtScore;

    private String score;


    /**
     * Création de l'activité Game Over
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // Récupération du score effectué
        score = getIntent().getExtras().get("score").toString();

        // Initialisation des objets graphiques
        btnPlayAgain = findViewById(R.id.btn_play_again);
        txtScore = findViewById(R.id.txt_score);

        // Action sur le clique du bouton rejouer pour lancer l'activité du jeu
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGame = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(goToGame);
            }
        });

        // Ajout du score effectué à la liste des scores
        txtScore.setText("Score = " + score);
    }

    /**
     * Ferme l'activité game over une fois l'activité main lancée
     */
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
