package com.example.turingmachine;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class Show implements Initializable {

    @FXML VBox vBox;
    @FXML ScrollPane scrollPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        vBox.setSpacing(100);
        scrollPane.setContent(vBox);

        for (int i = 0; i< TransitionFunctions.ResultTransitions.size(); i++){

            Label l = new Label(TransitionFunctions.ResultTransitions.get(i));
            l.setFont(Font.font(20));
            Main.InnerShadow(l);

            HBox hBox = new HBox(l, TransitionFunctions.tapeHBoxes.get(i));
            hBox.setSpacing(80);
            vBox.getChildren().add(hBox);
        }
    }
}
