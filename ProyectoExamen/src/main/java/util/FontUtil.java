package util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontUtil {

    private static Font basePixelFont;

    static {
        try {
            // Cargar la fuente desde el archivo
            File fontFile = new File("src/main/resources/arcadeclassic.regular.ttf");
            basePixelFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            // Registrar la fuente en el entorno gráfico
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(basePixelFont);

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
