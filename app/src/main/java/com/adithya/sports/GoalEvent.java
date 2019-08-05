package com.adithya.sports;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
       //final Bitmap bitmap = ((BitmapDrawable)cp.getDrawable()).getBitmap().copy(Bitmap.Config.ARGB_8888, true);
        cp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                x = (int) event.getX();
                y = (int) event.getY();

                Log.v("x val of cap img >>", x + "");
                Log.v("y val of cap img >>", y + "");


                Bitmap bitmap = Bitmap.createBitmap(400,300, Bitmap.Config.ARGB_8888);
                bitmap = bitmap.copy(bitmap.getConfig(), true);
                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                paint.setColor(Color.BLUE);
                paint.setStyle(Paint.Style.FILL);
                paint.setAntiAlias(true);
                cp.setImageBitmap(bitmap);
                cp.setBackgroundResource(R.drawable.footballfield);
                canvas.drawCircle(x/3,y/3, 5, paint);
                cp.invalidate();
                return false;
            }
        });
    }





}
