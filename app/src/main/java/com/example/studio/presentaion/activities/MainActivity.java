package com.example.studio.presentaion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.studio.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}