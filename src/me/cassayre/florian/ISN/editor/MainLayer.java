package me.cassayre.florian.ISN.editor;

import me.cassayre.florian.ISN.core.map.TileMap;
import me.cassayre.florian.ISN.core.textures.Texture;
import me.cassayre.florian.ISN.core.textures.TextureManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainLayer extends JPanel
{

    private int selectX = 0, selectY = 0;

    public MainLayer()
    {
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int x1 = (int) Math.floor(e.getX() / 32);
                int y1 = (int) Math.floor(e.getY() / 32);

                if(x1 >= 4)
                    return;

                int i = x1 + y1 * 4;

                if(i >= Texture.values().length) // Texture not in bounds
                    return;

                selectX = x1;
                selectY = y1;

                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g)
    {
        if(Editor.get() == null) return;

        TileMap map = Editor.get().getTileMap();
        TextureManager textureManager = Editor.get().getWindow().getTextureManager();

        if(map == null)
            return;

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        int k = 0;
        int x = 0, y = 0;
        for(int i = 0; i < Texture.values().length; i++)
        {
            Texture texture = Texture.getById(i);

            if(k != 0 && k % 4 == 0)
            {
                y++;
                x = 0;
            }

            textureManager.drawTexture(g, texture, x * 32, y * 32, 32);

            x++;

            k++;
        }

        g.setColor(Color.RED);

        g.drawLine(selectX * 32 - 1, selectY * 32 - 1, selectX * 32 + 32, selectY * 32 - 1);
        g.drawLine(selectX * 32 - 1, selectY * 32 - 1, selectX * 32 - 1, selectY * 32 + 32);
        g.drawLine(selectX * 32 + 32, selectY * 32 - 1, selectX * 32 + 32, selectY * 32 + 32);
        g.drawLine(selectX * 32 - 1, selectY * 32 + 32, selectX * 32 + 32, selectY * 32 + 32);
    }

    public Texture getSelected()
    {
        return Texture.values()[selectX + selectY * 4];
    }
}
