package com.wn.aplikacjapracadyplomowa;

import android.os.Looper;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class NewClassHandling extends Thread {
    private static final String TAG = "WN_NewClassHandling";

    //inicjalizacja handlera, ktory przynalezy do tego watku pobocznego
    public Handler handler;
    public Socket mySocket = new Socket();
    private SocketAddress socketAddress = new InetSocketAddress("192.168.1.99", 9999);
    public MessageSender myMessageSender;

    public void startConnection() {
        try {
            myMessageSender.start();
            mySocket.connect(socketAddress);
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
        // petla nieskonczona FOR
        Looper.loop();
        Log.d(TAG, "End of run() in external class");
    }


    // INNER CLASS
    class MessageSender extends Thread {

        Handler innerHandler;
        private PrintWriter printWriter;

        public void send(String movement_order) {
            try {
                printWriter = new PrintWriter(mySocket.getOutputStream(), true);
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


}