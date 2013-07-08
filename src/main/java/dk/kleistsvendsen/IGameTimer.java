package dk.kleistsvendsen;

public interface IGameTimer {
    public long timePlayed();

    public void pauseTimer();

    public void startTimer();

    public void resetTimer();
}
