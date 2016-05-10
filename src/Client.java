/**
 * Created by 40095 on 5/8/16.
 */

import Maps.HeightMap;
import Maps.HillMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Client extends Application {
    private Pane root;
    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        final int length = 128, width = 128, scalingFactor = 4;
        stage = primaryStage;
        root = new Pane();
        scene = new Scene(root, width * scalingFactor + 100, length * scalingFactor); //width and height of application
        stage.setScene(scene);
        stage.setTitle("MFZ Online");  //text for the title bar of the window

        TilePane tp = new TilePane();

        tp.setHgap(0);
        tp.setVgap(0);
        tp.setMaxWidth(width * scalingFactor);
        tp.setMinWidth(width * scalingFactor);
        tp.setMaxHeight(length * scalingFactor);
        tp.setMinHeight(length * scalingFactor);

        HeightMap table = new HillMap(length, width);
        table.mapTerrain();

        Rectangle r;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                r = new Rectangle(scalingFactor, scalingFactor, table.getCells()[i][j].getTerrain().getBackgroundColor());
                tp.getChildren().add(r);
            }
        }
        root.getChildren().addAll(tp);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

