package Display;

import Content.Frame;
import javafx.scene.shape.Rectangle;

/**
 * Created by 40095 on 5/13/16.
 */
public class FrameIcon extends TeamObjectIcon {
    Frame data;
    Rectangle background;
    
    
    public FrameIcon(Frame frame) {
        this.data = frame;
        background = new Rectangle(Client.scalingFactor, Client.scalingFactor, frame.getTeamColor());
    }

    @Override
    public Object getData() {
        return data;
    }

    public void setData(Frame data) {
        this.data = data;
    }
}
