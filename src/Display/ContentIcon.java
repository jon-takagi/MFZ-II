package Display;

import javafx.scene.shape.Shape;

/**
 * Created by 40095 on 5/13/16.
 */
public abstract class ContentIcon extends Shape {
    Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
