package me.cassayre.florian.ISN.game;

import me.cassayre.florian.ISN.core.map.TileMap;
import me.cassayre.florian.ISN.core.textures.TextureManager;

import java.io.IOException;

public class Game
{
    private static Game instance = null;

    private GameFrame frame;

    private TextureManager textureManager;

    private TileMap map = new TileMap(100, 50);

    public Game()
    {
        try
        {
            textureManager = new TextureManager();
        } catch(IOException e)
        {
            e.printStackTrace();
        }

        frame = new GameFrame();

        new GamePaintLoop(frame).start();

        new GameLoop().start();

        map.setSpawn(2, 3);
    }

    public GameFrame getFrame()
    {
        return frame;
    }

    public TileMap getTileMap()
    {
        return map;
    }

    public TextureManager getTextureManager()
    {
        return textureManager;
    }

    public static void main(String[] args)
    {
        instance = new Game();
    }

    public static Game get()
    {
        return instance;
    }
}
