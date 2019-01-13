package com.snakesonwheels.tabely.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

// SQLiteOpenHelper helps you open or create a database
public class DBTools extends SQLiteOpenHelper {

    Context context;

    // Context : provides access to application-specific resources and classes
    public DBTools(Context context) {

        // Call use the database or to create it
        super(context, "RestaurantDatabase.db", null, 4);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        Log.v("Haider", "Creating New Database");

        // This is How to create a table in SQLite
        // Make sure you don't put a ; at the end of the query


        String query1 = "CREATE TABLE customers ( customerId INTEGER PRIMARY KEY, firstName TEXT, " +
                "lastName TEXT, phoneNumber TEXT, emailAddress TEXT, homeAddress TEXT, username TEXT, password TEXT)";
        String query2 = "CREATE TABLE restaurants ( restaurantId INTEGER PRIMARY KEY, restaurantName Text, phone TEXT, address TEXT, " +
                "cuisine TEXT, openingTime INTEGER, closingTime INTEGER, description TEXT, rating INTEGER, priceRange TEXT, website TEXT)";
        String query3 = "CREATE TABLE restaurantTables ( restaurantTableId INTEGER PRIMARY KEY AUTOINCREMENT, restaurantId INTEGER, seats INTEGER," +
                "outside INTEGER, window INTEGER, available INTEGER)";
        String query4 = "CREATE TABLE reservation ( reservationId INTEGER PRIMARY KEY AUTOINCREMENT, restaurantTableId INTEGER, customerId INTEGER," +
                "startTime INTEGER, endTime INTEGER, date INTEGER)";


        // Executes the query provided as long as the query isn't a select or if the query doesn't return any data

        database.execSQL(query1);
        database.execSQL(query2);
        database.execSQL(query3);
        database.execSQL(query4);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 4) {
            String query = "DROP TABLE IF EXISTS restaurants";
            String query2 = "DROP TABLE IF EXISTS customers";
            String query3 = "DROP TABLE IF EXISTS restaurantTables";
            String query4 = "DROP TABLE IF EXISTS reservation";
            db.execSQL(query);
            db.execSQL(query2);
            db.execSQL(query3);
            db.execSQL(query4);
            onCreate(db);
        }

    }

    public void resetDatabase() {

        SQLiteDatabase database = this.getWritableDatabase();

        String query = "DROP TABLE IF EXISTS restaurants";
        String query2 = "DROP TABLE IF EXISTS customers";
        String query3 = "DROP TABLE IF EXISTS restaurantTables";
        String query4 = "DROP TABLE IF EXISTS reservation";
        database.execSQL(query);
        database.execSQL(query2);
        database.execSQL(query3);
        database.execSQL(query4);
        onCreate(database);

    }

    public void insertRestaurant(HashMap<String, String> queryValues, int openingTime, int closingTime) {

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Stores key value pairs being the column name and the data
        // ContentValues data type is needed because the database
        // requires its data type to be passed

        ContentValues values = new ContentValues();

        values.put("restaurantName" + " ", queryValues.get("restaurantName"));

        values.put("phone" + " ", queryValues.get("phone"));
        values.put("address", queryValues.get("address"));
        values.put("cuisine", queryValues.get("cuisine"));
        values.put("openingTime" + " ", openingTime);
        values.put("closingTime", closingTime);
        values.put("description", queryValues.get("description"));
        values.put("rating", queryValues.get("rating"));
        values.put("priceRange", queryValues.get("priceRange"));
        values.put("website", queryValues.get("website"));

        // Inserts the data in the form of ContentValues into the
        // table name provided

        database.insert("restaurants", null, values);

        // Release the reference to the SQLiteDatabase object

        database.close();
    }

    public long insertCustomer(String firstName, String lastName, String phoneNumber, String emailAddress, String homeAddress, String username, String password) {

        //TODO Have to handel the case where an existing username is entered.
        if (!doesUsernameExist(username)) {

            // Open a database for reading and writing

            SQLiteDatabase database = this.getWritableDatabase();

            // Stores key value pairs being the column name and the data
            // ContentValues data type is needed because the database
            // requires its data type to be passed


            ContentValues values = new ContentValues();

            values.put("firstName" + " ", firstName);
            values.put("lastname" + " ", lastName);
            values.put("phoneNumber", phoneNumber);
            values.put("emailAddress", emailAddress);
            values.put("homeAddress", homeAddress);
            values.put("username", username);
            values.put("password", password);

            // Inserts the data in the form of ContentValues into the
            // table name provided

            long success = database.insert("customers", null, values);

            // Release the reference to the SQLiteDatabase object

            database.close();
            return success;
        } else return -1;

    }

    public long insertReservation(String restaurantTableId, String customerId, int startTime, int endTime, int date) {


        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Stores key value pairs being the column name and the data
        // ContentValues data type is needed because the database
        // requires its data type to be passed


        ContentValues values = new ContentValues();

        values.put("restaurantTableId" + " ", restaurantTableId);
        values.put("customerId" + " ", customerId);
        values.put("startTime" + " ", startTime);
        values.put("endTime", endTime);
        values.put("date", date);

        // Inserts the data in the form of ContentValues into the
        // table name provided

        long success = database.insert("reservation", null, values);

        // Release the reference to the SQLiteDatabase object

        database.close();

        // success is the new ID of the Reservation just created. If it retruns -1 that means that there was an error inserting into the database.
        return success;


    }


    public long insertRestaurantTables(String restaurantId, String seats, String outside, String window, String available) {


        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Stores key value pairs being the column name and the data
        // ContentValues data type is needed because the database
        // requires its data type to be passed


        ContentValues values = new ContentValues();

        values.put("restaurantId" + " ", restaurantId);
        values.put("seats" + " ", seats);
        values.put("outside", outside);
        values.put("window", window);
        values.put("available", available);

        // Inserts the data in the form of ContentValues into the
        // table name provided

        long success = database.insert("restaurantTables", null, values);

        // Release the reference to the SQLiteDatabase object

        database.close();

        // success is the new ID of the Reservation just created. If it retruns -1 that means that there was an error inserting into the database.
        return success;


    }

    public ArrayList<HashMap<String, String>> getAllRestaurants(String name, String address) {

        // ArrayList that contains every row in the database
        // and each row key / value stored in a HashMap

        ArrayList<HashMap<String, String>> restaurantArrayList;

        restaurantArrayList = new ArrayList<HashMap<String, String>>();

        String selectQuery = "SELECT  * FROM restaurants WHERE restaurantName LIKE '%" + name + "%' AND address LIKE '%" + address + "%' ORDER BY restaurantName";

        // Open a database for reading and writing

        SQLiteDatabase database = this.getWritableDatabase();

        // Cursor provides read and write access for the
        // data returned from a database query

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);

        // Move to the first row

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> restaurantMap = new HashMap<String, String>();

                // Store the key / value pairs in a HashMap
                // Access the Cursor data by index that is in the same order
                // as used when creating the table


                restaurantMap.put("restaurantId", cursor.getString(0));
                restaurantMap.put("restaurantName", cursor.getString(1));

                restaurantMap.put("phone", cursor.getString(2));
                restaurantMap.put("address", cursor.getString(3));
                restaurantMap.put("cuisine", cursor.getString(4));

                restaurantMap.put("openingTime", cursor.getString(5));
                restaurantMap.put("closingTime", cursor.getString(6));
                restaurantMap.put("description", cursor.getString(7));
                restaurantMap.put("rating", cursor.getString(8));
                restaurantMap.put("priceRange", cursor.getString(9));
                restaurantMap.put("website", cursor.getString(10));

                restaurantArrayList.add(restaurantMap);
            } while (cursor.moveToNext()); // Move Cursor to the next row
        }

        // return restaurant list
        database.close();
        return restaurantArrayList;
    }

    public HashMap<String, String> getRestaurantDetails(String id) {
        HashMap<String, String> restaurantMap = new HashMap<String, String>();

        // Open a database for reading only

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM restaurants where restaurantId='" + id + "'";

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                restaurantMap.put("restaurantId", cursor.getString(0));
                restaurantMap.put("restaurantName", cursor.getString(1));

                restaurantMap.put("phone", cursor.getString(2));
                restaurantMap.put("address", cursor.getString(3));
                restaurantMap.put("cuisine", cursor.getString(4));

                restaurantMap.put("openingTime", cursor.getString(5));
                restaurantMap.put("closingTime", cursor.getString(6));
                restaurantMap.put("description", cursor.getString(7));
                restaurantMap.put("rating", cursor.getString(8));
                restaurantMap.put("priceRange", cursor.getString(9));
                restaurantMap.put("website", cursor.getString(10));

            } while (cursor.moveToNext());
        }
        database.close();
        return restaurantMap;
    }

    public boolean isTableReserved(int tableID, String startTime, String endTime, String date) {

        //1)Since startTime and endTime are dates, they can not be stored as Integers.
        //2) Since we could not handel dates properly before hence the dates stored in database are strings and should be just checked if are similar :(
        boolean isReserved = false;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM reservation where restaurantTableId='" + tableID + "'" + " AND date='" + date + "' AND (startTime= '" + startTime + "' OR startTime='" + endTime + "' OR endTime= '" + startTime + "' OR endTime='" + endTime + "')";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) { // cursor only moves to first if Query returns something... which in our case would mean that table is reserved
            isReserved = true;
        }
        database.close();
        return isReserved;
    }

    public boolean checkIfReservationExists(int tableID, int startTime, int endTime, int date) {
        SQLiteDatabase database = this.getReadableDatabase();
        HashMap<String, String> reservationMap = new HashMap<String, String>();

        String selectQuery = "SELECT * FROM reservation where restaurantTableId=" + tableID + " AND ((startTime>=" + startTime + " AND " + "startTime<" + endTime + ") OR (" +
                "endTime>" + startTime + " AND " + "endTime<=" + endTime + ")) AND date =" + date;
        int numberOfReservationsAtTheTime = 0;
        System.out.println(selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                System.out.println(numberOfReservationsAtTheTime);
                numberOfReservationsAtTheTime++;
            } while (cursor.moveToNext());
        }
        System.out.println(numberOfReservationsAtTheTime);
        database.close();
        boolean result = (numberOfReservationsAtTheTime == 0);
        Log.d("Haider :", "Result of checkIfReservationExist: " + !result);
        return !result;
    }

    public String CheckCustomerPassword(String username, String passwordGiven) {
        String passwordActual = null;
        String userId = null;
        boolean result = false;
        //HashMap<String, String> restaurantMap = new HashMap<String, String>();

        // Open a database for reading only

        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM customers where username='" + username + "'";
        //String selectQuery = "SELECT * FROM customers where customerId=1";

        // rawQuery executes the query and returns the result as a Cursor

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            userId = cursor.getString(0);
            passwordActual = cursor.getString(7);
        }

        if (passwordActual != null) {
            result = passwordGiven.equals(passwordActual);
        }
        database.close();

        if (result) {
            return userId;
        } else return "Error";
    }


    private boolean doesUsernameExist(String username) {
        String dbusername = null;
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM customers where username='" + username + "'";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            dbusername = cursor.getString(6);
        }
        if (dbusername != null && dbusername.equals(username)) {
            return true;
        } else return false;
    }

    public int getBestAvailableTableId(String restaurantId, int seats, int window, int outside, int startTime, int endTime, int date) {
        int bestTableId = -1;
        SQLiteDatabase database = this.getReadableDatabase();
        //String selectQuery = "SELECT * FROM restaurantTables WHERE restaurantId='" + restaurantId + "' AND window='" + window + "' AND outside='" + outside + "' ORDER BY seats ASC";
        String selectQuery = "SELECT * FROM restaurantTables WHERE restaurantId='" + restaurantId + "' ORDER BY seats ASC";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int tableSeats = cursor.getInt(2);
                int tableId = cursor.getInt(0);
                Log.d("Haider","Table ID: " + tableId);
                if (tableSeats >= seats && !checkIfReservationExists(tableId, startTime, endTime, date)) {
                    bestTableId = tableId;
                }
            } while (cursor.moveToNext());
        }
        database.close();
        Log.d("Haider","Get Table Id: " + bestTableId );
        return bestTableId; // if 0 is returned, it means no table is available.
    }
}
