package com.fisherevans.zSprite.swing.windows;

import com.fisherevans.zSprite.swing.Window;
import com.fisherevans.zSprite.swing.views.ColorSlider;

/**
 * Author: Fisher Evans
 * Date: 3/20/14
 */
public class ColorPicker extends Window {
    public ColorPicker() {
        super("Color Picker", "fill");
        getRootPanel().add(new ColorSlider(""));
        displayFrame();
    }
}
