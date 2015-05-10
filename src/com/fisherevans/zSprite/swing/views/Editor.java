package com.fisherevans.zSprite.swing.views;

import com.fisherevans.zSprite.Keyboard;
import com.fisherevans.zSprite.swing.View;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;

/**
 * Author: Fisher Evans
 * Date: 3/19/14
 */
public class Editor extends View implements MouseMotionListener, MouseListener, MouseWheelListener {
    private int _zoom = 10;
    private int _zoomWidth = 1, _zoomHeight = 1;
    private int _mx = 0, _my = 0;
    private int _imgX = 0, _imgY = 0;
    private int _tilesStartX = 1, _tilesStartY = 1;

    private BufferedImage _image;
    private BufferedImageOp _tileOP;

    public Editor() {
        super("");
        setPreferredSize(new Dimension(768, 512));
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        setCursor(blankCursor);

        _image = new BufferedImage(32, 32, BufferedImage.TYPE_4BYTE_ABGR);
        for(int y = 0;y < _image.getHeight();y++) {
            for(int x = 0;x < _image.getWidth();x++) {
                _image.setRGB(x, y, new Color((float)Math.random()*0.2f, (float)Math.random()*0.8f, (float)Math.random()).getRGB());
            }
        }

        _tileOP = new RescaleOp( new float[] {1.0f, 1.0f, 1.0f, 0.5f }, new float[] {0f, 0f, 0f, 0f }, null);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        int width = getWidth();
        int height = getHeight();

        _zoomWidth = _image.getWidth()*_zoom;
        _zoomHeight = _image.getHeight()*_zoom;

        int mainX = (width-_zoomWidth)/2;
        int mainY = (height-_zoomHeight)/2;

        int tilesWide = width/_zoomWidth + 2;
        int tilesHigh = height/_zoomHeight + 2;

        _tilesStartX = mainX - tilesWide/2*_zoomWidth;
        _tilesStartY = mainY - tilesHigh/2*_zoomHeight;

        g.setColor(Color.BLACK);
        g.drawRect(mainX - 1, mainY - 1, _zoomWidth + 1, _zoomHeight + 1);

        BufferedImage tileImage = _tileOP.filter(_image, null);
        for(int x = 0;x < tilesWide;x++)
            for(int y = 0;y < tilesHigh;y++)
                if(!(x == tilesWide/2 && y == tilesHigh/2))
                    g.drawImage(tileImage, x*_zoomWidth + _tilesStartX, y*_zoomHeight + _tilesStartY, _zoomWidth, _zoomHeight, null);

        g.drawImage(_image, mainX, mainY, _zoomWidth, _zoomHeight, null);

        int paintX = _mx - ((_mx - _tilesStartX%_zoom) % _zoom);
        int paintY = _my - ((_my - _tilesStartY%_zoom) % _zoom);

        g.setColor(Color.BLACK);
        g.drawRect(paintX, paintY, _zoom-1, _zoom-1);
        g.setColor(Color.red);
        for(int s = 1;s < _zoom/5.0;s++)
            g.drawRect(paintX+s, paintY+s, _zoom-1-(s*2), _zoom-1-(s*2));

        g.setColor(Color.black);
        g.drawLine(_mx-_zoom*2, _my,  _mx-_zoom, _my);
        g.drawLine(_mx+_zoom*2, _my,  _mx+_zoom, _my);
        g.drawLine(_mx, _my-_zoom*2,  _mx, _my-_zoom*1);
        g.drawLine(_mx, _my+_zoom*2,  _mx, _my+_zoom*1);

        g.setColor(Color.white);
        g.drawLine(_mx-_zoom*2, _my-1,  _mx-_zoom, _my-1);
        g.drawLine(_mx-_zoom*2, _my+1,  _mx-_zoom, _my+1);

        g.drawLine(_mx+_zoom*2, _my-1,  _mx+_zoom, _my-1);
        g.drawLine(_mx+_zoom*2, _my+1,  _mx+_zoom, _my+1);

        g.drawLine(_mx-1, _my-_zoom*2,  _mx-1, _my-_zoom*1);
        g.drawLine(_mx+1, _my-_zoom*2,  _mx+1, _my-_zoom*1);

        g.drawLine(_mx-1, _my+_zoom*2,  _mx-1, _my+_zoom*1);
        g.drawLine(_mx+1, _my+_zoom*2,  _mx+1, _my+_zoom*1);
    }

    private void getImageXY()
    {
        _imgX =  ((_mx - _tilesStartX) % _zoomWidth)/_zoom;
        _imgY =  ((_my - _tilesStartY) % _zoomHeight)/_zoom;
    }

    private void dragPencil(MouseEvent e) {
        double startX= _mx, startY = _my,
                dx = e.getX() - startX, dy = e.getY() - startY;
        double h = 1.0/Math.sqrt(dx*dx + dy*dy);

        for(double t = 0;t < 1.0;t += h)
        {
            _mx = (int)(startX + dx*t);
            _my = (int)(startY + dy*t);

            getImageXY();
            clickPencil();
        }
    }

    private void clickPencil()
    {
        try {
            _image.setRGB(_imgX, _imgY, Color.red.getRGB());
            repaint();
        }
        catch(Exception e) {}
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragPencil(e);
        _mx = e.getX();
        _my = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        _mx = e.getX();
        _my = e.getY();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickPencil();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(Keyboard.control) {
            _zoom -= e.getWheelRotation();
            if(_zoom < 1) _zoom = 1;
            else if(_zoom > 32) _zoom = 32;
            repaint();
        }
    }
}
