package com.fisherevans.zSpriteOld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/22/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tools extends JPanel
{
    private zSprite _zs;
    private ToolButtonListener _toolButtonListener;
    private JButton _activeButton;

    public static final String
            BUCKET_IMG = "bin/img/tool_bucket.png", BUCKET_TEXT = "Paint Bucket",
            PENCIL_IMG = "bin/img/tool_pencil.png", PENCIL_TEXT = "Pencil",
            BRUSH_IMG = "bin/img/tool_brush.png", BRUSH_TEXT = "Paint Brush",
            ERASER_IMG = "bin/img/tool_eraser.png", ERASER_TEXT = "Eraser",
            PICKER_IMG = "bin/img/tool_picker.png", PICKER_TEXT = "Color Picker",
            SELECT_IMG = "bin/img/tool_select.png", SELECT_TEXT = "Selection Tool",
            MOVE_IMG = "bin/img/tool_move.png", MOVE_TEXT = "Move",
            BACK_IMG = "bin/img/tool_back.png", BACK_TEXT = "Undo",
            FORWARD_IMG = "bin/img/tool_forward.png", FORWARD_TEXT = "Redo";

    public Tools(zSprite zs)
    {
        _zs = zs;
        _toolButtonListener = new ToolButtonListener();

        createButtons();

        setPreferredSize(new Dimension(75, 1));
        setVisible(true);
    }

    private void createButtons()
    {
        add(makeButton(BUCKET_IMG, BUCKET_TEXT));
        add(makeButton(PENCIL_IMG, PENCIL_TEXT));
        add(makeButton(BRUSH_IMG, BRUSH_TEXT));
        add(makeButton(ERASER_IMG, ERASER_TEXT));
        add(makeButton(PICKER_IMG, PICKER_TEXT));
        add(makeButton(SELECT_IMG, SELECT_TEXT));
        add(makeButton(MOVE_IMG, MOVE_TEXT));
        add(makeButton(BACK_IMG, BACK_TEXT));
        add(makeButton(FORWARD_IMG, FORWARD_TEXT));
    }

    private JButton makeButton(String imgL, String tip)
    {
        try
        {
            BufferedImage img = ImageIO.read(new File(imgL));
            JButton b = new JButton(new ImageIcon(img));
            b.setToolTipText(tip);
            b.setBorder(BorderFactory.createEmptyBorder());
            b.setContentAreaFilled(false);
            b.setPreferredSize(new Dimension(25, 25));
            b.addActionListener(_toolButtonListener);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            return b;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    private void setActive(JButton b)
    {
        if(_activeButton != null)
            _activeButton.setBorder(BorderFactory.createEmptyBorder());
        _activeButton = b;
        _activeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    private class ToolButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JButton b = (JButton)e.getSource();

            switch(b.getToolTipText())
            {
                case BUCKET_TEXT:
                    _zs.setCurrentTool(zSprite.Tool.Bucket);
                    setActive(b);
                    break;
                case PENCIL_TEXT:
                    _zs.setCurrentTool(zSprite.Tool.Pencil);
                    setActive(b);
                    break;
                case BRUSH_TEXT:
                    _zs.setCurrentTool(zSprite.Tool.Brush);
                    setActive(b);
                    break;
                case ERASER_TEXT:
                    _zs.setCurrentTool(zSprite.Tool.Eraser);
                    setActive(b);
                    break;
                case PICKER_TEXT:
                    _zs.setCurrentTool(zSprite.Tool.Picker);
                    setActive(b);
                    break;
                case SELECT_TEXT:
                    _zs.setCurrentTool(zSprite.Tool.Select);
                    setActive(b);
                    break;
                case MOVE_TEXT:
                    _zs.setCurrentTool(zSprite.Tool.Move);
                    setActive(b);
                    break;
                case BACK_TEXT:

                    break;
                case FORWARD_TEXT:

                    break;
                default: break;
            }
        }
    }
}
