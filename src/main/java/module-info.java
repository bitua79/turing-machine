module com.example.turingmachine {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.turingmachine to javafx.fxml;
    exports com.example.turingmachine;
}