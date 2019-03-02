package com.example.user.fallingwords;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by User on 02/03/2019.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    //Mocking the count of words asked as 20
    int count =20;
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void intent() {
        if(count == 20){
            Intent intent = new Intent(InstrumentationRegistry.getContext(), ScoreActivity.class );
            activityActivityTestRule.launchActivity(intent);
        }
        // Continue with your test
    }
}
