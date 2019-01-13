package com.snakesonwheels.tabely.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.snakesonwheels.tabely.R;
import com.snakesonwheels.tabely.model.RestaurantRequest;

public class FilterActivity extends AppCompatActivity {

    private Button buttonOk;
    private Button buttonReset;

    private CheckBox Windows;
    private CheckBox NoWindows;
    private CheckBox Inside;
    private CheckBox Outside;
    private CheckBox Poor;
    private CheckBox Fair;
    private CheckBox Good;
    private CheckBox Low;
    private CheckBox Middle;
    private CheckBox High;

    private RestaurantRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        request = new RestaurantRequest(getIntent());

        //initialize Button
        buttonOk = findViewById(R.id.OkButton);
        buttonReset = findViewById(R.id.ResetButton);

        //initialize CheckBox
        Windows = findViewById(R.id.checkBox1);
        NoWindows = findViewById(R.id.checkBox2);
        Inside = findViewById(R.id.checkBox3);
        Outside = findViewById(R.id.checkBox4);
        Poor = findViewById(R.id.checkBox5);
        Fair = findViewById(R.id.checkBox6);
        Good = findViewById(R.id.checkBox7);
        Low = findViewById(R.id.checkBox8);
        Middle = findViewById(R.id.checkBox9);
        High = findViewById(R.id.checkBox10);

        //Button functionality
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = request.createIntent(FilterActivity.this, new HomeActivity());

                // TODO filter functionality
                /**
                if (Windows.isChecked()) i.putExtra("window", 1);
                else if (NoWindows.isChecked()) i.putExtra("window", 0);
                else i.putExtra("window", 0);

                if (Outside.isChecked()) i.putExtra("outside", 1);
                else if (Inside.isChecked()) i.putExtra("outside", 0);
                else i.putExtra("outside", 0);

                if (Good.isChecked()) i.putExtra("rating", 4);
                else if (Fair.isChecked()) i.putExtra("rating", 3);
                else if (Poor.isChecked()) i.putExtra("rating", 1);
                else i.putExtra("rating", 0);

                if (High.isChecked()) i.putExtra("price", 3);
                else if (Middle.isChecked()) i.putExtra("price", 2);
                else if (Low.isChecked()) i.putExtra("price", 1);
                else i.putExtra("price", 0);
                */

                startActivity(i);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Windows.setChecked(true);
                NoWindows.setChecked(true);
                Inside.setChecked(true);
                Outside.setChecked(true);
                Poor.setChecked(true);
                Fair.setChecked(true);
                Good.setChecked(true);
                Low.setChecked(true);
                Middle.setChecked(true);
                High.setChecked(true);

            }
        });


    }
}
