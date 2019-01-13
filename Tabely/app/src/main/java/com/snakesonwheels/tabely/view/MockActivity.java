package com.snakesonwheels.tabely.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.snakesonwheels.tabely.R;

public class MockActivity extends AppCompatActivity {

    private TextView textSearch;
    private TextView textLocation;
    private TextView textTime;
    private TextView textDate;
    private TextView textSeats;

    private ImageButton buttonMenu;

    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock);

        //initialize infoTable textViews
        textSearch = (TextView) findViewById(R.id.TextSearch);
        textLocation = (TextView) findViewById(R.id.TextLocation);
        textTime = (TextView) findViewById(R.id.TextTime);
        textDate = (TextView) findViewById(R.id.TextDate);
        textSeats = (TextView) findViewById(R.id.TextSeats);

        //initialize buttonMenu
        buttonMenu = (ImageButton) findViewById(R.id.ButtonMenu);

        //initialize buttonBack
        buttonBack = (Button) findViewById(R.id.ButtonBack);

        //buttonMenu functionality
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MockActivity.this, MenuActivity.class));
            }
        });

        //buttonBack functionality
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //receive textView values
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String search = extras.getString("search");
            String location = extras.getString("location");
            String time = extras.getString("time");
            String date = extras.getString("date");
            String seats = extras.getString("seats");

            textSearch.setText(search);
            textLocation.setText(location);
            textTime.setText(time);
            textDate.setText(date);
            textSeats.setText(seats);
        }
    }
}
