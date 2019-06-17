package com.example.myapartment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewFlatDetail extends AppCompatActivity {
    TextView tvVFNumber, tvVFOName,tvVONumber,tvVRName,tvVRNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flat_detail);

        tvVFNumber = findViewById(R.id.tvVFNumber);
        tvVFOName = findViewById(R.id.tvVFOName);
        tvVONumber = findViewById(R.id.tvVONumber);
        tvVRName = findViewById(R.id.tvVRName);
        tvVRNumber = findViewById(R.id.tvVRNumber);

        final MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

        Intent i = new Intent();
        String id = getIntent().getStringExtra("id");

        flat f = dbHandler.getFlatDetail(id);

        tvVFNumber.setText(f.getFlatName());
        tvVFOName.setText(f.getFlatOwner());
        tvVONumber.setText(f.getFlatOwnerMobile());
        tvVRName.setText(f.getFlatResident());
        tvVRNumber.setText(f.getFlatResidentMobile());

        final Button btnCall = findViewById(R.id.btnCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewFlatDetail.this);
                builder.setTitle("Whom to call ?");

                String[] callList= new String[]{"Owner: "+tvVFOName.getText().toString(),"Res.: "+tvVRName.getText().toString()};

                builder.setItems(callList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0)
                        {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tvVONumber.getText().toString()));
                            startActivity(intent);
                        }
                        if(i==1){
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tvVRNumber.getText().toString()));
                            startActivity(intent);
                        }

                        //Toast.makeText(ViewFlatDetail.this, "hello" + i, Toast.LENGTH_SHORT).show();
                    }

                });
                builder.show();

            }
        });

        Button btnSms  = findViewById(R.id.btnSms);

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ViewFlatDetail.this);
                builder.setTitle("Whom to sent Sms ?");

                String[] callList= new String[]{"Owner: "+tvVFOName.getText().toString(),"Res.: "+tvVRName.getText().toString()};

                builder.setItems(callList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0)
                        {
                             Uri uri = Uri.parse("smsto:"+tvVONumber.getText().toString());
                            Intent sendSms = new Intent(Intent.ACTION_SEND,uri);
                            startActivity(sendSms);

                        }
                        if(i==1)
                        {
                            Uri uri = Uri.parse("smsto:"+tvVRNumber.getText().toString());
                            Intent sendSms = new Intent(Intent.ACTION_SEND,uri);
                            startActivity(sendSms);

                        }
                    }
                });
                builder.show();

            }

        });

        Button btnDel = findViewById(R.id.btnDelete);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ViewFlatDetail.this);
                builder.setMessage("Do yo want to delete flat "+tvVFNumber.getText().toString()+"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteFlat(tvVFNumber.getText().toString());
                        ViewFlatDetail.this.finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }


        });





    }
}
