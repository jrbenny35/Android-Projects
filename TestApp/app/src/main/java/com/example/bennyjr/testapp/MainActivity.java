package com.example.bennyjr.testapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private Button showHistory;
    private EditText game1ScoreEditText;
    private EditText game2ScoreEditText;
    private EditText game3ScoreEditText;
    private TextView date;
    private TextView seriesTotal;

    //Date
    private int month;
    private int day;
    private int year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        //Put todays date on screen
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(calendar.YEAR);
        day = calendar.get(calendar.DAY_OF_MONTH);
        month = calendar.get(calendar.MONTH);

        //Set date
        Date today = calendar.getTime();

        //Add and format date to "Date of games"
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String cs = dateFormat.format(today);

        date.setText(cs);

    }

    //Date Click Method
    public void changeDateClickHandler(View view){
        Log.d("MainActivity", "Changing the date");

        DatePickerDialog.OnDateSetListener dateSetListener;
        dateSetListener = new DatePickerDialog.OnDateSetListener(){ //Sets up date Listener
            @Override
            public void onDateSet(DatePicker view, int yearOfYear, int monthOfYear, int dayOfMonth) {
                year = yearOfYear;
                month = monthOfYear;
                day = dayOfMonth;

                Calendar cal = new GregorianCalendar(year, month, day);
                Date dateOfGames = cal.getTime();
                DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
                String cs = dateFormat.format(dateOfGames);

                //Sets date on screen
                date.setText(cs);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day );
        datePickerDialog.show();
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
        Date dateOfGames = new Date(day, month, year);

        //Set Scores
        bScore.setGame1(Integer.parseInt(game1ScoreEditText.getText().toString()));
        bScore.setGame2(Integer.parseInt(game2ScoreEditText.getText().toString()));
        bScore.setGame3(Integer.parseInt(game3ScoreEditText.getText().toString()));

        //Set Date
        bScore.setDate(dateOfGames);

        //Calculate total
        seriesTotal.setText(bScore.calculateSeriesTotalString());

        MyBowlingScoresApplication app = (MyBowlingScoresApplication) getApplication();
        app.addBowlingScores(bScore);
    }
}
