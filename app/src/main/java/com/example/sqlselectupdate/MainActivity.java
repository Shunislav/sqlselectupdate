package com.example.sqlselectupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


        buttonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Key = textkey.getText().toString();
                String Value = textvalue.getText().toString();

                bd mbd = new bd(MainActivity.this);
                mbd.search(Key);

            }
        });

    }
}