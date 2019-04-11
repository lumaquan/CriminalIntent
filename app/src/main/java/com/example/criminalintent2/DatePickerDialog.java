package com.example.criminalintent2;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerDialog extends DialogFragment{

    public static final String DATE_EXTRA = "crime_date_extra";

    public static DatePickerDialog newInstance(Date date) {
        DatePickerDialog dialog = new DatePickerDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATE_EXTRA, date);
        dialog.setArguments(bundle);
        return dialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final DatePicker datePicker = (DatePicker) requireActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
        Date date = (Date) getArguments().getSerializable(DATE_EXTRA);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setView(datePicker)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();
                        Date date = new GregorianCalendar(year, month, day).getTime();
                        sendResult(Activity.RESULT_OK, date); }})
                .create();

        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }


    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) return;
        Intent intent = new Intent();
        intent.putExtra(DATE_EXTRA, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
