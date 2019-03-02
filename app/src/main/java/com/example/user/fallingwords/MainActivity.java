package com.example.user.fallingwords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
