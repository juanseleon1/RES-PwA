package com.leon.sasepwa;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TryService extends Service {
    public TryService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
