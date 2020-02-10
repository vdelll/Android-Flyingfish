package com.appli.flyingfish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

/**
 * Vue contenant le poisson.
 */

public class FlyingFishView extends View {

    // poisson, sa position, et sa vitesse
    private Bitmap fish[] = new Bitmap[2];
    private int fishX = 10;
    private int fishY;
    private int fishSpeed;

    // Dimensions du canvas
    private int canvasWidth, canvasHeight;

    private boolean touch = false;

    // fond d'écran
    private Bitmap background;

    // Couleur du score
    private Paint scorePaint = new Paint();

    // Coeurs de vie
    private Bitmap life[] = new Bitmap[2];

    /**
     * Constructeur de la vue du poisson.
     * 1 : Création du poisson à partir des deux images disponible en ressource.
     * 2 : Création du fond d'écran à partir de l'image background disponible en ressource.
     * 3 : Création de l'objet Paint pour la couleur du score.
     * 4 : Initialisation de la vie avec les images de coeur des ressources.
     * 5 : Initialisation de la valeur de position Y de départ du poisson
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

    }

    /**
     * Ajout des objets créés au canva dans l'ordre d'apparition
     * 0 : Récupération des dimensions du canvas
     * 1 : le fond d'écran
     * 1.1 : Initialisation de la position Y minimum et maximum du poisson
     * 2 : Action lorsque le joueur touche l'ecran
     * 3 : le texte pour le score
     * 4 : Les trois coeurs de vie placés en valeurs absolues pour le moment
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

        //3 : le texte pour le score
        canvas.drawText("Score : ", 20, 60, scorePaint);

        //4 : Les trois coeurs de vie placés en valeurs absolues pour le moment
        canvas.drawBitmap(life[0], 400, 10, null);
        canvas.drawBitmap(life[0], 475, 10, null);
        canvas.drawBitmap(life[0], 550, 10, null);

    }

    /**
     * Evenement lorsque le joueur touche l'écran
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
