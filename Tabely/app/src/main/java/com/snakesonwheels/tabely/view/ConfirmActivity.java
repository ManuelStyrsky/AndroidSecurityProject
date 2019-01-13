package com.snakesonwheels.tabely.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.snakesonwheels.tabely.R;
import com.snakesonwheels.tabely.model.DBTools;
import com.snakesonwheels.tabely.model.MySingleton;
import com.snakesonwheels.tabely.model.RestaurantRequest;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.FeedbackManager;
import net.hockeyapp.android.UpdateManager;

public class ConfirmActivity extends AppCompatActivity {

    //for hockey app
    private final String HOCKEYAPP_ID = "229eba46646a45ce80d33076375c56aa";

    private Button buttonOk;
    private Button buttonCancel;
    private Button buttonCalendar;
    private Button buttonFeedback;

    private DBTools dbTools = new DBTools(this);
    private MySingleton mySingleton = MySingleton.getInstance();

    private RestaurantRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        request = new RestaurantRequest(getIntent());

        //initialize buttons
        buttonOk = findViewById(R.id.OkButton);
        buttonCancel = findViewById(R.id.CancelButton);
        buttonCalendar = findViewById(R.id.CalendarButton);
        buttonFeedback = findViewById(R.id.FeedbackButton);

        //Button Ok functionality
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int reserveTable = mySingleton.getReserveTable();
                int selectedTime = mySingleton.getSelectedTime();
                long result = dbTools.insertReservation("" + reserveTable, "" + request.userId, selectedTime, (selectedTime + 100) % 2400, request.date);

                RestaurantRequest newRequest = new RestaurantRequest();
                newRequest.userId = request.userId;

                Toast.makeText(getApplicationContext(), "The reservation has been stored. ID: " + result, Toast.LENGTH_SHORT).show();
                startActivity(newRequest.createIntent(ConfirmActivity.this, new HomeActivity()));
            }
        });

        //Button Cancel functionality
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantRequest newRequest = new RestaurantRequest();
                newRequest.userId = request.userId;

                Toast.makeText(getApplicationContext(), "The reservation has been deleted.", Toast.LENGTH_SHORT).show();
                startActivity(newRequest.createIntent(ConfirmActivity.this, new HomeActivity()));
            }
        });

        //Button Calendar functionality
        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, "My Table reservation in " + request.restaurantName);
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, request.address);
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Your reservation made with Tabely! You reserved a Table for up to " + request.seats + " people.");
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, request.seats);
                calIntent.putExtra(Events.EVENT_COLOR, 0xFFFF);
                startActivity(calIntent);
            }
        });

        //Button Feedback functionality
        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFeedbackActivity();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        UpdateManager.unregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForCrashes();
    }


    private void checkForCrashes() {
        CrashManager.register(this, HOCKEYAPP_ID);
    }

    public void showFeedbackActivity() {
        FeedbackManager.register(this, HOCKEYAPP_ID, null);
        FeedbackManager.showFeedbackActivity(this);
    }
}
