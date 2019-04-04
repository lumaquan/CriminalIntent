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

public interface FragmentLifecycles {

    String ON_ATTACH = "onAttach";
    String ON_CREATE = "onCreate";
    String ON_CREATE_VIEW = "onCreateView";
    String ON_VIEW_CREATED = "onViewCreated";
    String ON_ACTIVITY_CREATED = "onActivityCreated";
    String ON_CONFIGURATION_CHANGED = "onConfigurationChanged";
    String ON_START = "onStart";
    String ON_STOP = "onStop";
    String ON_DESTROY = "onDestroy";
    String ON_LOW_MEMORY = "onLowMemory";
    String ON_PAUSE = "onPause";
    String ON_RESUME = "onResume";
    String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    String ON_VIEW_STATE_RESTORED = "onViewStateRestored";
    String ON_DESTROY_VIEW = "onDestroyView";
    String ON_DETACH = "onDetach";
    String ON_OPTIONS_ITEM_SELECTED = "onOptionsItemSelected";
    String ON_CREATE_OPTIONS_MENU = "onCreateOptionsMenu";
    String ON_PREPARE_OPTIONS_MENU = "onPrepareOptionsMenu";
    String ON_ATTACH_FRAGMENT = "onAttachFragment";

    Map<String, Boolean> callbacks = new HashMap<String, Boolean>(){{
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
    }};


    void onAttach(Context context);
    void onCreate(@Nullable Bundle savedInstanceState);
    View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState);
    void onActivityCreated(@Nullable Bundle savedInstanceState);
    void onConfigurationChanged(Configuration newConfig);
    void onStart();
    void onStop();
    void onDestroy();
    void onLowMemory();
    void onPause();
    void onResume();
    void onSaveInstanceState(Bundle outState);
    void onViewStateRestored(@Nullable Bundle savedInstanceState);
    void onDetach();
    boolean onOptionsItemSelected(MenuItem item);
    void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater);
    void onPrepareOptionsMenu(Menu menu);
    void onAttachFragment(Fragment fragment);

}
