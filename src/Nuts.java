/**
 * Created by 40095 on 5/7/16.
 */
public class Nuts {
    public static void main(String[] args) {
        HillMap h = new HillMap(64, 64);
        Cell[][] cells = h.getCells();
        for (int i = 0; i < h.length; i++) {
            System.out.println((i) + "," + cells[32][i].getHeight());
        }
        new MapToImageWriter(h).go();
    }
}
