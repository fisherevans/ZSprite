package com.fisherevans.zSprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/24/13
 * Time: 1:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class RainbowSliderPane extends JPanel implements MouseMotionListener, MouseListener
{
    private RainbowPicker _rp;
    private BufferedImage _scale;
    private int _b;
    private int padding = 15;
    private final int minB = padding, maxB = padding+255;

    public RainbowSliderPane(RainbowPicker rp)
    {
        _rp = rp;
        _b = 127;

        try
        {
            _scale = ImageIO.read(new File("bin/img/slider.png"));
        }
        catch(Exception e)
        {
            System.out.println("Couldn't load slider.");
            e.printStackTrace();
            System.exit(1);
        }

        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        try
        {
            g.drawImage(_scale, 0, padding, _scale.getWidth(), _scale.getHeight(), null);

            int drawY = maxB - _b - 7;
            g.setColor(_rp.getNewColor());
            g.fillRect(0,drawY, 25, padding);
            g.setColor(new Color(255- _b,  255- _b,  255- _b));
            g.drawLine(0, drawY-1, 25, drawY-1);
            g.drawLine(0, drawY-2, 25, drawY-2);
            g.drawLine(0, drawY+padding, 25, drawY+padding);
            g.drawLine(0, drawY+1+padding, 25, drawY+1+padding);
        }
        catch(Exception e)
        {

        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        _b = maxB - e.getY();

        _b = _b < 0 ? 0 : _b;
        _b = _b > 255 ? 255 : _b;

        _rp.setNewColor();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        _b = maxB - e.getY();

        _b = _b < 0 ? 0 : _b;
        _b = _b > 255 ? 255 : _b;

        _rp.setNewColor();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setB(int b)
    {
        _b = b;
        repaint();
    }

    public int getB()
    {
        return _b;
    }
}
