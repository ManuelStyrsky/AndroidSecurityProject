package com.snakesonwheels.tabely.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.snakesonwheels.tabely.R;
import com.snakesonwheels.tabely.model.RestaurantRequest;
import com.snakesonwheels.tabely.model.MySingleton;

import java.util.Calendar;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private TextView textSearch;
    private TextView textAddress;
    public TextView textTime;
    private TextView textDate;
    private TextView textSeats;

    private TextView textUserId;

    private ImageButton buttonTime;
    private ImageButton buttonDate;
    private ImageButton buttonSeats;

    private Button buttonSearch;
    private Button buttonOffer;

    private RestaurantRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        request = new RestaurantRequest(getIntent());

        //initialize textViews and buttons
        textSearch = findViewById(R.id.TextSearch);
        textAddress = findViewById(R.id.TextAddress);
        textTime = findViewById(R.id.TextTime);
        textDate = findViewById(R.id.TextDate);
        textSeats = findViewById(R.id.TextSeats);

        textUserId = findViewById(R.id.TextUserId);

        buttonTime = findViewById(R.id.ButtonTime);
        buttonDate = findViewById(R.id.ButtonDate);
        buttonSeats = findViewById(R.id.ButtonSeats);
        buttonOffer = findViewById(R.id.ButtonOffer);
        buttonSearch = findViewById(R.id.ButtonSearch);
        buttonSearch.setEnabled(false);

        //For testing
        textUserId.setText(request.userId);

        //default values
        textSearch.setText(request.searchText);
        textAddress.setText(request.address);

        textTime.setText(request.getTimeString());
        textDate.setText(request.getDateString());
        textSeats.setText(request.seats + "");

        //buttonTime functionality
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        //buttonDate functionality
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        //buttonSeats functionality
        buttonSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPickerDialog();
            }
        });

        //buttonOffer functionality
        buttonOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(request.createIntent(HomeActivity.this, new OfferActivity()));
            }
        });

        //buttonSearch functionality with textView values to send
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(request.createIntent(HomeActivity.this, new RestaurantListActivity()));
            }
        });

        //create textWatchers for all textViews
        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                request.searchText = textSearch.getText().toString();
                changeButtonState();
            }
        });

        textAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                request.address = textAddress.getText().toString();
                changeButtonState();
            }
        });

        textTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });

        textDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });

        textSeats.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                changeButtonState();
            }
        });
    }

    //numberPicker functionality
    private void numberPickerDialog() {
        NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
        numberPicker.setValue(2);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int seats) {
                textSeats.setText("" + seats);
                request.seats = seats;
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(numberPicker);
        builder.setTitle("Number of Persons").setIcon(R.drawable.ic_group_black_24dp);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    //link timePicker to textView
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        request.time = hourOfDay * 100 + minute;
        textTime.setText(request.getTimeString());

        MySingleton mySing = MySingleton.getInstance();
        Date selectedDate = mySing.getSelectedDate();
        selectedDate.setHours(hourOfDay);
        selectedDate.setMinutes(minute);
        mySing.setSelectedDate(selectedDate);
        mySing.getSelectedCalendar().setTime(selectedDate);
    }

    //link datePicker to textView
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        request.setDate(dayOfMonth, (month + 1), year);
        textDate.setText(request.getDateString());

        MySingleton mySing = MySingleton.getInstance();
        Date selectedDate = mySing.getSelectedDate();
        selectedDate.setYear(year);
        selectedDate.setMonth(month);
        selectedDate.setDate(dayOfMonth);
        mySing.setSelectedDate(selectedDate);

        Calendar c = mySing.getSelectedCalendar();
        c.set(year, month, dayOfMonth);
        mySing.setSelectedCalendar(c);
    }

    //buttonSearch state logic
    private void changeButtonState() {
        if (textSearch.getText().toString().length() > 0
                && textAddress.getText().toString().length() > 0
                && textTime.getText().toString().length() > 0
                && textDate.getText().toString().length() > 0
                && textSeats.getText().toString().length() > 0) buttonSearch.setEnabled(true);
        else buttonSearch.setEnabled(false);
    }

    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Logout: {
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
                return true;
            }
            case R.id.Help: {
                Toast.makeText(getApplicationContext(), "feature not implemented", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.MyReservations: {
                startActivity(request.createIntent(HomeActivity.this, new MyReservationsActivity()));
                //return true;
            }
            case R.id.FindTable: {
                return true;
            }
            case R.id.Settings: {
                Toast.makeText(getApplicationContext(), "feature not implemented", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
