package com.example.bennyjr.testapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private Button showHistory;
    private EditText game1ScoreEditText;
    private EditText game2ScoreEditText;
    private EditText game3ScoreEditText;
    private TextView date;
    private TextView seriesTotal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        //Add and format date to "Date of games"
        Date today = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String cs = dateFormat.format(today);

        date.setText(cs);

    }

    private void setupViews(){
        saveButton = (Button) findViewById(R.id.button);
        showHistory = (Button) findViewById(R.id.showHistory);
        game1ScoreEditText = (EditText) findViewById(R.id.game1ScoreEditText);
        game2ScoreEditText = (EditText) findViewById(R.id.game2ScoreEditText);
        game3ScoreEditText = (EditText) findViewById(R.id.game3ScoreEditText);
        date = (TextView) findViewById(R.id.date);
        seriesTotal = (TextView) findViewById(R.id.textView2);
    }//End setupViews method

    /*
        Get Scores from interface, validate scores, create an object to hold scores
     */
    public void calculateClickHandler(View view){
        BowlingScore bScore = new BowlingScore();
        Context context = getApplicationContext();
        bScore.setContext(context);

        bScore.setGame1(Integer.parseInt(game1ScoreEditText.getText().toString()));
        bScore.setGame2(Integer.parseInt(game2ScoreEditText.getText().toString()));
        bScore.setGame3(Integer.parseInt(game3ScoreEditText.getText().toString()));
        bScore.setDate(new Date());

        seriesTotal.setText(bScore.calculateSeriesTotalString());

        MyBowlingScoresApplication app = (MyBowlingScoresApplication) getApplication();
        app.addBowlingScores(bScore);
    }
}
