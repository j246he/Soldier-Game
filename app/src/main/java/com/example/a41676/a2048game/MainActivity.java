package com.example.a41676.a2048game;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {
    //score
    public int score=0;
    public int maxScore;
    //component
    private TextView scoreTv,maxScoreTv;
    private  static MainActivity mainActivity=null;
    //constructor
    public MainActivity(){
        mainActivity=this;
    }
    public static MainActivity getMainActivity() {
        return  mainActivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Phone window
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //combine layout file
        setContentView(R.layout.gamestart);
        //get component
        scoreTv=(TextView)findViewById(R.id.score);
        maxScoreTv=(TextView)findViewById(R.id.highestscore);
    }
    public void showScore(){
        scoreTv.setText(score + " ");
    }
    public void addScore(int newScore){
        score+=newScore;
        showScore();
    }
    public void exit(){
        MainActivity.this.finish();
    }
    public void  clearScore(){
        score=0;
        showScore();
    }
}
