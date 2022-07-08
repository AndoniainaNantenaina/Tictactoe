package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EndingActivity extends AppCompatActivity {

    public String gameEndingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);
    }

    public void onReplayBtnClicked(View viewSource)
    {
        //  Afficher l'activité final
        Intent launchView = new Intent(this, LaunchActivity.class);
        startActivity(launchView);
    }
}