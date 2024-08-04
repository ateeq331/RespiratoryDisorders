package com.example.respiratorydisorders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class Main extends AppCompatActivity {

    private EditText age, weight;

    private String genderValue, diagnosisAgeValue, bloodGroupValue, birthOrderValue, maritalStatusValue,
            lifestyleValue, obesityValue, obesityInFamilyValue, fastFoodValue, smokingValue, highBPValue, diabetesValue;

    String url = "https://ml-engineer-disease-classify.hf.space/predict";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize Spinners
        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        Spinner diagnosisAgeSpinner = findViewById(R.id.diagnosisAgeSpinner);
        Spinner bloodGroupSpinner = findViewById(R.id.bloodGroupSpinner);
        Spinner birthOrderSpinner = findViewById(R.id.birthOrderSpinner);
        Spinner maritalStatusSpinner = findViewById(R.id.maritalStatusSpinner);
        Spinner lifestyleSpinner = findViewById(R.id.lifestyleSpinner);
        Spinner obesitySpinner = findViewById(R.id.obesitySpinner);
        Spinner obesityInFamilySpinner = findViewById(R.id.obesityInFamilySpinner);
        Spinner fastFoodSpinner = findViewById(R.id.fastFoodSpinner);
        Spinner smokingSpinner = findViewById(R.id.smokingSpinner);
        Spinner highBPSpinner = findViewById(R.id.highBPSpinner);
        Spinner diabetesSpinner = findViewById(R.id.diabetesSpinner);

        // Initialize EditTexts
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);

        // Initialize Button and TextView
        Button predictButton = findViewById(R.id.predictButton);

        // Set up Spinner for Gender
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.gender_options, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderValue = position == 0 ? "1" : "0";  // 1 for Male, 0 for Female
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                genderValue = "1";  // Default to Male if nothing selected
            }
        });

        // Set up Spinner for Diagnosis Age
        ArrayAdapter<CharSequence> diagnosisAgeAdapter = ArrayAdapter.createFromResource(this, R.array.diagnosis_age_options, android.R.layout.simple_spinner_item);
        diagnosisAgeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diagnosisAgeSpinner.setAdapter(diagnosisAgeAdapter);

        diagnosisAgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                diagnosisAgeValue = String.valueOf(position);  // Send position to API
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                diagnosisAgeValue = "0";  // Default to first position if nothing selected
            }
        });

        // Set up Spinner for Blood Group
        ArrayAdapter<CharSequence> bloodGroupAdapter = ArrayAdapter.createFromResource(this, R.array.blood_group_options, android.R.layout.simple_spinner_item);
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupSpinner.setAdapter(bloodGroupAdapter);

        bloodGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodGroupValue = String.valueOf(position);  // Send position to API
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                bloodGroupValue = "0";  // Default to first position if nothing selected
            }
        });

        // Set up Spinner for Birth Order
        ArrayAdapter<CharSequence> birthOrderAdapter = ArrayAdapter.createFromResource(this, R.array.birth_order_options, android.R.layout.simple_spinner_item);
        birthOrderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthOrderSpinner.setAdapter(birthOrderAdapter);

        birthOrderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                birthOrderValue = String.valueOf(position + 1);  // Send position to API (1 to 9)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                birthOrderValue = "1";  // Default to first position if nothing selected
            }
        });

        // Set up Spinner for Marital Status
        ArrayAdapter<CharSequence> maritalStatusAdapter = ArrayAdapter.createFromResource(this, R.array.marital_status_options, android.R.layout.simple_spinner_item);
        maritalStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalStatusSpinner.setAdapter(maritalStatusAdapter);

        maritalStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maritalStatusValue = position == 0 ? "1" : "0";  // 1 for Married, 0 for Unmarried
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                maritalStatusValue = "1";  // Default to Married if nothing selected
            }
        });

        // Set up Spinner for Lifestyle
        ArrayAdapter<CharSequence> lifestyleAdapter = ArrayAdapter.createFromResource(this, R.array.lifestyle_options, android.R.layout.simple_spinner_item);
        lifestyleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lifestyleSpinner.setAdapter(lifestyleAdapter);

        lifestyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lifestyleValue = String.valueOf(position);  // Send position to API
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                lifestyleValue = "0";  // Default to first position if nothing selected
            }
        });

        // Set up Spinner for Obesity
        ArrayAdapter<CharSequence> obesityAdapter = ArrayAdapter.createFromResource(this, R.array.yes_no_options, android.R.layout.simple_spinner_item);
        obesityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        obesitySpinner.setAdapter(obesityAdapter);

        obesitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                obesityValue = position == 0 ? "1" : "0";  // 1 for Yes, 0 for No
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                obesityValue = "1";  // Default to Yes if nothing selected
            }
        });

        // Set up Spinner for Obesity In Family
        ArrayAdapter<CharSequence> obesityInFamilyAdapter = ArrayAdapter.createFromResource(this, R.array.yes_no_options, android.R.layout.simple_spinner_item);
        obesityInFamilyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        obesityInFamilySpinner.setAdapter(obesityInFamilyAdapter);

        obesityInFamilySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                obesityInFamilyValue = position == 0 ? "1" : "0";  // 1 for Yes, 0 for No
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                obesityInFamilyValue = "1";  // Default to Yes if nothing selected
            }
        });

        // Set up Spinner for Fast Food
        ArrayAdapter<CharSequence> fastFoodAdapter = ArrayAdapter.createFromResource(this, R.array.yes_no_options, android.R.layout.simple_spinner_item);
        fastFoodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fastFoodSpinner.setAdapter(fastFoodAdapter);

        fastFoodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fastFoodValue = position == 0 ? "1" : "0";  // 1 for Yes, 0 for No
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                fastFoodValue = "1";  // Default to Yes if nothing selected
            }
        });

        // Set up Spinner for Smoking
        ArrayAdapter<CharSequence> smokingAdapter = ArrayAdapter.createFromResource(this, R.array.smoking_options, android.R.layout.simple_spinner_item);
        smokingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smokingSpinner.setAdapter(smokingAdapter);

        smokingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                smokingValue = position == 0 ? "1" : "0";  // 1 for Smoker, 0 for Non-Smoker
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                smokingValue = "1";  // Default to Smoker if nothing selected
            }
        });

        // Set up Spinner for High BP
        ArrayAdapter<CharSequence> highBPAdapter = ArrayAdapter.createFromResource(this, R.array.yes_no_options, android.R.layout.simple_spinner_item);
        highBPAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        highBPSpinner.setAdapter(highBPAdapter);

        highBPSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                highBPValue = position == 0 ? "1" : "0";  // 1 for Yes, 0 for No
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                highBPValue = "1";  // Default to Yes if nothing selected
            }
        });

        // Set up Spinner for Diabetes
        ArrayAdapter<CharSequence> diabetesAdapter = ArrayAdapter.createFromResource(this, R.array.yes_no_options, android.R.layout.simple_spinner_item);
        diabetesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diabetesSpinner.setAdapter(diabetesAdapter);

        diabetesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                diabetesValue = position == 0 ? "1" : "0";  // 1 for Yes, 0 for No
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                diabetesValue = "1";  // Default to Yes if nothing selected
            }
        });

        // Set onClickListener for the predict button
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ag = age.getText().toString();
                String wg = weight.getText().toString();

                if (ag.isEmpty()) {
                    Toast.makeText(Main.this, "Please enter your age", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (wg.isEmpty()) {
                    Toast.makeText(Main.this, "Please enter your weight", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Create an Intent to start the ResultActivity
                                Intent intent = new Intent(Main.this, Results.class);
                                // Pass the prediction result to the ResultActivity
                                intent.putExtra("PREDICTION_RESULT", response);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Main.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("Gender", genderValue);
                        params.put("Age", ag);
                        params.put("Diagnosis Age", diagnosisAgeValue);
                        params.put("Blood Group", bloodGroupValue);
                        params.put("Birth Order", birthOrderValue);
                        params.put("Marital Status", maritalStatusValue);
                        params.put("Lifestyle", lifestyleValue);
                        params.put("Weight", wg);
                        params.put("Obesity", obesityValue);
                        params.put("Obesity In Family", obesityInFamilyValue);
                        params.put("Fast Food", fastFoodValue);
                        params.put("Smoking", smokingValue);
                        params.put("High BP", highBPValue);
                        params.put("Diabetes", diabetesValue);
                        return params;
                    }
                };

                // Add the request to the RequestQueue.
                Volley.newRequestQueue(Main.this).add(stringRequest);
            }
        });
    }
}