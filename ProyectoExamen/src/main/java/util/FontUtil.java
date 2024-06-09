package util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontUtil {

    // Clase utilizada para cargar la fuente pixel que se utilizo

    private static Font basePixelFont;

    // Metodo estatico utilizado para cargar una fuente

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

    // Metodo estatico utilizado para obtener una fuentede un tamaño especificado

    public static Font getPixelFont(float size) {
        if (basePixelFont != null) {
            return basePixelFont.deriveFont(size);
        }
        return null;
    }
}
