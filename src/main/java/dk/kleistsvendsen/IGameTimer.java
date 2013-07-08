package dk.kleistsvendsen;

public interface IGameTimer {
    public long timeLeft();

    public void pauseTimer();

    public void startTimer();

    public void resetTimer();
}
