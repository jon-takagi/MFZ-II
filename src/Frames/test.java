package Frames;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by 40095 on 5/10/16.
 */
public class test {
    public static void main(String[] args) {
        File f = new File("longshot generals.json");
//                       
        Company c = null;
        try {
            String content = "";
            List<String> contents = Files.readAllLines(f.toPath());
            for (String s : contents) {
                content += s;
            }
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject)parser.parse(content);
                c = Company.companyFromJSON(obj);
                
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(c);
//        System.out.println(c.frames.get(0).getTeamColor());
    }
}
