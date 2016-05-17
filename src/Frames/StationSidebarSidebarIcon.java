package Frames;

import Content.Station;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Created by 40095 on 5/12/16.
 */
public class StationSidebarSidebarIcon extends TeamObjectSidebarIcon {
    Station station;

    public StationSidebarSidebarIcon(Station station) {
        this.station = station;
        Circle r = new Circle(7.5);
//        r.setFill(station.getTeamColor());
        r.setFill(station.getController().getTeamColor());
        getChildren().addAll(new Text("Station"), r);

    }
    
    public Station getData() {
        return station;
        
    }
}
