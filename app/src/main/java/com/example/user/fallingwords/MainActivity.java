package com.example.user.fallingwords;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> list = new ArrayList();
    Random rand = new Random();
    TextView language1Word, language2Word;
    Button wrongButton, rightButton;
    Handler handler = new Handler();
    boolean answer = true;
    float bottomOfScreen = 0;
    float screenSize =0;
    int count =0;
    int score =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        language1Word = findViewById(R.id.language1word);
        language2Word = findViewById(R.id.language2word);
        wrongButton = findViewById(R.id.wrong);
        rightButton = findViewById(R.id.right);
        loadData();
        calculateButtomOfScreen();
        generateOptions();
        handler.postDelayed(updateWordThread, 500);

       rightButton.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {
               switch (motionEvent.getAction()) {
                   case MotionEvent.ACTION_DOWN: {
                       if(!answer){
                           view.setBackground(getResources().getDrawable(R.drawable.ic_check_circle_red_24dp));
                       }
                       else{
                           score++;
                       }
                       break;
                   }
                   case MotionEvent.ACTION_UP: {
                       view.setBackground(getResources().getDrawable(R.drawable.ic_check_circle_green_24dp));
                       break;
                   }
               }
               return false;
           }
       });

        wrongButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        if(!answer){
                            view.setBackground(getResources().getDrawable(R.drawable.ic_cancel_green_24dp));
                            score++;
                        }
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        view.setBackground(getResources().getDrawable(R.drawable.ic_cancel_red_24dp));
                        break;
                    }
                }
                return false;
            }
        });
    }

    private void loadData(){
        list.clear();
        try{
            JSONArray jsonArray = new JSONArray(readDataFromJsonFile());

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HashMap<String, String> map = new HashMap<>();
                map.put(jsonObject.getString("text_eng"), jsonObject.getString("text_spa"));
                list.add(map);
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void generateOptions(){

        int random1 = rand.nextInt(list.size());
        int random2 = rand.nextInt(list.size());
        if(random1 == random2)
            answer= true;
        else
            answer = false;
        HashMap map = list.get(random1);
        HashMap map2 = list.get(random2);
        for (Object o : map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
                 language1Word.setText(pair.getKey().toString());
            }

        for (Object o : map2.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            language2Word.setText(pair.getValue().toString());
        }
    }

    private String readDataFromJsonFile() {
        String jsonValue= null;
        try{
            InputStream inputStream = getAssets().open("words_v2.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonValue = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonValue;
    }

    Runnable updateWordThread = new Runnable() {
        @Override
        public void run() {
            if(count <20){
                Log.i("Get BottomScreen ", bottomOfScreen+"");
                if(bottomOfScreen == 0){
                    generateOptions();
                    bottomOfScreen= getBottomOfScreen();
                    language2Word.animate()
                            .translationY(bottomOfScreen)
                            .setInterpolator(new AccelerateInterpolator())
                            .setDuration(3000);
                    count++;
                    handler.postDelayed(this, 4000);
                }
                else{
                    bottomOfScreen = 0;
                    language2Word.setTranslationY(bottomOfScreen);
                    handler.postDelayed(this, 100);
                }
            }
            else{
               gameOver();
            }
        }
    };

    public void gameOver(){
        Log.i("Game Over", ""+count);
        handler.removeCallbacks(updateWordThread);
    }

    public void calculateButtomOfScreen(){

        screenSize = getResources().getDisplayMetrics()
                .heightPixels - (wrongButton.getHeight() * 4);
        bottomOfScreen = screenSize;
        Log.i("bottomScreen cal", bottomOfScreen+"");
    }

    public float getBottomOfScreen(){
        return screenSize;
    }

}
