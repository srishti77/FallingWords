package com.example.user.fallingwords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    public static int AVERAGE_SCORE = 10;
    public static int ABOVE_AVERAGE_SCORE = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        int score = intent.getIntExtra("Score", -1);
        TextView scoreTextView = (TextView) findViewById(R.id.score);
        scoreTextView.setText(getString(R.string.score)+" "+score);

        TextView greetingsTextView = (TextView) findViewById(R.id.greetings);
        Button backButton = findViewById(R.id.back);

        if(score >AVERAGE_SCORE && score <ABOVE_AVERAGE_SCORE)
            greetingsTextView.setText(R.string.message_avg);
        else if(score >ABOVE_AVERAGE_SCORE)
            greetingsTextView.setText(R.string.message_good);
        else
            greetingsTextView.setText(R.string.message_do_good);

        backButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }


}
