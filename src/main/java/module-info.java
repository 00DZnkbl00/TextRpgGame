module com.example.textrpggame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.textrpggame to javafx.fxml;
    exports com.example.textrpggame;
}