package me.cassayre.florian.ISN.editor;

import me.cassayre.florian.ISN.core.textures.Texture;

import javax.swing.*;
import java.awt.*;

public class InfoComponent extends JTable
{

    public InfoComponent()
    {
        super(new Object[5][2], new String[]{"Donnée", "Valeur"});
        setMinimumSize(new Dimension(8 * 32, 100));

        update();
    }

    public void update()
    {
        Editor editor = Editor.get();
        if(editor == null)
            return;
        Window window = editor.getWindow();

        Texture selected = window.getToolsComponent().getMainLayer().getSelected();

        Object[][] values = new Object[][]
                {
                        {
                                "Nom", editor.getTileMap().getName()
                        },
                        {
                                "Description", editor.getTileMap().getDescription()
                        },
                        {
                                "Taille", editor.getTileMap().getWidth() + "x" + editor.getTileMap().getHeight()
                        },
                        {
                                "Texture", selected + " (" + "ID=" + selected.getId() + ", X=" + selected.getX() + ", Y=" + selected.getY() + ")"
                        },
                        {
                                "Position", "x=" + editor.getWindow().getMapComponent().getSelectedX() + " y=" + editor.getWindow().getMapComponent().getSelectedY()
                        }
                };

        for(int i = 0; i < 5; i++)
        {
            setValueAt(values[i][0], i, 0);
            setValueAt(values[i][1], i, 1);
        }
    }
}
