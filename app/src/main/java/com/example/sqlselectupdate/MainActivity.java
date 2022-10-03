package com.example.sqlselectupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText textkey = findViewById(R.id.Key);
        EditText textvalue = findViewById(R.id.Value);
        Button buttonSelect = findViewById(R.id.btnselect);
        Button buttonDelete = findViewById(R.id.btndelete);
        Button buttonUpdate = findViewById(R.id.btnupdate);
        Button buttonInsert = findViewById(R.id.btninsert);
        bd mbd = new bd(MainActivity.this);



        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Key = textkey.getText().toString();

                Cursor res = mbd.search(Key);
                if(res.getCount() <= 0)
                {
                    Toast.makeText(MainActivity.this,"Failed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    res.moveToFirst();
                    textvalue.setText(res.getString(0).toString());
                }


            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Key = textkey.getText().toString();
                String Value = textvalue.getText().toString();
                mbd.add(Key,Value);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Key = textkey.getText().toString();

                Boolean deleteData = mbd.delete(Key);
                if(deleteData = true){
                    Toast.makeText(MainActivity.this,"Выполнено удаление", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}