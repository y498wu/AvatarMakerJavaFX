import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.effect.ColorInput;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import sample.SVGLoader;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.effect.InnerShadow;

public class ControllerMiddle implements IView {
    private Model model;

    public void setModel(Model model){
        this.model = model;
        this.model.addView(this);
        updateView();
    }

    public ControllerMiddle()
    {
    }

    @FXML
    private VBox vBoxMiddle;

    @FXML
    private Group middleGroup;

    private Group hairGroup;
    private Group skinGroup;
    private Group browsGroup;
    private Group eyesGroup;
    private Group mouthGroup;
    private Group clothesGroup;
    private Group backgroundGroup;

    public Node getScreenshotNode(){
        return middleGroup;
    }

    public void updateView(){
        Rectangle bg = new Rectangle();
        bg.setWidth(200);
        bg.setHeight(200);
        bg.setFill(model.getBackgroundColor());
        backgroundGroup.getChildren().clear();
        backgroundGroup.getChildren().add(bg);

        String hairStyle = model.getHairStyle();
        SVGLoader loaderHair = new SVGLoader();
        Group loadedHair = loaderHair.loadSVG("resources/hair/"
                + hairStyle + ".svg");
        SVGPath svgPath = (SVGPath) loadedHair.getChildren().get(0);
        svgPath.setFill(model.getHairColor());
        hairGroup.getChildren().clear();
        hairGroup.getChildren().add(loadedHair);

        String skinStyle = model.getSkinStyle();
        ImageView skinIV = new ImageView(new Image("resources/skin/"
                + skinStyle + ".png"));
        skinIV.setFitWidth(200);
        skinIV.setFitHeight(200);
        skinGroup.getChildren().clear();
        skinGroup.getChildren().add(skinIV);

        String browsStyle = model.getBrowsStyle();
        ImageView browsIV = new ImageView(new Image("resources/brows/"
                + browsStyle + ".png"));
        browsIV.setFitWidth(200);
        browsIV.setFitHeight(200);
        browsIV.setLayoutY(model.getBrowsOffset() * -1);
        browsGroup.getChildren().clear();
        browsGroup.getChildren().add(browsIV);

        String eyesStyle = model.getEyesStyle();
        ImageView eyesIV = new ImageView(new Image("resources/eyes/"
                + eyesStyle + ".png"));
        eyesIV.setFitWidth(200);
        eyesIV.setFitHeight(200);
        eyesIV.setScaleX(model.getEyesOffset());
        eyesIV.setScaleY(model.getEyesOffset());
        eyesGroup.getChildren().clear();
        eyesGroup.getChildren().add(eyesIV);

        String mouthStyle = model.getMouthStyle();
        ImageView mouthIV = new ImageView(new Image("resources/mouth/"
                + mouthStyle + ".png"));
        mouthIV.setFitWidth(200);
        mouthIV.setFitHeight(200);
        mouthGroup.getChildren().clear();
        mouthGroup.getChildren().add(mouthIV);

        Group clothes = (Group)clothesGroup.getChildren().get(0);

        SVGPath svgPath0 = (SVGPath)clothes.getChildren().get(0);
        svgPath0.setFill(model.getClothesColor0());
        SVGPath svgPath1 = (SVGPath) clothes.getChildren().get(1);
        svgPath1.setFill(model.getClothesColor1());
        SVGPath svgPath2 = (SVGPath) clothes.getChildren().get(2);
        svgPath2.setFill(model.getClothesColor2());
        SVGPath svgPath3 = (SVGPath) clothes.getChildren().get(3);
        svgPath3.setFill(model.getClothesColor3());
        SVGPath svgPath4 = (SVGPath) clothes.getChildren().get(4);
        svgPath4.setFill(model.getClothesColor4());
    }

    private void removeAllEffects(){
        backgroundGroup.setEffect(null);
        hairGroup.setEffect(null);
        browsGroup.setEffect(null);
        eyesGroup.setEffect(null);

        Group clothes = (Group)clothesGroup.getChildren().get(0);
        for(Node n : clothes.getChildren()){
            n.setEffect(null);
        }
    }

