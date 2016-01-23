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
                int x1 = (int) Math.floor(e.getX() / Window.TEXTURES_SIZE);
                int y1 = (int) Math.floor(e.getY() / Window.TEXTURES_SIZE);

                if(x1 >= ToolsComponent.ELEMENTS_PER_LINE)
                    return;

                int i = x1 + y1 * ToolsComponent.ELEMENTS_PER_LINE;

                if(i >= Texture.values().length) // Texture not in bounds
                    return;

                selectX = x1;
                selectY = y1;

                Editor.get().getWindow().getInfoComponent().update();

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

            if(k != 0 && k % ToolsComponent.ELEMENTS_PER_LINE == 0)
            {
                y++;
                x = 0;
            }

            textureManager.drawTexture(g, texture, x * Window.TEXTURES_SIZE, y * Window.TEXTURES_SIZE, Window.TEXTURES_SIZE);

            x++;

            k++;
        }

        g.setColor(Color.RED);

        // TODO simplify this...
        g.drawLine(selectX * Window.TEXTURES_SIZE - 1, selectY * Window.TEXTURES_SIZE - 1, selectX * Window.TEXTURES_SIZE + Window.TEXTURES_SIZE, selectY * Window.TEXTURES_SIZE - 1);
        g.drawLine(selectX * Window.TEXTURES_SIZE - 1, selectY * Window.TEXTURES_SIZE - 1, selectX * Window.TEXTURES_SIZE - 1, selectY * Window.TEXTURES_SIZE + Window.TEXTURES_SIZE);
        g.drawLine(selectX * Window.TEXTURES_SIZE + Window.TEXTURES_SIZE, selectY * Window.TEXTURES_SIZE - 1, selectX * Window.TEXTURES_SIZE + Window.TEXTURES_SIZE, selectY * Window.TEXTURES_SIZE + Window.TEXTURES_SIZE);
        g.drawLine(selectX * Window.TEXTURES_SIZE - 1, selectY * Window.TEXTURES_SIZE + Window.TEXTURES_SIZE, selectX * Window.TEXTURES_SIZE + Window.TEXTURES_SIZE, selectY * Window.TEXTURES_SIZE + Window.TEXTURES_SIZE);
    }

    public Texture getSelected()
    {
        return Texture.values()[selectX + selectY * ToolsComponent.ELEMENTS_PER_LINE];
    }
}
