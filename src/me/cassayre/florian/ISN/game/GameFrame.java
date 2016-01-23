package me.cassayre.florian.ISN.game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame
{
    private GamePane gamePane;
    private boolean playerLeft, playerRight, playerUp, playerDown;

    public GameFrame()
    {
        super("ISN");

        setSize(400, 400);

        gamePane = new GamePane();

        setContentPane(gamePane);

        new Thread(() -> {
            setVisible(true);
        }).start();

        this.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyChar() == 'q')
                    playerLeft = true;
                if(e.getKeyChar() == 'd')
                    playerRight = true;
                if(e.getKeyChar() == 'z')
                    playerUp = true;
                if(e.getKeyChar() == 's')
                    playerDown = true;
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                if(e.getKeyChar() == 'q')
                    playerLeft = false;
                if(e.getKeyChar() == 'd')
                    playerRight = false;
                if(e.getKeyChar() == 'z')
                    playerUp = false;
                if(e.getKeyChar() == 's')
                    playerDown = false;
            }
        });
    }

    public GamePane getGamePane()
    {
        return gamePane;
    }

    public int getDirectionX()
    {
        return (playerLeft ? 0 : 1) + (playerRight ? 0 : -1);
    }

    public int getDirectionY()
    {
        return (playerUp ? 0 : 1) + (playerDown ? 0 : -1);
    }
}
