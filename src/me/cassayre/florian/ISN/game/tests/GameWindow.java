package me.cassayre.florian.ISN.game.tests;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame
{
    public boolean[][] map = new boolean[10][10];

    public double x = 3;
    public double y = 8;

    public double vecX = 0.01;
    public double vecY = 0.1;


    public GameWindow()
    {
        setContentPane(new DrawPane(this));

        setSize(750, 750);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        new Thread(() -> {
            setVisible(true);
        }).start();
    }
}

class DrawPane extends JPanel
{
    private GameWindow window;

    public DrawPane(GameWindow win)
    {
        window = win;

        for(int i = 0; i < 10; i++)
            window.map[i][0] = true;

        window.map[0][0] = true;

        new VelocityLoop(win).start();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        for(int x = 0; x < window.map[0].length; x++)
        {
            for(int y = 0; y < window.map[1].length; y++)
            {
                g.setColor(Color.BLACK);

                if(window.map[x][y])
                    g.fillRect(x * 50, window.map[1].length * 50 - 50 - y * 50, 50, 50);

                g.setColor(Color.RED);

                g.fillRect((int) Math.floor(window.x * 50) + 50, (int) Math.floor(window.map[1].length * 50 - 50 - window.y * 50), 51, 51);

                //System.out.println("-----------" + (window.map[1].length * 25 - 25 - window.y * 25));
            }
        }

    }

}

class VelocityLoop extends Thread
{
    private GameWindow window;

    public VelocityLoop(GameWindow win)
    {
        window = win;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(25);
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            window.vecY -= 0.005;

            double lastX = window.x;
            double lastY = window.y;

            window.x += window.vecX;

            if(tileCollision())
            {
                window.x = lastX;
                //window.vecX = 0;
                precisionX();
            }

            window.y += window.vecY;

            if(tileCollision())
            {
                window.y = lastY;
                precisionY();
                //window.vecY = 0;
            }


            //System.out.println("x: " + window.x + " y: " + window.y);
            //System.out.println("(" + window.vecX + " ; " + window.vecY + ")");

            window.repaint();
        }
    }

    public void precisionX()
    {
        double lastX = window.x;

        for(int i = 0; i < 10; i++)
        {
            window.x += window.vecX / 10;

            if(tileCollision())
            {
                window.x = lastX;
                window.vecX = 0;
                System.out.println("-----------------------");
                return;
            }

            lastX = window.x;
        }
    }

    public void precisionY()
    {
        double lastY = window.y;

        for(int i = 0; i < 20; i++)
        {
            window.y += window.vecY / 10;

            if(tileCollision())
            {
                window.y = lastY;
                window.vecY = 0;
                return;
            }

            lastY = window.y;
        }
    }

    public boolean tileCollision()
    {
        for(int x = (int) Math.floor(window.x); x <= Math.ceil(window.x + 1); x++)
        {
            for(int y = (int) Math.floor(window.y); y <= Math.ceil(window.y + 1); y++)
            {
                if(x < 0 || x >= 9 || y < 0 || y >= 9 || !window.map[x][y])
                    continue;

                if(collision(window.x, window.y, 1, 1, x, y, 1, 1))
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean collision(double x1, double y1, double w1, double h1, double x2, double y2, double w2, double h2)
    {
        if((x2 >= x1 + w1)      // trop à droite
                || (x2 + w2 <= x1) // trop à gauche
                || (y2 >= y1 + h1) // trop en bas
                || (y2 + h2 <= y1))  // trop en haut
            return false;
        else
            return true;
    }



}
