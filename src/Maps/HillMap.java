package Maps;

/**
 * Created by 40095 on 5/4/16.
 */
public class HillMap extends HeightMap {
    int r, centerx, centery;
    int distance;
    double theta;

    public HillMap(int l, int w) {
        super(l, w);
        double landVolume = length * width / 2.4;
        System.out.print("Generating Terrain");
        for (int iterations = 0; iterations < landVolume; iterations++) {
            if (gcd(iterations, (int) (landVolume / 10)) == iterations)
                System.out.print(".");
//            System.out.println(percentDone);
//            if(percentDone % 10 == 0)
//                System.out.print(".");
//            System.out.println((int) ((iterations * 100) / (landVolume)) + "% complete");
//
            theta = Math.random() * 360;
            distance = rinr(0, l / 2);
            centerx = (int) (l / 2 + Math.cos(theta) * distance);
            centery = (int) (l / 2 + Math.sin(theta) * distance);
//            centerx = rinr(0, length);
//            centery = rinr(0, width);
            r = rinr(0, 6);


            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    int h = square(r) - (square(i - centerx) + square(j - centery));
                    if (h >= 0) {
                        if (distance < 7) {
                            getCells()[i][j].setHeight(h * distance / 12 + getCells()[i][j].getHeight());
                        } else {
                            getCells()[i][j].setHeight(h + getCells()[i][j].getHeight());
                        }
                    }
//                    getCells()[i][j].setHeight(h + getCells()[i][j].getHeight());
                }
            }
        }
        System.out.println("done");
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                double h = getCells()[i][j].getHeight();
                if (h > max)
                    max = h;
                if (h < min)
                    min = h;
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                double h = getCells()[i][j].getHeight();
                double z = (h - min) / (max - min);
                getCells()[i][j].setHeight(z * 255);
            }
        }
    }

    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    int square(int x) {
        return x * x;
    }
}
