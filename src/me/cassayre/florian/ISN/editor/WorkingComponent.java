package me.cassayre.florian.ISN.editor;

import javax.swing.*;
import java.awt.*;

public class WorkingComponent extends JSplitPane
{
    public WorkingComponent(Component view1, Component view2)
    {
        super(JSplitPane.HORIZONTAL_SPLIT, view1, view2);

        setResizeWeight(1);

        setEnabled(false);
    }
}
