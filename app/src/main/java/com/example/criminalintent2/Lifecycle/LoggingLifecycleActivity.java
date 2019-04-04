package com.example.criminalintent2.Lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoggingLifecycleActivity extends AppCompatActivity {

    public static final String ON_CREATE = "onCreate";
    public static final String ON_POST_CREATE = "onPostCreate";
    public static final String ON_CONFIGURATION_CHANGED = "onConfigurationChanged";
    public static final String ON_POST_RESUME = "onPostResume";
    public static final String ON_START = "onStart";
    public static final String ON_STOP = "onStop";
    public static final String ON_DESTROY = "onDestroy";
    public static final String ON_LOW_MEMORY = "onLowMemory";
    public static final String ON_PAUSE = "onPause";
    public static final String ON_NEW_INTENT = "onNewIntent";
    public static final String ON_RESTART = "onRestart";
    public static final String ON_RESUME = "onResume";
    public static final String ON_ATTACHED_TO_WINDOW = "onAttachedToWindow";
    public static final String ON_DETACHED_FROM_WINDOW = "onDetachedFromWindow";
    public static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    public static final String ON_RESTORE_INSTANCE_STATE = "onRestoreInstanceState";
    public static final String ON_OPTIONS_ITEM_SELECTED = "onOptionsItemSelected";
    public static final String ON_CREATE_OPTIONS_MENU = "onCreateOptionsMenu";
    public static final String ON_PREPARE_OPTIONS_MENU = "onPrepareOptionsMenu";
    public static final String ON_CONTENT_CHANGED = "onContentChanged";
    public static final String ON_ATTACH_FRAGMENT = "onAttachFragment";
    public static final String ON_USER_LEAVE_HINT = "onUserLeaveHint";

    protected static Map<String, Boolean> callbacks = new HashMap<>();

    static {
        callbacks.put(ON_CREATE, true);
        callbacks.put(ON_POST_CREATE, true);
        callbacks.put(ON_CONFIGURATION_CHANGED, true);
        callbacks.put(ON_POST_RESUME, true);
        callbacks.put(ON_START, true);
        callbacks.put(ON_STOP, true);
        callbacks.put(ON_DESTROY, true);
        callbacks.put(ON_LOW_MEMORY, true);
        callbacks.put(ON_PAUSE, true);
        callbacks.put(ON_NEW_INTENT, true);
        callbacks.put(ON_RESTART, true);
        callbacks.put(ON_RESUME, true);
        callbacks.put(ON_ATTACHED_TO_WINDOW, true);
        callbacks.put(ON_DETACHED_FROM_WINDOW, true);
        callbacks.put(ON_SAVE_INSTANCE_STATE, true);
        callbacks.put(ON_RESTORE_INSTANCE_STATE, true);
        callbacks.put(ON_OPTIONS_ITEM_SELECTED, true);
        callbacks.put(ON_CREATE_OPTIONS_MENU, true);
        callbacks.put(ON_PREPARE_OPTIONS_MENU, true);
        callbacks.put(ON_CONTENT_CHANGED, true);
        callbacks.put(ON_ATTACH_FRAGMENT, true);
        callbacks.put(ON_USER_LEAVE_HINT, true);
    }

    private static int numInstances = 0;
    private String name = getClass().getSimpleName();
    protected static final String TAG = "XtremeLifecycle";

    Lifecycle mLifecycle;

    protected void beforeFather(String methodName) {
        if (callbacks.containsKey(methodName)) {
            String beforeCallingFather = "BEFORE";
         //  Log.d(TAG, beforeCallingFather + " " + methodName + " state:" + mLifecycle.getCurrentState().name() + " name: " + name);
            Log.d(TAG, methodName + "(" + mLifecycle.getCurrentState().name()+ ")"+ " -> " + name);
        }
    }

    protected void afterFather(String methodName) {
        if (callbacks.containsKey(methodName)) {
            String afterCallingFather = "AFTER";
            //Log.d(TAG, afterCallingFather + " " + methodName + " state:" + mLifecycle.getCurrentState().name() + " name: " + name);
          // Log.d(TAG, methodName + "(" + mLifecycle.getCurrentState().name()+ ")"+ " -> " + name);
        }
    }

    protected void anyInstant(String methodName) {
        String any = "ANY INSTANT";
        Log.d(TAG, any + " " + methodName + " state:" + mLifecycle.getCurrentState().name() + " name: " + name);
    }

    public LoggingLifecycleActivity() {
        name = name.concat(" " + String.valueOf(++numInstances));
        mLifecycle = getLifecycle();
        mLifecycle.addObserver(new ActivityLifecycleObserver());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {  // INITIALIZED
        if (savedInstanceState == null) {
            beforeFather("FIRST TIME !!!");
        }
        beforeFather(ON_CREATE);
        super.onCreate(savedInstanceState);
        afterFather(ON_CREATE);
    }

    @Override
    public void onContentChanged() {  // INITIALIZED
        beforeFather(ON_CONTENT_CHANGED);
        super.onContentChanged();
        afterFather(ON_CONTENT_CHANGED);
    }

    @Override
    protected void onRestart() {  //CREATED    ( BACK WITHOUT ROTATION )
        beforeFather(ON_RESTART);
        super.onRestart();
        afterFather(ON_RESTART);
    }

    @Override
    protected void onStart() {    // CREATED
        beforeFather(ON_START);
        super.onStart();
        afterFather(ON_START);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) { // STARTED   (ROTATION )
        beforeFather(ON_RESTORE_INSTANCE_STATE);
        super.onRestoreInstanceState(savedInstanceState);
        afterFather(ON_RESTORE_INSTANCE_STATE);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {  // STARTED
        beforeFather(ON_POST_CREATE);
        super.onPostCreate(savedInstanceState);
        afterFather(ON_POST_CREATE);
    }

    @Override
    protected void onResume() {  // STARTED
        beforeFather(ON_RESUME);
        super.onResume();
        afterFather(ON_RESUME);
    }

    @Override
    protected void onPostResume() {  // RESUMED
        beforeFather(ON_POST_RESUME);
        super.onPostResume();
        afterFather(ON_POST_RESUME);
    }

    @Override
    public void onAttachedToWindow() {  // RESUMED
        beforeFather(ON_ATTACHED_TO_WINDOW);
        super.onAttachedToWindow();
        afterFather(ON_ATTACHED_TO_WINDOW);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // RESUMED
        beforeFather(ON_CREATE_OPTIONS_MENU);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {  // RESUMED
        beforeFather(ON_PREPARE_OPTIONS_MENU);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPause() {  // STARTED
        beforeFather(ON_PAUSE);
        super.onPause();
        afterFather(ON_PAUSE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        beforeFather(ON_SAVE_INSTANCE_STATE);   // STARTED
        super.onSaveInstanceState(outState);
        afterFather(ON_SAVE_INSTANCE_STATE);   // CREATED
    }

    @Override
    protected void onStop() {  // CREATED
        beforeFather(ON_STOP);
        super.onStop();
        afterFather(ON_STOP);
    }

    @Override
    protected void onDestroy() {   // DESTROYED
        beforeFather(ON_DESTROY);
        super.onDestroy();
        afterFather(ON_DESTROY);
    }

    @Override
    public void onDetachedFromWindow() {  // DESTROYED
        beforeFather(ON_DETACHED_FROM_WINDOW);
        super.onDetachedFromWindow();
        afterFather(ON_DETACHED_FROM_WINDOW);
    }

    @Override
    protected void onUserLeaveHint() {
        beforeFather(ON_USER_LEAVE_HINT);
        super.onUserLeaveHint();
        beforeFather(ON_USER_LEAVE_HINT);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        beforeFather(ON_CONFIGURATION_CHANGED);
        super.onConfigurationChanged(newConfig);
        afterFather(ON_CONFIGURATION_CHANGED);
    }

    @Override
    public void onLowMemory() {
        beforeFather(ON_LOW_MEMORY);
        super.onLowMemory();
        afterFather(ON_LOW_MEMORY);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        beforeFather(ON_NEW_INTENT);
        super.onNewIntent(intent);
        afterFather(ON_LOW_MEMORY);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        beforeFather(ON_OPTIONS_ITEM_SELECTED);
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
//                return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        beforeFather(ON_ATTACH_FRAGMENT);
        super.onAttachFragment(fragment);
        afterFather(ON_ATTACH_FRAGMENT);
    }
}
