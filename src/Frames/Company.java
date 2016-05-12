package Frames;


import javafx.scene.paint.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 40095 on 4/20/16.
 */
public class Company {
    public static final int STARTING_STATIONS = 3;
    int scorePerAsset = 5;
    ArrayList<Frame> frames;
    int activeFrames;
    int totalSystems;
    int numOfFrames;
    String name;
    Color teamColor;
    ArrayList<Station> stations;
    boolean turn;

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

    public Company() {
    }


    public Color getTeamColor() {
        return teamColor;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public static Company companyFromJSON(JSONObject data) {
        Company c = new Company();
        c.name = (String) data.get("name");
        c.scorePerAsset = 5;
        c.frames = new ArrayList<>();
        JSONArray framesJSONArray = (JSONArray) data.get("frames");
        c.teamColor = Color.web((String) (data.get("color")));
        for (Object aFrameAsJSONObject : framesJSONArray) {
            c.frames.add(new Frame((JSONObject) aFrameAsJSONObject, c.teamColor));
        }
        c.stations = new ArrayList<>();
        for (int i = 0; i < Company.STARTING_STATIONS; i++) {
            c.stations.add(new Station(c));
        }
        return c;
    }

    public int calcScorePerAsset(Company[] competitors) {
        int minNumOfFrames = numOfFrames;
        int maxNumOfFrames = numOfFrames;
        int minTotalSystems = totalSystems;
        int maxTotalSystems = totalSystems;
        for (int i = 0; i < competitors.length; i++) {
            if (minNumOfFrames > competitors[i].numOfFrames)
                minNumOfFrames = competitors[i].numOfFrames;
            if (maxNumOfFrames < competitors[i].numOfFrames)
                maxNumOfFrames = competitors[i].numOfFrames;
            if (minTotalSystems > competitors[i].totalSystems)
                minTotalSystems = competitors[i].totalSystems;
            if (maxTotalSystems < competitors[i].totalSystems)
                maxTotalSystems = competitors[i].totalSystems;
        }
        if (minNumOfFrames == numOfFrames)
            scorePerAsset += 1;
        if (maxNumOfFrames == numOfFrames)
            scorePerAsset -= 1;
        if (minTotalSystems == totalSystems)
            scorePerAsset += 1;
        if (maxTotalSystems == totalSystems)
            scorePerAsset -= 1;

        return scorePerAsset;
    }

    public int getScore() {
        activeFrames = 0;
        totalSystems = 0;
        for (int i = 0; i < frames.size(); i++) {
            if (frames.get(i).isActive()) {
                totalSystems += frames.get(i).getSystems().size();
                activeFrames++;
            }
        }

        return scorePerAsset * (activeFrames + stations.size());
    }

    public String toString() {
        String s = name;
        for (Frame f : frames) {
            s += "\n" + f;
        }
        return s;
    }

    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public void setFrames(ArrayList<Frame> frames) {
        this.frames = frames;
    }

    public void writeJSONObject(File file) {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        JSONArray framesAsJSONArray = new JSONArray();
        for (Frame f : frames) {
            framesAsJSONArray.add(f.asJSONObject());
        }
        obj.put("frames", framesAsJSONArray);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(obj.toJSONString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
