package com.leon.sasepwa;

import android.os.Handler;
import android.os.HandlerThread;

public class DispatchQueue extends HandlerThread {

    private Handler handler;
    public DispatchQueue(String name){
        super(name);
    }


    public void waitUntilReady (){
        handler = new Handler(getLooper());
    }

    public void dispatchAsync (Runnable task){
        handler.post(task);
    }


    public void removePendingJobs(){
        handler.removeCallbacksAndMessages(null);
    }
}
