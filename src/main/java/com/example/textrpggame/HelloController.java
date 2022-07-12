package com.example.textrpggame;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private MapGenerator mapGenerator;
    private Location[] gameMap;
    private Location currentLocation;
    @FXML
    private Label welcomeText;

    @FXML
    ImageView imageView;

    @FXML
    private MenuButton menuBarButton;

    @FXML
    protected void onHelloButtonClick(int index) {
        currentLocation=currentLocation.goToLocation(index);
        loadLocation();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapGenerator=new MapGenerator("src/main/java/com/example/textrpggame/gameMap");
        gameMap = mapGenerator.loadMapFromFile();
        currentLocation = gameMap[0];

        loadLocation();
    }
    private void loadLocation() {
        welcomeText.setText(currentLocation.getInfo());
        menuBarButton.getItems().clear();
        try {
            imageView.setImage(new Image("file:" +currentLocation.getImgUrl()));
            System.out.println(currentLocation.getImgUrl());
        }catch (IllegalArgumentException exception){
            System.out.println(exception.getLocalizedMessage()+"\n"+currentLocation.getImgUrl());
        }
        if (currentLocation.canGoToNewLocation()) {
            String[] locationsNames = currentLocation.getLocationsNames();
            menuBarButton.setText("Wybierz obcje");
            for (int i = 0; i < locationsNames.length; i++) {
                MenuItem menuItem = new MenuItem();
                menuItem.setText(locationsNames[i]);
                int temp = i + 1;
                menuItem.setOnAction(x -> onHelloButtonClick(temp));
                menuBarButton.getItems().add(menuItem);
            }
        } else {
            menuBarButton.setText("GAME OVER");
            menuBarButton.setOnMouseClicked(x ->exitProgram());
        }
    }
    private void exitProgram(){
            Platform.exit();
            System.exit(0);
    }
}