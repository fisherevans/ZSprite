package com.fisherevans.zSprite.swing.views;

import com.fisherevans.zSprite.swing.View;
import org.newdawn.slick.CanvasGameContainer;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Author: Fisher Evans
 * Date: 3/20/14
 */
public class ColorSlider extends View {
    private BufferedImage _rainbow;
    public ColorSlider(String migLayoutParams) {
        super(migLayoutParams);

        Color c;
        c = JColorChooser.showDialog(
                getParent(),
                "Demo", Color.blue);
        JColorChooser dialog = new JColorChooser(Color.black);
        for(AbstractColorChooserPanel p:dialog.getChooserPanels())
            System.out.println(p.toString() + p.getClass().getName());
        setBackground(c);
    }

    @Override
    public void print(Graphics g) {
    }
}
