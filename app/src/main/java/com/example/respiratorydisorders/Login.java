package com.example.respiratorydisorders;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private ImageView imageViewShowPassword;
    private boolean passwordVisible = false;
    private FirebaseAuth mAuth;
    private TextView forgotPasswordTextView;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        imageViewShowPassword = findViewById(R.id.imageViewShowPassword);
        Button button = findViewById(R.id.signInButton);
        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        TextView signUpTextView = findViewById(R.id.signUpTextView);
        String signUpText = "Don't have an account? Sign Up";


        imageViewShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndLogin();
            }
        });

        SpannableString spannableString = new SpannableString(signUpText);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle click event, redirect user to SignUpActivity
                startActivity(new Intent(Login.this, Signup.class));
            }
        };


        // Set the clickable span to the "SignUp" text
        spannableString.setSpan(clickableSpan, signUpText.indexOf("Sign Up"), signUpText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the SpannableString to the TextView
        signUpTextView.setText(spannableString);
        signUpTextView.setMovementMethod(LinkMovementMethod.getInstance());


        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event for "Forgot Password?"
                startActivity(new Intent(Login.this, Forgot.class));
            }
        });
    }


    private void validateAndLogin() {
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

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, Start.class));
                            finish(); // Finish the LoginActivity to prevent going back to it
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication failed. " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
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