package com.websarva.wings.avoidgame3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondStage extends AppCompatActivity implements View.OnTouchListener {

    private ImageView hero, wall1, wall2, wall3,wall4,wall5,wall6,wall7,wall8,wall9,wall10, wall11, wall12,wall13,wall14;
    private  int screenWidth, screenHeight, LocalX, LocalY, screenX, screenY;
    private int result = 0;
    private float wall1X,wall1Y,wall2X,wall2Y,wall3X,wall3Y,wall4X,wall4Y,time;
    boolean firstTouch = true;
    boolean TimerRunning = true;
    boolean resultFlag = true;
    boolean finishFlag = true;
    boolean touchFlag = false;
    private float wallMoveX,wallMoveY;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hero = findViewById(R.id.hero);
        hero.setOnTouchListener(this);
        wall1 = findViewById(R.id.wall1);
        wall2 = findViewById(R.id.wall2);
        wall3 = findViewById(R.id.wall3);
        wall4 = findViewById(R.id.wall4);
        wall5 = findViewById(R.id.wall5);
        wall6 = findViewById(R.id.wall6);
        wall7 = findViewById(R.id.wall7);
        wall8 = findViewById(R.id.wall8);
        wall9 = findViewById(R.id.wall9);
        wall10 = findViewById(R.id.wall10);
        wall11 = findViewById(R.id.wall11);
        wall12 = findViewById(R.id.wall12);
        wall13 = findViewById(R.id.wall13);
        wall14 = findViewById(R.id.wall14);

        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;
        wall1.setY(screenHeight);
        wall2.setY(screenHeight);
        wall3.setY(screenHeight);
        wall4.setY(screenHeight);
        wall5.setY(screenHeight);
        wall6.setY(screenHeight);
        wall7.setY(screenHeight);
        wall8.setY(screenHeight);
        wall8.setX(screenWidth - wall8.getWidth());
        wall9.setY(screenHeight);
        wall9.setX(screenWidth - wall9.getWidth());
        wall10.setY(screenHeight);
        wall10.setX(screenWidth - wall10.getWidth());
        wall11.setY(screenHeight);
        wall11.setX(screenWidth - wall11.getWidth());
        wall12.setY(screenHeight);
        wall13.setY(screenHeight);
        wall14.setY(screenHeight);

    } public float wallMoveX(float wallX,float wallY,int speed,ImageView wall){
        wallY += speed;
        if (wallY > screenHeight) {
            wallX = (float)Math.floor(Math.random() * screenWidth-wall.getWidth());
            wallY = -20;

        }
        wallMoveX= wallX;

        return wallMoveX;
    }
    public float wallMoveY(float wallX,float wallY,int speed,ImageView wall){
        wallY += speed;
        if (wallY > screenHeight) {
            wallX = (float)Math.floor(Math.random() * screenWidth-wall.getWidth());
            wallY = -20;

        }
        wallMoveY= wallY;
        return wallMoveY;
    }

    public void wallMove(){
        wall1.setX(wallMoveX(wall1.getX(),wall1.getY(),10,wall1));
        wall1.setY(wallMoveY(wall1.getX(),wall1.getY(),10,wall1));
        hitCheck(wall1,wall1.getX(),wall1.getY());
        wall2.setX(wallMoveX(wall2.getX(),wall2.getY(),20,wall2));
        wall2.setY(wallMoveY(wall2.getX(),wall2.getY(),20,wall2));
        hitCheck(wall2,wall2.getX(),wall2.getY());
        wall3.setX(wallMoveX(wall3.getX(),wall3.getY(),15,wall3));
        wall3.setY(wallMoveY(wall3.getX(),wall3.getY(),15,wall3));
        hitCheck(wall3,wall3.getX(),wall3.getY());
    }

    public void wallMove4(){
        wall4Y += 8;
        if (wall4Y > screenHeight) {
            wall4Y = -20;

        }
        wall4.setX(wall4X);
        wall4.setY(wall4Y);
        hitCheckToStop(wall4,wall4X,wall4Y);
    }
    public void hitCheck(ImageView wall,float wallX,float wallY){
        float wallLeftX = wallX;
        float wall114 = wallX + wall.getWidth() / 4;
        float wallMiddleX = wallX + wall.getWidth() / 2;
        float wall134 = wallX + wall.getWidth() / 4 * 3;
        float wallUnderX = wallX + wall.getWidth();

        if(hero.getTop() < wallY && wallY < hero.getTop() + hero.getHeight()){
            if(hero.getLeft() < wallLeftX && wallLeftX < hero.getLeft() + hero.getWidth()
                    ||hero.getLeft() < wallMiddleX && wallMiddleX < hero.getLeft() + hero.getWidth()
                    ||hero.getLeft() < wallUnderX && wallUnderX < hero.getLeft() + hero.getWidth()
                    || hero.getLeft() < wall114 && wall114 < hero.getLeft() + hero.getWidth()
                    || hero.getLeft() < wall134 && wall134 < hero.getLeft() + hero.getWidth()){
                try {
                    resetTimer();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void hitCheckToStop(ImageView wall,float wallX,float wallY) {
        float wallLeftX = wallX;
        float wall116 = wallX + wall.getWidth() / 6;
        float wall113 = wallX + wall.getWidth() / 3;
        float wallMiddleX = wallX + wall.getWidth() / 2;
        float wall123 = wallX + wall.getWidth() / 3 * 2;
        float wall156 = wallX + wall.getWidth() / 6 * 5;
        float wallUnderX = wallX + wall.getWidth();

        if (hero.getTop() < wallY && wallY < hero.getTop() + hero.getHeight()) {
            if (hero.getLeft() < wallLeftX && wallLeftX < hero.getLeft() + hero.getWidth()
                    || hero.getLeft() < wallMiddleX && wallMiddleX < hero.getLeft() + hero.getWidth()
                    || hero.getLeft() < wallUnderX && wallUnderX < hero.getLeft() + hero.getWidth()
                    || hero.getLeft() < wall116 && wall116 < hero.getLeft() + hero.getWidth()
                    || hero.getLeft() < wall113 && wall113 < hero.getLeft() + hero.getWidth()
                    || hero.getLeft() < wall123 && wall123 < hero.getLeft() + hero.getWidth()
                    || hero.getLeft() < wall156 && wall156 < hero.getLeft() + hero.getWidth()) {
                if (touchFlag) {
                    try {
                        resetTimer();
                        ((TextView) findViewById(R.id.timeLabel)).setText("エラー");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void resetTimer(){
        result = 40-(int)time;
        if(resultFlag) {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("Result", result);
            countDownTimer.cancel();
            TimerRunning = false;
            startActivity(intent);
            resultFlag = false;
            finish();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(firstTouch){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(finishFlag){
                        wallMove();
                        wallMove4();
                        if(!TimerRunning){
                            ((TextView) findViewById(R.id.timeLabel)).setText("エラー");
                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        if (firstTouch) {
            countDownTimer  = new CountDownTimer(20000, 100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if(TimerRunning) {
                        time = (float) (millisUntilFinished / 100) / 10;
                        ((TextView) findViewById(R.id.timeLabel)).setText("STAGE2 クリアまで" + time + "秒");
                    }
                }
                @Override
                public void onFinish() {
                    countDownTimer.cancel();
                    TimerRunning = false;
                    finishFlag = false;
                    result = 20 - (int)time;
                    Intent intent = new Intent(getApplicationContext(), ThirdStage.class);
                    getIntent().getIntExtra("Result",result);
                    startActivity(intent);
                    finish();
                }
            }.start();
        }


        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                LocalX = hero.getLeft();
                LocalY = hero.getTop();

                screenX = x;
                screenY = y;
                firstTouch = false;
                touchFlag = true;


                break;

            case MotionEvent.ACTION_MOVE:

                int diffX = screenX - x;
                int diffY = screenY - y;

                LocalX -= diffX;
                LocalY -= diffY;

                hero.layout(LocalX,
                        LocalY,
                        LocalX + hero.getWidth(),
                        LocalY + hero.getHeight());

                screenX = x;
                screenY = y;


                break;

            case MotionEvent.ACTION_UP:

                touchFlag = false;
        }
        return true;
    }

}