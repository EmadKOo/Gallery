package com.example.studio.presentaion.fragments;

import static android.app.Activity.RESULT_OK;
import static com.example.studio.utility.Constants.CAMERA_PERMISSION_CODE;
import static com.example.studio.utility.Constants.CAPTURED_IMAGE;
import static com.example.studio.utility.Constants.GALLERY_PERMISSION_CODE;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studio.R;
import com.example.studio.databinding.FragmentHomeBinding;

import java.io.File;

import javax.xml.transform.Result;

import dagger.hilt.android.AndroidEntryPoint;

@RequiresApi(api = Build.VERSION_CODES.M)
@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private static final String TAG = "EMADADADA";
    private FragmentHomeBinding mBinding;
    private File imageFile;
    private Uri imageUri;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleViews();
    }


    private void handleViews() {
        mBinding.takePhoto.setOnClickListener(view -> {
            if (hasCameraPermission()){
                pickPhoto();
            }
        });
        mBinding.openGallery.setOnClickListener(view -> {
            hasGalleryPermission();
        });
    }


    private boolean hasCameraPermission() {
        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
            return false;
        } else
            return true;
    }

    private boolean hasGalleryPermission() {
        if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, GALLERY_PERMISSION_CODE);
            return false;
        } else
            return true;
    }

    private void pickPhoto() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "NewPic");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image To Text");
        imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAPTURED_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                pickPhoto();
            } else {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == GALLERY_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "gallery permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "gallery permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: outside " + resultCode + " "+ requestCode);
        Toast.makeText(getActivity(), "Eadda", Toast.LENGTH_SHORT).show();
        if (requestCode== CAPTURED_IMAGE && resultCode== RESULT_OK )
            mBinding.selectedImage.setImageURI(imageUri);
    }
}