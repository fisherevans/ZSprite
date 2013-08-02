package com.fisherevans.zSprite;

import javax.swing.*;
import javax.swing.JMenuBar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/22/13
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class Menu extends JMenuBar
{
    private zSprite _zs;
    private MenuListener _listener;
    private Insets _menuItemInsets = new Insets(50, 5, 50, 5);

    public static final String
        FILE = "File",
        FILE_NEW = "New File",
        FILE_OPEN = "Open",
        FILE_SAVE = "Save",
        FILE_SAVE_AS = "Save As...",
        FILE_EXIT = "Exit";

    public static final String
        EDIT = "Edit",
        EDIT_UNDO = "Undo",
        EDIT_REDO = "Redo",
        EDIT_SETTINGS = "Settings";

    public Menu(zSprite zs)
    {
        _zs = zs;
        _listener = new MenuListener();

        initFileMenu();
        initEditMenu();
    }

    private void initFileMenu()
    {
        JMenu file = new JMenu(FILE);

        file.add(new JMenuItem(FILE_NEW));
        file.add(new JMenuItem(FILE_OPEN));
        file.add(new JMenuItem(FILE_SAVE));
        file.add(new JMenuItem(FILE_SAVE_AS));
        file.add(new JMenuItem(FILE_EXIT));

        for(Component item:file.getComponents())
            ((JMenuItem)item).addActionListener(_listener);

        add(file);
    }

    private void initEditMenu()
    {
        JMenu edit = new JMenu(EDIT);

        edit.add(new JMenuItem(EDIT_UNDO));
        edit.add(new JMenuItem(EDIT_REDO));
        edit.add(new JMenuItem(EDIT_SETTINGS));

        for(Component item:edit.getComponents())
            configureMenuItem((JMenuItem)item);

        add(edit);
    }

    private void configureMenuItem(JMenuItem i)
    {
        i.addActionListener(_listener);
    }

    public class MenuListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch(((JMenuItem)e.getSource()).getText())
            {
                case FILE_NEW:
                {

                    break;
                }
                case FILE_OPEN:
                {

                    break;
                }
                case FILE_SAVE:
                {

                    break;
                }
                case FILE_SAVE_AS:
                {

                    break;
                }
                case FILE_EXIT:
                {

                    break;
                }
                case EDIT_UNDO:
                {

                    break;
                }
                case EDIT_REDO:
                {

                    break;
                }
                case EDIT_SETTINGS:
                {

                    break;
                }
            }
        }
    }
}
