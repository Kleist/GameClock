package dk.kleistsvendsen;

/**
 * Created by andreas on 07/07/13.
 */
public interface IGameTimer {
    public int timeLeft();

    public void pauseTimer();

    public void startTimer();

    public void resetTimer();
}
