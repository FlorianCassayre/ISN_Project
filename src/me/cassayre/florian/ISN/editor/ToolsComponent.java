package me.cassayre.florian.ISN.editor;

import me.cassayre.florian.ISN.core.textures.Texture;

import javax.swing.*;
import java.awt.*;

public class ToolsComponent extends JTabbedPane
{
    public static final int ELEMENTS_PER_LINE = 8;

    private final MainLayer mainLayer;

    public ToolsComponent()
    {
        super();

        setMinimumSize(new Dimension(ELEMENTS_PER_LINE * Window.TEXTURES_SIZE, (int) Math.floor(1 + ELEMENTS_PER_LINE / Texture.values().length) * Window.TEXTURES_SIZE));

        mainLayer = new MainLayer();

        addTab("Couche principale", mainLayer);
    }

    public MainLayer getMainLayer()
    {
        return mainLayer;
    }
}
