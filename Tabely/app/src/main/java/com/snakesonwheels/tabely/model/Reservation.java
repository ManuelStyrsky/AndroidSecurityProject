package com.snakesonwheels.tabely.model;

import java.util.Date;

public class Reservation {
    private Date startingTime;
    private Table table;

    public Reservation(Table table, Date startingTime) {
        this.table = table;
        this.startingTime = startingTime;
    }

    public Table getTable() {
        return table;
    }

    public Date getStartingTime() {
        return startingTime;
    }
}
