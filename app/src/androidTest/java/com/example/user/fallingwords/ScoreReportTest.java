package com.example.user.fallingwords;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by User on 02/03/2019.
 */
@RunWith(AndroidJUnit4.class)
public class ScoreReportTest {
    @Rule
    public ActivityTestRule<ScoreActivity> rule =
            new ActivityTestRule(ScoreActivity.class, true, false);

    int score=20;
    @Test
    public void intent() {
            Context targetContext = getInstrumentation()
                .getTargetContext();

            Intent intent = new Intent();
            intent.putExtra("Score", score);
            rule.launchActivity(intent);
            Espresso.onView(withId(R.id.greetings)).check(ViewAssertions.matches(withText(R.string.message_good)));
    }
}
