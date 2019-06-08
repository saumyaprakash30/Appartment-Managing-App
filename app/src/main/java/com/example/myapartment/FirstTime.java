package com.example.myapartment;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstTime extends AppCompatActivity {

    EditText etApName;
    Button btnFTSubmit;

    public static final String FILE_APARTMENT_NAME = "com.example.myapartment.apName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        this.setTitle("Apartment Detail");

        etApName = findViewById(R.id.etFTName);
        btnFTSubmit = findViewById(R.id.btnFTSubmit);

        Toast.makeText(this, "Only asked for one time !", Toast.LENGTH_SHORT).show();



        btnFTSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String apName = etApName.getText().toString();

                SharedPreferences.Editor editor = getSharedPreferences(FILE_APARTMENT_NAME,MODE_PRIVATE).edit();
                editor.putString("apName",apName);
                editor.commit();

                FirstTime.this.finish();

            }
        });


    }
}
