package com.example.myapartment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class guardAdd extends AppCompatActivity {
    EditText etName,etNumber,etGovId,etNote;
    Button btnSave,btnCancel;
    MyDBHandler mydb = new MyDBHandler(guardAdd.this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_add);

        etName = findViewById(R.id.etGuardName);
        etNumber = findViewById(R.id.etGuardNumber);
        etGovId = findViewById(R.id.etGuardGovId);
        etNote = findViewById(R.id.etGuardNote);
        btnSave = findViewById(R.id.btnGuardSave);
        btnCancel = findViewById(R.id.btnGuardCancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty())
                {
                    Toast.makeText(guardAdd.this, "Please fill Name and Number field !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    
                    guard g = new guard(etName.getText().toString().trim(),etNumber.getText().toString().trim(),etGovId.getText().toString().trim(),
                            etNote.getText().toString().trim());

                    mydb.addGuardDetail(g);
                    Toast.makeText(guardAdd.this, "Saved !", Toast.LENGTH_SHORT).show();
                    guardAdd.this.finish();

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardAdd.this.finish();
            }
        });

    }
}
