package fh.at.servlcetestapp;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private Thread backgroundThread;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "Service wurde erstellt");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String message = intent.getStringExtra("message");

        backgroundThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Log.d("MyService", "Nachricht: " + message + " | Zeit: " + System.currentTimeMillis());
                try {
                    Thread.sleep(1000); // 1 Sekunde warten
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        backgroundThread.start();

        return START_NOT_STICKY; // Laut Aufgabe
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // Wir brauchen kein Binding
    }

    @Override
    public void onDestroy() {
        if (backgroundThread != null) {
            backgroundThread.interrupt();
        }
        super.onDestroy();
    }
}
