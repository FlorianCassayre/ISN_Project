package me.cassayre.florian.ISN.game;

import me.cassayre.florian.ISN.core.map.entities.Player;

public class GameLoop extends Thread
{
    public GameLoop()
    {

    }

    @Override
    public void run()
    {
        while(true)
        {
            if(Game.get() == null)
                continue;

            GameFrame frame = Game.get().getFrame();

            if(frame == null)
                continue;

            final double maxX = 0.05;
            final double maxY = 0.1;
            final double acc = 0.05;
            final double slw = 0.003;
            final double air = 0.0;
            final double grvt = 0.008;

            Player player = Game.get().getTileMap().getPlayer();
            player.setVecX(player.getVecX() + frame.getDirectionX() * acc);
            player.setVecY(player.getVecY() + frame.getDirectionY() * acc);

            if(Math.abs(player.getVecX()) > maxX)
                player.setVecX(player.getVecX() > 0 ? maxX : -maxX);

            if(Math.abs(player.getVecY()) > maxY)
                player.setVecY(player.getVecY() > 0 ? maxY : -maxY);

            double vX = player.getVecX();
            double vY = player.getVecY();

            vX += -multiply(player.isOnGround() ? slw : air, vX);
            vY += -multiply(player.isOnGround() ? slw : air, vY);

            if(!sameSign(vX, player.getVecX()))
                vX = 0;
            if(!sameSign(vY, player.getVecY()))
                vY = 0;



            player.setVecX(vX);
            player.setVecY(vY);

            if(!player.isOnGround())
                player.setVecY(player.getVecY() + grvt);
            else
                if(player.getVecY() > 0)
                    player.setVecY(0);
            //player.a();
            //System.out.println(player.isOnGround());

            player.setX(player.getX() + player.getVecX());
            player.setY(player.getY() + player.getVecY());

            try
            {
                Thread.sleep(10);
            } catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private boolean sameSign(double d1, double d2)
    {
        return d1 >= 0 && d2 >= 0 || d1 <= 0 && d2 <= 0;
    }

    private double multiply(double d, double sign)
    {
        if(sign > 0)
            return d;
        if(sign < 0)
            return -d;
        return 0;
    }
}
