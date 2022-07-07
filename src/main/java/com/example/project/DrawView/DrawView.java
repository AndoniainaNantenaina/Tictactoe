package com.example.project.DrawView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

class Case {
    public boolean isDrawed = false;
    public int top;
    public int bottom;
    public int left;
    public int right;
    public int c;
    public int centerx;
    public int centery;

    //  Constructor
    Case(
            boolean isDrawed,
            int top,
            int bottom,
            int left,
            int right,
            Color c,
            int centerx,
            int centery) {
        this.isDrawed = isDrawed;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.c = c;
        this.centerx = centerx;
        this.centery = centery;
    }
}

public class DrawView extends View {
    Paint paint = new Paint();
    Paint obj = new Paint();

    int touchX = 0, touchY = 0;

    ArrayList<Integer> tabX = new ArrayList<Integer>()
            , tabY = new ArrayList<Integer>();

    ArrayList<Case> TabCases = new ArrayList<Case>();

    public DrawView(Context context) {
        super(context);
    }
    public DrawView(
            Context context,
            @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.WHITE);
        obj.setColor(Color.GREEN);

        int bottom = 200;
        int pixel = 200;
        int right = 200;
        int left = 10;
        int initialleft = 10;
        int top = 10;
        int boardsize = 3;

        int z=0;

        //  Boucle de création des cases
        for (int i=0;i<(boardsize*boardsize);i++){

            while (z< boardsize){

                for (int y=0; y < boardsize; y++){

                    //  Draw Case
                    canvas.drawRect(
                            left,
                            top,
                            right,
                            bottom,
                            paint);

                    if (TabCases.size() < 9)
                    {
                        //  Instantiation de l'objet Case
                        Case c = new Case(false, top, bottom, left, right);

                        //  Ajouter dans le tableau
                        TabCases.add(c);
                    }
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(20);
                    left = left+pixel;
                    right = right+pixel;
                }

                z=z+1;
                left = initialleft;
                top = top+pixel;
                right= pixel;
                bottom= bottom+pixel;
            }
        }

        if (touchX != 0 && touchY != 0)
        {
            System.out.println("Nombre de clic : " + tabX.size());

            //  Itération sur les tableaux
            for (int tX = 0; tX < tabX.size(); tX++)
            {
                System.out.println("Coords : ");
                System.out.println("X : " + tabX.get(tX));
                System.out.println("Y : " + tabY.get(tX));

                obj.setStyle(Paint.Style.STROKE);
                obj.setStrokeWidth(15);
                if ((tX & 1) == 0) {
                    obj.setColor(Color.GREEN);
                    canvas.drawCircle(
                            tabX.get(tX),
                            tabY.get(tX),
                            50,
                            obj
                    );
                }
                else
                {
                    obj.setColor(Color.RED);
                    canvas.drawCircle(
                            tabX.get(tX),
                            tabY.get(tX),
                            50,
                            obj
                    );
                }
            }
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touchX = (int)event.getX();
            touchY = (int)event.getY();

            System.out.println("TouchX = " + touchX);
            System.out.println("TouchY = " + touchY);

            System.out.println("Nombre de case : " + TabCases.size());

            //  Itération sur le tableau de case
            for (int t = 0; t <= TabCases.size(); t++)
            {
                if (touchX <= TabCases.get(t).right
                    && touchX >= TabCases.get(t).left
                    && touchY <= TabCases.get(t).bottom
                    && touchY >= TabCases.get(t).top
                    && !TabCases.get(t).isDrawed)
                {
                    touchX = TabCases.get(t).left + ( (TabCases.get(t).right - TabCases.get(t).left) / 2);
                    touchY = TabCases.get(t).top + ( (TabCases.get(t).bottom - TabCases.get(t).top) / 2 );

                    //  Ajouter les coords dans les tableaux
                    tabX.add(touchX);
                    tabY.add(touchY);

                    TabCases.get(t).isDrawed = true;

                    invalidate();
                    return true;
                }
            }

            return true;
        }

        return true;
    }
}
