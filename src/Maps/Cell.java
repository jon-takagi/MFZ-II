package Maps; /**
 * Created by 40095 on 5/3/16.
 */

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import Content.*;

public class Cell {
    Cell north;
    Cell south;
    Cell east;
    Cell west;
    Cell ne;
    Cell nw;
    Cell se;
    Cell sw;
    Cell[] neighbors;

    double height;
    Terrain terrain;

    Content contents = new EmptyContent();


    public Cell() {
    }

    public void calculateTerrain() {
        terrain = new Terrain(height);
        if (terrain.getTerrainType().equals("forest"))
            contents = new Cover(4);
        if (terrain.getTerrainType().equals("mountain"))
            contents = new Cover(6);
    }

    public void checkIfBeach() {
        if (terrain.getTerrainType().equals("grass") || terrain.getTerrainType().equals("forest")) {
            if ((adjacentTo("water") || adjacentTo("deep-water")) && (adjacentTo("forest") || adjacentTo("grass"))) {
                int r = HeightMap.rinr(0, 9);
                if (adjacentTo("sand")) {
                    if (r <= 8)
                        terrain.setTerrainType("sand");
                } else {
                    if (r <= 5)
                        terrain.setTerrainType("sand");
                }
            }
        }
    }


    boolean adjacentTo(String terrain) {
        for (Cell c : nsew()) {
            if (c.getTerrain().getTerrainType().equals(terrain))
                return true;
        }
        return false;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Color getHeightColor() {
        int h = (int) height;
        h = Math.min(255, h);
        h = Math.max(0, h);
        return Color.rgb(h, h, h);
    }

    public Cell[] getNeighbors() {
        return new Cell[]{east, ne, north, nw, west, sw, south, se};
    }

    public void setNeighbors(Cell e, Cell ne, Cell n, Cell nw, Cell w, Cell sw, Cell s, Cell se) {
        east = e;
        this.ne = ne;
        north = n;
        this.nw = nw;
        west = w;
        this.sw = sw;
        south = s;
        this.se = se;
        neighbors = getNeighbors();
    }

    public Cell[] nsew() {
        return new Cell[]{north, south, east, west};
    }

    public Paint getColor() {
        if (contents.getContentType() != null) {
            if (contents.getContentType().equals("frame") || (contents.getContentType().equals("station"))) {
                return ((TeamObject) contents).getTeamColor();
            }
        }
        return getTerrain().getBackgroundColor();
    }

    public Content getContents() {
        return contents;
    }

    public void setContents(Content contents) {
        this.contents = contents;
    }
}
