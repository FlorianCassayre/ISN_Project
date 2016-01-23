package me.cassayre.florian.ISN.editor;

import me.cassayre.florian.ISN.core.textures.TextureManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Window extends JFrame
{

    private final ScrollFrame scrollFrame;
    private final MapComponent mapComponent;
    private final MainMenu menu;

    private final ToolsComponent toolsComponent;
    private final WorkingComponent working;

    private TextureManager textureManager;

    public Window()
    {
        super("Editeur");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        mapComponent = new MapComponent(new Dimension(320, 320));
        scrollFrame = new ScrollFrame(mapComponent);
        mapComponent.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(800,300));

        toolsComponent = new ToolsComponent();
        working = new WorkingComponent(scrollFrame, toolsComponent);

        this.setContentPane(working);

        menu = new MainMenu();

        this.setJMenuBar(menu);

        try
        {
            textureManager = new TextureManager();
        } catch(IOException e)
        {
            e.printStackTrace();
        }

        new Thread(() -> {setVisible(true);}).start();
    }

    public MapComponent getMapComponent()
    {
        return mapComponent;
    }

    public TextureManager getTextureManager()
    {
        return textureManager;
    }

    public WorkingComponent getWorkingComponent()
    {
        return working;
    }

    public ToolsComponent getToolsComponent()
    {
        return toolsComponent;
    }
}
