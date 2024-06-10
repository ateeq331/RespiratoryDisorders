package com.example.respiratorydisorders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class Start extends AppCompatActivity {

    private Button btnstart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnstart=findViewById(R.id.btnstart);


        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, Input.class);
                startActivity(intent);
            }
        });

    }
}