package com.snakesonwheels.tabely.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.snakesonwheels.tabely.R;
import com.snakesonwheels.tabely.model.RestaurantRequest;

public class MyReservationsActivity extends AppCompatActivity {

    private RestaurantRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservations);

        request = new RestaurantRequest(getIntent());

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
                    Intent i = new Intent(MyReservationsActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                case R.id.Help: {
                    Toast.makeText(getApplicationContext(), "feature not implemented",Toast.LENGTH_SHORT).show();
                    return true;
                }
                case R.id.MyReservations: {
                    return true;
                }
                case R.id.FindTable: {
                    startActivity(request.createIntent(MyReservationsActivity.this, new HomeActivity()));
                }
                case R.id.Settings: {
                    Toast.makeText(getApplicationContext(), "feature not implemented",Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
            return super.onOptionsItemSelected(item);
        }

}
