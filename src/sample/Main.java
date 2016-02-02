package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scena = new Scene(root, 600,  350);
        primaryStage.setResizable(false);
        primaryStage.setScene(scena);
        primaryStage.show();

        MenuBar menuBar = new MenuBar();
        Menu mainMenu = new Menu("File");
        MenuItem exitCmd = new MenuItem("Exit");
        MenuItem textCmd = new MenuItem("Colour Text");
        mainMenu.getItems().addAll(textCmd, exitCmd);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
