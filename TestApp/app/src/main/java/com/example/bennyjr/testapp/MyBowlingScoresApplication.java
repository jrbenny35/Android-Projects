package com.example.bennyjr.testapp;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import static com.example.bennyjr.testapp.BowlingScoresDatabaseHelper.*;

/**
 * Created by bennyjr on 2/1/16.
 */
public class MyBowlingScoresApplication extends Application {

    private ArrayList<BowlingScore> allBowlingScores;
    private SQLiteDatabase bowlingScoresDB;

    @Override
    public void onCreate() {
        super.onCreate();

        //Create database object and create database
        BowlingScoresDatabaseHelper bowlingScoresDatabaseHelper = new BowlingScoresDatabaseHelper(this);
        bowlingScoresDB = bowlingScoresDatabaseHelper.getWritableDatabase();

        readBowlingScoresFromDB();

        allBowlingScores = new ArrayList<BowlingScore>();


    }

    public void addBowlingScores(BowlingScore bowlingScore){
        assert bowlingScore != null;

        //Create content value object for database insertion
        ContentValues contentValues = new ContentValues();

        //Add fields to database
        contentValues.put(BowlingScoresDatabaseHelper.DATE, bowlingScore.getDateEpoch());
        contentValues.put(BowlingScoresDatabaseHelper.GAME1, bowlingScore.getGame1());
        contentValues.put(BowlingScoresDatabaseHelper.GAME2, bowlingScore.getGame2());
        contentValues.put(BowlingScoresDatabaseHelper.GAME3, bowlingScore.getGame3());

        //Logging
        Log.d("Bowling Database", "Before inserting a record" + bowlingScore);

        //Insert into database
        long idPassedBack = bowlingScoresDB.insert(BowlingScoresDatabaseHelper.BOWLING_SCORES_TABLE, null, contentValues);

        //Set id
        bowlingScore.setId(idPassedBack);

        //Log after insert
        Log.d("Bowling Database", "After inserting record "+ bowlingScore);



        allBowlingScores.add(bowlingScore);
    }

    public ArrayList<BowlingScore> getAllBowlingScores() {
        return allBowlingScores;
    }

    private void readBowlingScoresFromDB(){

        allBowlingScores = new ArrayList<BowlingScore>();

        Cursor bowlingScoresCursor;

        String[] columns = new String[]{RECORD_ID, DATE, GAME1, GAME2, GAME3};
        bowlingScoresCursor = bowlingScoresDB.query(BOWLING_SCORES_TABLE, columns, null, null, null, null, DATE);

        bowlingScoresCursor.moveToFirst();

        //Building bowling score object
        BowlingScore bowlingScore;

        if(!bowlingScoresCursor.isAfterLast()){
            do{

                long id = bowlingScoresCursor.getLong(0);
                long dateEpoch = bowlingScoresCursor.getLong(1);
                int game1 = bowlingScoresCursor.getInt(2);
                int game2 = bowlingScoresCursor.getInt(3);
                int game3 = bowlingScoresCursor.getInt(4);

                bowlingScore = new BowlingScore(id, dateEpoch, game1, game2, game3);
                allBowlingScores.add(bowlingScore);

            } while(bowlingScoresCursor.moveToNext());
            bowlingScoresCursor.close();
        }

    }
}
