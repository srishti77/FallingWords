package com.example.user.fallingwords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", -1);
        TextView scoreTextView = (TextView) findViewById(R.id.score);
        scoreTextView.setText(getString(R.string.score)+" "+score);

        TextView greetingsTextView = (TextView) findViewById(R.id.greetings);

        if(score >10 && score <15)
            greetingsTextView.setText(R.string.message_avg);

        else if(score >15)
            greetingsTextView.setText(R.string.message_good);
        else
            greetingsTextView.setText(R.string.message_do_good);
    }


}
