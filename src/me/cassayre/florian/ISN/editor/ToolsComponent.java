package me.cassayre.florian.ISN.editor;

import javax.swing.*;

public class ToolsComponent extends JTabbedPane
{
    private final MainLayer mainLayer;

    public ToolsComponent()
    {
        super();

        mainLayer = new MainLayer();

        addTab("Couche principale", mainLayer);
    }

    public MainLayer getMainLayer()
    {
        return mainLayer;
    }
}
