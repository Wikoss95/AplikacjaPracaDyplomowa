package com.wn.aplikacjapracadyplomowa;

import android.os.Looper;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class NewClassHandling extends Thread {
    private static final String TAG = "WN_NewClassHandling";

    //inicjalizacja handlera, ktory przynalezy do tego watku pobocznego
    public Handler handler;
    public Socket mySocket = new Socket();
    private SocketAddress socketAddress = new InetSocketAddress("192.168.1.99", 9999);
    public MessageSender myMessageSender;
    private MainActivity activity;
    private MessageReader myMessageReader;
    private volatile boolean running = true;

    public NewClassHandling(MainActivity activity) {
        this.activity = activity;
    }

    public void startConnection() {
        try {
            myMessageSender.start();
            mySocket.connect(socketAddress);
//            myMessageReader.start();    // WLACZANIE CZYTANIA ;)
        } catch (IOException e) {
            Log.e(TAG, e.toString(), e);
        }
    }

    @Override
    public void run() {
        // Przygotowanie do wykonywania pracy Loopera -> do niego odwoluje sie automatycznie handler
        Looper.prepare();
        // Utworzenie nowego obiektu "handler"
        handler = new Handler();
        myMessageSender = new MessageSender();
        myMessageReader = new MessageReader();
        // petla nieskonczona FOR
        Looper.loop();
        Log.d(TAG, "End of run() in external class");
    }

    public void stopReading() {
        running = false;
        try {
            mySocket.close();
        } catch (IOException e) {
            Log.e(TAG, e.toString(), e);
        }
    }

    // INNER CLASS
    class MessageSender extends Thread {

        Handler innerHandler;

        public void send(String movement_order) {
            try {
                PrintWriter printWriter = new PrintWriter(mySocket.getOutputStream(), true);
                printWriter.write(movement_order);
                printWriter.close();
            } catch (IOException e) {
                Log.e(TAG, e.toString(), e);
            }

        }

        @Override
        public void run() {
            Looper.prepare();
            innerHandler = new Handler();
            Looper.loop();
            Log.d(TAG, "End of inner class job = sender reports having sent the message");
        }
    }


    class MessageReader extends Thread {

        @Override
        public void run() {
            while (running) {
                try {
                    sleep(100);
                    Log.e("ROZPOCZECIE","Rozpoczecie");
                    ByteArrayOutputStream result = new ByteArrayOutputStream();
                    BufferedInputStream inputStreamReader = new BufferedInputStream(mySocket.getInputStream());

                    byte[] buffer = new byte[32];
                    int length;
                    while ((length = inputStreamReader.read(buffer)) != -1) {
                        result.write(buffer, 0, length);
                        Log.v(TAG, "ReadByte: " + new String(buffer, StandardCharsets.UTF_8));
                    }
                    String message = new String(result.toByteArray(), StandardCharsets.UTF_8);
                    Log.d(TAG, "Message: " + message);
                    String[] messageSplit = message.split("/");
                    if (messageSplit.length == 6) {
                        activity.setCurrentResponse(Float.parseFloat(messageSplit[0]), Float.parseFloat(messageSplit[1]), Float.parseFloat(messageSplit[2]), Float.parseFloat(messageSplit[3]), Float.parseFloat(messageSplit[4]), Float.parseFloat(messageSplit[5]));
                    }
                } catch (Exception e) {
                    Log.e(TAG, e.toString(), e);
                }
            }
        }
    }

}
