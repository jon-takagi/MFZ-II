/**
 * Created by 40095 on 5/13/16.
 */

import Content.Station;
import Content.TeamObject;
import Display.Tile;
import Frames.Company;
import Maps.HeightMap;
import Maps.HillMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class IconTest extends Application {
    private BorderPane root;
    private Stage stage;
    private Scene scene;
    Company c = null;
    public static final int length = 64, width = 64, scalingFactor = 8;
    private HeightMap table;
    Tile[][] tiles;
    static TeamObject toBePlaced;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new BorderPane();
        scene = new Scene(root, length * scalingFactor, width * scalingFactor); //width and height of application
        stage.setScene(scene);
        stage.setTitle("Maps Application");  //text for the title bar of the window


        try {
            String content = "";
            List<String> contents = Files.readAllLines(new File("longshot generals.json").toPath());
            for (String s : contents) {
                content += s;
            }
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(content);
                c = Company.companyFromJSON(obj);
                c.setTurn(true);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        table = new HillMap(length, width);
        table.mapTerrain();


        TilePane tp = new TilePane();
        tp.setHgap(0);
        tp.setVgap(0);
        tp.setMaxWidth(width * scalingFactor);
        tp.setMinWidth(width * scalingFactor);
        tp.setMaxHeight(length * scalingFactor);
        tp.setMinHeight(length * scalingFactor);


        tiles = new Tile[length][width];

        table.getCells()[32][32].setContents(new Station(c));

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = new Tile(scalingFactor, table.getCells()[i][j], i, j);
                tp.getChildren().add(tiles[i][j]);
            }
        }
        
        
        root.setCenter(tp);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

