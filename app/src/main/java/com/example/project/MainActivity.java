package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.project.DrawView.DrawView;

public class MainActivity extends AppCompatActivity {

    private DrawView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int caseNumber = getIntent().getIntExtra("Case_number", 3);

        drawingView = (DrawView) findViewById(R.id.drawView);

        drawingView.setCASE_NUMBER(caseNumber);
    }
}