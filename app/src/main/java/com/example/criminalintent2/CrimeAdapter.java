package com.example.criminalintent2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {

    private List<Crime> mCrimes;
    private OnItemClickedListener listener;

    interface OnItemClickedListener {
        void onItemClick(UUID uuid);
    }

    public CrimeAdapter(List<Crime> mCrimes, OnItemClickedListener listener) {
        this.mCrimes = mCrimes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CrimeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CrimeHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeHolder crimeHolder, int i) {
        crimeHolder.bind(mCrimes.get(i));
    }

    @Override
    public int getItemCount() {
        if (mCrimes == null) return 0;
        return mCrimes.size();
    }

    class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView crimeTitle;
        private TextView crimeDate;
        private ImageView crimeSolvedImage;
        private SimpleDateFormat simpleDateFormat;
        private Crime mCrime;

        CrimeHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_crime, parent, false));
            crimeTitle = itemView.findViewById(R.id.crime_title);
            crimeDate = itemView.findViewById(R.id.crime_date);
            crimeSolvedImage = itemView.findViewById(R.id.crime_solved);
            itemView.setOnClickListener(this);
            simpleDateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
        }

        void bind(Crime crime) {
            mCrime = crime;
            crimeTitle.setText(mCrime.getTitle());
            crimeDate.setText(simpleDateFormat.format(mCrime.getDate()));
            crimeSolvedImage.setVisibility(mCrime.isSolved() ? View.VISIBLE : View.INVISIBLE);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(mCrime.getId());
            }
        }
    }

}
