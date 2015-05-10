package com.fisherevans.zSprite.swing;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

/**
 * Author: Fisher Evans
 * Date: 3/19/14
 */
public abstract class View extends JPanel {
    public View(String migLayoutParams) {
        super(new MigLayout(migLayoutParams));
    }
}
