package com.fisherevans.zSpriteOld;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/22/13
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class LiveSettings extends JPanel
{
    private zSprite _zs;

    private JPanel _colorSettings, _currentTool;
    private JTextField _colorText, _colorHexText, _colorHex, _colorRGBText, _colorR, _colorG, _colorB;
    private JButton _colorSwatch;

    public LiveSettings(zSprite zs)
    {
        super();
        _zs = zs;

        initColorComponents();

        setPreferredSize(new Dimension(1, 30));
        setVisible(true);
    }

    private void initColorComponents()
    {
        _colorText = new JTextField("Current Color");
        _colorText.setEditable(false);
        _colorText.setBorder(BorderFactory.createEmptyBorder());
        add(_colorText);

        _colorSwatch = new JButton();
        _colorSwatch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        _colorSwatch.setBorder(BorderFactory.createEmptyBorder());
        _colorSwatch.setPreferredSize(new Dimension(30, 20));
        add(_colorSwatch);

        _colorHexText = new JTextField("Hex: ");
        _colorHexText.setEditable(false);
        _colorHexText.setBorder(BorderFactory.createEmptyBorder());
        add(_colorHexText);

        _colorHex = new JTextField("#FFFFFF");
        _colorHex.setPreferredSize(new Dimension(58, 20));
        add(_colorHex);

        _colorRGBText = new JTextField("RGB: ");
        _colorRGBText.setEditable(false);
        _colorRGBText.setBorder(BorderFactory.createEmptyBorder());
        add(_colorRGBText);

        _colorR = new JTextField("255");
        _colorR.setPreferredSize(new Dimension(30, 20));
        add(_colorR);

        _colorG = new JTextField("255");
        _colorG.setPreferredSize(new Dimension(30, 20));
        add(_colorG);

        _colorB = new JTextField("255");
        _colorB.setPreferredSize(new Dimension(30, 20));
        add(_colorB);
    }

    public void setColor(Color c)
    {
        _colorSwatch.setBackground(c);
        String hex = Integer.toHexString(c.getRGB());
        _colorHex.setText("#" + hex.substring(2, hex.length()).toUpperCase());
        repaint();
    }
}
