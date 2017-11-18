package GUIEngine;

import javafx.scene.image.Image;

import java.io.File;

/**
 *  A utility class to obtain a {@link Image} from its path
 *
 *  @author adsrc
 *  @author brihijoshi
 *  @version 1.0
 */

public class ImageUtil {

    /**
     * A function to obtain the {@link Image} from its path by creating a {@link File} object and
     * converting it to its URI and then to a String
     *
     * @param file_path a String that is the path of the image to be obtained
     * @return {@link Image} that is obtained from it's path
     */


    public Image getImage(String file_path) {
        return new Image(new File(file_path).toURI().toString());
    }


}
