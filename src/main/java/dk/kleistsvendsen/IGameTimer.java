package dk.kleistsvendsen;

public interface IGameTimer {
    public int timeLeft();

    public void pauseTimer();

    public void startTimer();

    public void resetTimer();
}
