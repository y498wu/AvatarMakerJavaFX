import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class Model {
    List<IView> views = new ArrayList<>();
    public void addView(IView view){
        views.add(view);
    }

    // style properties
    String hairStyle = "hair_curly";
    String skinStyle = "skin_light";
    String browsStyle = "brows_default";
    String eyesStyle = "eyes_default";
    String mouthStyle = "mouth_default";
    Color hairColor = Color.color(Math.random(), Math.random(), Math.random());
    Boolean showColorPicker = false;
    Integer browsOffset = 0;
    Boolean showNumSpinner = false;
    double eyesOffset = 1.0;
    Boolean showDoubleSpinner = false;
    Color clothesColor0 = Color.color(Math.random(), Math.random(), Math.random());
    Color clothesColor1 = Color.color(Math.random(), Math.random(), Math.random());
    Color clothesColor2 = Color.color(Math.random(), Math.random(), Math.random());
    Color clothesColor3 = Color.color(Math.random(), Math.random(), Math.random());
    Color clothesColor4 = Color.color(Math.random(), Math.random(), Math.random());
    Color backgroundColor = Color.color(Math.random(), Math.random(), Math.random());

    String colorEditTarget = "";

    // get functions for view to call
    String getHairStyle(){
        return hairStyle;
    }
    String getSkinStyle(){
        return skinStyle;
    }
    String getBrowsStyle(){
        return browsStyle;
    }
    String getEyesStyle(){
        return eyesStyle;
    }
    String getMouthStyle(){
        return mouthStyle;
    }
    Color getHairColor(){
        return hairColor;
    }
    Boolean getShowColorPicker(){
        return showColorPicker;
    }
    Integer getBrowsOffset(){
        return browsOffset;
    }
    Boolean getShowNumSpinner(){
        return showNumSpinner;
    }
    Double getEyesOffset(){
        return eyesOffset;
    }
    Boolean getShowDoubleSpinner(){
        return showDoubleSpinner;
    }
    Color getClothesColor0(){
        return  clothesColor0;
    }
    Color getClothesColor1(){
        return  clothesColor1;
    }
    Color getClothesColor2(){
        return  clothesColor2;
    }
    Color getClothesColor3(){
        return  clothesColor3;
    }
    Color getClothesColor4(){
        return  clothesColor4;
    }
    Color getBackgroundColor(){
        return backgroundColor;
    }
    Color getColor(){
        if (colorEditTarget == "hair"){
            return getHairColor();
        }
        else if (colorEditTarget == "clothes0"){
            return getClothesColor0();
        }
        else if (colorEditTarget == "clothes1"){
            return getClothesColor1();
        }
        else if (colorEditTarget == "clothes2"){
            return getClothesColor2();
        }
        else if (colorEditTarget == "clothes3"){
            return getClothesColor3();
        }
        else if (colorEditTarget == "clothes4"){
            return getClothesColor4();
        }
        else if (colorEditTarget == "bg"){
            return getBackgroundColor();
        }
        else {
            return Color.GRAY;
        }
    }

    // set functions for controller to call
    void setHairStyle(String input){
        hairStyle = input;
        notifyObservers();
    }
    void setSkinStyle(String input){
        skinStyle = input;
        notifyObservers();
    }
    void setBrowsStyle(String input){
        browsStyle = input;
        notifyObservers();
    }
    void setEyesStyle(String input){
        eyesStyle = input;
        notifyObservers();
    }
    void setMouthStyle(String input){
        mouthStyle = input;
        notifyObservers();
    }
    void setHairColor(Color color){
        hairColor = color;
        notifyObservers();
    }
    void setShowColorPicker(Boolean bool){
        showColorPicker = bool;
        notifyObservers();
    }
    void setBrowsOffset(Integer offset){
        browsOffset = offset;
        notifyObservers();
    }
    void setShowNumSpinner(Boolean bool){
        showNumSpinner = bool;
        notifyObservers();
    }
    void setEyesOffset(Double val){
        eyesOffset = val;
        notifyObservers();
    }
    void setShowDoubleSpinner(Boolean bool){
        showDoubleSpinner = bool;
        notifyObservers();
    }
    void setClothesColor0(Color color){
        clothesColor0 = color;
        notifyObservers();
    }
    void setClothesColor1(Color color){
        clothesColor1 = color;
        notifyObservers();
    }
    void setClothesColor2(Color color){
        clothesColor2 = color;
        notifyObservers();
    }
    void setClothesColor3(Color color){
        clothesColor3 = color;
        notifyObservers();
    }
    void setClothesColor4(Color color){
        clothesColor4 = color;
        notifyObservers();
    }
    void setBackgroundColor(Color color){
        backgroundColor = color;
        notifyObservers();
    }

    void setColor(Color color){
        if (colorEditTarget == "hair"){
            setHairColor(color);
        }
        else if (colorEditTarget == "clothes0"){
            setClothesColor0(color);
        }
        else if (colorEditTarget == "clothes1"){
            setClothesColor1(color);
        }
        else if (colorEditTarget == "clothes2"){
            setClothesColor2(color);
        }
        else if (colorEditTarget == "clothes3"){
            setClothesColor3(color);
        }
        else if (colorEditTarget == "clothes4"){
            setClothesColor4(color);
        }
        else if (colorEditTarget == "bg"){
            setBackgroundColor(color);
        }
    }

    void setColorEditTarget(String target){
        colorEditTarget = target;
    }

    private void notifyObservers() {
        for (IView view: views)
            view.updateView();
    }
}
