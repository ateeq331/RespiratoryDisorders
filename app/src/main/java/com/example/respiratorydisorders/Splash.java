package com.example.respiratorydisorders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Initialize Firebase Auth

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ImageView imageView = findViewById(R.id.splashImage);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_up_animation);
        imageView.startAnimation(animation);
        imageView.setVisibility(View.VISIBLE);

        // Set a listener to start the next activity after animation ends
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Start the next activity after the animation ends
                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent going back to it on pressing back button
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}