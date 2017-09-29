package server;

public class SpectatorCounter {

    private int count = 0;

    public SpectatorCounter()
    {
    }

    public synchronized void count()
    {
        count++;
    }

    public int getCount()
    {
        return count;
    }

}
