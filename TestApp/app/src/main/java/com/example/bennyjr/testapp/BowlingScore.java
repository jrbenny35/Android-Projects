package com.example.bennyjr.testapp;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by bennyjr on 2/1/16.
 */
public class BowlingScore {

    private int game1;
    private int game2;
    private int game3;
    private int game;
    private Date date;
    private Context context;


    public BowlingScore(int game1, int game2, int game3, Date date, Context context) {
        this.game1 = game1;
        this.game2 = game2;
        this.game3 = game3;
        this.date = date;
        this.context = context;
    }

    public BowlingScore(){

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getGame1() {
        return game1;
    }

    public void setGame1(int game1) {
        if(game1 >= 0 && game1 <= 300)
            this.game1 = game1;
        else{
            game = 1;
            showError(game);
        }

        Log.d("BowlingScore Object", "Added game 1");
    }

    public int getGame2() {
        return game2;
    }

    public void setGame2(int game2) {
        if(game2 >= 0 && game2 <= 300)
            this.game2 = game2;

        else{
            game = 2;
            showError(game);
        }

        Log.d("BowlingScore Object", "Added game 2");
    }

    public int getGame3() {
        return game3;
    }

    public void setGame3(int game3) {
        if(game3 >= 0 && game3 <= 300)
            this.game3 = game3;
        else{
            game = 3;
            showError(game);
        }

        Log.d("BowlingScore Object", "Added game 3");
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private int calculateSeriesTotal(){
        return game1 + game2 + game3;
    }

    public String calculateSeriesTotalString(){
        int result = game1 + game2 + game3;
        return "" +  result;
    }

    public double calculateSeriesAverage(){
        return (double) calculateSeriesTotal() / 3;
    }

    private void showError(int game){
        CharSequence text = "Game " + game + " score must be less than 300 and must be a positive number";
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG );
        toast.show();
        this.game = 0;
    }


    @Override
    public String toString() {

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        return "BowlingScores{" +
                "game1= " + game1 +
                ", game2= " + game2 +
                ", game3= " + game3 +
                ", date= " + dateFormat.format(date) +
                '}';
    }
}
