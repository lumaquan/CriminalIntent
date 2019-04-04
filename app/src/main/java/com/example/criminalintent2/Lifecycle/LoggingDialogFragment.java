package com.example.criminalintent2.Lifecycle;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class LoggingDialogFragment implements FragmentLifecycles {

    FragmentLifecycles fragmentLifecycles;

    @Override
    public void onAttach(Context context) {
        fragmentLifecycles.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

    }

    @Override
    public void onAttachFragment(Fragment fragment) {

    }
}
