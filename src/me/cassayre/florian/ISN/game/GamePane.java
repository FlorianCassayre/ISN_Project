package me.cassayre.florian.ISN.game;

import me.cassayre.florian.ISN.core.map.Tile;
import me.cassayre.florian.ISN.core.map.TileMap;
import me.cassayre.florian.ISN.core.map.entities.Entity;
import me.cassayre.florian.ISN.core.map.entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePane extends JPanel
{
    @Override
    public void paintComponent(Graphics g)
    {
        if(Game.get() == null) return;

        TileMap map = Game.get().getTileMap();

        if(map == null || Game.get().getFrame().getWidth() == -1) return;

        /*
        for(int x = (int) Math.floor(map.getPlayer().getX() - Game.get().getFrame().getWidth() / 2); x <= Math.ceil(map.getPlayer().getX() + Game.get().getFrame().getWidth() / 2); x++)
        {
            for(int y = (int) Math.floor(map.getPlayer().getY() + Game.get().getFrame().getHeight() / 2); x <= Math.ceil(map.getPlayer().getX() + Game.get().getFrame().getWidth() / 2); x++)
            {

            }
        }*/

        final int size = 32;

        Player player = map.getPlayer();

        for(int x = -1; x < Math.ceil(Game.get().getFrame().getWidth() / size) + 1; x++)
        {
            for(int y = -1; y < Math.ceil(Game.get().getFrame().getHeight() / size) + 1; y++)
            {


                Tile tile = map.getTile(map.getPlayer().getX() + x - (Game.get().getFrame().getWidth() / size) / 2, map.getPlayer().getY() + y - (Game.get().getFrame().getHeight() / size) / 2);
                Game.get().getTextureManager().drawTexture(g, tile.getTexture(), - ((int) (player.getX() * size)) % size + x * size + (Game.get().getFrame().getWidth() / 2) % size, - ((int) (player.getY() * size)) % size + y * size  + (Game.get().getFrame().getHeight() / 2) % size, 32);
/*
                g.setColor(Color.RED);
                g.fillRect(x * size, y * size, );*/
            }
        }

        for(Entity entity : map.getEntities())
        {
            //System.out.println("o" + (int) ((player.getY() - entity.getY()) * 32 + 32 * (Game.get().getFrame().getHeight() / size) / 2));
            //System.out.println((int) entity.getBox().getWidth());
            g.setColor(Color.RED);

            //int screenX = player.getX() - entity.getX();
            g.fillRect((int) ((player.getX() - entity.getX()) * 32 + (Game.get().getFrame().getWidth()) / 2), (int) ((player.getY() - entity.getY()) * 32 + (Game.get().getFrame().getHeight()) / 2), (int) entity.getBox().getWidth() * 32, (int) - entity.getBox().getHeight() * 32);
        }
    }
}
