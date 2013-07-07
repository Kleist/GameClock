package com.pivotallabs;

import android.widget.Button;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class HomeActivityTest {
    private HomeActivity activity;
    private Button startButton;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(HomeActivity.class).create().get();
        startButton = (Button) activity.findViewById(R.id.start_button);
    }

    @Test
    public void shouldHaveStartButton() throws Exception {
        assertThat((String) startButton.getText(), equalTo("Start"));
    }
}
