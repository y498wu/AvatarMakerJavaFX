import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ControllerRight implements IView {
    @FXML
    private StackPane CommandPane;

    private ColorPicker picker = new ColorPicker();
    private Spinner<Integer> numSpinner = new Spinner<>();
    private Spinner<Double> doubleSpinner = new Spinner<>();

    private Model model;
    private Main app;

    public void setModel(Model model){
        this.model = model;
        this.model.addView(this);
        updateView();
    }
    public void setApplication(Main app) {
        this.app = app;
    }

    public void updateView(){
        picker.setVisible(model.getShowColorPicker());
        numSpinner.setVisible(model.getShowNumSpinner());
        doubleSpinner.setVisible(model.getShowDoubleSpinner());
        if(model.getShowColorPicker() == true){
            prepareColorPicker(model.getColor());
        } else if(model.getShowNumSpinner() == true){
            prepareNumSpinner(-8, 8, model.getBrowsOffset());
        } else if(model.getShowDoubleSpinner() == true){
            prepareDoubleSpinner(1.0, 2.0, model.getEyesOffset(), 0.1);
        }
    }

    public ControllerRight()
    {
    }

    @FXML
    public void initialize(){
        picker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.setColor(picker.getValue());
                //System.out.println("YAYAAAA COULEUR = " + picker.getValue().toString());
            }
        });
        CommandPane.getChildren().add(picker);
        picker.setVisible(false);

        numSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer old, Integer val) {
                model.setBrowsOffset(val);
                //System.out.println("YAYAAAA valeur = " + old.toString() + " " + val.toString());
            }
        });
        CommandPane.getChildren().add(numSpinner);
        numSpinner.setVisible(false);

        doubleSpinner.valueProperty().addListener(new ChangeListener<Double>() {
            @Override
            public void changed(ObservableValue<? extends Double> observableValue, Double old, Double val) {
                model.setEyesOffset(val);
            }
        });
        CommandPane.getChildren().add(doubleSpinner);
        doubleSpinner.setVisible(false);
    }

    @FXML
    public void saveScreenshot(){
        // Send save screenshot message back to main
        app.saveScreenshot();
    }

    private void prepareColorPicker(Color defaultColor){
        picker.setValue(defaultColor);
        picker.setVisible(true);
    }

    private void prepareNumSpinner(Integer min, Integer max, Integer defaultVal){
        SpinnerValueFactory<Integer> factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, defaultVal);
        numSpinner.setValueFactory(factory);
        numSpinner.setVisible(true);
    }

    private void prepareDoubleSpinner(Double min, Double max, Double defaultVal, Double increment){
        SpinnerValueFactory<Double> factory =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(min, max, defaultVal, increment);
        doubleSpinner.setValueFactory(factory);
        doubleSpinner.setVisible(true);
    }
}
