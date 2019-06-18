package com.example.myapartment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class maintenanceAvtivity extends AppCompatActivity {

    Button btnEleBill,btnGenFuel,btnGenSer,btnWaterBill,btnLiftSer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_avtivity);

        btnGenFuel = findViewById(R.id.btnGenFuel);
        btnLiftSer = findViewById(R.id.btnLiftSer);
        btnEleBill  = findViewById(R.id.btnEleBill);
        btnWaterBill = findViewById(R.id.btnWaterBill);
        btnGenSer = findViewById(R.id.btnGenSer);

        btnGenFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(maintenanceAvtivity.this,com.example.myapartment.GeneratorActivity.class   ));

            }
        });

    }
}
