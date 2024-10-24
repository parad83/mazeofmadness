package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import models.TileBuilder;
import models.TileManager;
import ui.Config;

public class Parser {
    public Parser() {}

    public void writeFile(String fn) {
        File f = new File(fn);
        byte[] data = new byte[Config.MAX_SCREEN_COL*Config.MAX_SCREEN_ROW];

        TileBuilder tm = new TileBuilder();
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
        } catch (IOException e) {}
    }

    public void readFile(String fn, TileManager tm) {
        File f = new File(fn);
        byte[] data = new byte[(int) f.length()];

        if (!f.exists() || f.isDirectory()) {
            System.out.println("File doesnt exist");
            return;
        }

        try {
            FileInputStream fis = new FileInputStream(f);
            fis.read(data, 0, data.length);
            fis.close();
            System.out.println(data.length);
            System.out.println(data[1]);

        } catch (IOException e) {}
    }
}

