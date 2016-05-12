package Frames;

import org.json.simple.JSONArray;

import java.util.ArrayList;

/**
 * Created by 40095 on 5/10/16.
 */
public class Systems {
    ArrayList<MFZ_System> systems;
    
    Systems(String s1, String s2, String s3, String s4) {
        systems = new ArrayList<>();
        systems.add(new MFZ_System(s1));
        systems.add(new MFZ_System(s2));
        systems.add(new MFZ_System(s3));
        systems.add(new MFZ_System(s4));
        for (MFZ_System system : systems) {
            if (system.getType().equals("DEF") || system.getType().equals("MVM") || system.getType().equals("COM"))
                system.setDiceType("d6");
            else { //System is a weapon system
                if(weaponsInRange(system.getDiceType()) == 1)
                    system.setDiceType("2d6");
                else 
                    system.setDiceType("d8");
            }
        }
    }
    int weaponsInRange(String r) {
        int numOfWeapons = 0;
        for (MFZ_System system : systems) {
            if (system.isActive() && system.getType().equals(r))
                numOfWeapons++;
        }
        return numOfWeapons;
    }
    public String toString() {
        String s = "";
        for (int i = 0; i < systems.size() - 1; i++) {
            s += systems.get(i) + ", ";
        }
        s += systems.get(systems.size() - 1);
        return s;
    }
    public int size() {
        return systems.size();
    }
    public JSONArray asJSONArray() {
        JSONArray arr = new JSONArray();
        for(MFZ_System system: systems) {
            arr.add(system.toString());
        }
        return arr;
    }
}
