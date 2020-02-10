package com.appli.flyingfish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

/**
 * Vue contenant le poisson.
 */

public class FlyingFishView extends View {

    private Bitmap fish;
    private Bitmap background;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    private int w, h;

    /**
     * Constructeur de la vue du poisson.
     * 1 : Création du poisson à partir de l'image fish1 disponible en ressource.
     * 2 : Création du fond d'écran à partir de l'image background disponible en ressource.
     * 3 : Création de l'objet Paint pour la couleur du score.
     * 4 : Initialisation de la vie avec les images de coeur des ressources.
     *
     * @param context Le contexte dans lequel la vue du poisson sera.
     */
    public FlyingFishView(Context context) {
        super(context);

        //1
        fish = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);

        //2
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        //3
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        //4
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);
    }

    /**
     * Ajout des objets créés au canva dans l'ordre d'apparition
     * 1 : le fond d'écran
     * 2 : le poisson
     * 3 : le texte pour le score
     * 4 : Les trois coeurs de vie placés en valeurs absolues pour le moment
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1
        canvas.drawBitmap(background, 0, 0, null);

        //2
        canvas.drawBitmap(fish, 0, 0, null);

        //3
        canvas.drawText("Score : ", 20, 60, scorePaint);

        //4
        canvas.drawBitmap(life[0], 400, 10, null);
        canvas.drawBitmap(life[0], 475, 10, null);
        canvas.drawBitmap(life[0], 550, 10, null);

    }
}
