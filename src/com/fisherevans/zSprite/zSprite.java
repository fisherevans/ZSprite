package com.fisherevans.zSprite;

import javax.swing.*;
import javax.swing.JMenuBar;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: immortal
 * Date: 2/22/13
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class zSprite implements WindowFocusListener
{
    private final String TITLE = "zSprite (0.1b) - A 2D sprite editor by Fisher Evans";

    private int _sW, _sH;

    private JFrame _window;
    private JPanel _content, _editorContent;
    private Tools _tools;
    private Editor _editor;
    private EditorSettings _editorSettings;
    private LiveSettings _liveSettings;
    private Swatch _swatch;
    private Menu _menu;

    private Tool _currentTool = Tool.NOTHING;
    private Color _currentColor = Color.WHITE;

    private JFrame _mainFocus = null;

    public enum Tool { NOTHING, Bucket, Pencil, Brush, Eraser, Picker, Select, Move }

    public zSprite()
    {
        initGlobalValues();

        initWindow();

        _menu = new Menu(this);
        _window.setJMenuBar(_menu);
        _window.addWindowFocusListener(this);

        _content = new JPanel(new BorderLayout());
        _editorContent = new JPanel(new BorderLayout());
        _window.setContentPane(_content);

        _editor = new Editor(this);
        _editorSettings = new EditorSettings(this);
        _tools = new Tools(this);
        _swatch = new Swatch(this);
        _liveSettings = new LiveSettings(this);

        _editorContent.add(_editor, BorderLayout.CENTER);
        //_editorContent.add(_liveSettings, BorderLayout.NORTH);
        _editorContent.add(_editorSettings, BorderLayout.SOUTH);

        _content.add(_editorContent, BorderLayout.CENTER);
        _content.add(_tools, BorderLayout.WEST);
        _content.add(_swatch, BorderLayout.EAST);

        _window.revalidate();
    }

    private void initGlobalValues()
    {
        Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        _sW = dim.width;
        _sH = dim.height;
    }

    private void initWindow()
    {
        _window = new JFrame(TITLE);
        _window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _window.setSize(750, 600);
        _window.setVisible(true);
        centerFrame(_window, 0, 0);
    }

    public void centerFrame(JFrame f, int x, int y)
    {
        f.setLocation((int) (_sW / 2f - f.getWidth() / 2f + x),
                (int) (_sH / 2f - f.getHeight() / 2f + y));
    }

    public void setMainFocus(JFrame w)
    {
        _mainFocus = w;
        w.toFront();
        w.revalidate();


        BufferedImage img = new BufferedImage(_content.getWidth(), _content.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        _content.paint(img.getGraphics());
        FrozenPanel dummy = new FrozenPanel(img);

        _window.setContentPane(dummy);
        _window.revalidate();
    }

    public void revertFocus()
    {
        _mainFocus = null;
        _window.toFront();
        _window.revalidate();
        _window.setContentPane(_content);
        _window.revalidate();
    }

    @Override
    public void windowGainedFocus(WindowEvent e)
    {
        if(_mainFocus != null)
        {
            Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
            if (runnable != null)
                runnable.run();
            _mainFocus.toFront();
        }
    }

    @Override
    public void windowLostFocus(WindowEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public JFrame getWindow()
    {
        return _window;
    }

    public Editor getEditor()
    {
        return _editor;
    }

    public Tools getTools()
    {
        return _tools;
    }

    public Swatch getSwatch()
    {
        return _swatch;
    }

    public EditorSettings getEditorSettings()
    {
        return _editorSettings;
    }

    public Menu getMenu()
    {
        return _menu;
    }

    public Color getCurrentColor()
    {
        return _currentColor;
    }

    public void setCurrentColor(Color currentColor)
    {
        _currentColor = currentColor;
        _swatch.setColor(_currentColor);
    }

    public Tool getCurrentTool()
    {
        return _currentTool;
    }

    public void setCurrentTool(Tool currentTool)
    {
        _currentTool = currentTool;
    }
}
