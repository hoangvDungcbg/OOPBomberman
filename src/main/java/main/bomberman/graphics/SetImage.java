package main.bomberman.graphics;

import java.io.File;
import java.net.MalformedURLException;

public class SetImage {
    public static String set(String filename){
        try {
            File file = new File(".\\res\\" + filename);
            return file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
