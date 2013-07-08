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
        when(ticSource.tic()).thenReturn(0L);
        gameTimer.startTimer();
    }

    @Test
    public void startThenTimeLeft() {
        when(ticSource.tic()).thenReturn(0L);
        gameTimer.startTimer();
        when(ticSource.tic()).thenReturn(1000L);
        assertEquals(1000L, gameTimer.timePlayed());
    }

    @Test
    public void timeLeftDoesntChangeAfterPause() {
        when(ticSource.tic()).thenReturn(0L).thenReturn(1L);
        gameTimer.startTimer();
        gameTimer.pauseTimer();
        long time = gameTimer.timePlayed();
        assertEquals(time, gameTimer.timePlayed());
        verify(ticSource, times(2)).tic();
        verifyNoMoreInteractions(ticSource);
    }

    @Test
    public void startButtonContinues() {
        when(ticSource.tic()).thenReturn(0L);
        gameTimer.startTimer();
        when(ticSource.tic()).thenReturn(1L);
        gameTimer.pauseTimer();
        when(ticSource.tic()).thenReturn(2L);
        gameTimer.startTimer();
        when(ticSource.tic()).thenReturn(3L);
        assertEquals(2, gameTimer.timePlayed());
    }
}
