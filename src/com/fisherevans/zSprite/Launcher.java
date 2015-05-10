package com.fisherevans.zSprite;

import com.fisherevans.zSprite.swing.windows.ColorPicker;
import com.fisherevans.zSprite.swing.windows.MainApp;

import java.io.File;

/**
 * Author: Fisher Evans
 * Date: 3/19/14
 */
public class Launcher {
    public static void main(String[] args) {
        loadLibraries();
        new MainApp();
        new ColorPicker();
    }

    private static void loadLibraries() {
        System.setProperty("org.lwjgl.librarypath", new File("dll").getAbsolutePath());
    }
}
