package Content;

import Display.FrameIcon;
import Frames.Systems;
import javafx.scene.paint.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by 40095 on 5/10/16.
 */
public class Frame extends TeamObject {
    private int whiteDice;
    private boolean active;
    private Systems systems;
    private int defenseScore;
    private String name;

    public Frame(JSONObject data, Color color) {
        contentType = "frame";
        whiteDice = 2;
        active = true;
        teamColor = color;
        this.name = (String) data.get("name");
        JSONArray systems_JSONArray = (JSONArray) data.get("systems");
        String s = systems_JSONArray.toString();
        s = s.substring(1, s.length() - 1);
        String[] systemNames = s.split(",");
        for (String system : systemNames) {
            system.substring(1, system.length() - 1);
        }
        this.systems = new Systems(systemNames[0], systemNames[1], systemNames[2], systemNames[3]);
        isPlaced = false;
    }

    public String toString() {
        return name + ": " + systems;
    }

    public boolean isActive() {
        return active;
    }

    public Systems getSystems() {
        return systems;
    }

    public String getName() {
        return name;
    }

    public JSONObject asJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        JSONArray systemsAsJSONArray = systems.asJSONArray();
        obj.put("systemIcons", systemsAsJSONArray);
        return obj;
    }
    
    public FrameIcon getIcon() {
        return new FrameIcon(this);
    }
}
