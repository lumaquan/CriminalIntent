package com.example.criminalintent2.Lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

public class LoggingLifecycleObserver implements LifecycleObserver {

    private static final String ON_CREATE_EVENT = "ON_CREATE";
    private static final String ON_START_EVENT = "ON_START";
    private static final String ON_RESUME_EVENT = "ON_RESUME";
    private static final String ON_PAUSE_EVENT = "ON_PAUSE";
    private static final String ON_STOP_EVENT = "ON_STOP";
    private static final String ON_DESTROY_EVENT = "ON_DESTROY";

    private String name = getClass().getSimpleName();
    private static final String TAG = "XtremeLifecycle";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void myOnCreate() {
        Log.d(TAG, ON_CREATE_EVENT +" " + name);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void myOnStart() {
        Log.d(TAG, ON_START_EVENT + " " +  name);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void myOnResume() {
        Log.d(TAG, ON_RESUME_EVENT  + " " +  name);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void myOnStop() {
        Log.d(TAG, ON_STOP_EVENT  + " " +  name);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void myOnDestroy() {
        Log.d(TAG, ON_DESTROY_EVENT  + " " +   name);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void myOnPause() {
        Log.d(TAG, ON_PAUSE_EVENT  + " " +   name);
    }

}
