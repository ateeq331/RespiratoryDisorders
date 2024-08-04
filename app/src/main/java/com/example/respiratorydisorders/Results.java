package com.example.respiratorydisorders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results); // Assuming your XML layout file is activity_result.xml

        TextView resultTextView = findViewById(R.id.resultTextView);
        Button backButton = findViewById(R.id.backButton);


        // Get the prediction result from the intent
        String predictionResult = getIntent().getStringExtra("PREDICTION_RESULT");

        // Set the prediction result to the TextView
        resultTextView.setText(predictionResult);

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to the previous activity
                finish();
            }
        });
    }
}
