package com.example.myapartment;

import android.provider.CalendarContract;
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

public class GeneratorActivity extends AppCompatActivity {
    TextView tvDet,tvFuelHead;
    EditText etFuel,etDateFuel;
    Button btnAddFuel;
    ImageButton btnCalender;
    CalendarView cv;
    Button btnFuelDelete,btnFuelCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);
        tvDet = findViewById(R.id.tvGFuelDet    );
        tvDet.setMovementMethod(new ScrollingMovementMethod());
        btnAddFuel = findViewById(R.id.btnAddFuel);
        etDateFuel = findViewById(R.id.etDateFuel);
        etFuel = findViewById(R.id.etFuel);
        btnCalender = findViewById(R.id.btnCalendar);
        cv = findViewById(R.id.cvGFuel);
        btnFuelDelete = findViewById(R.id.btnFuelDelete);
        btnFuelDelete.setVisibility(View.GONE);
        btnFuelCancel = findViewById(R.id.btnDelCancel);
        btnFuelCancel.setVisibility(View.GONE);
        tvFuelHead = findViewById(R.id.tvFuelHead);

        MyDBHandler myDBHandler = new MyDBHandler(GeneratorActivity.this,null,null,1);

        tvDet.setText(myDBHandler.getFuelPrint());

        cv.setVisibility(View.GONE);

        btnCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.setVisibility(View.VISIBLE);

                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
                    @Override
                    public void onSelectedDayChange(CalendarView calendarView, int year, int month, int date) {

                        etDateFuel.setText(year+"-"+month+"-"+date);
                        cv.setVisibility(View.GONE);
                    }
                });
            }
        });
        btnAddFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etDateFuel.getText().toString().isEmpty() || etFuel.getText().toString().isEmpty())
                {
                    Toast.makeText(GeneratorActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String date = etDateFuel.getText().toString().trim();
                    int fuel = Integer.parseInt(etFuel.getText().toString().trim());
                    MyDBHandler mydb = new MyDBHandler(GeneratorActivity.this,null,null,1);
                    mydb.addFuel(date,fuel);
                    String fuelPrint = mydb.getFuelPrint();
                    tvDet.setText(fuelPrint);
                    etDateFuel.setText("");
                    etFuel.setText("");
                }


            }
        });

        btnFuelCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFuelHead.setText("Add");
                etFuel.setVisibility(View.VISIBLE);
                btnAddFuel.setVisibility(View.VISIBLE);
                btnFuelDelete.setVisibility(View.GONE);
                btnFuelCancel.setVisibility(View.GONE);
                etDateFuel.setText("");
                etFuel.setText("");
            }
        });

        btnFuelDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etDateFuel.getText().toString().isEmpty())
                {
                    Toast.makeText(GeneratorActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MyDBHandler db = new MyDBHandler(GeneratorActivity.this,null,null,1);
                    Boolean check =db.fuelDelete(etDateFuel.getText().toString());
                    if(check)
                    {
                        tvFuelHead.setText("Add");
                        tvDet.setText(db.getFuelPrint());
                        etFuel.setVisibility(View.VISIBLE);
                        btnAddFuel.setVisibility(View.VISIBLE);
                        btnFuelDelete.setVisibility(View.GONE);
                        btnFuelCancel.setVisibility(View.GONE);
                        etDateFuel.setText("");
                        etFuel.setText("");
                    }
                    else
                    {
                        Toast.makeText(GeneratorActivity.this, "Wrong date !", Toast.LENGTH_SHORT).show();
                    }

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
                tvFuelHead.setText("Delete");
                etFuel.setVisibility(View.GONE);
                btnAddFuel.setVisibility(View.GONE);
                btnFuelDelete.setVisibility(View.VISIBLE);
                btnFuelCancel.setVisibility(View.VISIBLE);
                etDateFuel.setText("");
                etFuel.setText("");

        }

        return super.onOptionsItemSelected(item);
    }


}
