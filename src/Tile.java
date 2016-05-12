import Maps.Cell;
import javafx.scene.shape.Rectangle;

/**
 * Created by 40095 on 5/11/16.
 */
public class Tile extends Rectangle {
    int xCoord, yCoord;
    Cell cell;

    public Tile(double side, Cell cell, int xCoord, int yCoord) {
        super(side, side);
        this.cell = cell;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        setFill(cell.getColor());
    }
    
    
}
