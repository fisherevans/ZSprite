package com.fisherevans.zSprite;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/22/13
 * Time: 11:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditorSettings extends JPanel
{
    private zSprite _zs;

    public static final int TILE_A_MIN = 0;
    public static final int TILE_A_MAX = 100;
    public static final int TILE_A_INIT = 50;
    public static final String TILE_A_TEXT_FORMAT = "   BG Alpha: %d%%";

    public static final int ZOOM_MAX = 30;
    public static final int ZOOM_MIN = 1;
    public static final int ZOOM_INIT = 8;
    public static final String ZOOM_TEXT_FORMAT = "Zoom: x%d   ";

    private EditorSettingsListener _listener;

    private JSlider _zoomSlider, _tileASlider;
    private JTextField _zoomText, _tileAText;

    public EditorSettings(zSprite zs)
    {
        super(new GridLayout(0,4));
        _zs = zs;
        _listener = new EditorSettingsListener();

        _tileASlider = new JSlider(JSlider.HORIZONTAL, TILE_A_MIN, TILE_A_MAX, TILE_A_INIT);
        _tileASlider.addChangeListener(_listener);
        _tileAText = new JTextField(String.format(TILE_A_TEXT_FORMAT, TILE_A_INIT));
        _tileAText.setHorizontalAlignment(JTextField.LEFT);
        _tileAText.setEditable(false);

        _zoomSlider = new JSlider(JSlider.HORIZONTAL, ZOOM_MIN, ZOOM_MAX, ZOOM_INIT);
        _zoomSlider.addChangeListener(_listener);
        _zoomText = new JTextField(String.format(ZOOM_TEXT_FORMAT, ZOOM_INIT));
        _zoomText.setHorizontalAlignment(JTextField.RIGHT);
        _zoomText.setEditable(false);

        add(_tileASlider);
        add(_tileAText);
        add(_zoomText);
        add(_zoomSlider);

        setPreferredSize(new Dimension(1, 30));
        setVisible(true);
    }

    private class EditorSettingsListener implements ChangeListener
    {

        @Override
        public void stateChanged(ChangeEvent e)
        {
            if(e.getSource() == _zoomSlider)
            {
                _zs.getEditor().setZoom(_zoomSlider.getValue());
                _zoomText.setText(String.format(ZOOM_TEXT_FORMAT, _zoomSlider.getValue()));
                _zs.getEditor().repaint();
            }
            else if(e.getSource() == _tileASlider)
            {
                _zs.getEditor().setTileAlpha(_tileASlider.getValue()/100f);
                _tileAText.setText(String.format(TILE_A_TEXT_FORMAT, _tileASlider.getValue()));
                _zs.getEditor().repaint();
            }
        }
    }
}
