package com.snakesonwheels.tabely.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Table {
    private String name;

    public String getName() {
        return name;
    }
    private Reservation reservation;

    private List<Reservation> reservations = new ArrayList<Reservation>();

    public Table(String name) {
        this.name = name;
    }

    public Reservation reserveTable(Date startingTime) {
        Reservation reservation = new Reservation(this, startingTime);
        reservations.add(reservation);
        return reservation;
    }



    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public Boolean isReserved() {
        return reservations.size() != 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
