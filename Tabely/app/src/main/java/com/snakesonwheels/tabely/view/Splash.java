package com.snakesonwheels.tabely.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.snakesonwheels.tabely.R;


public class Splash extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash);

            Thread timer = new Thread() {
                public void run(){
                    try{
                        sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }finally {
                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);
                    }
                }
            };

            timer.start();

        }

        @Override
        protected void onPause() {
            super.onPause();
            finish();
        }
    }
