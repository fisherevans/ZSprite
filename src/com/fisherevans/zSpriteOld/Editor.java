package com.fisherevans.zSpriteOld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;
import java.io.File;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/22/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Editor extends JPanel
{
    private zSprite _zs;

    private BufferedImage _image;
    private int _zoom = EditorSettings.ZOOM_INIT;
    private float _tileAlpha = EditorSettings.TILE_A_INIT/100f;
    private Color _bgColor;

    private int _width, _height, _zoomWidth, _zoomHeight, _tileStartX, _tileStartY;
    private int _mx = -100, _my = -100;
    private int _imgX = 0, _imgY = 0;

    private boolean _paintInMain = false;
    private boolean _showMouse = true;

    private BufferedImageOp _tileOp;

    private EditorListener _listener;

    private BufferedImage _crosshair;

    private boolean keyAlt = true, keyShift = false;

    public Editor(zSprite zs)
    {
        super();
        _zs = zs;
        _bgColor = Color.WHITE;

        setTempImage();
        setTileAlpha(_tileAlpha);

        setBackground(_bgColor);
        setVisible(true);

        _listener = new EditorListener();

        setFocusable(true);
        addMouseMotionListener(_listener);
        addMouseListener(_listener);
        addKeyListener(_listener);

        try
        {
            _crosshair = ImageIO.read(new File("bin/img/cross.png"));
            Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(_crosshair, new Point(15, 15), "Crosshair");
            this.setCursor(cursor);
        }
        catch(Exception e)
        {
            System.out.println("Failed to load crosshair");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void setTempImage()
    {
        try
        {
            //_image = ImageIO.read(new File("D:\\test.png"));
            _image = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
            for(int x = 0;x < 16;x++)
            {
                for(int y = 0;y < 16;y++)
                {
                    _image.setRGB(x, y, Color.RED.getRGB());
                }
            }
        } catch(Exception e)
        {
            System.out.println("Loading the temp image failed.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        _width = this.getWidth();
        _height = this.getHeight();
        _zoomWidth = _image.getWidth()*_zoom;
        _zoomHeight = _image.getHeight()*_zoom;

        int mainX = _width/2 - _zoomWidth/2;
        int mainY = _height/2 - _zoomHeight/2;

        int tileX = 2 + _width/_zoomWidth;
        int tileY = 2 + _height/_zoomHeight;

        _tileStartX = mainX - tileX/2*_zoomWidth;
        _tileStartY = mainY - tileY/2*_zoomHeight;

        BufferedImage tileImage = _tileOp.filter(_image, null);

        g.setColor(Color.BLACK);
        g.drawRect(mainX-1, mainY-1, _zoomWidth+1, _zoomHeight+1);

        for(int x = 0;x < tileX;x++)
        {
            for(int y = 0;y < tileY;y++)
            {
                if(!(x == tileX/2 && y == tileY/2))
                {
                    g.drawImage(tileImage, x*_zoomWidth + _tileStartX, y*_zoomHeight + _tileStartY, _zoomWidth, _zoomHeight, null);
                }
            }
        }

        g.drawImage(_image, mainX, mainY, _zoomWidth, _zoomHeight,  null);

        if(_showMouse)
        {
            getImageXY();

            int paintX, paintY;
            if(_paintInMain)
            {
                paintX = _imgX*_zoom + mainX;
                paintY = _imgY*_zoom + mainY;
            } else {
                paintX = _mx - ((_mx - _tileStartX%_zoom) % _zoom);
                paintY = _my - ((_my - _tileStartY%_zoom) % _zoom);
            }

            g.setColor(Color.BLACK);
            g.drawRect(paintX, paintY, _zoom-1, _zoom-1);
            g.setColor(_zs.getCurrentColor());
            for(int s = 1;s < _zoom/5.0;s++)
                g.drawRect(paintX+s, paintY+s, _zoom-1-(s*2), _zoom-1-(s*2));
        }


    }

    private void getImageXY()
    {
        _imgX = ((_mx - _tileStartX) % _zoomWidth)/_zoom;
        _imgY = ((_my - _tileStartY) % _zoomHeight)/_zoom;
        //System.out.println(imgX + ", " + imgY);
    }

    private void clickPencil()
    {
        try
        {
            _image.setRGB(_imgX, _imgY, _zs.getCurrentColor().getRGB());
            repaint();
        }
        catch(Exception e)
        {}
    }

    private void dragPencil(MouseEvent e)
    {
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
        repaint();
    }

    private void clickBucket()
    {
        int[] start = { _imgX, _imgY };
        int startRPG = _image.getRGB(start[0], start[1]);
        ArrayList<int[]> neighbors = new ArrayList<>();
        ArrayList<int[]> doPaint = new ArrayList<>();

        do
        {
            int[] p = neighbors.remove(0);
            doPaint.add(p);

            int[] north = { p[0], p[1]+1 };
            int[] south = { p[0], p[1]-1 };
            int[] east = { p[0]+1, p[1] };
            int[] west = { p[0]-1, p[1] };
        }
        while (!neighbors.isEmpty());

    }

    private void drawLineTo(int x, int y)
    {
        Graphics g = _image.getGraphics();
        g.drawLine(_mx, _my, x, y);

        repaint();
    }

    public void setZoom(int zoom)
    {
        _zoom = zoom;
    }

    public void setTileAlpha(float tileAlpha)
    {
        _tileAlpha = tileAlpha;
        _tileOp = new RescaleOp( new float[] {1.0f, 1.0f, 1.0f, _tileAlpha }, new float[] {0f, 0f, 0f, 0f }, null);
    }

    public void setBgColor(Color bgColor)
    {
        _bgColor = bgColor;
        setBackground(_bgColor);
        repaint();
    }

    private class EditorListener implements MouseMotionListener, MouseListener, KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_ALT)
                keyAlt = true;
            else if(e.getKeyCode() == KeyEvent.VK_SHIFT)
                keyShift = true;

            System.out.println("Key");
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_ALT)
                keyAlt = false;
            else if(e.getKeyCode() == KeyEvent.VK_SHIFT)
                keyShift = false;

            System.out.println("Key");
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            switch(_zs.getCurrentTool())
            {
                case Pencil:
                    dragPencil(e);
                    break;
                case Picker:
                    _mx = keyShift ? _mx : e.getX();
                    _my = keyAlt ? _my : e.getY();
                    getImageXY();
                    _zs.setCurrentColor(new Color(_image.getRGB(_imgX, _imgY)));
                    break;
                default:
                    break;
            }

            _mx = keyShift ? _mx : e.getX();
            _my = keyAlt ? _my : e.getY();
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            _mx = e.getX();
            _my = e.getY();
            getImageXY();

            switch(_zs.getCurrentTool())
            {
                case Pencil:
                    clickPencil();
                    break;
                case Picker:
                    _zs.setCurrentColor(new Color(_image.getRGB(_imgX, _imgY)));
                    break;
                default: break;
            }
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            _mx = e.getX();
            _my = e.getY();
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
        }

        @Override
        public void keyTyped(KeyEvent e)
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
            _showMouse = true;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            _showMouse = false;
            repaint();
        }
    }
}
