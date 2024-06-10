package com.example.respiratorydisorders;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    private ListView valuesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);

        // Get the prediction result from the intent
        String prediction = getIntent().getStringExtra("prediction");

        // Find the TextView and set the prediction result
        TextView predictionTextView = findViewById(R.id.predictionTextView);
        predictionTextView.setText(prediction);

    }
}