package com.example.myapartment;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class newFlat extends AppCompatActivity {

    EditText etFlatName,etOwnerName,etOwnerNumber,etResName,etResNumber;
    TextView tvNewDetail;
    CheckBox cbSAA;
    MyDBHandler dbHandler;

    boolean edit ;
    String fn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flat);

        etFlatName = findViewById(R.id.etFlatNumber);
        etOwnerName = findViewById(R.id.etOwnerName);
        etOwnerNumber = findViewById(R.id.etOwnerNumber);
        etResName = findViewById(R.id.etResName);
        etResNumber = findViewById(R.id.etResNumber);


         edit = getIntent().getBooleanExtra("from",false);

        if(edit)
        {
            String eFN = getIntent().getStringExtra("editFN");
            String eFO = getIntent().getStringExtra("editFO");
            String eFON = getIntent().getStringExtra("editFON");
            String eFR  = getIntent().getStringExtra("editFR");
            String eFRN = getIntent().getStringExtra("editFRN");
            fn = eFN;

            etFlatName.setText(eFN);
            etOwnerName.setText(eFO);
            etOwnerNumber.setText(eFON);
            etResName.setText(eFR);
            etResNumber.setText(eFRN);

        }

    }

    public void onClickedAddOwner(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

        startActivityForResult(intent,101);
    }

    public void onClickedAddRes(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

        startActivityForResult(intent,102);
    }

    public  void onCheckedSame(View view)
    {
        cbSAA = findViewById(R.id.cbSAA);

        boolean checked = cbSAA.isChecked();
        if(checked)
        {
            etResName.setText(etOwnerName.getText());
            etResNumber.setText(etOwnerNumber.getText());
        }
        else
        {
            etResName.setText("");
            etResNumber.setText("");
        }

    }

    public void onSubmitButton(View view)
    {
            if((etFlatName.getText().toString().isEmpty() || etOwnerName.getText().toString().isEmpty() || etOwnerNumber.getText().toString().isEmpty()) ||
                    etResName.getText().toString().isEmpty() || etResNumber.getText().toString().isEmpty() )
            {
                Toast.makeText(newFlat.this, "Please fill all fields !", Toast.LENGTH_SHORT).show();
            }
            else
            {
                dbHandler = new MyDBHandler(this,null,null,1);

                flat newflat = new flat(etFlatName.getText().toString().toUpperCase().trim(),etOwnerName.getText().toString().trim(),etResName.getText().toString().trim(),
                        etOwnerNumber.getText().toString().trim(),etResNumber.getText().toString().trim());

                if(edit)
                {
                    dbHandler.deleteFlat(fn);
                    dbHandler.addFlat(newflat);
                    Intent rintent = new Intent();
                    rintent.putExtra("id1",etFlatName.getText().toString().trim());
                    setResult(RESULT_OK,   rintent);
                }
                else
                {
                    dbHandler.addFlat(newflat);
                }

                dbHandler.close();

                newFlat.this.finish();




            }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101)
        {
            if(resultCode == RESULT_OK)
            {
                Uri contactUri = data.getData();
                String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

                Cursor cursor = getContentResolver().query(contactUri, projection,
                        null, null, null);
                // If the cursor returned is valid, get the phone number
                if (cursor != null && cursor.moveToFirst()) {
                    int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = cursor.getString(numberIndex);
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    cursor.moveToFirst();
                    etOwnerName.setText(name);
                    etOwnerNumber.setText(number);

                }



            }
        }
        if(requestCode == 102)
        {
            if(resultCode == RESULT_OK)
            {
                Uri contactUri = data.getData();
                String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

                Cursor cursor = getContentResolver().query(contactUri, projection,
                        null, null, null);
                // If the cursor returned is valid, get the phone number
                if (cursor != null && cursor.moveToFirst()) {
                    int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = cursor.getString(numberIndex);
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    cursor.moveToFirst();
                    etResName.setText(name);
                    etResNumber.setText(number);

                }

            }
        }

    }
}
