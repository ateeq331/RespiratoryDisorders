package com.example.respiratorydisorders;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Input extends AppCompatActivity {

    private Spinner ageSpinner;
    private Spinner genderSpinner;
    private Spinner diagnosisAgeSpinner;
    private Spinner bloodGroupSpinner;
    private Spinner birthOrderSpinner;
    private Spinner maritalStatusSpinner;
    private Spinner lifestyleSpinner;
    private Spinner weightSpinner;
    private Spinner obesitySpinner;
    private Spinner obesityInFamilySpinner;
    private Spinner fastFoodSpinner;
    private Spinner smokingSpinner;
    private Spinner highBPSpinner;
    private Spinner diabetesSpinner;
    private int smokingValue; // Integer to store smoking value
    private int highBPValue; // Integer to store high BP value
    private int diabetesValue;
    private int fastFoodValue;
    private int obesityInFamilyValue;
    private int obesityValue;
    private int selectedWeightValue;
    private int selectedLifestyleValue;
    private int selectedBloodGroupValue;
    private int age;
    private int gender;
    private int diagnosisAge;
    private int birthOrder;
    private int maritalStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input); // Assuming your XML layout file is activity_input.xml

        genderSpinner = findViewById(R.id.genderSpinner);
        ageSpinner = findViewById(R.id.ageSpinner);
        diagnosisAgeSpinner = findViewById(R.id.diagnosisAgeSpinner);
        bloodGroupSpinner = findViewById(R.id.bloodGroupSpinner);
        birthOrderSpinner = findViewById(R.id.birthOrderSpinner);
        maritalStatusSpinner = findViewById(R.id.maritalStatusSpinner);
        lifestyleSpinner = findViewById(R.id.lifestyleSpinner);
        weightSpinner = findViewById(R.id.weightSpinner);
        obesitySpinner = findViewById(R.id.obesitySpinner);
        obesityInFamilySpinner = findViewById(R.id.obesityInFamilySpinner);
        fastFoodSpinner = findViewById(R.id.fastFoodSpinner);
        smokingSpinner = findViewById(R.id.smokingSpinner);
        highBPSpinner = findViewById(R.id.highBPSpinner);
        diabetesSpinner = findViewById(R.id.diabetesSpinner);



        // Define options
        String[] genderOptions = {"Female", "Male"};
        String[] ageOptions = {"0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70-79", "80-89", "90+"};
        String[] diagnosisAgeOptions = {"0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70-79", "80-89", "90+"};
        String[] bloodGroupOptions = {"Select", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        String[] birthOrderOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] maritalStatusOptions = {"Unmarried", "Married"};
        String[] lifestyleOptions = {"Work-Focused Life", "Artistic Life", "Traveler’s Life", "Nature-Focused Life", "Fancy Life"};
        String[] weightOptions = {"80 or below", "Above 80"};
        String[] obesityOptions = {"No", "Yes"};
        String[] obesityInFamilyOptions = {"Yes", "No"};
        String[] fastFoodOptions = {"No", "Yes"};
        String[] smokingOptions = {"No", "Yes"};
        String[] highBPOptions = {"No", "Yes"};
        String[] diabetesOptions = {"No", "Yes"};



        // Create ArrayAdapter using options
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderOptions);
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ageOptions);
        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bloodGroupOptions);
        ArrayAdapter<String> diagnosisAgeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diagnosisAgeOptions);
        ArrayAdapter<String> birthOrderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, birthOrderOptions);
        ArrayAdapter<String> maritalStatusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maritalStatusOptions);
        ArrayAdapter<String> lifestyleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lifestyleOptions);
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weightOptions);
        ArrayAdapter<String> obesityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obesityOptions);
        ArrayAdapter<String> obesityInFamilyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obesityInFamilyOptions);
        ArrayAdapter<String> fastFoodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fastFoodOptions);
        ArrayAdapter<String> smokingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, smokingOptions);
        ArrayAdapter<String> highBPAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, highBPOptions);
        ArrayAdapter<String> diabetesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diabetesOptions);



        // Specify the layout to use when the list of choices appears
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diagnosisAgeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthOrderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lifestyleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        obesityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        obesityInFamilyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fastFoodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smokingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        highBPAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diabetesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        // Apply the adapter to the spinners
        genderSpinner.setAdapter(genderAdapter);
        ageSpinner.setAdapter(ageAdapter);
        diagnosisAgeSpinner.setAdapter(diagnosisAgeAdapter);
        bloodGroupSpinner.setAdapter(bloodGroupAdapter);
        birthOrderSpinner.setAdapter(birthOrderAdapter);
        maritalStatusSpinner.setAdapter(maritalStatusAdapter);
        lifestyleSpinner.setAdapter(lifestyleAdapter);
        weightSpinner.setAdapter(weightAdapter);
        obesitySpinner.setAdapter(obesityAdapter);
        obesityInFamilySpinner.setAdapter(obesityInFamilyAdapter);
        fastFoodSpinner.setAdapter(fastFoodAdapter);
        smokingSpinner.setAdapter(smokingAdapter);
        highBPSpinner.setAdapter(highBPAdapter);
        diabetesSpinner.setAdapter(diabetesAdapter);



        // Set OnItemSelectedListener to handle spinner item selection
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected age range
                String selectedAgeRange = ageOptions[position];
                // Convert age range to a single age and save it in "age"
                age = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected gender
                String selectedGender = genderOptions[position];
                // Convert gender to integer value and save it in "gender" variable
                gender = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        diagnosisAgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected diagnosis age range
                String selectedDiagnosisAgeRange = diagnosisAgeOptions[position];
                // Convert diagnosis age range to a single age and save it in "diagnosisAge" variable
                diagnosisAge = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        bloodGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected blood group
                String selectedBloodGroup = bloodGroupOptions[position];
                // Convert blood group to integer value and save it in "bloodGroup" variable
                selectedBloodGroupValue = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        birthOrderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected birth order
                String selectedBirthOrder = birthOrderOptions[position];
                // Convert birth order to integer value and save it in "birthOrder" variable
                birthOrder = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        maritalStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected marital status
                String selectedMaritalStatus = maritalStatusOptions[position];
                // Convert marital status to integer value and save it in "maritalStatus" variable
                maritalStatus = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        lifestyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected lifestyle
                String selectedLifestyle = lifestyleOptions[position];
                // Convert lifestyle to integer value and save it in "selectedLifestyleValue" variable
                selectedLifestyleValue = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected weight
                String selectedWeight = weightOptions[position];
                // Convert weight to integer value and save it in "selectedWeightValue" variable
                selectedWeightValue = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        obesitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected obesity value
                String selectedObesity = obesityOptions[position];
                // Convert obesity value to integer and save it in "obesityValue" variable
                obesityValue = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        obesityInFamilySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected obesity in family value
                String selectedObesityInFamily = obesityInFamilyOptions[position];
                // Convert obesity in family value to integer and save it in "obesityInFamilyValue" variable
                obesityInFamilyValue = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        fastFoodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected fast food value
                String selectedFastFood = fastFoodOptions[position];
                // Convert fast food value to integer and save it in "fastFoodValue" variable
                fastFoodValue = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        smokingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected smoking value
                String selectedSmoking = smokingOptions[position];
                // Convert smoking value to integer and save it in "smokingValue" variable
                smokingValue = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        highBPSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected high BP value
                String selectedHighBP = highBPOptions[position];
                // Convert high BP value to integer and save it in "highBPValue" variable
                highBPValue = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        diabetesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve selected diabetes value
                String selectedDiabetes = diabetesOptions[position];
                // Convert diabetes value to integer and save it in "diabetesValue" variable
                diabetesValue = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Button submitButton = findViewById(R.id.btnPredict);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected values from spinners
                age = ageSpinner.getSelectedItemPosition();
                gender = genderSpinner.getSelectedItemPosition();
                diagnosisAge = diagnosisAgeSpinner.getSelectedItemPosition();
                selectedBloodGroupValue = bloodGroupSpinner.getSelectedItemPosition();
                birthOrder = birthOrderSpinner.getSelectedItemPosition();
                maritalStatus = maritalStatusSpinner.getSelectedItemPosition();
                selectedLifestyleValue = lifestyleSpinner.getSelectedItemPosition();
                selectedWeightValue = weightSpinner.getSelectedItemPosition();
                obesityValue = obesitySpinner.getSelectedItemPosition();
                obesityInFamilyValue = obesityInFamilySpinner.getSelectedItemPosition();
                fastFoodValue = fastFoodSpinner.getSelectedItemPosition();
                smokingValue = smokingSpinner.getSelectedItemPosition();
                highBPValue = highBPSpinner.getSelectedItemPosition();
                diabetesValue = diabetesSpinner.getSelectedItemPosition();

                // Create JSON object to send to Flask API
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("Age", age);
                    jsonObject.put("Gender", gender);
                    jsonObject.put("Diagnosis Age", diagnosisAge);
                    jsonObject.put("Blood Group", selectedBloodGroupValue);
                    jsonObject.put("Birth Order", birthOrder);
                    jsonObject.put("Marital Status", maritalStatus);
                    jsonObject.put("Lifestyle", selectedLifestyleValue);
                    jsonObject.put("Weight", selectedWeightValue);
                    jsonObject.put("Obesity", obesityValue);
                    jsonObject.put("Obesity in Family", obesityInFamilyValue);
                    jsonObject.put("Fast Food", fastFoodValue);
                    jsonObject.put("Smoking", smokingValue);
                    jsonObject.put("High BP", highBPValue);
                    jsonObject.put("Diabetes", diabetesValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Send data to Flask API
                new SendDataToApiTask().execute(jsonObject.toString());
            }
        });
    }

    private class SendDataToApiTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            String jsonString = params[0];
            String urlString = "http://127.0.0.1:5000/predict";
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");

                try (OutputStream os = urlConnection.getOutputStream()) {
                    byte[] input = jsonString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                // Handle the response from the API
                Toast.makeText(Input.this, "Response: " + result, Toast.LENGTH_LONG).show();

                // Start a new activity and pass the API result to it
                Intent intent = new Intent(Input.this, Results.class);
                intent.putExtra("apiResult", result);
                startActivity(intent);
            } else {
                Toast.makeText(Input.this, "Failed to get response from the API", Toast.LENGTH_LONG).show();
            }
        }
    }
}
