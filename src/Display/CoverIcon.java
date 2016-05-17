package Display;

import Content.Cover;
import com.sun.javafx.geom.Shape;
import javafx.scene.shape.Rectangle;

/**
 * Created by 40095 on 5/13/16.
 */
public class CoverIcon extends ContentIcon {
    Cover c;
    Rectangle bg;
    public CoverIcon(Cover cover) {
        c = cover;
        bg = new Rectangle(Client.scalingFactor, Client.scalingFactor);
        
    }

    @Override
    public Shape impl_configShape() {
        return null;
    }
}
