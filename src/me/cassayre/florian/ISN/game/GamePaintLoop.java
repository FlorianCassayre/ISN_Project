package me.cassayre.florian.ISN.game;

public class GamePaintLoop extends Thread
{
    private final GameFrame frame;

    public GamePaintLoop(GameFrame frame)
    {
        this.frame = frame;
    }

    @Override
    public void run()
    {
        while(true)
        {
            frame.repaint();

            try
            {
                Thread.sleep(10);
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
