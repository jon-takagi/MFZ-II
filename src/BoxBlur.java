/**
 * Created by 40095 on 5/3/16.
 */
public class BoxBlur extends Blur {

    Cell[][] blur(Cell[][] c) {
        Cell[][] cells = c;
        int cellAvg = 0;
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                for (Cell blah : c[i][j].getNeighbors()) {
                    cellAvg += blah.getHeight();
                }
                double avg = cellAvg / 8;
                cells[i][j].setHeight(avg);
                cellAvg = 0;
            }
        }
        return cells;
    }

    Cell[][] blur(HeightMap m) {
        return blur(m.getCells());

    }
}
