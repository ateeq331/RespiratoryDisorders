package com.example.respiratorydisorders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;


public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private ImageView imageViewShowPassword;
    private boolean passwordVisible = false;


    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);


        mAuth = FirebaseAuth.getInstance();


        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        imageViewShowPassword = findViewById(R.id.imageViewShowPassword);
        Button button = findViewById(R.id.signUpButton);


        TextView loginTextView = findViewById(R.id.loginTextView);
        String loginText = "Already have an account? Log in";

        SpannableString spannableString = new SpannableString(loginText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle click event, redirect user to LoginActivity
                startActivity(new Intent(Signup.this, Login.class));
                // Finish the current activity to prevent going back to it
                finish();
            }
        };
        spannableString.setSpan(clickableSpan, loginText.indexOf("Log in"), loginText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the SpannableString to the TextView
        loginTextView.setText(spannableString);
        loginTextView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());





        imageViewShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateAndSignUp();


            }
        });
    }



    private void validateAndSignUp() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (!isValidEmail(email)) {
            emailEditText.setError("Invalid email address");
            emailEditText.requestFocus();
            return;
        }

        if (!isValidPassword(password)) {
            passwordEditText.setError("Password must be at least 6 characters long");
            passwordEditText.requestFocus();
            return;
        }

        // Here you would implement your Firebase Authentication code to sign up the user
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(Signup.this, "Registered Successfuly", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Signup.this, Login.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(Signup.this, "Registration Failed! Check your Internet or  Account maybe exists already ", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


    private boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }


    private void togglePasswordVisibility() {
        if (passwordVisible) {
            // Hide the password
            passwordEditText.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                    android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imageViewShowPassword.setImageResource(R.drawable.pwdh);
        } else {
            // Show the password
            passwordEditText.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
            imageViewShowPassword.setImageResource(R.drawable.pwds);
        }
        passwordVisible = !passwordVisible;
    }
}