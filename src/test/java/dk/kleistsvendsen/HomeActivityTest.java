package dk.kleistsvendsen;

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
    private Button pauseButton;
    private Button resetButton;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(HomeActivity.class).create().get();
        startButton = (Button) activity.findViewById(R.id.start_button);
        pauseButton = (Button) activity.findViewById(R.id.pause_button);
        resetButton = (Button) activity.findViewById(R.id.reset_button);
    }

    @Test
    public void shouldHaveStartButton() throws Exception {
        assertThat((String) startButton.getText(), equalTo("Start"));
    }

    @Test
    public void shouldHavePauseButton() throws Exception {
        assertThat((String) pauseButton.getText(), equalTo("Pause"));
    }

    @Test
    public void shouldHaveResetButton() throws Exception {
        assertThat((String) resetButton.getText(), equalTo("Reset"));
    }
}
