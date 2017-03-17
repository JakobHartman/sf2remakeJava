package util;

import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class FontUtil {
    private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private static ArrayList<String> fontsAdded = new ArrayList<>();

    private static void loadFont(String path) {
        try {
            File fontFile = new File(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            ge.registerFont(font);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static TrueTypeFont getFont(String path, String name, int size) {
        if (!fontsAdded.contains(name)) {
            loadFont(path);
            fontsAdded.add(name);
        }
        return new TrueTypeFont(new Font(name, Font.BOLD, size), true);
    }
}
