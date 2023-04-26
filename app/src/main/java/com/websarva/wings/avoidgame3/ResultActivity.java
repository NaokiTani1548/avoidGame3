package com.websarva.wings.avoidgame3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView result = findViewById(R.id.result);

        int score = getIntent().getIntExtra("Result",0);
            if(score == 0 || score == 65){
                result.setText("Congratulation!\n  Game Clear");
            }else {
                result.append("\n  " + score + "秒耐えた！");
            }

    }
    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}