module com.example.animalcontrol {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.animalcontrol to javafx.fxml;
    exports com.example.animalcontrol;
}