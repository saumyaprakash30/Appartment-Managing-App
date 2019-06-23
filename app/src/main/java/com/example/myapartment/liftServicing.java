package com.example.myapartment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class liftServicing extends AppCompatActivity {

    TextView tvHead,tvDet;
    EditText etDate,etNote;
    ImageButton btnCalendar;
    Button btnAdd,btnDelete ,btnCancel;
    CalendarView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift_servicing);

        etDate = findViewById(R.id.etLiftDate);
        tvDet = findViewById(R.id.tvLiftDet);
        tvHead = findViewById(R.id.tvLiftHead);
        etNote = findViewById(R.id.etLiftNote);
        btnCalendar = findViewById(R.id.btnLiftCalendar);
        btnAdd = findViewById(R.id.btnLiftAdd);
        btnDelete = findViewById(R.id.btnLiftDelete);
        btnCancel = findViewById(R.id.btnLiftCancel);
        cv = findViewById(R.id.cvCalendar);

        tvDet.setMovementMethod(new ScrollingMovementMethod());
        etNote.setMovementMethod(new ScrollingMovementMethod());

        cv.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etNote.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.GONE);
                btnAdd.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.GONE);
                etNote.setText("");
                etDate.setText("");
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.setVisibility(View.VISIBLE);

                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView calendarView, int y, int m, int d) {
                        etDate.setText(y+"-"+m+"-"+d);
                        cv.setVisibility(View.GONE);
                    }
                });
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fuelmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        etNote.setVisibility(View.GONE);
        btnCancel.setVisibility(View.VISIBLE);
        btnDelete.setVisibility(View.VISIBLE);
        btnAdd.setVisibility(View.GONE);
        etNote.setText("");
        etDate.setText("");


        return super.onOptionsItemSelected(item);
    }
}
