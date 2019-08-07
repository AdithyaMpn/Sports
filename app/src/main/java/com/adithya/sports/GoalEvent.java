package com.adithya.sports;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class GoalEvent extends AppCompatActivity {

    private String TAG = GoalEvent.class.getSimpleName();
    int x,y;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference ref= database.getReference("Data");

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

                int RealX = x/3;
                int RealY = y/3;

                Log.v("RealX >>", RealX + "");
                Log.v("RealY >>", RealY+ "");


                Bitmap bitmap = Bitmap.createBitmap(400,300, Bitmap.Config.ARGB_8888);
                bitmap = bitmap.copy(bitmap.getConfig(), true);
                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                paint.setColor(Color.BLUE);
                paint.setStyle(Paint.Style.FILL);
                paint.setAntiAlias(true);
                cp.setImageBitmap(bitmap);
                cp.setBackgroundResource(R.drawable.footballfield);
                canvas.drawCircle(RealX,RealY, 5, paint);
                cp.invalidate();

                if(RealX<80)
                    Toast.makeText(GoalEvent.this, "From Left D Area", Toast.LENGTH_SHORT).show();
                if(RealX>80&&RealX<199)
                    Toast.makeText(GoalEvent.this, "From Left Non-D Area", Toast.LENGTH_SHORT).show();
                if(RealX>199&&RealX<320)
                    Toast.makeText(GoalEvent.this, "From Right Non-D Area", Toast.LENGTH_SHORT).show();
                if(RealX>320)
                    Toast.makeText(GoalEvent.this, "From Right D Area", Toast.LENGTH_SHORT).show();

                String[] colors = {"By Head", "By Leg"};

                AlertDialog.Builder builder = new AlertDialog.Builder(GoalEvent.this);
                builder.setTitle("How is the Goal Shot");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        String[] colors = {"Jersy1", "Jersy2","Jersy3"};

                        AlertDialog.Builder builder = new AlertDialog.Builder(GoalEvent.this);
                        builder.setTitle("Who Assisted");
                        builder.setItems(colors, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on colors[which]
                                Toast.makeText(GoalEvent.this,"Mark the Assisted Player Position",Toast.LENGTH_LONG).show();
                            }
                        });
                        builder.show();
                    }
                });
                builder.show();

                return false;
            }
        });
    }

    private class GetData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(GoalEvent.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://api.affixus.com/pub/home/live/5c9f4d3003d5dba159be3efd";
            String jsonStr = sh.makeServiceCall(url);

            Map<String, Object> retMap = new Gson().fromJson(
                    jsonStr, new TypeToken<HashMap<String, Object>>() {}.getType()
            );
            
            ref.setValue(retMap);
            Log.v(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {


            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

    }

public void update(View view){
    new GetData().execute();
}

}
