package Display;

import Content.Station;
import javafx.scene.shape.Circle;

/**
 * Created by 40095 on 5/13/16.
 */
public class StationIcon extends TeamObjectIcon {
    Station data;
    Circle bg;

    public StationIcon(Station s) {
        this.data = s;
        bg = new Circle(Client.scalingFactor, s.getTeamColor());
    }

    public Station getData() {
        return data;
    }

    public void setData(Station s) {
        this.data = s;
    }
}
