package Maps;

/**
 * Created by 40095 on 5/3/16.
 */
public class RandomNoiseMap extends HeightMap {
    RandomNoiseMap(int length, int width) {
        super(length, width);
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < width; j++) {
                int v = rinr(1, 255);
                getCells()[i][j].setHeight(v);
            }
        }
    }
}
