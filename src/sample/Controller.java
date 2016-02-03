package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    @FXML
    AnchorPane fxmlRoot;
    @FXML
    Label mLabelPoints, tipsy;
    @FXML
    ImageView starsy, starsy2;
    ArrayList<ImageView> mImageViewArrayList;
    ArrayList<Timeline> mTimelineArrayList;
    Timeline mBackgroundTimeline, mMBackgroundTimeline2, mGameLoop, mTipsyTimeline;
    ArrayList<String> mKeyPeressedArrayList;
    int mShootDeley;
    int mSpeed = 4;
    Spaceship mSpaceship;
    Enemy mEnemy;
    ImageView mBackgroundImage = new ImageView(new Image("img/galaxy.png"));
    boolean mIsTips;
    private int mPunkty = 0;

    @FXML
    public void initialize() {

        mTipsyTimeline = new Timeline();
        mTipsyTimeline.setCycleCount(Animation.INDEFINITE);
        mTipsyTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue(tipsy.opacityProperty(), 0.1)));
        mTipsyTimeline.setAutoReverse(true);
        mTipsyTimeline.play();


        //mLabelTips.setText("Press W,A,S,D to move Press M to shoot.");
        // mLabelTips.setLayoutY(150);
        // mLabelTips.setLayoutX(150);
        //fxmlRoot.getChildren().add(mLabelTips);
        mBackgroundImage.setFitHeight(300);
        mBackgroundImage.setPreserveRatio(true);
        mSpaceship = new Spaceship(fxmlRoot);
        mEnemy = new Enemy(fxmlRoot);
        mImageViewArrayList = new ArrayList<>();
        mTimelineArrayList = new ArrayList<>();
        mSpaceship.drag();
        mEnemy.drag();
        mShootDeley = 0;
        mKeyPeressedArrayList = new ArrayList<>();
        mGameLoop = new Timeline();
        mGameLoop.setCycleCount(Animation.INDEFINITE);
        mGameLoop.getKeyFrames().add(new KeyFrame(Duration.millis(16), event1 -> {


            double x = mSpaceship.getosX();
            double y = mSpaceship.getosY();


            if (mKeyPeressedArrayList.contains("A")) {

                mSpaceship.setLEFT(x -= mSpeed);
            }
            if (mKeyPeressedArrayList.contains("D")) {

                mSpaceship.setRIGHT(x += mSpeed);
            }
            if (mKeyPeressedArrayList.contains("W")) {

                mSpaceship.setUP(y -= mSpeed);
            }
            if (mKeyPeressedArrayList.contains("S")) {

                mSpaceship.setDOWN(y += mSpeed);
            }

            if (mShootDeley < 10) {
                mShootDeley++;
            }
            if (mKeyPeressedArrayList.contains("M")) {


                if (mShootDeley == 10) {
                    mShootDeley = 0;
                    strzal();
                    }
            }

            mEnemy.dodge(mSpaceship.getosY());
        }));
        mGameLoop.play();
        fxmlRoot.setOnKeyPressed(event -> {

            tipsy.setVisible(false);
            mTipsyTimeline.stop();


            switch (event.getCode()) {
                case S:
                    if (!mKeyPeressedArrayList.contains("S")) {
                        mKeyPeressedArrayList.add("S");
                    }
                    break;
                case D:
                    if (!mKeyPeressedArrayList.contains("D")) {
                        mKeyPeressedArrayList.add("D");
                    }
                    break;
                case A:
                    if (!mKeyPeressedArrayList.contains("A")) {
                        mKeyPeressedArrayList.add("A");
                    }
                    break;
                case W:
                    if (!mKeyPeressedArrayList.contains("W")) {
                        mKeyPeressedArrayList.add("W");
                    }
                    break;
                case M:
                    if (!mKeyPeressedArrayList.contains("M")) {
                        mKeyPeressedArrayList.add("M");
                    }
                    break;
                case O:
                    System.out.println("ImageViewArray " + mImageViewArrayList.toString());
                    System.out.println("TimelineArray " + mTimelineArrayList.toString());
                    System.out.println("KeyPressedArray " + mKeyPeressedArrayList.toString());
                    System.out.println("FXMLRootArray " + fxmlRoot.getChildren().toString());
                    System.out.println("EnemyArray " + mEnemy.getListaObrazow());
                    break;

            }
        });

        fxmlRoot.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case S:
                    mKeyPeressedArrayList.remove("S");
                    break;
                case D:
                    mKeyPeressedArrayList.remove("D");
                    break;
                case W:
                    mKeyPeressedArrayList.remove("W");
                    break;
                case A:
                    mKeyPeressedArrayList.remove("A");
                    break;
                case M:
                    mKeyPeressedArrayList.remove("M");
                    break;

            }

        });


        mBackgroundTimeline = new Timeline();
        mBackgroundTimeline.setCycleCount(Animation.INDEFINITE);
        mBackgroundTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(15000), new KeyValue(starsy.translateXProperty(), -900)));

        mBackgroundTimeline.play();

        mMBackgroundTimeline2 = new Timeline();
        mMBackgroundTimeline2.setCycleCount(Animation.INDEFINITE);
        mMBackgroundTimeline2.getKeyFrames().add(new KeyFrame(Duration.millis(25000), new KeyValue(starsy2.translateXProperty(), -950)));

        mMBackgroundTimeline2.play();


    }

    private boolean trafienie(ImageView pocisk) {
        return pocisk.getBoundsInParent().intersects(mEnemy.getBounds());

    }


    private double losowaniePola() {
        Random random = new Random();
        double wylosowana;
        double start = mEnemy.getosY() / 10;
        do {
            wylosowana = random.nextInt(30);
            wylosowana = wylosowana * 10;
        } while (wylosowana == start);
        return wylosowana;
    }


    private void strzal() {

        if (mTimelineArrayList.size() <= 3) {
            mTimelineArrayList.add(new Timeline());
            mTimelineArrayList.forEach(timeline -> {
                if (!(timeline.getStatus() == Animation.Status.RUNNING)) {

                    ImageView laser = new ImageView(new Image("img/greenLaserRay.png"));
                    laser.setFitWidth(50);
                    laser.setPreserveRatio(true);
                    final double[] osX = {mSpaceship.getLaserX()};
                    final double[] osY = {mSpaceship.getLaserY()};
                    laser.setTranslateX(osX[0]);
                    laser.setTranslateY(osY[0]);
                    mImageViewArrayList.clear();
                    mImageViewArrayList.add(laser);
                    fxmlRoot.getChildren().addAll(mImageViewArrayList);
                    timeline.setCycleCount(60);
                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(16), event1 -> {
                        laser.setTranslateX(osX[0] += 15);
                        if (trafienie(laser)) {
                            fxmlRoot.getChildren().remove(laser);
                            fxmlRoot.getChildren().remove(mImageViewArrayList);
                            mImageViewArrayList.remove(laser);
                            mTimelineArrayList.remove(timeline);
                            timeline.stop();
                            mTimelineArrayList.remove(timeline);
                            mLabelPoints.setText("Points: " + String.valueOf(++mPunkty));
                            mEnemy.setTranslateY(losowaniePola());

                        }
                    }));

                    timeline.setOnFinished(event1 -> {
                        mImageViewArrayList.remove(laser);
                        mTimelineArrayList.remove(timeline);
                        fxmlRoot.getChildren().remove(laser);
                    });
                    timeline.play();
                }
            });
        } else System.out.println("ladowanie");


    }
}
