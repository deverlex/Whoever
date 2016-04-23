package vn.whoever.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by spider man on 1/7/2016.
 */
public class NotifyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {

            for(int i = 0 ;i < 1000; ++i) {
                Log.d("Service", "service running...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }

            stopSelf();
        }
    });
}
