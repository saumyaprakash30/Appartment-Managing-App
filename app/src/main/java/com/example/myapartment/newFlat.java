package com.example.myapartment;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class newFlat extends AppCompatActivity {

    EditText etFlatName,etOwnerName,etOwnerNumber,etResName,etResNumber;
    TextView tvNewDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flat);

        etFlatName = findViewById(R.id.etFlatNumber);
        etOwnerName = findViewById(R.id.etOwnerName);
        etOwnerNumber = findViewById(R.id.etOwnerNumber);
        etResName = findViewById(R.id.etResName);
        etResNumber = findViewById(R.id.etResNumber);
        tvNewDetail = findViewById(R.id.tvNewDetail);

        etFlatName.setText("C1");


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
