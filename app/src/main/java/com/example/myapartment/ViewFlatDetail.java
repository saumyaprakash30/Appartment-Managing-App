package com.example.myapartment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewFlatDetail extends AppCompatActivity {
    TextView tvVFNumber, tvVFOName,tvVONumber,tvVRName,tvVRNumber;
    flat ef;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.editFlatDetail:
                Intent intent= (new Intent(ViewFlatDetail.this,com.example.myapartment.newFlat.class));
                boolean edit = true;
                intent.putExtra("from",true);
                intent.putExtra("edit",edit);
                intent.putExtra("editFN",ef.getFlatName());
                intent.putExtra("editFO",ef.getFlatOwner());
                intent.putExtra("editFON",ef.getFlatOwnerMobile());
                intent.putExtra("editFR",ef.getFlatResident());
                intent.putExtra("editFRN",ef.getFlatResidentMobile());
                startActivityForResult(intent,1001);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_flat_detail_action_bar,menu);
        return super.onCreateOptionsMenu(menu);


    }

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
        ef = f;

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
                builder.setTitle("Call to");

                String[] callList= new String[]{"Owner: "+tvVFOName.getText().toString(),"Res.: "+tvVRName.getText().toString()};

                builder.setItems(callList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(ContextCompat.checkSelfPermission(ViewFlatDetail.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                        {
                            ActivityCompat.requestPermissions(ViewFlatDetail.this,new String[]{Manifest.permission.CALL_PHONE},1);
                        }
                        else{
                        if(i==0)
                        {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tvVONumber.getText().toString()));
                            startActivity(intent);
                        }
                        if(i==1){
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tvVRNumber.getText().toString()));
                            startActivity(intent);
                        }


                        }
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
                builder.setTitle("Send Sms to");

                String[] callList= new String[]{"Owner: "+tvVFOName.getText().toString(),"Res.: "+tvVRName.getText().toString()};

                builder.setItems(callList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(ContextCompat.checkSelfPermission(ViewFlatDetail.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
                        {
                            ActivityCompat.requestPermissions(ViewFlatDetail.this,new String[]{Manifest.permission.SEND_SMS},1);
                        }
                        else
                            if(i==0)
                            {
                                Uri uri = Uri.parse("smsto:"+tvVONumber.getText().toString());
                                Intent sendSms = new Intent(Intent.ACTION_SEND,uri);
                                startActivity(sendSms);

                            }
                            else if(i==1)
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1001)
        {
            if(resultCode== RESULT_OK)
            {


                MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
                String id = data.getStringExtra("id1");
                flat f = dbHandler.getFlatDetail(id);


                tvVFNumber.setText(f.getFlatName());
                tvVFOName.setText(f.getFlatOwner());
                tvVONumber.setText(f.getFlatOwnerMobile());
                tvVRName.setText(f.getFlatResident());
                tvVRNumber.setText(f.getFlatResidentMobile());

            }
        }
    }
}
