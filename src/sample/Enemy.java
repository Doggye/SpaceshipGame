package sample;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;


public class Enemy {

    ArrayList<ImageView> listaObrazow;
    //ImageView mEnemy = new ImageView(new Image("img/spaceship.png"));
    ImageView mEnemy = new ImageView(new Image("img/gif.gif"));
    private Double wielkosc =50.0;


    public Enemy(Pane pane){

        listaObrazow = new ArrayList<>();
        mEnemy.setPreserveRatio(true);
        mEnemy.setFitHeight(wielkosc);
        mEnemy.setScaleX(-1);
        mEnemy.setLayoutX(520);
        listaObrazow.add(mEnemy);
        pane.getChildren().addAll(listaObrazow);
    }

    public void setWielkosc(Double wielkosc) {
        mEnemy.setFitHeight(wielkosc/8);
    }

    public ImageView getImageView(){
        return mEnemy;  }

    public double getosX(){
        return mEnemy.getLayoutX();
    }

    public double getosY(){
        return mEnemy.getLayoutY();
    }

    public void drag(){
        final double[] ofsetX = new double[1];
        final double[] kliX = new double[1];
        final double[] ofsetY = new double[1];
        final double[] kliY = new double[1];
        mEnemy.setOnMousePressed(event2 -> {
            kliX[0] = event2.getSceneX();
            ofsetX[0] = event2.getSceneX() - mEnemy.getLayoutX();
            kliY[0] = event2.getSceneY();
            ofsetY[0] = event2.getSceneY() - mEnemy.getLayoutY();
        });
        mEnemy.setOnMouseDragged(event3 -> {
            double zmiennaX = event3.getSceneX();
            mEnemy.setLayoutX(zmiennaX-ofsetX[0]);
            double zmiennaY = event3.getSceneY();
            mEnemy.setLayoutY(zmiennaY-ofsetY[0]);
        });

    }

    public double getPX(){
        return mEnemy.getLayoutX();

    }

    public double getLaserY(){

        return mEnemy.getLayoutY()+(15);
    }
    public double getLaserX(){
        return mEnemy.getLayoutX()+40;
    }

    public void setY (double y){
        mEnemy.setLayoutY(y);
    }

    public void setX (double x){
        mEnemy.setLayoutX(x);
    }

    public Bounds getBounds() {
        return mEnemy.getBoundsInParent();
    }

    public void setTranslateY(double translateY) {
        mEnemy.setLayoutY(translateY);
    }

    public void follow (double playerY){

        double zmienna = mEnemy.getLayoutY();
        if (playerY>zmienna){
            mEnemy.setLayoutY(++zmienna);}

        if (playerY<zmienna){
            mEnemy.setLayoutY(--zmienna);
        }
    }

    public void dodge(double playerY){
        double zmienna = mEnemy.getLayoutY();

  /*      if (Math.abs(playerY-zmienna)<50){
            System.out.println("uciekaj");

        }*/
        if (playerY - zmienna < 60 && playerY - zmienna >= 0 && mEnemy.getLayoutY() >= 0) {


            if (mEnemy.getLayoutY()<50 && playerY<30){
                mEnemy.setLayoutY(++zmienna);
                mEnemy.setLayoutY(++zmienna);
                mEnemy.setLayoutY(++zmienna);

            }else if( mEnemy.getLayoutY()>0) {mEnemy.setLayoutY(--zmienna);
            }
        }


        if (playerY - zmienna > -60 && playerY - zmienna < 0 && mEnemy.getLayoutY() <= 315) {


            if (mEnemy.getLayoutY()>260 && playerY>270){
                mEnemy.setLayoutY(--zmienna);
                mEnemy.setLayoutY(--zmienna);
                mEnemy.setLayoutY(--zmienna);

            } else if (mEnemy.getLayoutY() < 315) {
                mEnemy.setLayoutY(++zmienna);
            }

        }

    }
}
