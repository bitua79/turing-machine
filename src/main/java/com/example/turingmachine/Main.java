package com.example.turingmachine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application implements Initializable {
    public static Scene mainScene;
    public static AnchorPane parent;
    public static Stage stage;

    @FXML
    AnchorPane anchorPane;
    @FXML
    VBox transitionsVBox;
    @FXML
    ScrollPane transitionsScroll;
    @FXML
    VBox vBox;

    @FXML
    Label qLBL;
    @FXML
    TextField qTXF;

    @FXML
    Label sigmaLBL;
    @FXML
    TextField sigmaTXF;

    @FXML
    Label gamaLBL;
    @FXML
    TextField gamaTXF;

    @FXML
    Label fLBL;
    @FXML
    TextField fTXF;

    @FXML
    Label thetaLBL;
    @FXML
    TextArea thetaTXF;
    @FXML
    Button add;
    @FXML
    Button go;

    @FXML
    Label inputLBL;
    @FXML
    TextField inputTXF;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        AnchorPane anchorPane = new AnchorPane();
        mainScene = new Scene(anchorPane, 650, 760);

        openFxml("Main.fxml");

        stage.setFullScreen(false);
        stage.setWidth(650);
        stage.setHeight(760);
        stage.setResizable(false);
        stage.setScene(mainScene);
        stage.setTitle("Turing Machine");
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DropShadow(qTXF, sigmaTXF, gamaTXF, fTXF, thetaTXF, inputTXF);
        InnerShadow(qLBL, sigmaLBL, gamaLBL, fLBL, thetaLBL, inputLBL, add, go, transitionsScroll);
    }

    public static void openFxml(String URL) {

        new Thread(() -> {
            try {
                parent = FXMLLoader.load(Main.class.getResource(URL));
                new Thread(() -> {
                    Platform.runLater(() -> {
                        Main.mainScene.setRoot(new Pane());
                        mainScene.setRoot(parent);
                    });
                }).start();

            } catch (IOException e) {
                e.printStackTrace();
                openFxml("Main.fxml");
            }
        }).start();
    }

    public static void InnerShadow(Node... node) {
        for (Node value : node) {
            value.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.7), 6, 0.0, 0, 2));
        }
    }

    public static void DropShadow(Node... node) {
        for (Node value : node) {
            value.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.7), 6, 0.0, 0, 2));
        }
    }


    //getting inputs
    ArrayList<String> δ = new ArrayList<>();
    String Q;
    String Σ;
    String Γ;
    String F;

    @FXML
    public void add() {
        if (!qTXF.isDisable()) {
            Q = qTXF.getText();
            System.out.println(Q);
            Σ = sigmaTXF.getText();
            System.out.println(Σ);
            Γ = gamaTXF.getText();
            System.out.println(Γ);
            F = fTXF.getText();
            System.out.println(F);

            qTXF.setDisable(true);
            sigmaTXF.setDisable(true);
            gamaTXF.setDisable(true);
            fTXF.setDisable(true);
        }
        String read = thetaTXF.getText();

        Scanner scanner = new Scanner(read);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            δ.add(s);
            System.out.println(s);
        }

        transitionsVBox.getChildren().add(new Label("    " + thetaTXF.getText() + "\n"));
        thetaTXF.setText("");
    }

    @FXML
    public void go() {
        Machine machine = new Machine(Q, Σ, Γ, F, δ, inputTXF.getText());
        try {
            machine.run(inputTXF.getText());
            machine.run("aabbcc");

        } catch (HaltException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    // Sample Input
    // Q:
    // Q = {q0, q1, q2, q3, q4, q5}
    // Σ:
    // Σ = {a,b,c}
    // Γ:
    // Γ = {a,b,c,x, y, z,⊡}
    // F:
    // F = {q5}
    //  δ:
    //  δ(q0,a) = (q1,x, R)
    //  δ(q0,y) = (q4,y, R)
    //  δ(q1,a) = (q1,a, R)
    //  δ(q1,y) = (q1,y, R)
    //  δ(q1,b) = (q2,y, R)
    //  δ(q2,b) = (q2,b, R)
    //  δ(q2,z) = (q2,z, R)
    //  δ(q2,c) = (q3,z, L)
    //  δ(q3,y) = (q3,y, L)
    //  δ(q3,z) = (q3,z, L)
    //  δ(q3,a) = (q3,a, L)
    //  δ(q3,b) = (q3,b, L)
    //  δ(q3,x) = (q0,x, R)
    //  δ(q4,y) = (q4,y, R)
    //  δ(q4,z) = (q4,z, R)
    //  δ(q4,⊡) = (q5,⊡, R)
    // input string
    // aabbcc

}

