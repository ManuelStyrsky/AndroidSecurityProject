package com.snakesonwheels.tabely.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.snakesonwheels.tabely.R;
import com.snakesonwheels.tabely.model.DBTools;
import com.snakesonwheels.tabely.model.MySingleton;
import com.snakesonwheels.tabely.model.RestaurantRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class RestaurantDetailsActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private TextView textTime;
    private TextView textDate;
    private TextView textSeats;

    private TextView textRestaurantName;
    private TextView textCuisine;
    private TextView textAddress;
    private TextView textOpeningTime;
    private TextView textClosingTime;
    private TextView textPhone;
    private TextView textWebsite;
    private TextView textDescription;

    private TextView textPriceRange;
    private RatingBar ratingBar;

    private ImageButton buttonTime;
    private ImageButton buttonDate;
    private ImageButton buttonSeats;

    public Button buttonTimeLeft;
    public Button buttonTimeMiddle;
    public Button buttonTimeRight;
    public Button buttonReserve;

    private RestaurantRequest request;
    private String restaurantId;

    private DBTools dbTools = new DBTools(this);
    private MySingleton mySingleton = MySingleton.getInstance();
    private HashMap<String, String> RestaurantDetails;

    private int[] activeTables = new int[3];
    private int currentTable = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        // Read Intent and save it in request
        Intent i = getIntent();
        request = new RestaurantRequest(i);
        restaurantId = request.restaurantId;

        // Initializing Layout
        textTime = findViewById(R.id.TextTime);
        textDate = findViewById(R.id.TextDate);
        textSeats = findViewById(R.id.TextSeats);

        textRestaurantName = findViewById(R.id.textViewRestaurantName);
        textCuisine = findViewById(R.id.textViewCuisine);
        textAddress = findViewById(R.id.textViewAddress);
        textOpeningTime = findViewById(R.id.textViewOpeningTime);
        textClosingTime = findViewById(R.id.textViewClosingTime);
        textPhone = findViewById(R.id.textViewPhone);
        textWebsite = findViewById(R.id.textViewWebsite);
        textDescription = findViewById(R.id.textViewDescription);

        textPriceRange = findViewById(R.id.textViewPriceRange);
        ratingBar = findViewById(R.id.ratingbar);

        buttonTime = findViewById(R.id.ButtonTime);
        buttonDate = findViewById(R.id.ButtonDate);
        buttonSeats = findViewById(R.id.ButtonSeats);

        buttonTimeLeft = findViewById(R.id.buttontimeleft);
        buttonTimeMiddle = findViewById(R.id.buttontimemiddle);
        buttonTimeRight = findViewById(R.id.buttontimeright);
        buttonReserve = findViewById(R.id.buttonreserve);

        // Reading Request
        textTime.setText(request.getTimeString());
        textDate.setText(request.getDateString());
        textSeats.setText("" + request.seats);

        // Reading Database
        RestaurantDetails = dbTools.getRestaurantDetails(restaurantId);

        textRestaurantName.setText(RestaurantDetails.get("restaurantName"));
        textCuisine.setText(RestaurantDetails.get("cuisine"));
        textAddress.setText(RestaurantDetails.get("address"));
        textOpeningTime.setText(RestaurantDetails.get("openingTime"));
        textClosingTime.setText(RestaurantDetails.get("closingTime"));
        textPhone.setText(RestaurantDetails.get("phone"));
        textWebsite.setText(RestaurantDetails.get("website"));
        textDescription.setText(RestaurantDetails.get("description"));
        textPriceRange.setText(RestaurantDetails.get("priceRange"));
        ratingBar.setRating(Float.parseFloat(RestaurantDetails.get("rating")));

        // Save Information in request
        i.putExtra("restaurantName", RestaurantDetails.get("restaurantName"));
        i.putExtra("address", RestaurantDetails.get("address"));
        i.putExtra("cuisine", RestaurantDetails.get("cuisine"));
        i.putExtra("open", RestaurantDetails.get("openingTime"));
        i.putExtra("close", RestaurantDetails.get("closingTime"));
        i.putExtra("phone", RestaurantDetails.get("phone"));
        i.putExtra("website", RestaurantDetails.get("website"));
        i.putExtra("description", RestaurantDetails.get("description"));
        i.putExtra("price", textPriceRange.getText().toString());
        i.putExtra("rating", ratingBar.getNumStars());

        request = new RestaurantRequest(i);

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

        // TimeButton Setup
        timeButtonSetup();

        // ReserveButton Logic
        buttonReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int reserveTable = activeTables[currentTable];
                request.time = getTimeForSelectedSlot();

                mySingleton.setReserveTable(reserveTable);
                mySingleton.setSelectedTime(request.time);

                //pass request to confirm activity
                System.out.println(request.userId);
                startActivity(request.createIntent(RestaurantDetailsActivity.this, new ConfirmActivity()));
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
                timeButtonSetup();
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
        timeButtonSetup();

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
        timeButtonSetup();

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

    private void timeButtonSetup() {
        //communicate with db
        request.open = request.parseTime(RestaurantDetails.get("openingTime"));
        request.close = request.parseTime(RestaurantDetails.get("closingTime"));

        int beforeTime = request.getBeforeTableTime();
        int afterTime = request.getAfterTableTime();
        int openingTime = request.open;
        int closingTime = request.close;

        if (beforeTime < openingTime || (beforeTime + 100) > closingTime) {
            activeTables[0] = -1;
        } else {
            activeTables[0] = dbTools.getBestAvailableTableId(restaurantId, request.seats, request.window, request.outdoor, request.getBeforeTableTime(), ((request.getBeforeTableTime() + 100) % 2400), request.date);
        }
        if (request.time < openingTime || (request.time + 100) > closingTime) {
            activeTables[1] = -1;
        } else {
            activeTables[1] = dbTools.getBestAvailableTableId(restaurantId, request.seats, request.window, request.outdoor, request.time, (request.time + 100), request.date);
        }
        if (afterTime < openingTime || (afterTime + 100) > closingTime) {
            activeTables[2] = -1;
        } else {
            activeTables[2] = dbTools.getBestAvailableTableId(restaurantId, request.seats, request.window, request.outdoor, request.getAfterTableTime(), ((request.getAfterTableTime() + 100) % 2400), request.date);
        }

        // TimeButton Setup - Disabling / Enabling
        buttonReserve.setEnabled(false);
        if (activeTables[0] == -1) {
            buttonTimeLeft.setEnabled(false);
        } else {
            buttonTimeLeft.setEnabled(true);
        }
        if (activeTables[1] == -1) {
            buttonTimeMiddle.setEnabled(false);
        } else {
            buttonTimeMiddle.setEnabled(true);
        }
        if (activeTables[2] == -1) {
            buttonTimeRight.setEnabled(false);
        } else {
            buttonTimeRight.setEnabled(true);
        }

        buttonTimeLeft.setSelected(false);
        buttonTimeRight.setSelected(false);
        buttonTimeMiddle.setSelected(false);

        // TimeButton Setup - Setting Time
        int before = request.getBeforeTableTime();
        int middle = request.getTableTime();
        int after = request.getAfterTableTime();

        buttonTimeLeft.setText(request.getTimeString(before));
        buttonTimeMiddle.setText(request.getTimeString(middle));
        buttonTimeRight.setText(request.getTimeString(after));

        // TimeButton Logic
        if (buttonTimeRight.isEnabled()) {
            buttonTimeRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonTimeLeft.setSelected(false);
                    buttonTimeLeft.setTextColor(Color.BLACK);
                    buttonTimeMiddle.setSelected(false);
                    buttonTimeMiddle.setTextColor(Color.BLACK);
                    buttonTimeRight.setSelected(true);
                    buttonTimeRight.setTextColor(Color.WHITE);
                    buttonReserve.setEnabled(true);

                    currentTable = 2;
                }
            });
        }

        if (buttonTimeLeft.isEnabled()) {
            buttonTimeLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonTimeLeft.setSelected(true);
                    buttonTimeLeft.setTextColor(Color.WHITE);
                    buttonTimeMiddle.setSelected(false);
                    buttonTimeMiddle.setTextColor(Color.BLACK);
                    buttonTimeRight.setSelected(false);
                    buttonTimeRight.setTextColor(Color.BLACK);
                    buttonReserve.setEnabled(true);

                    currentTable = 0;
                }
            });
        }

        if (buttonTimeMiddle.isEnabled()) {
            buttonTimeMiddle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonTimeLeft.setSelected(false);
                    buttonTimeLeft.setTextColor(Color.BLACK);
                    buttonTimeMiddle.setSelected(true);
                    buttonTimeMiddle.setTextColor(Color.WHITE);
                    buttonTimeRight.setSelected(false);
                    buttonTimeRight.setTextColor(Color.BLACK);
                    buttonReserve.setEnabled(true);

                    currentTable = 1;
                }
            });

        }
    }

    private int getTimeForSelectedSlot() {
        switch (currentTable) {
            case 0:
                return request.getBeforeTableTime();
            case 1:
                return request.time;
            case 2:
                return request.getAfterTableTime();
            default:
                return -1;
        }
    }
}
