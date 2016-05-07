/**
 * Created by 40095 on 5/6/16.
 */

import java.awt.*;

public class Terrain {
    double height;
    int rgb;
    String terrainType;

    public void setHeight(double height) {
        this.height = height;
    }

    public String getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(String terrainType) {
        this.terrainType = terrainType;
    }

    public int getRgb() {
        String backgroundColor = "#000000";
        switch (terrainType) {
            case "mountain":
                backgroundColor = "#666666";
                break;
            case "forest":
                backgroundColor = "#008000";
                break;
            case "grass":
                backgroundColor = "#00ff00";
                break;
            case "sand":
                backgroundColor = "#ffcc00";
                break;
            case "water":
                backgroundColor = "#0099ff";
                break;
            case "deep-water":
                backgroundColor = "#000099";
                break;
            case "void":
                backgroundColor = "#ff00ff";
        }
        return Color.decode(backgroundColor).getRGB();
//        return Color.BLUE.getRGB();
    }

    public Terrain(double height) {
        this.height = height;

        if (height >= 175) {
            terrainType = "mountain";
        } else if (height >= 100 && height < 175) {
            terrainType = "forest";
        } else if (height >= 50 && height < 100) {
            terrainType = "grass";
        } else if (height >= 20 && height < 50) {
            terrainType = "water";
        } else if (height < 20) {
            terrainType = "deep-water";
        }
        
        if(height < 0) {
            terrainType = "void";
        }
    }

    public String toString() {
        return getTerrainType();
    }
}
