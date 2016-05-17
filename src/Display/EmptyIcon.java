package Display;

import com.sun.javafx.geom.Shape;
import javafx.scene.shape.Rectangle;

/**
 * Created by 40095 on 5/13/16.
 */
public class EmptyIcon extends ContentIcon{
    Rectangle bg;
    @Override
    public Shape impl_configShape() {
        return null;
    }
    public EmptyIcon() {
        bg = new Rectangle(Client.scalingFactor, Client.scalingFactor);
    }
}
