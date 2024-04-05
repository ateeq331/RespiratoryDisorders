package com.example.respiratorydisorders;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class Input extends AppCompatActivity {

    private Spinner spinnerGender;
    private Button btnPredict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input);

        spinnerGender = findViewById(R.id.spinnerGender);
        btnPredict = findViewById(R.id.btnPredict);

        // Populate spinner with gender options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        // Set click listener for the Predict button
        btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected gender from spinner
                String selectedGender = spinnerGender.getSelectedItem().toString();
                float genderValue = selectedGender.equals("Male") ? 1.0f : 0.0f;

                // Replace this toast message with your handling of the prediction result
                Toast.makeText(Input.this, "Prediction: " + genderValue, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
