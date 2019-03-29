package com.example.criminalintent2;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;

import com.example.criminalintent2.Lifecycle.LoggingLifecycleActivity;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends LoggingLifecycleActivity {

    public static final String CRIME_ID_EXTRA = "crime_id_extra";
    private ViewPager mCrimeViewPager;
    private CrimePageAdapter crimePageAdapter;
    private List<Crime> mCrimes;
    Integer[] colors = {Color.GREEN, Color.BLUE, Color.RED};
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public static Intent newIntent(Context context, UUID uuid) {
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(CRIME_ID_EXTRA, uuid);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        mCrimeViewPager = findViewById(R.id.crime_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();

        crimePageAdapter = new CrimePageAdapter(getSupportFragmentManager(), mCrimes);
        mCrimeViewPager.setAdapter(crimePageAdapter);
        mCrimeViewPager.setClipToPadding(false);
        mCrimeViewPager.setPadding(dpToPx(20), 0, dpToPx(20), 0);
        mCrimeViewPager.setPageTransformer(true, new ZoomOutTransformation());

        UUID uuid = (UUID) getIntent().getSerializableExtra(CRIME_ID_EXTRA);
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(uuid)) mCrimeViewPager.setCurrentItem(i);
        }
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;

    }

    @Override
    public void onBackPressed() {
        if (mCrimeViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mCrimeViewPager.setCurrentItem(mCrimeViewPager.getCurrentItem() - 1);
        }
    }

    static class ZoomOutTransformation implements ViewPager.PageTransformer {

        private static final float MIN_SCALE = 0.65f;
        private static final float MIN_ALPHA = 0.3f;

        @Override
        public void transformPage(View page, float position) {

            if (position < -1) {  // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.setAlpha(0);

            } else if (position <= 1) { // [-1,1]

                page.setScaleX(Math.max(MIN_SCALE, 1 - Math.abs(position)));
                page.setScaleY(Math.max(MIN_SCALE, 1 - Math.abs(position)));
                page.setAlpha(Math.max(MIN_ALPHA, 1 - Math.abs(position)));

            } else {  // (1,+Infinity]
                // This page is way off-screen to the right.
                page.setAlpha(0);
            }
        }
    }


}
