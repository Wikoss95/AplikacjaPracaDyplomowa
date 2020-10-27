package com.wn.aplikacjapracadyplomowa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "WN_MainActivity";
    public volatile boolean FLAG = false;
    public String mes = "Otrzymane dane";
    public String sentMessage = "WIADOMOSC";

    //inicjalizacja nowej instancji + tworzenie obiektu

    //tylko inicjalizacja
    NewClassHandling sendMovementOrder;
    //    NewClassHandling tasks;
    RadioButton isConnectedButton;
    RadioButton isBoundButton;
    RadioButton isClosedButton;

    private Button connectButton;
    private Button setButton;
    private Button clearButton;

    private int jointBasic1 = 0;
    private int jointBasic2 = 0;

    private int positionValueX;
    private int positionValueY;
    private int positionValueZ;
    private int orientationValueR;
    private int orientationValueP;
    private int orientationValueY;

    private SeekBar positionSeekBarX;
    private TextView positionChangeIndicatorX;
    private TextView positionCurrentIndicatorX;
    private SeekBar positionSeekBarY;
    private TextView positionChangeIndicatorY;
    private TextView positionCurrentIndicatorY;
    private SeekBar positionSeekBarZ;
    private TextView positionChangeIndicatorZ;
    private TextView positionCurrentIndicatorZ;
    private SeekBar orientationSeekBarR;
    private TextView orientationChangeIndicatorR;
    private TextView orientationCurrentIndicatorR;
    private SeekBar orientationSeekBarP;
    private TextView orientationChangeIndicatorP;
    private TextView orientationCurrentIndicatorP;
    private SeekBar orientationSeekBarY;
    private TextView orientationChangeIndicatorY;
    private TextView orientationCurrentIndicatorY;


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButton = (Button) findViewById(R.id.movementButtonID);
        clearButton = (Button) findViewById(R.id.clearAllButtonID);
        connectButton = (Button) findViewById(R.id.connectButtonID);

        positionSeekBarX = (SeekBar) findViewById(R.id.positionSeekBarX);
        positionChangeIndicatorX = (TextView) findViewById(R.id.positionChangeIndicatorX);
        positionCurrentIndicatorX = (TextView) findViewById(R.id.positionCurrentIndicatorX);

        positionSeekBarY = (SeekBar) findViewById(R.id.positionSeekBarY);
        positionChangeIndicatorY = (TextView) findViewById(R.id.positionChangeIndicatorY);
        positionCurrentIndicatorY = (TextView) findViewById(R.id.positionCurrentIndicatorY);

        positionSeekBarZ = (SeekBar) findViewById(R.id.positionSeekBarZ);
        positionChangeIndicatorZ = (TextView) findViewById(R.id.positionChangeIndicatorZ);
        positionCurrentIndicatorZ = (TextView) findViewById(R.id.positionCurrentIndicatorZ);

        orientationSeekBarR = (SeekBar) findViewById(R.id.orientationSeekBarR);
        orientationChangeIndicatorR = (TextView) findViewById(R.id.orientationChangeIndicatorR);
        orientationCurrentIndicatorR = (TextView) findViewById(R.id.orientationCurrentIndicatorR);

        orientationSeekBarP = (SeekBar) findViewById(R.id.orientationnSeekBarP);
        orientationChangeIndicatorP = (TextView) findViewById(R.id.orientationChangeIndicatorP);
        orientationCurrentIndicatorP = (TextView) findViewById(R.id.orientationCurrentIndicatorP);

        orientationSeekBarY = (SeekBar) findViewById(R.id.orientationnSeekBarY);
        orientationChangeIndicatorY = (TextView) findViewById(R.id.orientationChangeIndicatorY);
        orientationCurrentIndicatorY = (TextView) findViewById(R.id.orientationCurrentIndicatorY);


        positionSeekBarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.d("SB", "Joint 1 progress");
                internalValueX = (positionSeekBarX.getProgress() - 90);
                positionChangeIndicatorX.setText(internalValueX + "\u00B0");
                positionChangeIndicatorX.setTextSize(14);
                positionChangeIndicatorX.setTextColor(Color.BLACK);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Stop");
                positionChangeIndicatorX.setTextSize(12);
                positionChangeIndicatorX.setTextColor(Color.DKGRAY);
                positionValueX = internalValueX;
            }

            int internalValueX;
        });

        positionSeekBarY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.d("SB", "Joint 1 progress");
                internalValueY = (positionSeekBarY.getProgress() - 90);
                positionChangeIndicatorY.setText(internalValueY + "\u00B0");
                positionChangeIndicatorY.setTextSize(14);
                positionChangeIndicatorY.setTextColor(Color.BLACK);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Stop");
                positionChangeIndicatorY.setTextSize(12);
                positionChangeIndicatorY.setTextColor(Color.DKGRAY);
                positionValueY = internalValueY;
            }

            int internalValueY;
        });

        positionSeekBarZ.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.d("SB", "Joint 1 progress");
                internalValueZ = (positionSeekBarZ.getProgress() - 90);
                positionChangeIndicatorZ.setText(internalValueZ + "\u00B0");
                positionChangeIndicatorZ.setTextSize(14);
                positionChangeIndicatorZ.setTextColor(Color.BLACK);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Stop");
                positionChangeIndicatorZ.setTextSize(12);
                positionChangeIndicatorZ.setTextColor(Color.DKGRAY);
                positionValueZ = internalValueZ;
            }

            int internalValueZ;
        });

        orientationSeekBarR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.d("SB", "Joint 1 progress");
                internalValueR = (orientationSeekBarR.getProgress() - 90);
                orientationChangeIndicatorR.setText(internalValueR + "\u00B0");
                orientationChangeIndicatorR.setTextSize(14);
                orientationChangeIndicatorR.setTextColor(Color.BLACK);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Stop");
                orientationChangeIndicatorR.setTextSize(12);
                orientationChangeIndicatorR.setTextColor(Color.DKGRAY);
                orientationValueR = internalValueR;
            }

            int internalValueR;
        });

        orientationSeekBarP.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.d("SB", "Joint 1 progress");
                internalValueP = (orientationSeekBarP.getProgress() - 90);
                orientationChangeIndicatorP.setText(internalValueP + "\u00B0");
                orientationChangeIndicatorP.setTextSize(14);
                orientationChangeIndicatorP.setTextColor(Color.BLACK);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Stop");
                orientationChangeIndicatorP.setTextSize(12);
                orientationChangeIndicatorP.setTextColor(Color.DKGRAY);
                orientationValueP = internalValueP;
            }

            int internalValueP;
        });

        orientationSeekBarY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.d("SB", "Joint 1 progress");
                internalValueY = (orientationSeekBarY.getProgress() - 90);
                orientationChangeIndicatorY.setText(internalValueY + "\u00B0");
                orientationChangeIndicatorY.setTextSize(14);
                orientationChangeIndicatorY.setTextColor(Color.BLACK);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "Joint 1 Set-Stop");
                orientationChangeIndicatorY.setTextSize(12);
                orientationChangeIndicatorY.setTextColor(Color.DKGRAY);
                orientationValueY = internalValueY;
            }

            int internalValueY;
        });


