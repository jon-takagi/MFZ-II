package Maps; /**
 * Created by 40095 on 5/3/16.
 */

import java.awt.*;

public class Cell {

    static String[] buckets = {"#000000", "#080808", "#101010", "#181818", "#202020", "#282828", "#303030", "#383838", "#404040", "#484848", "#505050", "#585858", "#606060", "#686868", "#707070", "#787878", "#808080", "#888888", "#909090", "#989898", "#A0A0A0", "#A8A8A8", "#B0B0B0", "#B8B8B8", "#C0C0C0", "#C8C8C8", "#D0D0D0", "#D8D8D8", "#E0E0E0", "#E8E8E8", "#F0F0F0", "#F8F8F8", "#FFFFFF"};

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

    public Cell() {
    }

    public void calculateTerrain() {
        terrain = new Terrain(height);
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

    public String getHexHeight() {
        int bucketNum = (int) Math.min(255, height) / 8;
        if (bucketNum < 0)
            return buckets[0];
        return buckets[bucketNum];
    }

    public Color getHeightColor() {
        int h = (int) height;
        if (h > 255)
            h = 255;
        if (h < 0)
            h = 0;
        return new Color(h, h, h);
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
}
