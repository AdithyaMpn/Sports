package com.adithya.sports;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goalEvent(View view){
        Intent intent = new Intent(this, GoalEvent.class);
        startActivity(intent);
        Toast.makeText(this,"Touch the Field Where the GOAL is Shot from", Toast.LENGTH_LONG).show();
    }
}
