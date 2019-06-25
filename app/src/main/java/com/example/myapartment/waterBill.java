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

public class waterBill extends AppCompatActivity {
    TextView tvDet,tvHead;
    ImageButton btnCalendar;
    EditText etDate,etNote;
    CalendarView cv;
    Button btnAdd,btnDelete,btnCancel;

    MyDBHandler mydb = new MyDBHandler(waterBill.this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_bill);

        tvDet = findViewById(R.id.tvWaterDet);
        tvHead = findViewById(R.id.tvWaterHead);
        etDate = findViewById(R.id.etWaterDate);
        etNote = findViewById(R.id.etWaterNote);
        cv = findViewById(R.id.cvWaterCalendar);
        btnAdd = findViewById(R.id.btnWaterAdd);
        btnCalendar = findViewById(R.id.btnWaterCalendar);
        btnDelete = findViewById(R.id.btnWaterDelete);
        btnCancel = findViewById(R.id.btnWaterCancel);

        cv.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);

        tvDet.setMovementMethod(new ScrollingMovementMethod());
        tvDet.setText(mydb.getWaterBillDetail());

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.setVisibility(View.VISIBLE);

                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange( CalendarView calendarView, int y, int m, int d) {
                        etDate.setText(y+"-"+m+"-"+d);
                        cv.setVisibility(View.GONE);
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDelete.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                btnAdd.setVisibility(View.VISIBLE);
                etNote.setVisibility(View.VISIBLE);cv.setVisibility(View.GONE);
                etNote.setText("");
                etDate.setText("");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNote.getText().toString().isEmpty() || etDate.getText().toString().isEmpty())
                {
                    Toast.makeText(waterBill.this, "Please fill all details !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mydb.addWaterBill(etDate.getText().toString().trim(),etNote.getText().toString());
                    tvDet.setText(mydb.getWaterBillDetail());

                    etNote.setText("");
                    etDate.setText("");

                    Toast.makeText(waterBill.this, "Saved !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etDate.getText().toString().isEmpty())
                {
                    Toast.makeText(waterBill.this, "Please fill the date !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mydb.deleteWaterBill(etDate.getText().toString());
                    btnDelete.setVisibility(View.GONE);
                    btnCancel.setVisibility(View.GONE);
                    btnAdd.setVisibility(View.VISIBLE);
                    etNote.setVisibility(View.VISIBLE);
                    etNote.setText("");
                    etDate.setText("");
                    tvDet.setText(mydb.getWaterBillDetail());
                    Toast.makeText(waterBill.this, "Deleted !", Toast.LENGTH_SHORT).show();
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
            case R.id.fuelDelete:
                btnDelete.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
                btnAdd.setVisibility(View.GONE);
                etNote.setVisibility(View.GONE);cv.setVisibility(View.GONE);
                etNote.setText("");
                etDate.setText("");

        }

        return super.onOptionsItemSelected(item);
    }
}
