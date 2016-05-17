package Content;

import Display.StationIcon;
import Frames.Company;
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
        teamColor = controller.getTeamColor();
        contentType = "station";
    }
    
    public StationIcon getIcon() {
        return new StationIcon(this);
    }
}
