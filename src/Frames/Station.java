package Frames;

import javafx.scene.paint.*;

/**
 * Created by 40095 on 5/11/16.
 */
public class Station extends TeamObject {
    Company controller;

    public Company getController() {
        return controller;
    }

    public void setController(Company controller) {
        this.controller = controller;
    }

    public Station(Company controller) {
        this.controller = controller;
        teamColor = controller.teamColor;
        contentType = "station";
    }

    public Object getData() {
        return controller;
    }

    public Paint getTeamColor() {
        RadialGradient gradient1 = new RadialGradient(0, 0, 4, 4, 8, false, CycleMethod.NO_CYCLE, new Stop(0,
                controller.getTeamColor()), new
                Stop(1, Color.GOLD));
        return gradient1;

    }
}
