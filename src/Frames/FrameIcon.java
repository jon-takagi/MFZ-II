package Frames;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by 40095 on 5/11/16.
 */
public class FrameIcon extends TeamObjectIcon {
    Rectangle[] systemIcons;
    Frame frame;

    public FrameIcon(Frame frame) {
        super();
        this.frame = frame;
        HBox top = new HBox();
        HBox bottom = new HBox();
        Rectangle[] sys = new Rectangle[4];
        for (int i = 0; i < sys.length; i++) {
            sys[i] = new Rectangle(25, 25, systemColor(frame.getSystems().systems.get(i)));
            if (frame.getSystems().systems.get(i).isActive()) {
                sys[i].setStroke(Color.BLACK);
            } else {
                sys[i].setStroke(Color.GRAY);
            }
        }
        top.getChildren().add(sys[0]);
        top.getChildren().add(sys[1]);
        bottom.getChildren().add(sys[2]);
        bottom.getChildren().add(sys[3]);
        Text name = new Text(frame.getName());
        getChildren().addAll(name, top, bottom);
    }

    private Color systemColor(MFZ_System system) {
        switch (system.getType()) {
            case "DEF":
                return Color.web("#0099ff");
            case "COM":
                return Color.web("#ffcc00");
            case "MVM":
                return Color.GREEN;
            case "MLE":
            case "ART":
            case "DIR":
                return Color.web("#cc0000");
        }
        return Color.VIOLET;
    }

    public Frame getData() {
        return frame;
    }

}
