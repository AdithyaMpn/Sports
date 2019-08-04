package com.adithya.sports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class GoalEvent extends AppCompatActivity {



    int x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_event);

        Log.d("GoalEvent", "Hello World");

       final ImageView cp = findViewById(R.id.imageView);

        cp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = (int) event.getX();
                y = (int) event.getY();

                Log.v("x val of cap img >>", x + "");
                Log.v("y val of cap img >>", y + "");


                return false;
            }
        });
    }





}
