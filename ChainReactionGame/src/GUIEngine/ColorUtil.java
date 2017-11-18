package GUIEngine;

import javafx.scene.paint.Color;

/**
 *  A utility class to obtain a String containing a hex-code
 *  of a given {@link Color} object.
 *
 *  @author adsrc
 *  @version 1.0
 */
public class ColorUtil {

    /**
     * A function to convert the <code>value</code> of a {@link Color}
     * object to a String containing the hex-code
     *
     * @param color The {@link Color} object whose hex-code is to be obtained
     * @return a String containing the hex-code
     */

    public static String colorToHex(Color color) {

        return "#" + color.toString().substring(2);

    }

}
