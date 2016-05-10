package Maps;

import javafx.scene.paint.Color;

/**
 * Created by 40095 on 5/6/16.
 */


public class Terrain {
    double height;
    String terrainType;
    Color backgroundColor;

    public void setHeight(double height) {
        this.height = height;
        if (height >= 175) {
            terrainType = "mountain";
            backgroundColor = Color.web("#666666");
        } else if (height >= 100 && height < 175) {
            terrainType = "forest";
            backgroundColor = Color.web("#008000");
        } else if (height >= 50 && height < 100) {
            terrainType = "grass";
            backgroundColor = Color.web("#00ff00");
        } else if (height >= 20 && height < 50) {
            terrainType = "water";
            backgroundColor = Color.web("#0099ff");
        } else if (height < 20) {
            terrainType = "deep-water";
            backgroundColor = Color.web("#000099");
        }
    }

    public String getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(String terrainType) {
        this.terrainType = terrainType;
        if (terrainType.equals("sand")) {
            backgroundColor = Color.web("#ffcc00");
        }
    }

    int getRgb() {
        int r = (int) (backgroundColor.getRed() * 255);
        int g = (int) (backgroundColor.getGreen() * 255);
        int b = (int) (backgroundColor.getBlue() * 255);
        return (r << 16) + (g << 8) + b;
    }

    public Terrain(double height) {
        setHeight(height);
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public String toString() {
        return getTerrainType();
    }
}
