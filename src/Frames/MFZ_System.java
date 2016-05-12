package Frames;

/**
 * Created by 40095 on 5/10/16.
 */
public class MFZ_System {
    boolean active;
    String type;
    int roll;
    String diceType;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDiceType() {
        return diceType;
    }

    public void setDiceType(String diceType) {
        this.diceType = diceType;
    }

    public int getRoll() {
        if (isActive()) {
            if (diceType.equals("d6")) {
                roll = 1 + (int) (Math.random() * ((6 - 1) + 1));
            }
            if (diceType.equals("2d6")) {
                roll = (1 + (int) (Math.random() * ((6 - 1) + 1)) + (1 + (int) (Math.random() * ((6 - 1) + 1))));
            }
            if (diceType.equals("d8")) {
                roll = 1 + (int) (Math.random() * ((8 - 1) + 1));
            }
        } else {
            roll = 0;
        }
        return roll;
    }

    public String getType() {
        return type;
    }

    public MFZ_System(String type) {
        setActive(true);
        this.type = type.substring(1, type.length() - 1);
    }
    public String toString() {
        return type;
        
    }
}
