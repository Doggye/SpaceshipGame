package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    @FXML
    AnchorPane rocik;
    //GridPane rocik;
    @FXML
    Button butonik, dugi;
    @FXML
    Label ziomek, plotek;
    @FXML
    ImageView starsy, starsy2, starsy21;
    Button mClon;
    ArrayList<Button> listaButonow;
    ArrayList<ImageView> listaObrazow;
    ArrayList<Button> bufforlistaButonow;
    ArrayList<Timeline> listaTime;
    Timeline mBackgroundTime, mBackgroundTime2, mTime;
    ArrayList<String> lista;
    double x = 0;
    double y = 0;
    int fps = 0;
    int zmienna=0;
    int predkosc =4;

    Enemy mEnemy;

    private int mPunkty = 0;
    Spaceship samolot;

    ImageView tlo = new ImageView(new Image("img/galaxy.png"));


    public Controller() {

    }


    public void akcja(ActionEvent event) {

/*        try {
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nowy.fxml"));
            Parent root1 = FXMLLoader.load(getClass().getResource("nowy.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage stage = new Stage();
            Scene scena = new Scene(root1, 400, 500);
            stage.setScene(scena);

            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.WINDOW_MODAL);

            stage.setFullScreenExitHint(":asdasdasd");
            //stage.setFullScreen(true);
            stage.setAlwaysOnTop(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("l"));
            stage.show();
            System.out.println("zwykle " + stage.getScene());
            System.out.println("z wykrzy " + !stage.isShowing());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    @FXML
    public void initialize() {



        tlo.setFitHeight(300);
        tlo.setPreserveRatio(true);
        //rocik.getChildren().add(tlo);
        butonik.setVisible(false);
        samolot = new Spaceship(rocik);
        mEnemy = new Enemy(rocik);
        listaObrazow = new ArrayList<>();

        //listaButonow= new ArrayList<>();
        //bufforlistaButonow = new ArrayList<>();
        listaTime = new ArrayList<>();
        //double wysokosc = butonik.getScene().getHeight();
        // rocik.getChildren().addAll(listaObrazow);
        samolot.drag();
        mEnemy.drag();


        lista = new ArrayList<>();
        mTime = new Timeline();
        mTime.setCycleCount(Animation.INDEFINITE);
        mTime.getKeyFrames().add(new KeyFrame(Duration.millis(16), event1 -> {

            double x = samolot.getosX();
            double y = samolot.getosY();

            if (zmienna<10){
                zmienna++;
            }

            if (lista.contains("A")) {

                samolot.setLEFT(x -= predkosc);
            }
            if (lista.contains("D")) {

                samolot.setRIGHT(x += predkosc);
            }
            if (lista.contains("W")) {

                samolot.setUP(y -= predkosc);
            }
            if (lista.contains("S")) {

                samolot.setDOWN(y += predkosc);
            }
            if (lista.contains("M")) {


                if (zmienna==10){
                    zmienna=0;
                    strzal();
                    }
            }

            //mEnemy.follow(samolot.getosY());

            mEnemy.dodge(samolot.getosY());
        }));
        mTime.play();
        rocik.setOnKeyPressed(event -> {


            switch (event.getCode()) {
                case S:
                    if (!lista.contains("S")) {
                        lista.add("S");
                    }
                    break;
                case D:
                    if (!lista.contains("D")) {
                        lista.add("D");
                    }
                    break;
                case A:
                    if (!lista.contains("A")) {
                        lista.add("A");
                    }
                    break;
                case W:
                    if (!lista.contains("W")) {
                        lista.add("W");
                    }
                    break;
                case M:
                    if (!lista.contains("M")) {
                        lista.add("M");
                    }
                    break;
                case O:
                    System.out.println("Lista obrazowe "+listaObrazow.toString());
                    System.out.println("Lista time "+listaTime.toString());
                    System.out.println("Lista obrazowe klawiszy "+lista.toString());
                    System.out.println("lista rovik "+rocik.getChildren().toString());
                    break;

            }
        });

        rocik.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case S:
                    lista.remove("S");
                    break;
                case D:
                    lista.remove("D");
                    break;
                case W:
                    lista.remove("W");
                    break;
                case A:
                    lista.remove("A");
                    break;
                case M:
                    lista.remove("M");
                    break;

            }

        });



        mBackgroundTime = new Timeline();
        mBackgroundTime.setCycleCount(Animation.INDEFINITE);
        mBackgroundTime.getKeyFrames().add(new KeyFrame(Duration.millis(15000), new KeyValue(starsy.translateXProperty(), -900)));

        mBackgroundTime.play();

        mBackgroundTime2 = new Timeline();
        mBackgroundTime2.setCycleCount(Animation.INDEFINITE);
        mBackgroundTime2.getKeyFrames().add(new KeyFrame(Duration.millis(25000), new KeyValue(starsy2.translateXProperty(), -950)));

        mBackgroundTime2.play();


    }

    private void kolizja() {

    }


    public void drugi(ActionEvent event) {


    }

    private boolean trafienie(ImageView pocisk) {
        boolean trafienie = false;
        if (pocisk.getBoundsInParent().intersects(mEnemy.getBounds())) {
            trafienie = true;
        }
        return trafienie;
    }


    private double losowaniePola() {
        Random random = new Random();
        double wylosowana;
        double start = mEnemy.getosY();
        //int poprzedniaLosowa = mWylosowanePole;
        do {
            wylosowana = random.nextInt(30);
            wylosowana = wylosowana * 10;
        } while (wylosowana == start);
        return wylosowana;
    }


    private void strzal() {

        if (listaTime.size() <= 3) {
            listaTime.add(new Timeline());
            listaTime.forEach(timeline -> {
                if (!(timeline.getStatus() == Animation.Status.RUNNING)) {
                    //Button lol = new Button("lol");
                    ImageView laser = new ImageView(new Image("img/greenLaserRay.png"));
                    laser.setFitWidth(50);
                    laser.setPreserveRatio(true);
                    final double[] osX = {samolot.getLaserX()};
                    final double[] osY = {samolot.getLaserY()};
                    laser.setTranslateX(osX[0]);
                    laser.setTranslateY(osY[0]);
                    //listaButonow.clear();
                    listaObrazow.clear();
                    listaObrazow.add(laser);
                    rocik.getChildren().addAll(listaObrazow);
                    timeline.setCycleCount(60);
                    //timeline.getKeyFrames().add(new KeyFrame(Duration.millis(250), new KeyValue(laser.translateXProperty(), 300)
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(16), event1 -> {
                        laser.setTranslateX(osX[0] += 15);
                        if (trafienie(laser)) {
                            rocik.getChildren().remove(laser);
                            rocik.getChildren().remove(listaObrazow);
                            listaObrazow.remove(laser);
                            listaTime.remove(timeline);
                            timeline.stop();
                            listaTime.remove(timeline);
                            plotek.setText(String.valueOf(++mPunkty));
                            mEnemy.setTranslateY(losowaniePola());

                        }
                    }));

                    timeline.setOnFinished(event1 -> {
                        listaObrazow.remove(laser);
                        listaTime.remove(timeline);
                        rocik.getChildren().remove(laser);
                    });
                    timeline.play();
                }
            });
        } else System.out.println("ladowanie");


    }
}
