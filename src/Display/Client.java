package Display; /**
 * Created by 40095 on 5/8/16.
 */

import Content.TeamObject;
import Frames.*;
import Maps.Cell;
import Maps.HeightMap;
import Maps.HillMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Client extends Application {
    private BorderPane root;
    private Stage stage;
    private Scene scene;
    private VBox companyDisplayPanel;
    private VBox fileChoosingPanel;

    Company c = null;
    public static final int length = 64, width = 64, scalingFactor = 8;
    private HeightMap table;
    Tile[][] tiles;
    static TeamObject toBePlaced;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new BorderPane();
        scene = new Scene(root, width * scalingFactor + 200, length * scalingFactor); //width and height of application
        stage.setScene(scene);
        stage.setTitle("MFZ Online");  //text for the title bar of the window

        TilePane tp = new TilePane();
        tp.setHgap(0);
        tp.setVgap(0);
        tp.setMaxWidth(width * scalingFactor);
        tp.setMinWidth(width * scalingFactor);
        tp.setMaxHeight(length * scalingFactor);
        tp.setMinHeight(length * scalingFactor);

        table = new HillMap(length, width);
//        table = new DiamondSquareMap(length, width);
//        table = new RandomNoiseMap(length, width);
        table.mapTerrain();

        configureRightPanel();
        tiles = new Tile[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = new Tile(scalingFactor, table.getCells()[i][j], i, j);
//                System.out.println(tiles[i][j] == null);
                tp.getChildren().add(tiles[i][j]);
            }
        }
        root.setLeft(fileChoosingPanel);
        root.setRight(tp);
        stage.show();
    }

    private void configureRightPanel() {
        Button chooseFileButton = new Button("Open");
        chooseFileButton.setOnMouseClicked(event -> {
            openFile(chooseFile());
            companyDisplayPanel = new VBox();
            configureCompanyDisplayBox();
            root.setLeft(companyDisplayPanel);
        });
        fileChoosingPanel = new VBox();
        fileChoosingPanel.getChildren().addAll(chooseFileButton);

    }

    private void configureCompanyDisplayBox() {
        Text name = new Text(c.getName());
        companyDisplayPanel.getChildren().addAll(name);
        companyDisplayPanel.setSpacing(19);
        for (int i = 0; i < c.getFrames().size(); i++) {
            FrameSidebarSidebarIcon fi = new FrameSidebarSidebarIcon(c.getFrames().get(i));
            fi.setOnMouseClicked(event -> {
                toBePlaced = (TeamObject) ((TeamObjectSidebarIcon) event.getSource()).getData();
            });
            companyDisplayPanel.getChildren().add(fi);
        }
        for (int i = 0; i < Company.STARTING_STATIONS; i++) {
            StationSidebarSidebarIcon si = new StationSidebarSidebarIcon(c.getStations().get(i));
            si.setOnMouseClicked(event -> {
                toBePlaced = (TeamObject) ((TeamObjectSidebarIcon) event.getSource()).getData();
            });
            companyDisplayPanel.getChildren().add(si);
        }
    }

    public File chooseFile() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open");
        return fc.showOpenDialog(stage);
    }

    public void openFile(File f) {
        try {
            String content = "";
            List<String> contents = Files.readAllLines(f.toPath());
            for (String s : contents) {
                content += s;
            }
            JSONParser parser = new JSONParser();
            try {
                JSONObject obj = (JSONObject) parser.parse(content);
                c = Company.companyFromJSON(obj);
                c.setTurn(true);
                placeFrames();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void placeFrames() {
        Cell[][] cells = table.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                tiles[i][j].setOnMouseClicked(event -> {
                    if (c.isTurn()) {
                        Tile t = (Tile) event.getSource();
                        System.out.print("x: " + t.xCoord + ", y: " + t.yCoord);
                        if (toBePlaced != null && !toBePlaced.isPlaced()) {
                            for (int k = 1; k < companyDisplayPanel.getChildren().size(); k++) {
                                TeamObjectSidebarIcon fi = (TeamObjectSidebarIcon) companyDisplayPanel.getChildren().get(k);
                                if (fi.getData().equals(toBePlaced)) {
                                    companyDisplayPanel.getChildren().remove(k);
                                }
                            }
                            t.setContents(toBePlaced);
                            toBePlaced.setPlaced(true);
                        }
                    }
                });
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

