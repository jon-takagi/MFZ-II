package Content;

import Display.CoverIcon;

/**
 * Created by 40095 on 5/11/16.
 */
public class Cover extends Content {
    int height;
    boolean destroyed;
    
    public Cover(int height) {
        this.height = height;
        destroyed = height > 0;
        contentType = "cover";
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void takeDamage(int hits) {
        height -= hits / 2;
        destroyed = height > 0;

    }
    
    public CoverIcon getIcon() {
        return new CoverIcon(this);
    }
    
    
}
