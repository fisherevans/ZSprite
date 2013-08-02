package com.fisherevans.zSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/22/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Swatch extends JPanel
{
    private zSprite _zs;

    private SwatchButtonListener _swatchListener;

    private JPanel _colorSettings;
    private JButton _colorDisplay, _addSwatch, _removeSwatch;

    private boolean _removing = false;

    public Swatch(zSprite zs)
    {
        _zs = zs;

        _swatchListener = new SwatchButtonListener();

        _colorSettings = new JPanel();
        _colorSettings.setPreferredSize(new Dimension(100, 120));
        add(_colorSettings);
        generateColorSettings();

        generateSwatch();

        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        setPreferredSize(new Dimension(100, 1));
        setVisible(true);
    }

    public void generateColorSettings()
    {
        _colorDisplay = new JButton();
        //_colorDisplay.setBorder(BorderFactory.createEmptyBorder());
        _colorDisplay.setContentAreaFilled(false);
        _colorDisplay.setPreferredSize(new Dimension(80, 60));
        _colorDisplay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        _colorDisplay.addActionListener(_swatchListener);

        _addSwatch = new JButton("Add");
        _addSwatch.setPreferredSize(new Dimension(80, 22));
        _addSwatch.addActionListener(_swatchListener);

        _removeSwatch = new JButton("Remove");
        _removeSwatch.setPreferredSize(new Dimension(80, 22));
        _removeSwatch.addActionListener(_swatchListener);

        _colorSettings.add(_colorDisplay);
        _colorSettings.add(_addSwatch);
        _colorSettings.add(_removeSwatch);

    }

    public void generateSwatch()
    {
        for(int h = 0;h < 255;h += 25)
        {
            addSwatch(Color.getHSBColor(h / 255f, 1f, 1f));
        }
    }

    public void addSwatch(Color c)
    {
        JButton tempB = new JButton();
        tempB.setPreferredSize(new Dimension(18, 18));
        tempB.setBackground(c);
        tempB.addActionListener(_swatchListener);
        tempB.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(tempB);

        revalidate();
    }

    public void setColor(Color c)
    {
        System.out.println(_colorDisplay.getWidth() + " - " + _colorDisplay.getHeight());
        BufferedImage img = new BufferedImage(_colorDisplay.getWidth(), _colorDisplay.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = img.getGraphics();
        g.setColor(c);
        g.fillRect(0, 0, _colorDisplay.getWidth(), _colorDisplay.getHeight());

        float hsb[] = new float[3];
        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsb);

        g.setColor(hsb[2] > 0.5f ? Color.BLACK : Color.WHITE);
        g.drawString("#" + Integer.toHexString(c.getRGB()).substring(2, 8).toUpperCase(), 5, 15);

        _colorDisplay.setIcon(new ImageIcon(img));
    }

    private class SwatchButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource().equals(_colorDisplay))
            {
                RainbowPicker picker = new RainbowPicker(_zs);
                _zs.setMainFocus(picker.getWindow());
            }
            else if(e.getSource().equals(_addSwatch))
            {
                addSwatch(_zs.getCurrentColor());
            }
            else if(e.getSource().equals(_removeSwatch))
            {
                if(_removing)
                {
                    _removing = false;
                    _removeSwatch.setText("Remove");
                }

                else
                {
                    _removing = true;
                    _removeSwatch.setText("REMOVING!");
                }
            }
            else
            {
                if(_removing)
                {
                    remove((JButton)e.getSource());
                    _removeSwatch.doClick();
                }
                else
                    _zs.setCurrentColor(((JButton)e.getSource()).getBackground());
            }

            revalidate();
        }
    }
}
