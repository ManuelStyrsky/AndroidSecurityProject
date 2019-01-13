package com.snakesonwheels.tabely;

import com.snakesonwheels.tabely.model.RestaurantRequest;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RestaurantRequestTest {

    @Test public void testDateStringCreation(){
        RestaurantRequest request = new RestaurantRequest();
        int day = 20;
        int month = 1;
        int year = 2007;
        String dateString = day+".0"+month+".";
        System.out.println(dateString);
        request.setDate(day,month,year);
        String calculatedString = request.getDateString();
        System.out.println(calculatedString);
        assertTrue(calculatedString.equals(dateString));
    }

    @Test public void testTimeStringCreation() {
        RestaurantRequest request = new RestaurantRequest();
        int hours = 20;
        int minutes = 15;
        String timeString = hours+":"+minutes;
        System.out.println(timeString);
        request.setTime(hours,minutes);
        String calculatedString = request.getTimeString();
        System.out.println(calculatedString);
        assertTrue(calculatedString.equals(timeString));
    }

    @Test public void testBeforeAndAfterTime() {
        RestaurantRequest request = new RestaurantRequest();
        int hours = 19;
        int minutes = 48;
        request.setTime(hours,minutes);
        int beforeTimeExpected = 1930;
        int tableTimeExpected = 2000;
        int afterTimeExpected = 2030;
        int beforeTimeActual = request.getBeforeTableTime();
        int tableTimeActual = request.getTableTime();
        int afterTimeActual = request.getAfterTableTime();
        System.out.println(beforeTimeActual);
        System.out.println(tableTimeActual);
        System.out.println(afterTimeActual);
        assertTrue(beforeTimeActual == beforeTimeExpected);
        assertTrue(tableTimeExpected == tableTimeActual);
        assertTrue(afterTimeExpected == afterTimeActual);
    }

}
