package dk.kleistsvendsen;

import android.widget.Button;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class HomeActivityTest {
    private HomeActivity activity;
    private Button startButton;
    @Mock
    private IGameTimer gameTimer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        TestGuiceModule module = new TestGuiceModule();
        module.addBinding(IGameTimer.class, gameTimer);
        TestGuiceModule.setUp(this, module);
        activity = Robolectric.buildActivity(HomeActivity.class).create().get();
        startButton = (Button) activity.findViewById(R.id.start_button);
    }

    @After
    public void tearDown() {
        TestGuiceModule.tearDown();
    }

    @Test
    public void mockShouldBeNotNull() throws Exception {
        assertNotNull(gameTimer);
    }

    @Test
    public void shouldHaveStartButton() throws Exception {
        assertThat((String) startButton.getText(), equalTo("Start"));
    }

    @Test
    public void startButtonConnectedToGameTimer() throws Exception {
        startButton.performClick();
        verify(gameTimer).startTimer();
    }

    @Test
    public void shouldHavePauseButton() throws Exception {
        Button pauseButton = (Button) activity.findViewById(R.id.pause_button);
        assertThat((String) pauseButton.getText(), equalTo("Pause"));
    }

    @Test
    public void shouldHaveResetButton() throws Exception {
        Button resetButton = (Button) activity.findViewById(R.id.reset_button);
        assertThat((String) resetButton.getText(), equalTo("Reset"));
    }

    @Test
    public void shouldHaveDefaultTimeLeftText() throws Exception {
        TextView timeLeftText = (TextView) activity.findViewById(R.id.timeLeftText);
        assertThat((String) timeLeftText.getText(), equalTo("20:00"));
    }

    @Test
    public void shouldHaveDefaultTimePlayedText() throws Exception {
        TextView timePlayedText = (TextView) activity.findViewById(R.id.timePlayedText);
        assertThat((String) timePlayedText.getText(), equalTo("0:00"));
    }
}
