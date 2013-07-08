package dk.kleistsvendsen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static roboguice.RoboGuice.getInjector;

@RunWith(RobolectricTestRunner.class)
public class GameTimerTest {
    private GameTimer gameTimer;

    @Mock
    private ITicSource ticSource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TestGuiceModule module = new TestGuiceModule();
        module.addBinding(ITicSource.class, ticSource);
        TestGuiceModule.setUp(this, module);
        gameTimer = getInjector(Robolectric.application).getInstance(GameTimer.class);
    }

    @After
    public void tearDown() {
        TestGuiceModule.tearDown();
    }

    @Test
    public void testCallsTickOnStartTimer() {
        mockQueueTic(0);
        gameTimer.startTimer();
    }

    @Test
    public void timeLeftReturnsTimeSinceStart() {
        mockQueueTic(100);
        gameTimer.startTimer();
        mockQueueTic(1000);
        assertEquals(900L, gameTimer.timePlayed());
    }

    private void mockQueueTic(int tic) {
        when(ticSource.tic()).thenReturn((long) tic);
    }

    @Test
    public void timeLeftDoesntChangeAfterPause() {
        mockQueueTic(0);
        mockQueueTic(1);
        gameTimer.startTimer();
        gameTimer.pauseTimer();
        long time = gameTimer.timePlayed();
        assertEquals(time, gameTimer.timePlayed());
        verify(ticSource, times(2)).tic();
        verifyNoMoreInteractions(ticSource);
    }

    @Test
    public void pauseWhenPausedDoesNothing() {
        mockQueueTic(0);
        mockQueueTic(1);
        mockQueueTic(2);
        mockQueueTic(3);
        gameTimer.startTimer();
        gameTimer.pauseTimer();
        long time = gameTimer.timePlayed();
        gameTimer.pauseTimer();
        assertEquals(time, gameTimer.timePlayed());
    }

    @Test
    public void startButtonContinues() {
        mockQueueTic(0);
        gameTimer.startTimer();
        mockQueueTic(1);
        gameTimer.pauseTimer();
        mockQueueTic(2);
        gameTimer.startTimer();
        mockQueueTic(3);
        assertEquals(2, gameTimer.timePlayed());
    }
}
