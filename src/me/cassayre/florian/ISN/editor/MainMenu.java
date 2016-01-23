package me.cassayre.florian.ISN.editor;

import com.google.gson.Gson;
import me.cassayre.florian.ISN.core.map.TileMap;
import me.cassayre.florian.ISN.core.serializer.MapLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;

public class MainMenu extends JMenuBar
{
    private final JMenu file = new JMenu("Fichier");
    private final JMenu edit = new JMenu("Edition");

    public MainMenu()
    {
        super();

        JMenuItem newFile = new JMenuItem("Nouveau");
        newFile.setIcon(UIManager.getIcon("FileView.fileIcon"));
        newFile.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TileMap t = new TileMap(50, 15);
                t.setSpawn(2, 5);
                Editor.get().setTileMap(t);
            }
        });

        JMenuItem openFile = new JMenuItem("Ouvrir...");
        openFile.setIcon(UIManager.getIcon("FileView.directoryIcon"));
        openFile.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });

        JMenuItem saveFile = new JMenuItem("Enregistrer sous...");
        saveFile.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
        saveFile.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String filename = "E:/testjson.json";

                Gson gson = new Gson();
                String s = gson.toJson(MapLoader.serialize(Editor.get().getTileMap()));

                FileOutputStream outputStream;

                try {
                    outputStream = new FileOutputStream(filename);
                    outputStream.write(s.getBytes());
                    outputStream.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JMenuItem exitFile = new JMenuItem("Quitter");
        //exitFile.setIcon(UIManager.getIcon("FileChooser.detailsViewIcon"));

        exitFile.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?","Attention", JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });

        file.add(newFile);
        file.add(new JSeparator());
        file.add(openFile);
        file.add(saveFile);
        file.add(new JSeparator());
        file.add(exitFile);

        add(file);
        add(edit);
    }
}
