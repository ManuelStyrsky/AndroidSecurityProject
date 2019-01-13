package com.snakesonwheels.tabely.model;

import android.app.Activity;
import android.content.Intent;

import java.util.Calendar;

public class RestaurantRequest {

    public String restaurantId;
    public String userId;

    public String searchText;
    public String restaurantName;
    public String address;
    public String cuisine;
    public int open;
    public int close;
    public String phone;
    public String website;
    public String description;

    public int time;
    public int date;
    public int seats;

    public int outdoor;
    public int window;
    public String price;
    public int rating;

    public RestaurantRequest() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        this.setTime(hour, minute);
        this.setDate(day, month, year);
        this.seats = 2;
        this.address = "";
        this.searchText = "";
    }

    public RestaurantRequest(Intent i) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        this.setTime(hour, minute);
        this.setDate(day, month, year);
        this.seats = 2;

        this.restaurantId = i.getStringExtra("restaurantId");
        this.userId = i.getStringExtra("userId");

        this.searchText = i.getStringExtra("search");
        this.restaurantName = i.getStringExtra("restaurantName");
        this.address = i.getStringExtra("address");
        this.cuisine = i.getStringExtra("cuisine");
        this.open = i.getIntExtra("open", 0);
        this.close = i.getIntExtra("close", 0);
        this.phone = i.getStringExtra("phone");
        this.website = i.getStringExtra("website");
        this.description = i.getStringExtra("description");

        this.time = i.getIntExtra("time", time);
        this.date = i.getIntExtra("date", date);
        this.seats = i.getIntExtra("seats", seats);

        this.outdoor = i.getIntExtra("outdoor", 0);
        this.window = i.getIntExtra("window", 0);
        this.price = i.getStringExtra("price");
        this.rating = i.getIntExtra("rating", 0);
    }

    public Intent createIntent(Activity from, Activity to) {
        Intent i = new Intent(from, to.getClass());

        i.putExtra("restaurantId", restaurantId);
        i.putExtra("userId", userId);

        i.putExtra("search", searchText);
        i.putExtra("restaurantName", restaurantName);
        i.putExtra("address", address);
        i.putExtra("cuisine", cuisine);
        i.putExtra("open", open);
        i.putExtra("close", close);
        i.putExtra("phone", phone);
        i.putExtra("website", website);
        i.putExtra("description", description);

        i.putExtra("time", time);
        i.putExtra("date", date);
        i.putExtra("seats", seats);

        i.putExtra("outdoor", outdoor);
        i.putExtra("window", window);
        i.putExtra("price", price);
        i.putExtra("rating", rating);

        return i;
    }

    public void setDate(int day, int month, int year) {
        this.date = (day * 1000000 + month * 10000 + year);
    }

    public void setTime(int hours, int minutes) {
        this.time = (hours * 100 + minutes);
    }

    public String getDateString() {
        String d = "" + date / 1000000;
        String m = "" + (date % 1000000) / 10000;
        if (d.length() < 2) d = "0" + d;
        if (m.length() < 2) m = "0" + m;
        return d + "." + m + ".";
    }

    public String getTimeString(int time) {
        String h = "" + time / 100;
        String m = "" + time % 100;
        if (h.length() < 2) h = "0" + h;
        if (m.length() < 2) m = "0" + m;
        return h + ":" + m;
    }

    public String getTimeString() {
        return getTimeString(this.time);
    }

    public int getTableTime() {
        int hours = time / 100;
        int minutes = time % 100;
        if (minutes < 15) {
            minutes = 0;
        } else if (minutes < 45) {
            minutes = 30;
        } else {
            minutes = 0;
            hours = (hours + 1) % 24;
        }
        return hours * 100 + minutes;
    }

    public int getBeforeTableTime() {
        int hours = time / 100;
        int minutes = time % 100;
        if (minutes < 15) {
            minutes = 0;
        } else if (minutes < 45) {
            minutes = 30;
        } else {
            minutes = 0;
            hours = (hours + 1) % 24;
        }
        int middle = hours * 100 + minutes;

        if (minutes == 0) {
            return ((hours - 1) % 24) * 100 + 30;
        } else {
            return hours * 100;
        }
    }

    public int getAfterTableTime() {
        int hours = time / 100;
        int minutes = time % 100;
        if (minutes < 15) {
            minutes = 0;
        } else if (minutes < 45) {
            minutes = 30;
        } else {
            minutes = 0;
            hours = (hours + 1) % 24;
        }

        int middle = hours * 100 + minutes;

        if (minutes == 0) {
            return hours * 100 + 30;
        } else {
            return ((hours + 1) % 24) * 100;
        }
    }

    public int parseTime(String time) {
        int out = 0;
        
        char[] chars = time.toCharArray();
        int[] ints = new int[chars.length];

        for(int i = 0; i<ints.length; i++) {
            ints[i] = Character.getNumericValue(chars[i]);
        }

        if (ints.length == 3) {
            out += ints[0] * 100;
            out += ints[1] * 10;
            out += ints[2];
        } else if (ints.length == 4 && ints[0] == '0') {
            out += ints[1] * 100;
            out += ints[2] * 10;
            out += ints[3];
        } else if (ints.length == 4) {
            out += ints[0] * 1000;
            out += ints[1] * 100;
            out += ints[2] * 10;
            out += ints[3];
        } else
            out = -1;

        return  out;
    }
}
