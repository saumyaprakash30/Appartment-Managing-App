package com.example.myapartment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String FILE_APARTMENT_NAME = "com.example.myapartment.apName";

    Button btnFlatsDetail,  btnSendNotice,btnMaintain,btnGuardDetail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFlatsDetail = findViewById(R.id.btnFlatsDetail);
        btnSendNotice = findViewById(R.id.btnSendNotice);
        btnMaintain = findViewById(R.id.btnMaintain);
        btnGuardDetail = findViewById(R.id.btnGuardDetail);

        SharedPreferences pref = getSharedPreferences(FILE_APARTMENT_NAME,MODE_PRIVATE);
        String apName =  pref.getString("apName","empty");


        if(apName.equals("empty")){
            Intent intent = new Intent(this,com.example.myapartment.FirstTime.class);
            startActivity(intent);
        }
        else
        {
            this.setTitle(apName);
        }

        btnFlatsDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,com.example.myapartment.FlatsDetail.class));
            }
        });

        btnSendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,com.example.myapartment.noticeActivity.class));
            }
        });
        btnMaintain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,com.example.myapartment.maintenanceAvtivity.class));
            }
        });

        btnGuardDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,com.example.myapartment.guardDetail.class));
            }
        });




    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        SharedPreferences pref = getSharedPreferences(FILE_APARTMENT_NAME,MODE_PRIVATE);
        this.setTitle(pref.getString("apName",null));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.change_apartment_name,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.changeApartmentName:
                startActivity(new Intent(MainActivity.this,com.example.myapartment.FirstTime.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
