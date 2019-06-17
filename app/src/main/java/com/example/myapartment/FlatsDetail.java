package com.example.myapartment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FlatsDetail extends AppCompatActivity {
     ListView lvFlat;
     Button btnNewFlatAdd;
     MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);;

     ArrayList<flat> list = new ArrayList<flat>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flats_detail);



        list = dbHandler.getFlats();
        /*flat c2 = new flat("c2","Geeta Verma","Satya Prakash","8271246090","9934364998");
        list.add(c2);
        */

        lvFlat =(ListView) findViewById(R.id.lvFlats);
        customAdatper adatper = new customAdatper(this,list);
        lvFlat.setAdapter(adatper);

        btnNewFlatAdd = findViewById(R.id.btnNewFlatAdd);

        btnNewFlatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  (new Intent(FlatsDetail.this,com.example.myapartment.newFlat.class));
                intent.putExtra("from",false);
                startActivity(intent);

            }
        });

        lvFlat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent(FlatsDetail.this,com.example.myapartment.ViewFlatDetail.class);
                    String data = list.get(i).getFlatName();
                    intent.putExtra("id",data);
                    if(data == null || data == "")
                    {
                        Toast.makeText(FlatsDetail.this, "nothing", Toast.LENGTH_SHORT).show();
                    }
                    else
                        startActivity(intent);


            }
        });


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        list = dbHandler.getFlats();

        lvFlat =(ListView) findViewById(R.id.lvFlats);
        customAdatper adatper = new customAdatper(this,list);
        lvFlat.setAdapter(adatper);

    }
}
