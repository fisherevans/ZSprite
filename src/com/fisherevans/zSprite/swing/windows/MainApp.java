package com.fisherevans.zSprite.swing.windows;

import com.fisherevans.zSprite.swing.views.DummyView;
import com.fisherevans.zSprite.swing.views.Editor;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Fisher Evans
 * Date: 3/19/14
 */
public class MainApp extends com.fisherevans.zSprite.swing.Window {

    private Editor _editor;
    private DummyView _westBar, _southBar, _eastBar, _tools, _pallet, _navigator;

    public MainApp() {
        super("ZSprite", "fill");
        initViews();
        displayFrame();
    }

    private void initViews() {
        _editor = new Editor();
        _westBar = new DummyView();
        _southBar = new DummyView();
        _eastBar = new DummyView();
        _navigator = new DummyView();
        _pallet = new DummyView();
        _tools = new DummyView();

        _westBar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
        _southBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
        _eastBar.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));

        _westBar.add(_tools, "width 100%, height 100%");

        _eastBar.add(_navigator, "wrap");
        _eastBar.add(_pallet, "");

        getRootPanel().add(_westBar, "dock west,width 80");
        getRootPanel().add(_eastBar, "dock east,width 200");
        getRootPanel().add(_southBar, "dock south,height 150");
        getRootPanel().add(_editor, "grow");

    }
}
