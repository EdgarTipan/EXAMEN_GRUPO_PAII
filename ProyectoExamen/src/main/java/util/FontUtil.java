package util;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FontUtil {

    private static Font basePixelFont;

    static {
        try {
            InputStream is = FontUtil.class.getResourceAsStream("/arcadeclassic.regular.ttf");
            if (is != null) {
                basePixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
                
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(basePixelFont);
            } else {
                throw new IOException("No se pudo encontrar el archivo de fuente.");
            }

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public static Font getPixelFont(float size) {
        if (basePixelFont != null) {
            return basePixelFont.deriveFont(size);
        }
        return null;
    }
}
