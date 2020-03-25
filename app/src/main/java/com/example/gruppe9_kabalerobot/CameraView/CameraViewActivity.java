package com.example.gruppe9_kabalerobot.CameraView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.camera2.Camera2Config;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCapture.OnImageCapturedCallback;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gruppe9_kabalerobot.R;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;

public class CameraViewActivity extends AppCompatActivity implements CameraXConfig.Provider, View.OnClickListener{

    private PreviewView previewView;
    private Button imageCaptureButton;
    private ImageCapture imageCapture;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        previewView = findViewById(R.id.preview);
        imageCaptureButton = findViewById(R.id.captureImage);
        imageCaptureButton.setOnClickListener(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                // No errors need to be handled for this Future.
                // This should never be reached.
            }
        }, ContextCompat.getMainExecutor(this));

    }


    @NonNull
    @Override
    public CameraXConfig getCameraXConfig() {
        return Camera2Config.defaultConfig();
    }

    @Override
    public void onClick(View view) {
        if (view == imageCaptureButton) {
            imageCapture.takePicture(ContextCompat.getMainExecutor(this), imageCapturedCallback);
        }
    }

    private void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .build();

        preview.setSurfaceProvider(previewView.getPreviewSurfaceProvider());

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        imageCapture =
                new ImageCapture.Builder()
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                        .build();

        OrientationEventListener orientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                int rotation;

                // Monitors orientation values to determine the target rotation value
                if (orientation >= 45 && orientation < 135) {
                    rotation = Surface.ROTATION_270;
                } else if (orientation >= 135 && orientation < 225) {
                    rotation = Surface.ROTATION_180;
                } else if (orientation >= 225 && orientation < 315) {
                    rotation = Surface.ROTATION_90;
                } else {
                    rotation = Surface.ROTATION_0;
                }

                imageCapture.setTargetRotation(rotation);
            }
        };

        orientationEventListener.enable();

        cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture, preview);
    }

    private OnImageCapturedCallback imageCapturedCallback = new OnImageCapturedCallback() {
        @SuppressLint("UnsafeExperimentalUsageError")
        @Override
        public void onCaptureSuccess(@NonNull ImageProxy image) {
            // Create new fragment and transaction
            Fragment newFragment = new ImageFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Set arguments



            Bitmap bitmap = toBitmapImage(image);
            Bundle args = new Bundle();
            args.putParcelable("path", bitmap);
            newFragment.setArguments(args);
            // Replace whatever is in the fragment_container view with this fragment,
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
        }

        @Override
        public void onError(@NonNull ImageCaptureException exception) {
            Toast.makeText(CameraViewActivity.this, "Image Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private Bitmap toBitmapImage(ImageProxy image) {
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] bytes = new byte[buffer.capacity()];
        buffer.get(bytes);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}
