package com.example.gruppe9_kabalerobot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gruppe9_kabalerobot.CameraView.CameraViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //region Fields

    private Button startCamera;
    private int PERMISSION_ALL = 1;
    private String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA};

    //endregion

    //region Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        startCamera = findViewById(R.id.startCamera);

        startCamera.setOnClickListener(this);
    }

    //endregion

    //region OnClick

    @Override
    public void onClick(View view) {
        if (view == startCamera){
            Intent i = new Intent(this, CameraViewActivity.class);
            startActivity(i);
        }
    }

    //endregion

    //region Support methods

    private static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    //endregion
}
