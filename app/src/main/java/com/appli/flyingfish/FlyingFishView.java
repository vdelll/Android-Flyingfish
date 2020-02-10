package com.appli.flyingfish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Vue du jeu.
 */

public class FlyingFishView extends View {

    // poisson, sa position, et sa vitesse
    private Bitmap fish[] = new Bitmap[2];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;

    // Dimensions du canvas
    private int canvasWidth, canvasHeight;

    // Caractéristiques des boules jaunes
    private int yellowX, yellowY, yellowSpeed = 16;
    private Paint yellowPaint = new Paint();

    // Caractéristiques des boules vertes
    private int greenX, greenY, greenSpeed = 20;
    private Paint greenPaint = new Paint();

    // Caractéristiques des boules vertes
    private int redX, redY, redSpeed = 25;
    private Paint redPaint = new Paint();

    // Booleen pour le toucher de l'écran
    private boolean touch = false;

    // fond d'écran
    private Bitmap background;

    // Couleur du score
    private Paint scorePaint = new Paint();

    // Coeurs de vie
    private Bitmap life[] = new Bitmap[2];

    // score
    private int score;

    // nombre de vie du poisson
    private int nbOfLife;

    /**
     * Constructeur de la vue du jeu.
     * 1 : Création du poisson à partir des deux images disponible en ressource.
     * 2 : Création du fond d'écran à partir de l'image background disponible en ressource.
     * 3 : Création de l'objet Paint pour la couleur du score.
     * 4 : Initialisation de la vie avec les images de coeur des ressources.
     * 5 : Initialisation de la valeur de position Y de départ du poisson
     * 6 : Propriétés des boules jaunes.
     * 7 : Propriétés des boules vertes
     * 7.1 : Propriétés des boules vertes
     * 8 : Initialisation du score à 0.
     * 9 : Initialisation du nombre de vie du poisson à 3
     *
     * @param context Le contexte dans lequel la vue du poisson sera.
     */
    public FlyingFishView(Context context) {
        super(context);

        //1 : Création du poisson à partir des deux images disponible en ressource.
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        //2 : Création du fond d'écran à partir de l'image background disponible en ressource.
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        //3 : Création de l'objet Paint pour la couleur du score.
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        //4 : Initialisation de la vie avec les images de coeur des ressources.
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        //5 : Initialisation de la valeur de position Y de départ du poisson
        fishY = 550;

        //6 : Propriétés des boules jaunes
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        //7 : Propriétés des boules vertes
        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        //7.1 : Propriétés des boules vertes
        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        //8 : Initialisation du score à 0
        score = 0;

        //9 : Initialisation du nombre de vie du poisson à 3
        nbOfLife = 3;

    }

    /**
     * Ajout des objets créés au canva dans l'ordre d'apparition
     * 0 : Récupération des dimensions du canvas
     * 1 : le fond d'écran
     * 1.1 : Initialisation de la position Y minimum et maximum du poisson
     * 2 : Action lorsque le joueur touche l'ecran
     * 2.1 : Paramétrage des boules jaunes
     * 2.2 : Paramétrage des boules vertes
     * 2.3 : Paramétrage des boules rouges
     * 3 : le texte pour le score
     * 4 : Les trois coeurs de vie et remplacement de l'image lorsque le joueur touche une boule rouge
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //0 : Récupération des dimensions du canvas
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        //1 : le fond d'écran
        canvas.drawBitmap(background, 0, 0, null);

        //1.1 : Initialisation de la position Y minimum et maximum du poisson
        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;

        if (fishY < minFishY) {
            fishY = minFishY;
        }
        if (fishY > maxFishY) {
            fishY = maxFishY;
        }

        fishSpeed = fishSpeed + 2;

        //2 : Action lorsque le joueur touche l'ecran
        if (touch) {
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;
        } else {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }

        //2.1 : Paramétrage des boules jaunes
        yellowX = yellowX - yellowSpeed;

        if (hitBallChecker(yellowX, yellowY)) {
            score = score + 10;
            yellowX = -100;
        }

        if (yellowX < 0) {
            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(yellowX, yellowY, 25, yellowPaint);

        //2.2 : Paramétrage des boules vertes
        greenX = greenX - greenSpeed;

        if (hitBallChecker(greenX, greenY)) {
            score = score + 20;
            greenX = -100;
        }

        if (greenX < 0) {
            greenX = canvasWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(greenX, greenY, 25, greenPaint);

        //2.3 : Paramétrage des boules rouges
        redX = redX - redSpeed;

        if (hitBallChecker(redX, redY)) {
            redX = -100;
            nbOfLife--;

            if (nbOfLife == 0) {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();

                // Redirige vers l'activité game over si le joueur n'a plus de vie
                Intent goToGameOver = new Intent(getContext(), GameOverActivity.class);
                goToGameOver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                goToGameOver.putExtra("score", score);
                getContext().startActivity(goToGameOver);
            }
        }

        if (redX < 0) {
            redX = canvasWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(redX, redY, 30, redPaint);

        //3 : le texte pour le score
        canvas.drawText("Score : " + score, 20, 60, scorePaint);

        //4 : Les trois coeurs de vie et remplacement de l'image lorsque le joueur touche une boule rouge
        for (int i = 0; i < 3; i++) {
            int x = (int) (475 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < nbOfLife) {
                canvas.drawBitmap(life[0], x, y, null);
            } else {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
    }

    /**
     * Methode permettant de contrôler si une boule touche le poisson
     *
     * @param x position X de l'élement à tester par rapport à l'emplacement du poisson
     * @param y position Y de l'élement à tester par rapport à l'emplacement du poisson
     * @return vrai si touché, faux si pas touché
     */
    public boolean hitBallChecker(int x, int y) {
        if (fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight())) {
            return true;
        }
        return false;
    }

    /**
     * Evenement lorsque le joueur touche l'écran permettant de faire remonter le poisson
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch = true;
            fishSpeed = -22;
        }
        return true;
    }
}