//        readAngle1.setText( jointBasic1 + "\u00B0");
//        readAngle1.setText( jointBasic2 + "\u00B0");

        sendMovementOrder = new NewClassHandling();
        sendMovementOrder.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sendMovementOrder.stop();
//        tasks.stop();
    }

    public void startConnection(View view) {
        //uruchamianie nowego watku
        try {
            sendMovementOrder.handler.post(new Runnable() {
                @Override
                public void run() {
                    sendMovementOrder.startConnection();
                    if (sendMovementOrder.mySocket.isConnected()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "Połączono z urządzeniem!", Toast.LENGTH_SHORT).show();
                                connectButton.setBackgroundColor(Color.DKGRAY);
                            }
                        });
                    }
                }
            });

        } catch (Exception e) {
            Log.e(TAG, e.toString(), e);
        }
    }


    // nowe zadanie przekazuje do handlera, ktory jest w nowej klasie pewne instrukcje, ten wrzuca
    // do loopera i pozniej wykonuje
    public void sendMessage(View view) {
        final NewClassHandling.MessageSender messageSender = sendMovementOrder.myMessageSender;
//        recieveReadings.handler.post(new Runnable() {
        messageSender.innerHandler.post(new Runnable() {
            @Override
            public void run() {
                messageSender.send(sentMessage);
                Log.d(TAG, "MESSAGE SENT");
            }
        });
    }

//    public void messageReciever(final String message){
//
//        recieveReadings.handler.post(new Runnable() {
//            @Override
//            public void run() {
//                while (!FLAG){
//                    Log.d(TAG, "Sth to be acquired: " + message);
//                    SystemClock.sleep(1000);
//                }
//            }
//        });

}
