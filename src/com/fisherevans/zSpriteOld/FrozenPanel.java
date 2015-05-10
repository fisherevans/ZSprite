package com.fisherevans.zSpriteOld;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/24/13
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class FrozenPanel extends JPanel
{
    private BufferedImage _img;

    public FrozenPanel(BufferedImage img)
    {
        super();
        _img = img;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(_img, 0, 0, _img.getWidth(), _img.getHeight(), null);
    }
}
