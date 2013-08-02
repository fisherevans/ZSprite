package com.fisherevans.zSprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/24/13
 * Time: 1:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class RainbowPickerPane extends JPanel implements MouseMotionListener, MouseListener
{
    private RainbowPicker _rp;
    private BufferedImage _rb;
    private BufferedImage _circle;

    private final int padding = 15;
    private final int minX = padding, maxX = 255+padding-1;
    private final int minY = padding, maxY = 255+padding-1;

    private int _mx = minX, _my = minY;

    private int _h, _s;

    public RainbowPickerPane(RainbowPicker rp)
    {
        _rp = rp;

        try
        {
            _rb = ImageIO.read(new File("bin/img/rainbow.png"));
            _circle = ImageIO.read(new File("bin/img/rainbow_picker.png"));
        }
        catch(Exception e)
        {
            System.out.println("Couldn't read rainbow files");
            e.printStackTrace();
            System.exit(1);
        }

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(_rb, padding, padding, _rb.getWidth(), _rb.getHeight(), null);

        g.drawImage(_circle, maxX-_h-2, maxY-_s-2, 7, 7, null);
    }

    private int clamp(int n, int min, int max)
    {
        if(n < min) return min;
        if(n > max) return max;
        return n;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        x = clamp(x, minX, maxX);
        y = clamp(y, minY, maxY);

        _h = maxX-x+1;
        _s = maxY-y+1;

        _rp.setNewColor();

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        x = clamp(x, minX, maxX);
        y = clamp(y, minY, maxY);

        _h = maxX-x+1;
        _s = maxY-y+1;

        _rp.setNewColor();

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
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

    public int getH()
    {
        return _h;
    }

    public int getS()
    {
        return _s;
    }

    public void setSH(int s, int h)
    {
        _s = s;
        _h = h;
        repaint();
    }
}
