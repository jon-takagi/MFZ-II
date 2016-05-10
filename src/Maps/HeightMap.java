package Maps;

/**
 * Created by 40095 on 5/3/16.
 */
public abstract class HeightMap {
    int length;
    int width;
    private Cell[][] cells;

    final int Min = 1;
    final int Max = 255;
    EmptyCell e = new EmptyCell();

    HeightMap(int l, int w) {
        length = l;
        width = w;
        cells = new Cell[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell();
            }
        }
        cells[0][0].setNeighbors(cells[1][0], e, e, e, e, e, cells[0][1], cells[1][1]); //top left corner
        cells[0][width - 1].setNeighbors(e, e, e, e, cells[0][width - 2], cells[1][width - 2], cells[1][width - 1], e);
        // top right corner
        cells[length - 1][width - 1].setNeighbors(e, e, cells[length - 2][width - 1], cells[length - 2][width - 2],
                cells[length - 1][width - 2], e, e, e);                                //bottom right corner
        cells[length - 1][0].setNeighbors(cells[length - 1][1], cells[length - 2][1], cells[length - 2][0], e, e, e, e,
                e);  //bottom left

        for (int i = 1; i < width - 1; i++) {
            cells[0][i].setNeighbors(cells[0][i + 1], e, e, e, cells[0][i - 1], cells[1][i - 1], cells[1][i],
                    cells[1][i + 1]); //top row
            cells[length - 1][i].setNeighbors(cells[length - 1][i + 1], cells[length - 2][i + 1], cells[length -
                    2][i], cells[length - 2][i - 1], cells[length - 1][i - 1], e, e, e); //bottom row
        }
        for (int i = 1; i < length - 1; i++) {
            cells[i][0].setNeighbors(cells[i][1], cells[i - 1][1], cells[i - 1][0], e, e, e, cells[i + 1][0], cells[i
                    + 1][1]); //left
            cells[i][width - 1].setNeighbors(e, e, cells[i - 1][width - 1], cells[i - 1][width - 2], cells[i][width - 2],
                    cells[i + 1][width - 2], cells[i + 1][width - 1], e);     //right
        }
        for (int i = 1; i < length - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                cells[i][j].setNeighbors(cells[i][j + 1], cells[i - 1][j + 1], cells[i - 1][j],
                        cells[i - 1][j - 1], cells[i][j - 1], cells[i + 1][j - 1], cells[i + 1][j], cells[i + 1][j + 1]);

            }
        }

    }

    public Cell[][] getCells() {
        return cells;
    }

    public void mapTerrain() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j].calculateTerrain();
            }
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j].checkIfBeach();
            }
        }

    }

    void setCells(Cell[][] c) {
        cells = c;
    }
    
    
    static int rinr(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
    void blur(Blur b) {
        setCells(b.blur(getCells()));
        
    }
}
