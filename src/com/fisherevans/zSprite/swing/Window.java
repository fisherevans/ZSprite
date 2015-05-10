package com.fisherevans.zSprite.swing;

import com.fisherevans.zSprite.Keyboard;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Fisher Evans
 * Date: 3/20/14
 */
public class Window {
    private JFrame _frame;
    private JPanel _rootPanel;

    public Window(String title, String migArgs) {
        initFrame(title);
        initRootPanel(migArgs);
    }

    private void initFrame(String title) {
        _frame = new JFrame("ZSprite");
        _frame.addKeyListener(new Keyboard());
    }

    private void initRootPanel(String migArgs) {
        _rootPanel = new JPanel(new MigLayout(migArgs));
        _frame.add(_rootPanel);
    }

    public void displayFrame() {
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.pack();
        centerJFrame(_frame);
        _frame.setVisible(true);
    }

    public JPanel getRootPanel() {
        return _rootPanel;
    }

    public JFrame getFrame() {
        return _frame;
    }

    public static void centerJFrame(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }
}
