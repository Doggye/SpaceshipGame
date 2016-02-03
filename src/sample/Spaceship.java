package sample;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;


public class Spaceship {

    ArrayList<ImageView> listaObrazow;
    ImageView mSpaceship = new ImageView(new Image("img/spaceship.png"));
    private Double wielkosc = 40.0;

    public Spaceship(Pane pane){

        listaObrazow = new ArrayList<>();
        mSpaceship.setPreserveRatio(true);
        mSpaceship.setFitHeight(wielkosc);

        mSpaceship.setScaleX(-1);
        listaObrazow.add(mSpaceship);
        pane.getChildren().addAll(listaObrazow);
    }


    public double getosX(){
        return mSpaceship.getLayoutX();

    }

    public double getosY(){
        return mSpaceship.getLayoutY();

    }

    public void drag(){
        final double[] offsetX = new double[1];
        final double[] klikX = new double[1];
        final double[] offsetY = new double[1];
        final double[] klikY = new double[1];
        mSpaceship.setOnMousePressed(event1 -> {
            klikX[0] = event1.getSceneX();
            offsetX[0] = event1.getSceneX() - mSpaceship.getLayoutX();
            klikY[0] = event1.getSceneY();
            offsetY[0] = event1.getSceneY() - mSpaceship.getLayoutY();



        });
        mSpaceship.setOnMouseDragged(event -> {
            double zmiennaX = event.getSceneX();
            mSpaceship.setLayoutX(zmiennaX-offsetX[0]);
            double zmiennaY = event.getSceneY();
            mSpaceship.setLayoutY(zmiennaY-offsetY[0]);

        });

    }

    public double getLaserY(){

        return mSpaceship.getLayoutY()+(15);
    }
    public double getLaserX(){
        return mSpaceship.getLayoutX()+40;
    }

    public void setUP (double y){
        if (mSpaceship.getLayoutY()>0){
        mSpaceship.setLayoutY(y);}
    }
    public void setDOWN (double y){
        if (mSpaceship.getLayoutY()<350){
        mSpaceship.setLayoutY(y);}
    }

    public void setLEFT (double x){
        if (mSpaceship.getLayoutX()>0){
        mSpaceship.setLayoutX(x);}

    }
    public void setRIGHT (double x){
        if (mSpaceship.getLayoutX()<600){
        mSpaceship.setLayoutX(x);}

    }


}
