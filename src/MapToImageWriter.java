import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/*from www.ja v  a 2s.com*/

public class MapToImageWriter {
    HeightMap t;
    int height;
    int width;

    public static void main(final String[] args) {
        new MapToImageWriter().go();
    }
    MapToImageWriter() {
    }
    
    MapToImageWriter(HeightMap h) {
        t = h;
        height = t.length;
        width = t.width;
    }
    
    void go() {
        height = 65;
        width = 65;
        printMap();
    }

    void printMap() {
        final BufferedImage res = new BufferedImage(width * 8, height * 8, BufferedImage.TYPE_INT_RGB);
//        t = new DiamondSquareMap(height, width);
        t = new HillMap(height, width);
//        t.blur(new BoxBlur());
//        t.blur(new BoxBlur());

        t.mapTerrain();
        for (int x = 0; x < height * 8; x++) {
            for (int y = 0; y < width * 8; y++) {
                res.setRGB(x, y, t.getCells()[x / 8][y / 8].getTerrain().getRgb());
//                res.setRGB(x, y, t.getCells()[x / 8][y / 8].getHeightColor().getRGB());
            }
        }
        try {
            ImageIO.write(res, "bmp", new File("map.bmp"));
            //ImageIO.write(rendImage, "PNG", new File(path));
            //ImageIO.write(rendImage, "jpeg", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("complete");
    }

}