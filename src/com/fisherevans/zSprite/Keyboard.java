package com.fisherevans.zSprite;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Author: Fisher Evans
 * Date: 3/19/14
 */
public class Keyboard implements KeyListener {
    public static boolean control = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_CONTROL)
            control = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_CONTROL)
            control = false;
    }
}
