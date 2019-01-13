package com.snakesonwheels.tabely.model;

import java.util.Calendar;
import java.util.Date;

public class MySingleton {
    private static final MySingleton ourInstance = new MySingleton();
    private Date selectedDate;
    private Calendar selectedCalendar;

    private int reserveTable;
    private int selectedTime;

    public static MySingleton getInstance() {
        return ourInstance;
    }

    private MySingleton() {
        selectedDate = Calendar.getInstance().getTime();
        selectedCalendar = Calendar.getInstance();
    }

    public Date getSelectedDate(){
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate){
        this.selectedDate = selectedDate;
    }

    public Calendar getSelectedCalendar(){
        return selectedCalendar;
    }

    public void setSelectedCalendar(Calendar selectedCalendar){
        this.selectedCalendar = selectedCalendar;
    }
    public int getReserveTable() {
        return reserveTable;
    }

    public int getSelectedTime() {
        return selectedTime;
    }

    public void setReserveTable(int reserveTable) {
        this.reserveTable = reserveTable;
    }

    public void setSelectedTime(int selectedTime) {
        this.selectedTime = selectedTime;
    }
}
