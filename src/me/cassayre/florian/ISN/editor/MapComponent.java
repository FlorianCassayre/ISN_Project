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
    public static final int OFFSET = 4;

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
                selectX = (int) Math.floor(e.getX() / Window.TEXTURES_SIZE);
                selectY = (int) Math.floor(e.getY() / Window.TEXTURES_SIZE);

                TileMap map = Editor.get().getTileMap();

                int realX = selectX - OFFSET;
                int realY = selectY - OFFSET;

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

        clear(g, getWidth() / Window.TEXTURES_SIZE + 1, getHeight() / Window.TEXTURES_SIZE + 1, map);

        if(map != null)
        {
            for(int x = 0; x < map.getWidth(); x++)
            {
                for(int y = 0; y < map.getHeight(); y++)
                {
                    Tile tile = map.getTile(x, y);

                    if(!tile.getTexture().equals(Texture.AIR))
                        textureManager.drawTexture(g, tile.getTexture(), x * Window.TEXTURES_SIZE + OFFSET * Window.TEXTURES_SIZE, y * Window.TEXTURES_SIZE + OFFSET * Window.TEXTURES_SIZE, Window.TEXTURES_SIZE);
                }
            }

            g.setColor(Color.WHITE);
            g.drawRect((int) (map.getSpawnX() * Window.TEXTURES_SIZE) + OFFSET * Window.TEXTURES_SIZE, (int) (map.getSpawnY() * Window.TEXTURES_SIZE) + OFFSET * Window.TEXTURES_SIZE - Window.TEXTURES_SIZE * 2, Window.TEXTURES_SIZE, Window.TEXTURES_SIZE * 2);
            g.drawString("SPAWN", (int) (map.getSpawnX() * Window.TEXTURES_SIZE) + OFFSET * Window.TEXTURES_SIZE - 1, (int) (map.getSpawnY() * Window.TEXTURES_SIZE) + OFFSET * Window.TEXTURES_SIZE);
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
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                if(map != null && x < map.getWidth() + OFFSET && x >= OFFSET && y < map.getHeight() + OFFSET && y >= OFFSET)
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
                g.fillRect(x * Window.TEXTURES_SIZE, y * Window.TEXTURES_SIZE, Window.TEXTURES_SIZE, Window.TEXTURES_SIZE);


            }
        }
    }
}
