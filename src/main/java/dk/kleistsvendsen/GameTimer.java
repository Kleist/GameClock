package dk.kleistsvendsen;

import com.google.inject.Inject;

public class GameTimer implements IGameTimer {
    @Inject
    private ITicSource ticSource_;
    private long startTic_;

    public GameTimer() {
        startTic_ = ticSource_.tic();
    }

    @Override
    public long timeLeft() {
        return ticSource_.tic() -startTic_;
    }

    @Override
    public void pauseTimer() {

    }

    @Override
    public void startTimer() {
        ticSource_.tic();
    }

    @Override
    public void resetTimer() {

    }
}
