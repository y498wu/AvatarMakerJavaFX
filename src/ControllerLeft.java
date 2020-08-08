import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class ControllerLeft {
    @FXML
    private TitledPane HairPane;

    @FXML
    private TitledPane SkinPane;

    @FXML
    private TitledPane BrowsPane;

    @FXML
    private TitledPane EyesPane;

    @FXML
    private  TitledPane MouthPane;

    private Model model;

    public void setModel(Model model){
        this.model = model;
    }

    public ControllerLeft()
    {
    }

    private ImageView setImageView(String category, String imageName){
        Image img = new Image("file:src/resources/" + category + "/" + imageName + ".png");
        ImageView iv = new ImageView(img);
        iv.setFitHeight(150);
        iv.setFitWidth(150);
        iv.setPickOnBounds(true);
        iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // System.out.println(category + " and " + imageName);
                if(category == "hair"){
                    model.setHairStyle(imageName);
                } else if(category == "skin"){
                    model.setSkinStyle(imageName);
                } else if(category == "brows"){
                    model.setBrowsStyle(imageName);
                } else if(category == "eyes"){
                    model.setEyesStyle(imageName);
                } else if(category == "mouth"){
                    model.setMouthStyle(imageName);
                }
            }
        });
        return iv;
    }

    @FXML
    private void initialize()
    {
        // hair grid pane
        GridPane hairGP = new GridPane();
        // put image views into grid pane
        hairGP.add(setImageView("hair", "hair_curly"), 0, 0);
        hairGP.add(setImageView("hair", "hair_long"), 0, 1);
        hairGP.add(setImageView("hair", "hair_short"), 1, 0);
        hairGP.add(setImageView("hair", "hair_wavy"), 1, 1);
        // set gaps
        hairGP.setHgap(40);
        hairGP.setVgap(40);
        HairPane.setContent(hairGP);

        // skin grid pane
        GridPane skinGP = new GridPane();
        // put image views into grid pane
        skinGP.add(setImageView("skin", "skin_brown"), 0, 0);
        skinGP.add(setImageView("skin", "skin_light"), 0, 1);
        skinGP.add(setImageView("skin", "skin_lighter"), 1, 0);
        // set gaps
        skinGP.setHgap(40);
        skinGP.setVgap(40);
        SkinPane.setContent(skinGP);

        // brows grid pane
        GridPane browsGP = new GridPane();
        // put image views into grid pane
        browsGP.add(setImageView("brows", "brows_angry"), 0, 0);
        browsGP.add(setImageView("brows", "brows_default"), 0, 1);
        browsGP.add(setImageView("brows", "brows_sad"), 1, 0);
        // set gaps
        browsGP.setHgap(40);
        browsGP.setVgap(40);
        BrowsPane.setContent(browsGP);

        // eyes grid pane
        GridPane eyesGP = new GridPane();
        // put image views into grid pane
        eyesGP.add(setImageView("eyes", "eyes_closed"), 0, 0);
        eyesGP.add(setImageView("eyes", "eyes_default"), 0, 1);
        eyesGP.add(setImageView("eyes", "eyes_wide"), 1, 0);
        // set gaps
        eyesGP.setHgap(40);
        eyesGP.setVgap(40);
        EyesPane.setContent(eyesGP);

        // eyes grid pane
        GridPane mouthGP = new GridPane();
        // put image views into grid pane
        mouthGP.add(setImageView("mouth", "mouth_default"), 0, 0);
        mouthGP.add(setImageView("mouth", "mouth_sad"), 0, 1);
        mouthGP.add(setImageView("mouth", "mouth_serious"), 1, 0);
        // set gaps
        mouthGP.setHgap(40);
        mouthGP.setVgap(40);
        MouthPane.setContent(mouthGP);
    }
}
