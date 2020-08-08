import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class Main extends Application {

    static final int W_WIDTH = 1200;
    static final int W_HEIGHT = 800;

    Model model;
    ControllerMiddle controllerMiddle;

    @Override
    public void start(Stage stage) {
        // Intro scene
        GridPane root = new GridPane();
        Scene scene = new Scene(root , W_WIDTH, W_HEIGHT);

        FXMLLoader fxmlLoaderLeft = new FXMLLoader(getClass().getResource(
                "Left.fxml"));
        FXMLLoader fxmlLoaderMiddle = new FXMLLoader(getClass().getResource(
                "Middle.fxml"));
        FXMLLoader fxmlLoaderRight = new FXMLLoader(getClass().getResource(
                "Right.fxml"));
        VBox vBoxLeft = null;
        VBox vBoxMiddle = null;
        VBox vBoxRight = null;
        try {
            vBoxLeft = fxmlLoaderLeft.<VBox>load();
            vBoxMiddle = fxmlLoaderMiddle.<VBox>load();
            vBoxRight = fxmlLoaderRight.<VBox>load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // load the controllers and models
        ControllerLeft controllerLeft = fxmlLoaderLeft.getController();
        model = new Model();
        controllerLeft.setModel(model);

        controllerMiddle = fxmlLoaderMiddle.getController();
        controllerMiddle.setModel(model);

        ControllerRight controllerRight = fxmlLoaderRight.getController();
        controllerRight.setModel(model);
        controllerRight.setApplication(this);

        root.add(vBoxLeft, 0, 0);
        root.add(vBoxMiddle, 1, 0);
        root.add(vBoxRight, 2, 0);

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(30);
        root.getColumnConstraints().add(column);

        column = new ColumnConstraints();
        column.setPercentWidth(40);
        root.getColumnConstraints().add(column);

        column = new ColumnConstraints();
        column.setPercentWidth(30);
        root.getColumnConstraints().add(column);

        root.setPrefSize(W_WIDTH, W_HEIGHT);
        root.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        GridPane.setVgrow(vBoxLeft, Priority.ALWAYS);
        GridPane.setVgrow(vBoxMiddle, Priority.ALWAYS);
        GridPane.setVgrow(vBoxRight, Priority.ALWAYS);

        // Attach the scene to the stage and show it
        stage.setTitle("Avatar Maker");
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(400);
        stage.show();
    }

    public void saveScreenshot(){
        // Save the actual screen shot
        File f = new FileChooser().showSaveDialog(null);
        if (f != null){
            SnapshotParameters params = new SnapshotParameters();
            params.setFill(model.getBackgroundColor());
            Node n = controllerMiddle.getScreenshotNode();
            Image screenshot = n.snapshot(params, null);
            try {
                OutputStream output = Files.newOutputStream(f.toPath());
                ImageIO.write(SwingFXUtils.fromFXImage(screenshot, null), "png", output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
