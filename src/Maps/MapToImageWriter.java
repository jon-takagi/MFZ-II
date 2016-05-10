package Maps;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/*from www.ja v  a 2s.com*/

public class MapToImageWriter {
    HeightMap t;
    int height;
    int width;
    int scaleFactor;

    public static void main(final String[] args) {
        new MapToImageWriter(8).go();
    }
    MapToImageWriter(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
    
    MapToImageWriter(HeightMap h) {
        t = h;
        height = t.length;
        width = t.width;
    }
    
    void go() {
        height = 64;
        width = 64;
        printMap();
    }

    void printMap() {
        final BufferedImage res = new BufferedImage(width * scaleFactor, height * scaleFactor, BufferedImage.TYPE_INT_RGB);
//        t = new DiamondSquareMap(height, width);
        t = new HillMap(height, width);
//        t.blur(new BoxBlur());
//        t.blur(new BoxBlur());

        t.mapTerrain();
        for (int x = 0; x < height * scaleFactor; x++) {
            for (int y = 0; y < width * scaleFactor; y++) {
                res.setRGB(x, y, t.getCells()[x / scaleFactor][y / scaleFactor].getTerrain().getRgb());
//                res.setRGB(x, y, t.getCells()[x / scaleFactor][y / scaleFactor].getHeightColor().getRGB());
            }
        }
        try {
            ImageIO.write(res, "png", new File("map.png"));
            //ImageIO.write(rendImage, "PNG", new File(path));
            //ImageIO.write(rendImage, "jpeg", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("complete");
    }

}