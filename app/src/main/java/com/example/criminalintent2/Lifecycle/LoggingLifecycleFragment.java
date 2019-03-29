package com.example.criminalintent2.Lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;


public class LoggingLifecycleFragment extends Fragment {

    public static final String ON_ATTACH = "onAttach";
    public static final String ON_CREATE = "onCreate";
    public static final String ON_CREATE_VIEW = "onCreateView";
    public static final String ON_VIEW_CREATED = "onViewCreated";
    public static final String ON_ACTIVITY_CREATED = "onActivityCreated";
    public static final String ON_CONFIGURATION_CHANGED = "onConfigurationChanged";
    public static final String ON_START = "onStart";
    public static final String ON_STOP = "onStop";
    public static final String ON_DESTROY = "onDestroy";
    public static final String ON_LOW_MEMORY = "onLowMemory";
    public static final String ON_PAUSE = "onPause";
    public static final String ON_RESUME = "onResume";
    public static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    public static final String ON_VIEW_STATE_RESTORED = "onViewStateRestored";
    public static final String ON_DESTROY_VIEW = "onDestroyView";
    public static final String ON_DETACH = "onDetach";
    public static final String ON_OPTIONS_ITEM_SELECTED = "onOptionsItemSelected";
    public static final String ON_CREATE_OPTIONS_MENU = "onCreateOptionsMenu";
    public static final String ON_PREPARE_OPTIONS_MENU = "onPrepareOptionsMenu";
    public static final String ON_ATTACH_FRAGMENT = "onAttachFragment";
    private static int numInstances = 0;
    private String name = getClass().getSimpleName();
    protected static final String TAG = "XtremeLifecycler";

    protected static Map<String, Boolean> callbacks = new HashMap<>();

    static {
        callbacks.put(ON_ATTACH, true);
        callbacks.put(ON_CREATE, true);
        callbacks.put(ON_CREATE_VIEW, true);
        callbacks.put(ON_VIEW_CREATED, true);
        callbacks.put(ON_ACTIVITY_CREATED, true);
        callbacks.put(ON_CONFIGURATION_CHANGED, true);
        callbacks.put(ON_START, true);
        callbacks.put(ON_STOP, true);
        callbacks.put(ON_DESTROY, true);
        callbacks.put(ON_LOW_MEMORY, true);
        callbacks.put(ON_PAUSE, true);
        callbacks.put(ON_RESUME, true);
        callbacks.put(ON_SAVE_INSTANCE_STATE, true);
        callbacks.put(ON_VIEW_STATE_RESTORED, true);
        callbacks.put(ON_DESTROY_VIEW, true);
        callbacks.put(ON_DETACH, true);
        callbacks.put(ON_OPTIONS_ITEM_SELECTED, true);
        callbacks.put(ON_CREATE_OPTIONS_MENU, true);
        callbacks.put(ON_PREPARE_OPTIONS_MENU, true);
        callbacks.put(ON_ATTACH_FRAGMENT, true);
    }

    Lifecycle mLifecycle;

    protected void beforeFather(String methodName) {
        if (callbacks.containsKey(methodName)) {
            String beforeCallingFather = "BEFORE";
            //   Log.d(TAG, beforeCallingFather + " " + methodName + " state:" + mLifecycle.getCurrentState().name() + " name: " + name);
            Log.d(TAG, methodName + "(" + mLifecycle.getCurrentState().name() + ")" + " -> " + name);
        }
    }

    protected void afterFather(String methodName) {
        if (callbacks.containsKey(methodName)) {
            String afterCallingFather = "AFTER";
//            Log.d(TAG, afterCallingFather + " " + methodName + " state:" + mLifecycle.getCurrentState().name() + " name: " + name);
            //    Log.d(TAG, methodName + "(" + mLifecycle.getCurrentState().name()+ ")"+ " -> " + name);

        }
    }

    public LoggingLifecycleFragment() {
        name = name.concat(" " + String.valueOf(++numInstances));
        mLifecycle = getLifecycle();
        mLifecycle.addObserver(new FragmentLifecyclerObserver());
    }

    @Override
    public void onAttach(Context context) {
        setHasOptionsMenu(true);
        beforeFather(ON_ATTACH);
        super.onAttach(context);
        afterFather(ON_ATTACH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        beforeFather(ON_CREATE);
        super.onCreate(savedInstanceState);
        afterFather(ON_CREATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        beforeFather(ON_CREATE_VIEW);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        beforeFather(ON_VIEW_CREATED);
        super.onViewCreated(view, savedInstanceState);
        afterFather(ON_VIEW_CREATED);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        beforeFather(ON_ACTIVITY_CREATED);
        super.onActivityCreated(savedInstanceState);
        afterFather(ON_ACTIVITY_CREATED);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        beforeFather(ON_CONFIGURATION_CHANGED);
        super.onConfigurationChanged(newConfig);
        afterFather(ON_CONFIGURATION_CHANGED);
    }

    @Override
    public void onStart() {
        beforeFather(ON_START);
        super.onStart();
        afterFather(ON_START);
    }

    @Override
    public void onStop() {
        beforeFather(ON_STOP);
        super.onStop();
        afterFather(ON_STOP);
    }

    @Override
    public void onDestroy() {
        beforeFather(ON_DESTROY);
        super.onDestroy();
        afterFather(ON_DESTROY);
    }

    @Override
    public void onLowMemory() {
        beforeFather(ON_LOW_MEMORY);
        super.onLowMemory();
        afterFather(ON_LOW_MEMORY);
    }

    @Override
    public void onPause() {
        beforeFather(ON_PAUSE);
        super.onPause();
        afterFather(ON_PAUSE);
    }


    @Override
    public void onResume() {
        beforeFather(ON_RESUME);
        super.onResume();
        afterFather(ON_RESUME);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        beforeFather(ON_SAVE_INSTANCE_STATE);
        super.onSaveInstanceState(outState);
        afterFather(ON_SAVE_INSTANCE_STATE);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        beforeFather(ON_VIEW_STATE_RESTORED);
        super.onViewStateRestored(savedInstanceState);
        afterFather(ON_VIEW_STATE_RESTORED);
    }

    @Override
    public void onDestroyView() {
        beforeFather(ON_DESTROY_VIEW);
        super.onDestroyView();
        afterFather(ON_DESTROY_VIEW);
    }

    @Override
    public void onDetach() {
        beforeFather(ON_DETACH);
        super.onDetach();
        afterFather(ON_DETACH);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        beforeFather(ON_OPTIONS_ITEM_SELECTED);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        beforeFather(ON_CREATE_OPTIONS_MENU);
        super.onCreateOptionsMenu(menu, menuInflater);
        afterFather(ON_CREATE_OPTIONS_MENU);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        beforeFather(ON_PREPARE_OPTIONS_MENU);
        super.onPrepareOptionsMenu(menu);
        afterFather(ON_PREPARE_OPTIONS_MENU);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        beforeFather(ON_ATTACH_FRAGMENT);
        super.onAttachFragment(fragment);
        afterFather(ON_ATTACH_FRAGMENT);
    }


}
