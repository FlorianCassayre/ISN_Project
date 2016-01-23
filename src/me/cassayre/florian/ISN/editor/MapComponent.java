package me.cassayre.florian.ISN.editor;

import me.cassayre.florian.ISN.core.map.Tile;
import me.cassayre.florian.ISN.core.map.TileMap;
import me.cassayre.florian.ISN.core.textures.Texture;
import me.cassayre.florian.ISN.core.textures.TextureManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapComponent extends JPanel
{

    private int selectX = -1, selectY = -1;

    public MapComponent(Dimension size)
    {
        super();

        setPreferredSize(size);

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                selectX = (int) Math.floor(e.getX() / 32);
                selectY = (int) Math.floor(e.getY() / 32);

                TileMap map = Editor.get().getTileMap();

                int realX = selectX - 4;
                int realY = selectY - 4;

                if(realX >= 0 && realY >= 0 && realX < map.getWidth() && realY < map.getHeight())
                    map.setTile(realX, realY, new Tile(Editor.get().getWindow().getToolsComponent().getMainLayer().getSelected()));

                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        if(Editor.get() == null)
            return;

        TileMap map = Editor.get().getTileMap();

        TextureManager textureManager = Editor.get().getWindow().getTextureManager();

        clear(g, getWidth() / 32 + 1, getHeight() / 32 + 1, map);

        if(map != null)
        {
            for(int x = 0; x < map.getWidth(); x++)
            {
                for(int y = 0; y < map.getHeight(); y++)
                {
                    Tile tile = map.getTile(x, y);

                    if(!tile.getTexture().equals(Texture.AIR))
                        textureManager.drawTexture(g, tile.getTexture(), x * 32 + 4 * 32, y * 32 + 4 * 32, 32);
                }
            }

            g.setColor(Color.WHITE);
            g.drawRect((int) (map.getSpawnX() * 32) + 4 * 32, (int) (map.getSpawnY() * 32) + 4 * 32 - 32 * 2, 32, 32 * 2);
            g.drawString("SPAWN", (int) (map.getSpawnX() * 32) + 4 * 32 - 1, (int) (map.getSpawnY() * 32) + 4 * 32);
        }

        g.setColor(Color.RED);

        g.drawLine(selectX * 32 - 1, selectY * 32 - 1, selectX * 32 + 32, selectY * 32 - 1);
        g.drawLine(selectX * 32 - 1, selectY * 32 - 1, selectX * 32 - 1, selectY * 32 + 32);
        g.drawLine(selectX * 32 + 32, selectY * 32 - 1, selectX * 32 + 32, selectY * 32 + 32);
        g.drawLine(selectX * 32 - 1, selectY * 32 + 32, selectX * 32 + 32, selectY * 32 + 32);
        //g.fillRect(100, 100, 200, 200);
    }

    private void clear(Graphics g, int width, int height, TileMap map)
    {
        final int step = 32;

        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                if(map != null && x < map.getWidth() + 4 && x >= 4 && y < map.getHeight() + 4 && y >= 4)
                {
                    if((x + y) % 2 == 0)
                    {
                        g.setColor(Color.BLACK);
                    } else
                    {
                        g.setColor(Color.MAGENTA);
                    }
                }
                else
                {
                    g.setColor(Color.BLUE);
                }
                g.fillRect(x * step, y * step, step, step);


            }
        }
    }
}
