package Maps;

/**
 * Created by 40095 on 5/4/16.
 */
public class DiamondSquareMap extends HeightMap {
    int roughness = 1;
    private Cell[][] cells = getCells();

    public DiamondSquareMap(int l, int w) {
        super(l, w);
        cells = getCells();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
//                cells[i][j].setHeight(rinr(1, 255));
            }
        }
        divide(l);
    }

    void divide(int size) {
        int x, y;
        int half = size / 2;
        int scale = roughness * size;
        if (half < 1)
            return;
        for (y = half; y < length; y += size) {
            for (x = half; x < width; x += size) {
//                square(x, y, half, rinr(1, 255));
                square(x, y, half, rinr(1,255) * scale * 2 - scale);
            }
        }
        for (y = half; y < length; y += size) {
            for (x = half; x < width; x += size) {
//                diamond(x, y, half, 0);
                diamond(x, y, half, rinr(1,255) * scale * 2 - scale);
            }
        }
        divide(size / 2);
    }

    private void square(int x, int y, int size, double offset) {
        double ave = average(new double[]{
                cells[x - size][y - size].getHeight(),    // upper left
                cells[x + size][y - size].getHeight(),   // upper right
                cells[x + size][y + size].getHeight(),   // lower right
                cells[x - size][y + size].getHeight()    // lower left
        });
//        cells[x][y].setHeight(ave + offset);
        cells[x][y].setHeight((10 + size) * 8);
    }

    private void diamond(int x, int y, int size, double offset) {
        double ave = average(new double[]{
                cells[x][y - size].getHeight(),    // upper left
                cells[x + size][y].getHeight(),   // upper right
                cells[x][y + size].getHeight(),   // lower right
                cells[x - size][y].getHeight()    // lower left
        });
//        cells[x][y].setHeight(ave + offset);
        cells[x][y].setHeight((size + 10) * 8);

    }

    private double average(double[] x) {
        double sum = 0;
        for (double d : x) {
            sum += d;
        }
        return sum / x.length;
    }
}
