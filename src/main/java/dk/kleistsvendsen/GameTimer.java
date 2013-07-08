package dk.kleistsvendsen;

import android.os.SystemClock;

public class GameTimer implements IGameTimer {

    @Override
    public int timeLeft() {
        return (int)(SystemClock.elapsedRealtime()%1000);
    }

    @Override
    public void pauseTimer() {

    }

    @Override
    public void startTimer() {

    }

    @Override
    public void resetTimer() {

    }
}
