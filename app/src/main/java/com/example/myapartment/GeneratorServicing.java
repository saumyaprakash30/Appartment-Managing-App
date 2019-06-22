package com.example.myapartment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GeneratorServicing extends AppCompatActivity {

    TextView tvHead,tvDet;
    EditText etDate,etNote;
    Button btnAdd,btnDelete,btnCancel;
    CalendarView cv;
    ImageButton btnCalendar;
    MyDBHandler myDB = new MyDBHandler(GeneratorServicing.this,null,null,1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator_servicing);

        tvHead = findViewById(R.id.tvGSerHead);
        tvDet = findViewById(R.id.tvGSerDet);
        etDate = findViewById(R.id.etGDateSer);
        etNote = findViewById(R.id.etGSerNote);
        btnAdd = findViewById(R.id.btnGSerAdd);
        btnDelete = findViewById(R.id.btnGSerDelete);
        btnCancel = findViewById(R.id.btnGDelCancelSer);
        cv = findViewById(R.id.cvGSer);
        btnCalendar = findViewById(R.id.btnGCalendarSer);

        tvDet.setMovementMethod(new ScrollingMovementMethod());

        cv.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);

        tvDet.setText(myDB.getGenSerDetail());

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.setVisibility(View.VISIBLE);

                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView calendarView, int year, int month, int date) {
                        etDate.setText(year+"-"+month+"-"+date);

                        cv.setVisibility(View.GONE);
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAdd.setVisibility(View.VISIBLE);
                btnDelete.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                etNote.setVisibility(View.VISIBLE);
                etDate.setText("");
                etNote.setText("");

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etDate.getText().toString().isEmpty() || etNote.getText().toString().isEmpty())
                {
                    Toast.makeText(GeneratorServicing.this, "Please fill all fields  !", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    myDB.addGenSer(etDate.getText().toString().trim(),etNote.getText().toString().trim());

                    tvDet.setText(myDB.getGenSerDetail());
                    etNote.setText("");
                    etDate.setText("");
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etDate.getText().toString().isEmpty())
                {
                    Toast.makeText(GeneratorServicing.this, "Please fill date !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    myDB.delGenSer(etDate.getText().toString().trim());
                    tvDet.setText(myDB.getGenSerDetail());

                    btnAdd.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.GONE);
                    btnCancel.setVisibility(View.GONE);
                    etNote.setVisibility(View.VISIBLE);
                    etDate.setText("");

                }
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
        switch (item.getItemId())
        {
            case R.id.fuelDelete :
                btnAdd.setVisibility(View.GONE);
                btnDelete.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
                etNote.setVisibility(View.GONE);
                etDate.setText("");
                etNote.setText("");

        }
        return super.onOptionsItemSelected(item);
    }
}
