package com.etc.Game2048.vo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import com.example.a41676.a2048game.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CardView extends GridLayout{
    private List<Point> emptyPoint=new ArrayList<Point>();
    private Card[][] cardMap=new Card[4][4];


    public CardView(Context context) {
        super(context);
        initGame();
    }
    public CardView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        initGame();
    }

    public CardView(Context context, AttributeSet attributeSet, int defStyle){
        super(context,attributeSet,defStyle);
        initGame();
    }

    public void initGame(){



        //game layout
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new View.OnTouchListener() {
            private float startX,startY,offsetX,offsetY;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()){
                    //figure touch
                    case MotionEvent.ACTION_DOWN:
                        startX=event.getX();
                        startY=event.getY();
                        break;
                    //figure up
                    case MotionEvent.ACTION_UP:
                        offsetX=event.getX()-startX;
                        offsetY=event.getY()-startY;
                        if (Math.abs(offsetX)>Math.abs(offsetY)){
                            if(offsetX<-5){
                                swiptLeft();
                            }
                            else if (offsetX>5){
                                swiptRight();
                            }
                        }
                        else {
                            if (offsetY<-5){
                                swiptUp();
                            }
                            if (offsetY>5){
                                swiptBottom();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int cardWidth=(Math.min(w,h)-10)/4;
        addCard(cardWidth,cardWidth);
        startGame();
    }

    public void startGame() {
        MainActivity.getMainActivity().clearScore();
        //the num on the card
        for (int y=0;y<4;y++){
            for (int x=0;x<4;x++){
                cardMap[x][y].setNum(0);
            }
        }
        addRandomNum();
    }

    public void addRandomNum() {
        //clear the point
        emptyPoint.clear();
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                if(cardMap[x][y].getNum()==0){
                    //append x,y to the list emptyPoint
                    emptyPoint.add(new Point(x,y));
                }
            }
        }
        Point p=emptyPoint.remove((int)(Math.random()*emptyPoint.size()));
        cardMap[p.x][p.y].setNum(Math.random()>0.25?2:4);
        for (int y=0;y<4;y++){
            for (int x=0;x<4;x++){
                if (cardMap[x][y].getNum()==0){
                    cardMap[x][y].label.setBackgroundColor(0x33ffffff);
                }
                else if (cardMap[x][y].getNum()==2){
                    cardMap[x][y].label.setBackgroundColor(0xffeee4da);
                    cardMap[x][y].label.setTextSize(20);
                }
                else if (cardMap[x][y].getNum()==4){
                    cardMap[x][y].label.setBackgroundColor(0xffece0c8);
                    cardMap[x][y].label.setTextSize(18);
                }
                else if (cardMap[x][y].getNum()==8){
                    cardMap[x][y].label.setBackgroundColor(0xfff2b179);
                    cardMap[x][y].label.setTextSize(20);
                }
                else if (cardMap[x][y].getNum()==16){
                    cardMap[x][y].label.setBackgroundColor(0xffec8d54);
                    cardMap[x][y].label.setTextSize(18);
                }
                else if (cardMap[x][y].getNum()==32){
                    cardMap[x][y].label.setBackgroundColor(0xfff57c5f);
                    cardMap[x][y].label.setTextSize(24);
                }
                else if (cardMap[x][y].getNum()==64){
                    cardMap[x][y].label.setBackgroundColor(0xfffa5d3c);
                    cardMap[x][y].label.setTextSize(18);
                }
                else if (cardMap[x][y].getNum()==128){
                    cardMap[x][y].label.setBackgroundColor(0xffedce71);
                    cardMap[x][y].label.setTextSize(14);
                }
                else if (cardMap[x][y].getNum()==256){
                    cardMap[x][y].label.setBackgroundColor(0xffedcc61);
                    cardMap[x][y].label.setTextSize(16);
                }
                else if (cardMap[x][y].getNum()==512){
                    cardMap[x][y].label.setBackgroundColor(0xffecc850);
                    cardMap[x][y].label.setTextSize(18);
                }
                else if (cardMap[x][y].getNum()==1024){
                    cardMap[x][y].label.setBackgroundColor(0xffedc53f);
                    cardMap[x][y].label.setTextSize(16);
                }
                else if (cardMap[x][y].getNum()==2048){
                    cardMap[x][y].label.setBackgroundColor(0xffe0ba0);
                    cardMap[x][y].label.setTextSize(24);
                }
                /*
                else if (cardMap[x][y].getNum()==4096){
                    cardMap[x][y].label.setBackgroundColor(0xffecc403);
                    cardMap[x][y].label.setTextSize(24);
                }
                else if (cardMap[x][y].getNum()==8192){
                    cardMap[x][y].label.setBackgroundColor(0xffaab767);
                    cardMap[x][y].label.setTextSize(24);
                }
                */
            }
        }
    }

    //initialize 16 cards
    public void addCard(int cardWidth, int cardHeight) {
        Card c;
        for(int y=0;y<4;y++){
            for (int x=0;x<4;x++){
                c=new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);
                cardMap[x][y]=c;
            }
        }
    }

    //game finishes
    public void checkComplete(){
        boolean complete=true;
        ALL:
            for(int y=0;y<4;y++){
                for(int x=0;x<4;x++){
                    //check if the game finises
                    if(cardMap[x][y].getNum()==0||
                            (x>0&&cardMap[x][y].equals(cardMap[x-1][y]))||
                            (x<3&&cardMap[x][y].equals(cardMap[x+1][y]))||
                            (y>0&&cardMap[x][y].equals(cardMap[x][y-1]))||
                            (y<3&&cardMap[x][y].equals(cardMap[x][y+1]))){
                        complete=false;
                        break ALL;
                    }
                }
            }
            if (complete){
                //when the game finishes, show the dialog
                new AlertDialog.Builder(getContext())
                        .setTitle("Hello")
                        .setMessage("game over, your score is"+MainActivity.getMainActivity().score)
                        .setPositiveButton("new game", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startGame();
                            }
                        })
                        .setNegativeButton("exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.getMainActivity().exit();
                            }
                        })
                        .show();
            }
    }

    public void swiptLeft(){
        boolean isadd=false;
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                for(int x1=x+1;x1<4;x1++){
                    //if the right one has num and the left one does not has num,
                    // the right num give num to the left one and set the right num=0
                    if(cardMap[x1][y].getNum()>0){
                        if(cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x--;
                            isadd=true;
                        }
                        else if(cardMap[x][y].equals(cardMap[x1][y])){
                            cardMap[x][y].setNum(cardMap[x][y].getNum()*2);
                            cardMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            isadd=true;
                        }
                        break;
                    }
                }
            }
        }
        if(isadd){
            addRandomNum();
            checkComplete();
        }
    }

    public void swiptRight(){
        boolean isadd=false;
        for(int y=0;y<4;y++){
            for(int x=3;x>=0;x--){
                for(int x1=x-1;x1>=0;x1--){
                    //if the left one has num and the right one does not has num,
                    // the left num give num to the right one and set the left num=0
                    if(cardMap[x1][y].getNum()>0){
                        if(cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x++;
                            isadd=true;
                        }
                        else if(cardMap[x][y].equals(cardMap[x1][y])){
                            cardMap[x][y].setNum(cardMap[x1][y].getNum()*2);
                            cardMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            isadd=true;
                        }
                        break;
                    }
                }
            }
        }
        if(isadd){
            addRandomNum();
            checkComplete();
        }
    }

    public void swiptUp(){
        boolean isadd=false;
        for(int x=0;x<4;x++){
            for(int y=0;y<4;y++){
                for(int y1=y+1;y1<4;y1++){
                    //when the bottom has the num and the up has no num,
                    //give the num to the up
                    if(cardMap[x][y1].getNum()>0){
                        if(cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y--;
                            isadd=true;
                        }
                        else if(cardMap[x][y].equals(cardMap[x][y1])){
                            cardMap[x][y].setNum(cardMap[x][y1].getNum()*2);
                            cardMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            isadd=true;
                        }
                        break;
                    }
                }
            }
        }
        if(isadd){
            addRandomNum();
            checkComplete();
        }

    }

    public void swiptBottom(){
        boolean isadd=false;
        for(int x=0;x<4;x++){
            for(int y=3;y>=0;y--){
                for(int y1=y-1;y1>=0;y1--) {
                    //if the up one has num and the bottom one does not has num,
                    // the up num give num to the bottom one and set the left num=0
                    if(cardMap[x][y1].getNum()>0){
                        if(cardMap[x][y].getNum()<=0){
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y++;
                            isadd=true;
                        }
                        else if(cardMap[x][y].equals(cardMap[x][y1])){
                            cardMap[x][y].setNum(cardMap[x][y1].getNum()*2);
                            cardMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            isadd=true;
                        }
                        break;
                    }
                }
            }
        }
        if(isadd){
            addRandomNum();
            checkComplete();
        }
    }

}

