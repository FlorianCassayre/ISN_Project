package me.cassayre.florian.ISN.editor;

import me.cassayre.florian.ISN.core.map.TileMap;

import javax.swing.*;
import java.awt.*;

public class Editor
{
    private static Editor instance = null;

    private final Window window;

    private TileMap tileMap = null;
    private boolean canSafeClose = true;

    public Editor()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            e.printStackTrace();
        }

        window = new Window();

        setTileMap(new TileMap(10, 10));
    }

    public Window getWindow()
    {
        return window;
    }

    public TileMap getTileMap()
    {
        return tileMap;
    }

    public void setTileMap(TileMap map)
    {
        tileMap = map;

        window.getMapComponent().setPreferredSize(new Dimension(tileMap.getWidth() * 32 + 8 * 32, tileMap.getHeight() * 32 + 8 * 32));


        canSafeClose = true;

        System.out.println(tileMap.getWidth() + " " + tileMap.getHeight());

        window.getInfoComponent().update();

        window.invalidate();
    }

    public static void main(String[] main)
    {
        instance = new Editor();
    }

    public static Editor get()
    {
        return instance;
    }
}
