package com.example.gruppe9_kabalerobot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startCamera = findViewById(R.id.startCamera);

        startCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == startCamera){
            Intent i = new Intent(this, CameraViewActivity.class);
            startActivity(i);
        }
    }
}
