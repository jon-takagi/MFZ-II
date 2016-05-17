package Display;

import Maps.Cell;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import Content.*;

/**
 * Created by 40095 on 5/11/16.
 */
public class Tile extends StackPane {
    int xCoord, yCoord;
    Cell cell;
    Rectangle bgR;
    ContentIcon ci;

    public Rectangle getBgR() {
        return bgR;
    }

    public Tile(double side, Cell cell, int xCoord, int yCoord) {
        bgR = new Rectangle(side, side);
        this.cell = cell;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        bgR.setFill(cell.getColor());
        ci = cell.getContents().getIcon();
        getChildren().add(bgR);
//        System.out.println(ci == null);
//        if(ci != null)
//            getChildren().addAll(ci, bgR);
    }

    public Cell getCell() {
        return cell;
    }

    public void setContents(Content c) {
        cell.setContents(c);
        getChildren().removeAll();
        getChildren().clear();
        ci = cell.getContents().getIcon();
        getChildren().addAll(ci, bgR);

    }
}
