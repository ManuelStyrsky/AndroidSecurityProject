package com.snakesonwheels.tabely.model;

import android.content.Context;

import java.util.HashMap;

public class InsertDummyDataHere {

    public static String restaurantdescription = "";

    public static void addRestaurants(Context context) {

        DBTools dbTools = new DBTools(context);
        dbTools.resetDatabase();

        dbTools = new DBTools(context);
        HashMap<String, String> queryValuesMap = new HashMap<String, String>();

        // Get the values from the EditText boxes

        queryValuesMap.put("restaurantName", "OnkelLuu ");
        queryValuesMap.put("description", restaurantdescription + "Asian taken-out food");
        queryValuesMap.put("rating", "4");
        queryValuesMap.put("priceRange", "€");
        queryValuesMap.put("website", "m.facebook.com/onkelluus/");
        queryValuesMap.put("phone", "017634635887");
        queryValuesMap.put("address", "Boltzmannstraße 11,Garching");
        queryValuesMap.put("cuisine", "Asian");
        dbTools.insertRestaurant(queryValuesMap,1100,2300);

        dbTools.insertRestaurantTables("1", "2", "1", "0", "1");
        //dbTools.insertRestaurantTables("0", "4", "1", "0", "1");
       //dbTools.insertRestaurantTables("0", "4", "1", "0", "1");

        dbTools = new DBTools(context);
        HashMap<String, String> queryValuesMap2 = new HashMap<String, String>();

        queryValuesMap2.put("restaurantName", "Garchinger Augustiner ");
        queryValuesMap2.put("description", restaurantdescription + "German restaurant. Comfortable environment");
        queryValuesMap2.put("rating", "4");
        queryValuesMap2.put("priceRange", "€€");
        queryValuesMap2.put("website", "www.garchinger-augustiner.com");
        queryValuesMap2.put("phone", "08920966739");
        queryValuesMap2.put("address", "Freisinger Landstraße 4,Garching");
        queryValuesMap.put("cuisine", "German");
        dbTools.insertRestaurant(queryValuesMap2,1100,2300);

        /*dbTools.insertRestaurantTables("1", "2", "1", "0", "1");
        dbTools.insertRestaurantTables("1", "2", "1", "0", "1");
        dbTools.insertRestaurantTables("1", "4", "1", "0", "1");
        dbTools.insertRestaurantTables("1", "4", "1", "0", "1");
        dbTools.insertRestaurantTables("1", "6", "1", "0", "1");
        dbTools.insertRestaurantTables("1", "2", "0", "1", "1");
        dbTools.insertRestaurantTables("1", "2", "0", "1", "1");
        dbTools.insertRestaurantTables("1", "2", "0", "1", "1");
        dbTools.insertRestaurantTables("1", "4", "0", "1", "1");
        dbTools.insertRestaurantTables("1", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("1", "6", "0", "0", "1");
        dbTools.insertRestaurantTables("1", "8", "0", "0", "1");
        dbTools.insertRestaurantTables("1", "8", "0", "0", "1");*/

        //Reservation
        // dbTools.insertReservation("3","1","1800","2000","24062018");
        // dbTools.insertReservation("6","3","1900","2100","29062018");
        dbTools = new DBTools(context);
        HashMap<String, String> queryValuesMap3 = new HashMap<String, String>();

        queryValuesMap3.put("restaurantName", "Poseidon");
        queryValuesMap3.put("description", restaurantdescription + "Greek Restaurant. Comfortable environment.Leisure");
        queryValuesMap3.put("rating", "1");
        queryValuesMap3.put("priceRange", "€€");
        queryValuesMap3.put("website", "www.poseidongarching.de");
        queryValuesMap3.put("phone", "0893206527");
        queryValuesMap3.put("address", "Freisinger Landstraße 3,Garching");
        queryValuesMap.put("cuisine", "Greek");
        dbTools.insertRestaurant(queryValuesMap3,1100,2300);

        dbTools.insertRestaurantTables("2", "2", "1", "0", "1");
        dbTools.insertRestaurantTables("2", "2", "1", "0", "1");
        dbTools.insertRestaurantTables("2", "4", "1", "0", "1");
        dbTools.insertRestaurantTables("2", "4", "1", "0", "1");
        dbTools.insertRestaurantTables("2", "6", "1", "0", "1");
        dbTools.insertRestaurantTables("2", "2", "0", "1", "1");
        dbTools.insertRestaurantTables("2", "4", "1", "1", "1");
        dbTools.insertRestaurantTables("2", "4", "1", "1", "1");
        dbTools.insertRestaurantTables("2", "6", "1", "1", "1");
        dbTools.insertRestaurantTables("2", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("2", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("2", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("2", "6", "0", "0", "1");
        dbTools.insertRestaurantTables("2", "6", "0", "0", "1");
        dbTools.insertRestaurantTables("2", "8", "0", "0", "1");
        dbTools.insertRestaurantTables("2", "8", "0", "0", "1");

        //reservation
        // dbTools.insertReservation(20,4,1800,1900,19062018);

        dbTools = new DBTools(context);
        HashMap<String, String> queryValuesMap4 = new HashMap<String, String>();

        queryValuesMap4.put("restaurantName", "Moti Mahal");
        queryValuesMap4.put("description", restaurantdescription + "Indian Restaurant. Supper. Comfortable environment . Leisure");
        queryValuesMap4.put("rating", "5");
        queryValuesMap4.put("priceRange", "€€");
        queryValuesMap4.put("website", "www.motimahal.eu");
        queryValuesMap4.put("phone", "0893201116");
        queryValuesMap4.put("address", "Schleißheimer Str. 4, 85748 Garching");
        queryValuesMap.put("cuisine", "Indian");
        dbTools.insertRestaurant(queryValuesMap4,1100,2300);

        dbTools.insertRestaurantTables("3", "2", "1", "0", "1");
        dbTools.insertRestaurantTables("3", "2", "1", "0", "1");
        dbTools.insertRestaurantTables("3", "4", "1", "0", "1");
        dbTools.insertRestaurantTables("3", "4", "1", "0", "1");
        dbTools.insertRestaurantTables("3", "6", "1", "0", "1");
        dbTools.insertRestaurantTables("3", "2", "0", "1", "1");
        dbTools.insertRestaurantTables("3", "4", "1", "1", "1");
        dbTools.insertRestaurantTables("3", "4", "1", "1", "1");
        dbTools.insertRestaurantTables("3", "6", "1", "1", "1");
        dbTools.insertRestaurantTables("3", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("3", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("3", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("3", "6", "0", "0", "1");
        dbTools.insertRestaurantTables("3", "6", "0", "0", "1");
        dbTools.insertRestaurantTables("3", "8", "0", "0", "1");
        dbTools.insertRestaurantTables("3", "8", "0", "0", "1");


        dbTools = new DBTools(context);
        HashMap<String, String> queryValuesMap5 = new HashMap<String, String>();

        queryValuesMap5.put("restaurantName", "Ristorante Pizzeria Roma");
        queryValuesMap5.put("description", restaurantdescription + "Italian Restaurant. Comfortable environment.Leisure");
        queryValuesMap5.put("rating", "3");
        queryValuesMap5.put("priceRange", "€€");
        queryValuesMap5.put("website", "www.ristorante-roma-garching.de");
        queryValuesMap5.put("phone", "089 32627530");
        queryValuesMap5.put("address", "Rathauspl. 7,Garching");
        queryValuesMap.put("cuisine", "Italian");
        dbTools.insertRestaurant(queryValuesMap5,1100,2300);

        dbTools.insertRestaurantTables("4", "2", "1", "0", "1");
        dbTools.insertRestaurantTables("4", "2", "0", "1", "1");
        dbTools.insertRestaurantTables("4", "4", "0", "1", "1");
        dbTools.insertRestaurantTables("4", "4", "1", "0", "1");
        dbTools.insertRestaurantTables("4", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("4", "6", "0", "0", "1");
        dbTools.insertRestaurantTables("4", "6", "0", "0", "1");
        dbTools.insertRestaurantTables("4", "8", "0", "0", "1");
        dbTools.insertRestaurantTables("4", "8", "0", "0", "1");

        dbTools = new DBTools(context);
        HashMap<String, String> queryValuesMap6 = new HashMap<String, String>();

        queryValuesMap6.put("restaurantName", "Honghong restaurant");
        queryValuesMap6.put("description", restaurantdescription + "Chinese Restaurant. Comfortable environment.Leisure");
        queryValuesMap6.put("rating", "3");
        queryValuesMap6.put("priceRange", "€€€");
        queryValuesMap6.put("website", "www.honghong.eu");
        queryValuesMap6.put("phone", "08936055185");
        queryValuesMap6.put("address", "Bürgerpl.10,Garching");
        queryValuesMap.put("cuisine", "Asian");
        dbTools.insertRestaurant(queryValuesMap6,1000,2200);

        dbTools.insertRestaurantTables("5", "2", "1", "0", "1");
        dbTools.insertRestaurantTables("5", "2", "0", "1", "1");
        dbTools.insertRestaurantTables("5", "4", "1", "0", "1");
        dbTools.insertRestaurantTables("5", "4", "0", "1", "1");
        dbTools.insertRestaurantTables("5", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("5", "6", "0", "1", "1");
        dbTools.insertRestaurantTables("5", "6", "0", "0", "1");
        dbTools.insertRestaurantTables("5", "8", "0", "0", "1");
        dbTools.insertRestaurantTables("5", "8", "0", "0", "1");
        dbTools.insertRestaurantTables("5", "4", "0", "0", "1");


        dbTools = new DBTools(context);
        HashMap<String, String> queryValuesMap7 = new HashMap<String, String>();

        queryValuesMap7.put("restaurantName", "Nano Sushi");
        queryValuesMap7.put("description", restaurantdescription + "Japanese Restaurant. Comfortable environment.Leisure");
        queryValuesMap7.put("rating", "4");
        queryValuesMap7.put("priceRange", "€€");
        queryValuesMap7.put("website", "www.nanosushi.de");
        queryValuesMap7.put("phone", "08932669812");
        queryValuesMap7.put("address", "Bürgerpl. 10,Garching");
        queryValuesMap.put("cuisine", "Japanese");
        dbTools.insertRestaurant(queryValuesMap7,1100,2300);

        dbTools.insertRestaurantTables("6", "2", "1", "0", "1");
        dbTools.insertRestaurantTables("6", "2", "0", "1", "1");
        dbTools.insertRestaurantTables("6", "4", "1", "0", "1");
        dbTools.insertRestaurantTables("6", "4", "0", "1", "1");
        dbTools.insertRestaurantTables("6", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("6", "6", "0", "1", "1");
        dbTools.insertRestaurantTables("6", "8", "0", "0", "1");


        dbTools = new DBTools(context);
        HashMap<String, String> queryValuesMap8 = new HashMap<String, String>();

        queryValuesMap8.put("restaurantName", "Farmers Steakhouse");
        queryValuesMap8.put("description", restaurantdescription + "Supper. Comfortable environment.Leisure");
        queryValuesMap8.put("rating", "2");
        queryValuesMap8.put("priceRange", "€€");
        queryValuesMap8.put("website", "www.farmerscafeandsteakhouse.de");
        queryValuesMap8.put("phone", "08932002988");
        queryValuesMap8.put("address", "schleißheimer Str.22,Garching");
        queryValuesMap.put("cuisine", "German");
        dbTools.insertRestaurant(queryValuesMap8,1100,2300);

        dbTools.insertRestaurantTables("7", "2", "1", "0", "1");
        dbTools.insertRestaurantTables("7", "2", "0", "1", "1");
        dbTools.insertRestaurantTables("7", "4", "1", "0", "1");
        dbTools.insertRestaurantTables("7", "4", "0", "1", "1");
        dbTools.insertRestaurantTables("7", "4", "0", "0", "1");
        dbTools.insertRestaurantTables("7", "6", "0", "1", "1");
        dbTools.insertRestaurantTables("7", "6", "0", "0", "1");
        dbTools.insertRestaurantTables("7", "8", "0", "0", "1");
        dbTools.insertRestaurantTables("7", "8", "0", "0", "1");
        dbTools.insertRestaurantTables("7", "4", "0", "0", "1");

    }
}
