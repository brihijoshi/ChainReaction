package GUIEngine;

import javafx.scene.paint.Color;

public class ColorUtil {

    public static String colorToHex(Color color) {
        return "#" + color.toString().substring(2);
    }

}
