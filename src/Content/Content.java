package Content;

import Display.ContentIcon;

/**
 * Created by 40095 on 5/11/16.
 */
public abstract class Content {
    protected String contentType;

    public String getContentType() {
        return contentType;
    }

    public abstract ContentIcon getIcon();
    
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
