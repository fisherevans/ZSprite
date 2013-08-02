package com.fisherevans.zSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/24/13
 * Time: 12:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class RainbowPicker extends WindowAdapter
{
    private zSprite _zs;
    private JFrame _window;
    private final String TITLE = "zSprite - Color Picker";
    private JPanel _content, _confirmPane, _colorPane, _sliderPane, _definePane, _pickPane;
    private RainbowPickerPane _rainbowPane;
    private RainbowSliderPane _brightness;
    private JTextField _hex, _hexEnter, _r, _rEnter, _g, _gEnter, _b, _bEnter, _new, _current, _newDis, _currentDis;
    private JButton _ok, _cancel, _brightnessB;
    private BufferedImage _slider;

    private RainbowListener _rl;

    public RainbowPicker(zSprite zs)
    {
        _zs = zs;
        _rl = new RainbowListener();
        initWindow();
        _content = new JPanel(new BorderLayout());
        _window.setContentPane(_content);
        _window.addWindowListener(this);

        makeDefinePane();
        _content.add(_definePane, BorderLayout.SOUTH);
        makeConfirmPane();
        _content.add(_confirmPane, BorderLayout.EAST);
        makeColorPane();
        _content.add(_colorPane);

        Color c = _zs.getCurrentColor();
        float hsb[] = new float[3];
        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), hsb);
        _currentDis.setBackground(c);
        _rainbowPane.setSH((int)(hsb[1]*255f), (int)(hsb[0]*255f));
        _brightness.setB((int)(hsb[2]*255f));
        setNewColor();

        _window.revalidate();
    }

    public void setNewColor()
    {
        Color c = Color.getHSBColor(_rainbowPane.getH()/255f, _rainbowPane.getS()/255f, _brightness.getB()/255f);
        System.out.println(c.getRGB() + "");
        System.out.println(_currentDis.getBackground().getRGB() + " - " + c.getRGB() + "");
        _newDis.setBackground(c);
        int r, b, g, rgb;
        rgb = c.getRGB();

        _hexEnter.setText("#" + Integer.toHexString(rgb).substring(2, 8).toUpperCase());

        r = (rgb & 0x00FF0000) >> 16;
        g = (rgb & 0x0000FF00) >> 8;
        b = (rgb & 0x000000FF) >> 0;

        _rEnter.setText(r+"");
        _gEnter.setText(g+"");
        _bEnter.setText(b+"");

        _confirmPane.repaint();
        _colorPane.repaint();

        //System.out.println("H:" + _rainbowPane.getH() + " S:" + _rainbowPane.getB() + " B:" + _brightness.getB() + " R:" + r + " G:" + g + " B:" + b);
    }

    private void initWindow()
    {
        _window = new JFrame(TITLE);
        _window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _window.setSize(410, 380);
        _window.setResizable(false);
        _window.setVisible(true);
        _zs.centerFrame(_window, 0, 0);
    }

    private void makeConfirmPane()
    {
        _confirmPane = new JPanel();
        _confirmPane.setPreferredSize(new Dimension(100, 1));

        Dimension bdim = new Dimension(80, 30);
        Dimension tdim = new Dimension(80, 20);
        Dimension cdim = new Dimension(80, 60);

        // #########################

        _ok = new JButton("Okay");
        _ok.setPreferredSize(bdim);
        _ok.addActionListener(_rl);
        _ok.setCursor(new Cursor(Cursor.HAND_CURSOR));

        _cancel = new JButton("Cancel");
        _cancel.setPreferredSize(bdim);
        _cancel.addActionListener(_rl);
        _cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        _new = new JTextField("New:");
        _new.setPreferredSize(tdim);
        _new.setEditable(false);
        _new.setBorder(BorderFactory.createEmptyBorder());
        _new.setHorizontalAlignment(JTextField.CENTER);

        _newDis = new JTextField("");
        _newDis.setPreferredSize(cdim);
        _newDis.setEditable(false);
        _newDis.setHorizontalAlignment(JTextField.CENTER);

        _current = new JTextField("Current:");
        _current.setPreferredSize(tdim);
        _current.setEditable(false);
        _current.setBorder(BorderFactory.createEmptyBorder());
        _current.setHorizontalAlignment(JTextField.CENTER);

        _currentDis = new JTextField("");
        _currentDis.setPreferredSize(cdim);
        _currentDis.setEditable(false);
        _currentDis.setHorizontalAlignment(JTextField.CENTER);

        // #########################

        _confirmPane.add(_ok);
        _confirmPane.add(_cancel);
        _confirmPane.add(_new);
        _confirmPane.add(_newDis);
        _confirmPane.add(_current);
        _confirmPane.add(_currentDis);

        _confirmPane.setVisible(true);
    }

    private void makeColorPane()
    {
        _colorPane = new JPanel(new BorderLayout());

        _brightness = new RainbowSliderPane(this);
        _brightness.setPreferredSize(new Dimension(25, 255));
        _colorPane.add(_brightness, BorderLayout.EAST);

        _rainbowPane = new RainbowPickerPane(this);
        _rainbowPane.setPreferredSize(new Dimension(350+15, 255));

        _colorPane.add(_rainbowPane, BorderLayout.WEST);
    }

    private void makeDefinePane()
    {
        _definePane = new JPanel();
        _definePane.setPreferredSize(new Dimension(1, 70));
        _definePane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        _hex = new JTextField("Hex Code:");
        _hex.setEditable(false);
        _hex.setBorder(BorderFactory.createEmptyBorder());

        _hexEnter = new JTextField("#FFFFFF");
        _hexEnter.setPreferredSize(new Dimension(62, 20));

        _r = new JTextField("   R:");
        _r.setEditable(false);
        _r.setBorder(BorderFactory.createEmptyBorder());

        _rEnter = new JTextField("000");
        _rEnter.setPreferredSize(new Dimension(30, 20));

        _g = new JTextField(" G:");
        _g.setEditable(false);
        _g.setBorder(BorderFactory.createEmptyBorder());

        _gEnter = new JTextField("000");
        _gEnter.setPreferredSize(new Dimension(30, 20));

        _b = new JTextField(" B:");
        _b.setEditable(false);
        _b.setBorder(BorderFactory.createEmptyBorder());

        _bEnter = new JTextField("000");
        _bEnter.setPreferredSize(new Dimension(30, 20));

        _definePane.add(_hex);
        _definePane.add(_hexEnter);
        _definePane.add(_r);
        _definePane.add(_rEnter);
        _definePane.add(_g);
        _definePane.add(_gEnter);
        _definePane.add(_b);
        _definePane.add(_bEnter);
    }

    private void makePickPane()
    {
        _pickPane = new JPanel(new BorderLayout());

        _brightness = new RainbowSliderPane(this);
        _brightness.setPreferredSize(new Dimension(25, 255));
        _brightness.setBackground(Color.BLACK);
        _pickPane.add(_brightness, BorderLayout.EAST);
    }

    private class RainbowListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource().equals(_cancel))
            {
                _zs.revertFocus();
                _window.dispose();
            }
            else if(e.getSource().equals(_ok))
            {
                _zs.setCurrentColor(_newDis.getBackground());
                _zs.revertFocus();
                _window.dispose();
            }
        }
    }

    public void windowClosing(WindowEvent e)
    {
        _zs.revertFocus();
    }

    public JFrame getWindow()
    {
        return _window;
    }

    public Color getNewColor()
    {
        return _newDis.getBackground();
    }
}
