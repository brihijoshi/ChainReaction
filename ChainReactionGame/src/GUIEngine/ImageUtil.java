package GUIEngine;

import javafx.scene.image.Image;

import java.io.File;

public class ImageUtil {


    public Image getImage(String file_path) {
        return new Image(new File(file_path).toURI().toString());
    }


}
