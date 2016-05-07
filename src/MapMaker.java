import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by 40095 on 5/2/16.
 */
public class MapMaker {
    final String HEADER = "<!DOCTYPE html> \n<html>\n<head>\n<link type='text/css' href='stylesheet.css' " +
            "rel='stylesheet'> </href>\n</head>\n<body>\n<table>";
    PrintWriter mapWriter, heightWriter;
    HeightMap t;


    public static void main(String[] args) {
        new MapMaker().go();
    }

    private void go() {
//        t = new RandomNoiseMap(60, 60);
//        t = new HillMap(512, 512);
        t = new HillMap(60, 60);
//        t = new DiamondSquareMap(60, 60);
        try {
            mapWriter = new PrintWriter("map.html");
            heightWriter = new PrintWriter("noise.html");
            for (int i = 0; i < 1; i++) {
                t.blur(new BoxBlur());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        writeHeight(heightWriter);
        writeMap(mapWriter);
    }

    String getMap() {
        String map = "";
        map += HEADER;
        for (int i = 0; i < t.length; i++) {
            map += ("\t\t\t<tr>");
            for (int j = 0; j < t.width; j++) {
                map += ("\t\t\t\t<td class='" + t.getCells()[i][j].getTerrain() + "'> </td>");
            }
            map += ("\t\t\t</tr>");
        }
        map += "</table>";

        return map;
    }

    void writeHeight(PrintWriter pw) {
        pw.println(HEADER);
        for (int i = 0; i < t.length; i++) {
            pw.println("\t\t\t<tr>");
            for (int j = 0; j < t.width; j++) {
                pw.println("\t\t\t\t<td style='background-color:" + t.getCells()[i][j].getHexHeight() + "'> </td>");
            }
            pw.println("\t\t\t</tr>");
        }
        pw.println("</table>");
        pw.flush();
        pw.close();
        System.out.println("printing complete");
    }

    void writeMap(PrintWriter pw) {
        pw.println(HEADER);
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t.width; j++) {
                t.getCells()[i][j].calculateTerrain();
            }
        }
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t.width; j++) {
                t.getCells()[i][j].checkIfBeach();
            }
        }
        for (int i = 0; i < t.length; i++) {
            pw.println("\t\t\t<tr>");
            for (int j = 0; j < t.width; j++) {
                pw.println("\t\t\t\t<td class='" + t.getCells()[i][j].getTerrain() + "'> </td>");
            }
            pw.println("\t\t\t</tr>");
        }
        pw.println("</table>");
        pw.flush();
        pw.close();
        System.out.println("printing complete");
    }
}