    private void hoverEffect(Node node){
        InnerShadow ds = new InnerShadow();
        ds.setColor(Color.RED);
        ds.setRadius(20);
        node.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                if(node.getEffect() == null){
                    node.setEffect(ds);
                }
            }
        });
        node.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                if(node.getEffect() == ds){
                    node.setEffect(null);
                }
            }
        });
    }

    private void setupClothes(Node clothesNode, InnerShadow is, String target){
        clothesNode.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                model.setColorEditTarget(target);
                model.setShowDoubleSpinner(false);
                model.setShowColorPicker(true);
                model.setShowNumSpinner(false);
                removeAllEffects();
                clothesNode.setEffect(is);
                event.consume();
            }
        });
        hoverEffect(clothesNode);
    }

    @FXML
    private void initialize(){
        hairGroup = new Group();
        skinGroup = new Group();
        browsGroup = new Group();
        eyesGroup = new Group();
        mouthGroup = new Group();
        clothesGroup = new Group();
        backgroundGroup = new Group();
        middleGroup.getChildren().add(backgroundGroup);
        middleGroup.getChildren().add(skinGroup);
        middleGroup.getChildren().add(hairGroup);
        middleGroup.getChildren().add(eyesGroup);
        middleGroup.getChildren().add(browsGroup);
        middleGroup.getChildren().add(mouthGroup);
        middleGroup.getChildren().add(clothesGroup);
        // nose
        ImageView noseIV = new ImageView(new Image("resources/nose_default.png"));
        noseIV.setFitWidth(200);
        noseIV.setFitHeight(200);
        middleGroup.getChildren().add(noseIV);
        // highlight
        InnerShadow is = new InnerShadow();
        is.setColor(Color.BLUE);
        is.setRadius(10);
        backgroundGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                model.setColorEditTarget("bg");
                model.setShowColorPicker(true);
                model.setShowNumSpinner(false);
                model.setShowDoubleSpinner(false);
                removeAllEffects();
                backgroundGroup.setEffect(is);
                event.consume();
            }
        });
        // hair click
        hairGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                model.setColorEditTarget("hair");
                model.setShowColorPicker(true);
                model.setShowNumSpinner(false);
                model.setShowDoubleSpinner(false);
                removeAllEffects();
                hairGroup.setEffect(is);
                event.consume();
            }
        });
        // eyebrows click
        browsGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                model.setShowNumSpinner(true);
                model.setShowColorPicker(false);
                model.setShowDoubleSpinner(false);
                removeAllEffects();
                browsGroup.setEffect(is);
                event.consume();
            }
        });
        // eyes click
        eyesGroup.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                model.setShowDoubleSpinner(true);
                model.setShowColorPicker(false);
                model.setShowNumSpinner(false);
                removeAllEffects();
                eyesGroup.setEffect(is);
                event.consume();
            }
        });
        // vBox click to un-select
        vBoxMiddle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                model.setShowDoubleSpinner(false);
                model.setShowColorPicker(false);
                model.setShowNumSpinner(false);
                removeAllEffects();
            }
        });
        hoverEffect(backgroundGroup);
        hoverEffect(hairGroup);
        hoverEffect(browsGroup);
        hoverEffect(eyesGroup);

        SVGLoader loaderClothes = new SVGLoader();
        Group loadedClothes = loaderClothes.loadSVG("resources/clothes.svg");
        clothesGroup.getChildren().add(loadedClothes);

        setupClothes(loadedClothes.getChildren().get(0), is, "clothes0");
        setupClothes(loadedClothes.getChildren().get(1), is, "clothes1");
        setupClothes(loadedClothes.getChildren().get(2), is, "clothes2");
        setupClothes(loadedClothes.getChildren().get(3), is, "clothes3");
        setupClothes(loadedClothes.getChildren().get(4), is, "clothes4");
    }
}
