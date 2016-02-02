package com.example.bennyjr.testapp;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by bennyjr on 2/1/16.
 */
public class MyBowlingScoresApplication extends Application {

    private ArrayList<BowlingScore> allBowlingScores;

    @Override
    public void onCreate() {
        super.onCreate();

        allBowlingScores = new ArrayList<BowlingScore>();


    }

    public void addBowlingScores(BowlingScore bowlingScore){
        assert bowlingScore != null;

        allBowlingScores.add(bowlingScore);
    }

    public ArrayList<BowlingScore> getAllBowlingScores() {
        return allBowlingScores;
    }

}
