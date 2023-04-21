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

    private ImageView hero, wall1, wall2, wall3,wall4;
    private  int screenWidth, screenHeight, LocalX, LocalY, screenX, screenY,time;
    private int result = 0;
    private float wall1X,wall1Y,wall2X,wall2Y,wall3X,wall3Y,wall4X,wall4Y;
    boolean firstTouch = true;
    boolean TimerRunning = true;
    boolean resultFlag = true;
    boolean finishFlag = true;
    boolean touchFlag = false;
    boolean clearFlag = false;
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

    }
    public void wallMove1(){
        wall1Y += 10;
        if (wall1Y > screenHeight) {
            wall1X = (float)Math.floor(Math.random() * screenWidth-wall1.getWidth());
            wall1Y = -20;

        }
        wall1.setX(wall1X);
        wall1.setY(wall1Y);
        hitCheck(wall1,wall1X,wall1Y);
    }
    public void wallMove2(){
        wall2Y += 20;
        if (wall2Y > screenHeight) {
            wall2X = (float)Math.floor(Math.random() * screenWidth-wall2.getWidth());
            wall2Y = -20;

        }
        wall2.setX(wall2X);
        wall2.setY(wall2Y);
        hitCheck(wall2,wall2X,wall2Y);
    }
    public void wallMove3(){
        wall3Y += 15;
        if (wall3Y > screenHeight) {
            wall3X = (float)Math.floor(Math.random() * screenWidth-wall3.getWidth());
            wall3Y = -20;

        }
        wall3.setX(wall3X);
        wall3.setY(wall3Y);
        hitCheck(wall3,wall3X,wall3Y);
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
        result = 40-time;
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
                        wallMove1();
                        wallMove2();
                        wallMove3();
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
                        time = (int) millisUntilFinished / 1000;
                        ((TextView) findViewById(R.id.timeLabel)).setText("STAGE2 クリアまで" + time + "秒");
                    }
                }
                @Override
                public void onFinish() {
                    countDownTimer.cancel();
                    TimerRunning = false;
                    finishFlag = false;
                    result = 20 - time ;
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
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