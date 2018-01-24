package com.etc.Game2048.vo;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout{
    public Card(Context context) {
        super(context);
        //TextView instance
        label=new TextView(getContext());
        //set properties of TextView
        label.setTextSize(32);
        //label.setTextColor(0xffffffff);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0x33ffff);
        //set layout
        LayoutParams layoutParams=new LayoutParams(-1,-1);
        layoutParams.setMargins(10,10,0,0);
        //add components
        addView(label,layoutParams);
        setNum(0);
    }
    //define TextView
    public TextView label;
    private int num=0;
    public String[] cardName= new String[12];

    public String getCardName(int num){
        cardName[0]="0";
        cardName[1]="Soldier";//2
        cardName[2]="Monitor";//4
        cardName[3]="Lieutenant";//8
        cardName[4]="Captain";//16
        cardName[5]="Major";//32
        cardName[6]="Colonel";//64
        cardName[7]="Brigadier";//128
        cardName[8]="Maj.general";//256
        cardName[9]="General";//512
        cardName[10]="Marshal";//1024
        cardName[11]="King";//2048

        if(num==0){
            return "";
        }
        else if(num==2){
            return cardName[1];
        }
        else if(num==4){
           return cardName[2];
        }
        else if(num==8){
            return cardName[3];
        }
        else if(num==16){
            return cardName[4];
        }
        else if(num==32){
            return cardName[5];
        }
        else if(num==64){
            return cardName[6];
        }
        else if(num==128){
            return cardName[7];
        }
        else if(num==256){
            return cardName[8];
        }
        else if(num==512){
            return cardName[9];
        }
        else if(num==1024){
            return cardName[10];
        }
        else if(num==2048){
            return cardName[11];
        }
        return "";
    }

    public int getNum() {

        return num;
    }

    public void setNum(int num) {


        this.num = num;

        if(num==0){
            label.setText("");
        }
        else{
            label.setText(getCardName(num));
        }


    }

    // compare card
    public boolean equals(Card card){
        return getNum()==card.getNum();
    }

}
