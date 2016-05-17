package Content;

import Display.ContentIcon;
import Display.EmptyIcon;

/**
 * Created by 40095 on 5/13/16.
 */
public class EmptyContent extends Content {
    @Override
    public ContentIcon getIcon() {
        return new EmptyIcon();
    }
}
