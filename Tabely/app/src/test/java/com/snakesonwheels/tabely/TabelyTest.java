package com.snakesonwheels.tabely;

import com.snakesonwheels.tabely.view.HomeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>

public class TabelyTest {

    //insert more attributes here for further tests
    private HomeActivity homeActivity;

    @Before
    public void setUp() throws Exception {
        homeActivity = new HomeActivity();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTime() {
        assertTrue("starting time is 20:00", homeActivity.textTime.equals("20:00"));
    }

//just test the integration into bamboo
}
 */