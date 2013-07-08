package dk.kleistsvendsen;

import com.google.inject.Inject;

public class GameTimer implements IGameTimer {
    private ITicSource ticSource_;
    private long startTic_;
    private long pauseTic_;
    private boolean running_;

    @Inject
    public GameTimer(ITicSource ticSource) {
        running_ = false;
        ticSource_ = ticSource;
        startTic_ = 0;
        pauseTic_ = 0;
    }

    @Override
    public long timeLeft() {
        if (running_) {
            return ticSource_.tic() -startTic_;
        }
        else {
            return pauseTic_ - startTic_;
        }
    }

    @Override
    public void pauseTimer() {
        pauseTic_ = ticSource_.tic();
        running_ = false;
    }

    @Override
    public void startTimer() {
        running_ = true;
        ticSource_.tic();
    }

    @Override
    public void resetTimer() {

    }
}
