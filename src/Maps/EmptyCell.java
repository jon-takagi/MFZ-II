package Maps;

/**
 * Created by 40095 on 5/3/16.
 */
public class EmptyCell extends Cell {
    EmptyCell() {
        north = this;
        south = this;
        east = this;
        west = this;
        ne = this;
        nw = this;
        se = this;
        sw = this;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public Terrain getTerrain() {
        return new Terrain(getHeight());
    }
}
