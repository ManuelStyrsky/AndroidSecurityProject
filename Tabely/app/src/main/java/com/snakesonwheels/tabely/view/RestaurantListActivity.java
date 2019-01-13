package com.snakesonwheels.tabely.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.snakesonwheels.tabely.R;
import com.snakesonwheels.tabely.model.DBTools;
import com.snakesonwheels.tabely.model.RestaurantRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantListActivity extends ListActivity {

    private TextView restaurantId;

    private EditText searchBar;

    private RestaurantRequest request;

    DBTools dbTools = new DBTools(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Read Intent and save it in request
        request = new RestaurantRequest(getIntent());

        // Initializing Layout
        searchBar = findViewById(R.id.searchBar);

        // Apply request Data
        searchBar.setText(request.searchText);

        // Gets all the data from the database and stores it in an ArrayList
        ArrayList<HashMap<String, String>> restaurantList = dbTools.getAllRestaurants(request.searchText, request.address);

        // Check to make sure there are restaurants to display
        if (restaurantList.size() != 0) {

            ListView listView = getListView();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // When an item is clicked get the TextView with a matching checkId
                    restaurantId = view.findViewById(R.id.restaurantId);

                    // Create and update Intent
                    Intent i = request.createIntent(RestaurantListActivity.this, new RestaurantDetailsActivity());
                    i.putExtra("restaurantId", restaurantId.getText().toString());

                    startActivity(i);
                }
            });
            ListAdapter adapter = new SimpleAdapter(RestaurantListActivity.this, restaurantList, R.layout.restaurant_entry, new String[]{"restaurantId", "address", "restaurantName"}, new int[]{R.id.restaurantId, R.id.lastName, R.id.restaurantName});

            // setListAdapter provides the Cursor for the ListView
            // The Cursor provides access to the database data
            setListAdapter(adapter);
        }

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String search = searchBar.getText().toString().trim();

                ArrayList<HashMap<String, String>> restaurantList = dbTools.getAllRestaurants(search, request.address);

                ListView listView = getListView();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        restaurantId = view.findViewById(R.id.restaurantId);
                        Intent i = request.createIntent(RestaurantListActivity.this, new RestaurantDetailsActivity());
                        i.putExtra("restaurantId", restaurantId.getText().toString());
                        startActivity(i);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(RestaurantListActivity.this, restaurantList, R.layout.restaurant_entry, new String[]{"restaurantId", "address", "restaurantName"}, new int[]{R.id.restaurantId, R.id.lastName, R.id.restaurantName});
                setListAdapter(adapter);
            }


            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void resetSearch(View view) {
        searchBar.setText("");
    }
}
